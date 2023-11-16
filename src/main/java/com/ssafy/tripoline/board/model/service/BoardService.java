package com.ssafy.tripoline.board.model.service;

import java.util.List;

import com.ssafy.tripoline.board.model.dto.Article;
import com.ssafy.tripoline.board.model.dto.Comment;
import com.ssafy.tripoline.board.model.dto.PageBean;

public interface BoardService {

	public List<Article> searchAll(PageBean bean); // 게시글 전체 검색

	public List<Article> searchByCategory(PageBean bean, int categoryId); // 카테고리로 글 검색하기

	public List<Article> getBestAll(PageBean bean); // 베스트 글 목록 가져오기

	public Article getArticle(int articleId); // 게시글 상세정보 가져오기

	public void remove(int articleId); // 게시글 삭제

	public void update(Article article); // 게시글 수정

	public void write(Article article); // 게시글 작성

	public void updateLike(int articleId); // 게시글 좋아요
	
	public List<Comment> getCommentsByArticleId(int articleId); // 게시물의 댓글 목록 가져오기
	
	public void writeComment(Comment comment); // 댓글 작성하기
	
	public void updateComment(Comment comment); // 댓글 수정하기

	public void deleteComment(int commentId); // 댓글 삭제하기
}
