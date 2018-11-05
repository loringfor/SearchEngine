package com.idc.dao;

import com.idc.domain.HTML;
import com.idc.domain.QueryResult;

public interface HTMLIndexDao {
	//HTML文档保存到索引库
	void save(HTML html);
	
	//分页查询,返回对象QueryResult来进行分页显示
	QueryResult<HTML> query(String queryString,QueryResult<HTML> result);
}
