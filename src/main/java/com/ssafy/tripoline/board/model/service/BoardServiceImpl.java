package com.ssafy.tripoline.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.tripoline.board.model.dao.BoardDao;
import com.ssafy.tripoline.board.model.dto.Article;
import com.ssafy.tripoline.board.model.dto.BoardException;
import com.ssafy.tripoline.board.model.dto.Comment;
import com.ssafy.tripoline.board.model.dto.PageBean;
import com.ssafy.tripoline.util.PageUtility;

import lombok.extern.slf4j.Slf4j;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao dao;

	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Article> searchAll(PageBean bean) {
		System.out.println("게시글 searchAll 수행 중...............");
		try {
			int total = dao.totalCount(bean);
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), null);
			bean.setPageLink(page.getPageBar());
			bean.setTotal(total);
			return dao.searchAll(bean);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("전체 게시글 목록 정보 조회 중 오류 발생");
		}
	}

	@Override
	public List<Article> searchByCategory(PageBean bean, @PathVariable int categoryId) {
		System.out.println("게시글 searchByCategory 수행 중...............");
		try {
			int total = dao.categoryCount(categoryId);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("categoryId", categoryId);
			paramMap.put("bean", bean);
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), null);
			bean.setPageLink(page.getPageBar());
			bean.setTotal(total);

			return dao.searchByCategory(paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("카테고리 게시 목록 정보 조회 중 오류 발생");
		}
	}

	@Override
	public Article getArticle(int articleId) {
		try {
			Article article = dao.getArticle(articleId);

			if (article == null) {
				throw new BoardException("게시글 정보가 존재하지 않니다.");
			}
			dao.updateHit(articleId); // 조회수 반영
			return article;
		} catch (SQLException e) {
			throw new BoardException("게시글 조회 중 오류 발생");
		}
	}

	@Override
	public void remove(int articleId) {
		try {
			dao.remove(articleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoardException("게시글  삭제 중 오류 발생");
		}
	}

	@Override
	public void update(Article article) {
		try {
			dao.update(article);
		} catch (Exception e) {
			throw new BoardException("게시글 정보 수정 중 오류 발생");
		}
	}

	@Override
	public void write(Article article) {
		System.out.println("입력된 article...." + article);
		try {
			Article find = dao.getArticle(article.getArticleId());
			if (find != null)
				throw new BoardException("이미 등록된 게시글 입니다.");

			dao.write(article);
		} catch (SQLException e) {
			throw new BoardException("게시 정보 등록 중 오류 발생");
		}
	}

	@Override
	public List<Article> getBestAll(PageBean bean) {
		try {
			int total = dao.BestTotalCount(bean);
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), null);
			bean.setPageLink(page.getPageBar());
			bean.setTotal(total);

			return dao.getBestAll(bean);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BoardException("베스트 게시글 목록 정보 조회 중 오류 발생");
		}
	}

	@Override
	public void updateLike(int articleId) {
		try {
			Article find = dao.getArticle(articleId);
			if (find == null)
				throw new BoardException("게시글 정보를 찾을 수 없습니다.");
			dao.updateLike(articleId);
			dao.getArticle(articleId); // 좋아요가 반영된 게시글 정보를 가져온다
		} catch (SQLException e) {
			throw new BoardException("게시 정보 등록 중 오류 발생");
		}
	}

	@Override
	public List<Comment> getCommentsByArticleId(int articleId) {
		try {
			return dao.getCommentsByArticleId(articleId);
		} catch (SQLException e) {
			throw new BoardException("댓글 조회 중 오류 발생");
		}
	}

	@Override
	public void writeComment(Comment comment) {
		try {
			Article find = dao.getArticle(comment.getArticleId());
			if (find == null) throw new BoardException("존재하지 않는 게시글 입니다.");
			dao.writeComment(comment);
		} catch (SQLException e) {
			throw new BoardException("댓글 등록 중 오류 발생");
		}
	}

}
