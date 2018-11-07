package com.idc.service;

import com.idc.domain.HTML;
import com.idc.domain.QueryResult;

public interface SearchService {
	//分页查询
	QueryResult<HTML> pagingSearch(String queryString,QueryResult<HTML> result);
//	QueryResult<HTML> pagingSearch(String queryString);
	
	//根据rowkey获得网页快照内容，此处rowkey为标题.getBytes()
	String getPageShot(String title);
}
