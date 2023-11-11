package com.ssafy.tripoline.board.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Comment {
	private int commentId;
	private int articleId;
	private String memberId;
	private String memberName;
	private String content;
	private LocalDateTime registerTime; 
}
