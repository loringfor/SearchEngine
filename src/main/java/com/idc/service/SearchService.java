package com.idc.service;

import com.idc.domain.HTML;
import com.idc.domain.QueryResult;

public interface SearchService {
	//分页查询
	public QueryResult<HTML> pagingSearch(String queryString,QueryResult<HTML> queryResult);
	
	//根据rowkey获得网页快照内容，此处rowkey为标题.getBytes()
	public byte[] getPageShot(String title);
}
