package com.jxl.jcrawler.model.hupupostnew;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "newinfo")
public class HupuNewData {
	@Id
	private String id;
	private String postTitle;
	private String authorName;
	private String startDate;
	private List<String> replyCounts;
	private List<String> scanCounts;
	private List<String> monitorTimes;
	private String url;
	private String plate;//所属板块
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public List<String> getReplyCounts() {
		return replyCounts;
	}
	public void setReplyCounts(List<String> replyCounts) {
		this.replyCounts = replyCounts;
	}
	public List<String> getScanCounts() {
		return scanCounts;
	}
	public void setScanCounts(List<String> scanCounts) {
		this.scanCounts = scanCounts;
	}
	public List<String> getMonitorTimes() {
		return monitorTimes;
	}
	public void setMonitorTimes(List<String> monitorTimes) {
		this.monitorTimes = monitorTimes;
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
