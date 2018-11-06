package com.idc.service.impl;

import com.idc.dao.HTMLIndexDao;
import com.idc.dao.impl.HBaseDaoImpl;
import com.idc.dao.impl.HTMLIndexDaoImpl;
import com.idc.domain.HTML;
import com.idc.domain.QueryResult;
import com.idc.service.SearchService;

public class SearchServiceImpl implements SearchService{

	private HTMLIndexDao indexDao=new HTMLIndexDaoImpl();

	//分页查询
	public QueryResult<HTML> pagingSearch(String queryString,QueryResult<HTML> result){
		return indexDao.query(queryString,result);
	}
	
	//返回查询网页的快照
	public byte[] getPageShot(String title){
		HBaseDaoImpl hbaseDao=new HBaseDaoImpl();
		HTML html=hbaseDao.queryByRowKey(title);
		return html.getContent();
	}


	public QueryResult<HTML> pagingSearch(String queryString) {
		return indexDao.query(queryString);
	}

	public static void main(String args[]){
//		List<HTML> list=new SearchServiceImpl().pagingSearch("视频");
//		System.out.println("总结果数："+list.size());
//		for(HTML html:list){
//			System.out.println("title:"+html.getTitle());
//			System.out.println("description:"+html.getDescription());
//			System.out.println("date:"+html.getDate());
//			System.out.println("content:"+html.getContent());
//			System.out.println("===========================================");
//		}
	}
	
}
