package com.jxl.jcrawler.model.hupupost;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "info")
public class HupuPost {
	@Id
	private String id;
	private String postTitle;
	private String authorName;
	private String startDate;
	private List<Count> counts;
	private String url;
	private String plate;//所属板块
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public List<Count> getCounts() {
		return counts;
	}
	public void setCounts(List<Count> counts) {
		this.counts = counts;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	
}
