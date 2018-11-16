<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.idc.domain.HTML,java.util.List,com.idc.domain.QueryResult"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<style type="text/css">
	*{
		margin:0px;
		padding:0px;
		
	}
	#main{
		width:100%;
		margin-left:120px;
	}
	#count{
		font-size:14px;
		font-family:"微软雅黑";
		color:#999999;
	}
	#content{
		font-size:16px;
		font-family:"微软雅黑";
	}
	#url_date{
		font-size:13px;
		color:#008000;
		font-family:"微软雅黑";
	}
	#snapShot{
		font-size:13px;
		color:#999999;
		font-family:"微软雅黑";
	}
	#pageNow{
		font-size:15px;
		font-weight: bold;
		font-family:"微软雅黑";
		border:1px solid #999999;
		width:20px;
		height:20px;
		margin-right:10px;
		padding:3px 3px 3px 3px;
	}
	#pageNowUrl{
		text-decoration: none;
		color:#000000;
	}
	h4{
		font-family:"微软雅黑";
	}
</style>
<title>搜索结果</title>
</head>

<body>
		<%
			QueryResult<HTML> queryResult=(QueryResult<HTML>)request.getAttribute("queryResult");
			List<HTML> list=queryResult.getList();
//			System.out.println("前端，页面数量"+list.size());
			int rowCount=queryResult.getRowCount();
			String queryString=new String(request.getParameter("queryString").getBytes("UTF-8"),"UTF-8");
		%>
	<div id="main">
		<br/>
		<span id="count">鹏鹏为您找到相关结果约<%=rowCount%>个</span>
		<br/><br/>
		<table  cellpadding="0" cellspacing="0" width="45%">
			<%
				for(HTML html:list){
					String date=html.getDate();
					String title=html.getTitle();
//					System.out.println("front: "+ html.getContent());
					title=title.replaceAll("<font color='red'>"+queryString+"</font>",queryString);
			%>
				<tr>
					<td><h4><a href="<%=html.getUrl()%>"><%=html.getTitle()%></a></h4></td>
				</tr>
				<tr>
					<td><span id="content"><%=html.getContent()%></span></td>
				</tr>
				<tr>
					<td><span id="url_date"><%=html.getUrl().substring(7)%>...<%=date %></span><a id="snapShot" href="<%=request.getContextPath()%>/snapShotServlet?title=<%=title%>" target="_blank">鹏鹏快照</a></td>
				</tr>
				<tr>
					<td><br/></td>
				</tr>
			<%
				}
			%>
		</table>

		<br/>
		<p style="font-weight: bold">鹏鹏搜索，就是这么强👍</p>
		<br/>
		<table>
			<tr>
				<%
					for(int i=0;i<queryResult.getPageCount();i++){
				%>
					<td>
						<a id="pageNowUrl" href="<%=request.getContextPath()%>/pagingSearchServlet?pageNow=<%=i%>&&queryString=<%=queryString%>">
							<div id="pageNow" align="center">
								<%=i+1 %>
							</div>
						</a>
					</td>
				<%
					}
				%>
			</tr>
		</table>

	</div>
</body>
</html>