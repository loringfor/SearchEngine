package com.idc.util;

import java.util.Date;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;
import org.jruby.util.Numeric;

import com.idc.domain.HTML;

public class HTMLDocumentUtils {
	/**
	 * HTML实体转Document
	 * @param html
	 * @return
	 */
	public static Document HTML2Document(HTML html){
		Document doc=new Document();

		/*
     * 参数说明 public Field(String name, String value, Store store, Index index)
     * name : 字段名称
     * value : 字段的值 store :
			*  Field.Store.YES:存储字段值（未分词前的字段值）
			*   Field.Store.NO:不存储,存储与索引没有关系
			*  Field.Store.COMPRESS:压缩存储,用于长文本或二进制，但性能受损
     * index : 建立索引的方式，是否建立分词等等
			*  Field.Index.ANALYZED:分词建索引
			*  Field.Index.ANALYZED_NO_NORMS:分词建索引，但是Field的值不像通常那样被保存，而是只取一个byte，这样节约存储空间
			*  Field.Index.NOT_ANALYZED:不分词且索引 ,一旦指定为这种类型后将会被lucenn录入索引中，但不会被作为关键搜索，除非输入所有的关键字
			*  Field.Index.NOT_ANALYZED_NO_NORMS:不分词建索引，Field的值去一个byte保存
     */
		doc.add(new Field("title",html.getTitle(),Store.YES,Index.ANALYZED));
		doc.add(new Field("description",html.getDescription(),Store.YES,Index.ANALYZED));
		doc.add(new Field("content",html.getContent(),Store.YES,Index.ANALYZED));
		doc.add(new Field("date",NumericUtils.longToPrefixCoded(html.getDate().getTime()),Store.YES,Index.NO));
		doc.add(new Field("url",html.getUrl(),Store.YES,Index.NO));
		System.out.println(doc.toString());
		return doc;
	}

	public static HTML document2HTML(Document doc) {
		String title=doc.get("title");
		String description=doc.get("description");
		String content=doc.get("content");
		Date date=new Date(NumericUtils.prefixCodedToLong(doc.get("date")));
		String url=doc.get("url");
		HTML html=new HTML(title,description,date,content,url);
		return html;
	}
}
