package com.ssafy.tripoline.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.board.model.dto.Article;
import com.ssafy.tripoline.board.model.dto.Comment;
import com.ssafy.tripoline.board.model.dto.PageBean;

@Mapper
public interface BoardDao {

	public List<Article> searchAll(PageBean bean) throws SQLException; // 게시글 검색

	public int totalCount(PageBean bean) throws SQLException; // 총 게시글 수 가져오기

	public List<Article> searchByCategory(Map<String, Object> paramMap) throws SQLException; // 카테고리로 글 검색하기

	public int categoryCount(int categoryId) throws SQLException; // 카테고리에 해당하는 글 갯수 가져오기

	public Article getArticle(int articleId) throws SQLException; // 게시글 상세정보 가져오기

	public void remove(int articleId) throws SQLException; // 게시글 삭제

	public void update(Article article) throws SQLException; // 게시글 수정

	public void write(Article article) throws SQLException; // 게시글 작성

	public List<Article> getBestAll(PageBean bean) throws SQLException; // 베스트 글 목록 가져오기

	public int BestTotalCount(PageBean bean) throws SQLException; // 베스트 게시글 수 가져오기

	public void updateLike(int articleId) throws SQLException; // 좋아요 수 반영

	public void updateHit(int articleId) throws SQLException; // 조회수 반영

	public List<Comment> getCommentsByArticleId(int articleId) throws SQLException; // 게시물의 댓글 목록 가져오기
	
	public Comment getCommentById(int commentId) throws SQLException; // 댓글 가져오기
	
	public void writeComment(Comment comment) throws SQLException; // 댓글 작성하기
	
	public void updateComment(Comment comment) throws SQLException; // 댓글 수정하기

	public void deleteComment(int commentId) throws SQLException; // 댓글 삭제하기
}
