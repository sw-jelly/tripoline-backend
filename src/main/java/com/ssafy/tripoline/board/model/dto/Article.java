package com.ssafy.tripoline.board.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Article implements Serializable {
	@ApiParam(value="게시글 번호")
	private int articleId;
	
	@ApiParam(value="게시글 제목")
	private String articleTitle;
	
	@ApiParam(value="게시글 내용")
	private String articleContent;
	
	@ApiParam(value="작성자 아이디")
	private String memberId;
	
	@ApiParam(value="작성자 이름")
	private String memberName;
	
	@ApiParam(value="게시글 좋아요 수")
	private int likeCount;
	
	@ApiParam(value="게시글 조회 수")
	private int viewCount;
	
	@ApiParam(value="게시글 등록일자")
	private Date registerTime;
	
	@ApiParam(value="게시글 수정일자")
	private Date updateTime; 
	
	@ApiParam(value="게시글 분류번호")
	private int categoryId;
	
	@ApiParam(value="첨부 이미지")
	private String image;
	
	@ApiParam(value="코멘트 개수")
	private int commentCount;
	
//	@ApiParam(value="파일 첨부")
//	private List<FileInfoDto> fileInfos;

	
}

