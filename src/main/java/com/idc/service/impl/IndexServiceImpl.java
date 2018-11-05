package com.idc.service.impl;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.index.CorruptIndexException;

import com.idc.dao.impl.HBaseDaoImpl;
import com.idc.dao.impl.HTMLIndexDaoImpl;
import com.idc.domain.HTML;
import com.idc.util.LuceneUtils;

public class IndexServiceImpl {
	private HBaseDaoImpl hbaseDao=new HBaseDaoImpl("html");
	private HTMLIndexDaoImpl indexDao=new HTMLIndexDaoImpl();
	
	public void createIndex() throws CorruptIndexException, IOException{
		List<HTML> list=hbaseDao.queryAll();
		for(HTML html:list){
			indexDao.save(html);
		}
		indexDao.indexCommit();
	}
	
	public void optimize() throws CorruptIndexException, IOException{
		LuceneUtils.getIndexWriter().optimize();
	}
	
	public static void main(String args[]) throws CorruptIndexException, IOException{
		new IndexServiceImpl().createIndex();
	}
}

