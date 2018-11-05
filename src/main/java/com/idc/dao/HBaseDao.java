package com.idc.dao;

import com.idc.domain.HTML;

public interface HBaseDao {
	
	//根据行键查询HTML文档
	HTML queryByRowKey(String rowKey);
}
