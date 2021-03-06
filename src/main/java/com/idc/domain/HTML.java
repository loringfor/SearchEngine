package com.idc.domain;

public class HTML {
	private String title;
	private String description;
	private String date;
	private String content;
	private String url;

	public HTML(String title, String description, String date, String content,
			String url) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.content = content;
		this.url = url;
	}

	public HTML(){}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "HTML [title=" + title + ", description=" + description
				+ ", date=" + date + ", content=" + content
				+ ", url=" + url + "]";
	}
	
	
	
}
