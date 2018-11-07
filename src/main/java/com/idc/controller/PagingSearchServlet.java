package com.idc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idc.domain.HTML;
import com.idc.domain.QueryResult;
import com.idc.service.SearchService;
import com.idc.service.impl.SearchServiceImpl;

//由于servlet的设计为单例模式，所以要继承SingleThreadModel接口，以防并发操作时共享资源出错
@SuppressWarnings("deprecation")
public class PagingSearchServlet extends HttpServlet implements SingleThreadModel{

	private static final long serialVersionUID = 1L;
	private SearchService searchService=new SearchServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pageNowStr=null;
		String queryString=null;
		QueryResult<HTML> queryResult=null;
		queryString=new String(request.getParameter("queryString").getBytes("UTF-8"),"UTF-8");
		System.out.println("controller queryString:"+queryString);
		pageNowStr=request.getParameter("pageNow");
		//验证queryString是否合法，有内容
		if(queryString==null||queryString.trim().equals("")){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		if(pageNowStr!=null){
			int pageNow=0;
			try{
				//验证传入的分页pageNow值是否合法
				pageNow=Integer.parseInt(request.getParameter("pageNow"));
			}catch(Exception e){
				System.out.println("分页不合格");
				request.getRequestDispatcher("/WEB-INF/ErrorPage/illegalOperation.jsp").forward(request, response);
				return;
			}
			queryResult=new QueryResult<HTML>(pageNow);
		}else{
			queryResult=new QueryResult<HTML>();
		}
		queryResult=searchService.pagingSearch(queryString,queryResult);
		System.out.println("查询内容："+queryResult.getList().get(1).getContent());
//		queryResult=searchService.pagingSearch(queryString);
		request.setAttribute("queryResult", queryResult);
		request.getRequestDispatcher("/WEB-INF/page/searchResult.jsp").forward(request, response);
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
}
