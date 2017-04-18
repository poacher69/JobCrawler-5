package utils;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.selenium.webdriven.commands.IsSomethingSelected;

/**
 * the class to store result parsed from web the content of webPage is stored in
 * this field as the form of json
 * 
 * @author yangyudong
 *
 */
public class ResultItem {

	private String url;
	private String site;
	private String parseTime;
	private String content;
	private String docType;

	public ResultItem(String url, String site, String parseTime, String content, String docType) {
		super();
		this.url = url;
		this.site = site;
		this.parseTime = parseTime;
		this.content = content;
		this.docType = docType;
	}

	public ResultItem(String url, String site, String parseTime, String docType) {
		super();
		this.url = url;
		this.site = site;
		this.parseTime = parseTime;
		this.docType = docType;
	}

	public ResultItem() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getParseTime() {
		return parseTime;
	}

	public void setParseTime(String parseTime) {
		this.parseTime = parseTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	// public static void main(String[] args) {
	// ResultItem ri=new ResultItem();
	// ri.setContent("hello");
	// String s=JSON.toJSONString(ri);
	// Object o=JSON.parseObject(s, TestDao.class);
	// System.out.println(o instanceof ResultItem);
	// }

}
