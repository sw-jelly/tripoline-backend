package com.ssafy.tripoline.board.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;

/** UI 화면 페이지에 대한 정보를 표시하는 클래스  */
public class PageBean implements Serializable{
	/**검색 조건*/
	@ApiParam(value="검색 조건")
	private String key;
	/**검색 단어*/
	@ApiParam(value="검색 단어")
	private String word;
	/**페이징 처리에 대한 link정보*/
	private String pageLink;
	
	/**현재 페이지 번호*/
	@ApiParam(value="페이지 번호", required = true)
	private int pageNo;
	
	@ApiParam(value="전체 데이타 개수")
	private int total;

	
	
	/**한 페이지에 보여주 content 개수*/
	private int interval = 5;
	/**페이지 시작 번호*/
	private int start=0;
	public PageBean() {	}
	public PageBean(String key, String word, int pageNo) {
		setKey(key);
		setWord(word);
		setPageNo(pageNo);

	}
	public PageBean(String key, String word, String pageNo) {
		setKey(key);
		setWord(word);
		setPageNo(pageNo);
	}
	
	private void setPageNo(String pageNo) {
		System.out.println("###########################################setPageNo(String)");
		try {
			this.pageNo = Integer.parseInt(pageNo);
			if(this.pageNo==0) {
				this.pageNo = 1;
			}
		} catch (Exception e) {
			this.pageNo = 1;
		}
		System.out.println("###########################################pageNo:"+this.pageNo);
	}
	public String getKey() {
		return key;
	}
	public String getKey(String k) {
		if(key!=null && key.equals(k)) {
			return "selected='selected'";
		}else {
			return "";
		}
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getPageLink() {
		return pageLink;
	}
	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}
	
	
	public int getPageNo() {
		if(pageNo ==0) {
			return 1;
		}else {
			return pageNo;
		}
	}
	public void setPageNo(int pageNo) {
		System.out.println("###########################################setPageNo(int)");
		if(pageNo==0) {
			this.pageNo = 1;
		}else {			
			this.pageNo = pageNo;
		}
		System.out.println("###########################################pageNo:"+this.pageNo);
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getStart() {
		if(pageNo>1) {
			return start = (pageNo-1)*interval;
		}else {
			return 0;
		}
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageBean [key=").append(key).append(", word=").append(word)
				.append(", pageNo=").append(pageNo).append(", interval=").append(interval)
				.append(", pagelink=")
				.append(pageLink)
				.append(", start=").append(start).append("]");
		return builder.toString();
	}
}












