package com.ssafy.tripoline.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tripoline.board.model.dto.Article;
import com.ssafy.tripoline.board.model.dto.BoardException;
import com.ssafy.tripoline.board.model.dto.Comment;
import com.ssafy.tripoline.board.model.dto.PageBean;
import com.ssafy.tripoline.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/boardRest")
@Api(value = "게시글 관련 API", description = "게시글 관련 요청을 처리해주는 API 정보")
@CrossOrigin(origins = { "*" })
public class BoardRestController {
	private Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	private BoardService boardService;

	public BoardRestController(BoardService boardService) {
		this.boardService = boardService;
	}

	private static final String SUCCESS = "success";
	
	@ApiOperation(value = "게시글 정보 등록", notes = "게시글 정보를 등록한다.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<?> regist(@RequestBody Article article) {
		
		logger.debug("Article.regist.............. article:{}", article);
		try {
			boardService.write(article);
			return new ResponseEntity<Void>(HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "전체 게시글 목록 조회", notes = "전체 게시글을 조회하는 API")
	/**
	 * ResponseEntity 응답 코드 + 응답 데이터를 전송하기 위한 객체
	 * 
	 * @param bean
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> searchAll(PageBean bean) {
		logger.debug("board.searchAll............bean:{}", bean);
		List<Article> articles = null;
		try {
			articles = boardService.searchAll(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		logger.debug("board.searchAll............bean:{}", articles);
		logger.debug("board.searchAll............bean:{}", bean);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articles", articles);
		result.put("page", bean);

		if (articles != null && !articles.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "카테고리별 게시글 목록 조회", notes = "카테고리(categoryId)에 해당하는 게시글 리스트 조회")
	@GetMapping("/searchby/{categoryId}")
	public ResponseEntity<?> searchByCategory( PageBean bean, @PathVariable int categoryId) {
		logger.debug("board.searchByCategory............categoryId:{}", categoryId);
		logger.debug("board.searchByCategory............bean:{}", bean);
		List<Article> articles = null;

		try {
			articles = boardService.searchByCategory(bean, categoryId);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		logger.debug("board.searchByCategory............articles:{}", articles);
		logger.debug("board.searchByCategory............bean:{}", bean);
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articles", articles);
		result.put("page", bean);

		if (articles != null && !articles.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "베스트 게시글 목록 조회", notes = " 일정 조회수, 좋아요 조건을 충족하는 게시글 리스트 조회")
	@GetMapping("/searchBest")
	public ResponseEntity<?> searchBest(PageBean bean) {
		logger.debug("board.categorysearchAll............bean:{}", bean);
		List<Article> articles = null;
		try {
			articles = boardService.getBestAll(bean);
		} catch (Exception e) {
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		logger.debug("board.categorysearchAll............bean:{}", articles);
		logger.debug("board.categorysearchAll............bean:{}", bean);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("articles", articles);
		result.put("page", bean);

		if (articles != null && !articles.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "articleId에 해당하는 게시글 상세 정보 조회", notes = "articleId에 해당하는 게시글 정보 조회")
	@GetMapping("/{articleId}")
	public ResponseEntity<?> search(@PathVariable int articleId) {
		logger.debug("board.search............ articleId:{}", articleId);
		Article article = null;
		try {
			article = boardService.getArticle(articleId);
		} catch (Exception e) {
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		logger.debug("board.search............ board:{}", article);

		if (article != null) {
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "게시글 좋아요", notes = "articleId에 해당하는 게시글의 좋아요를 증가시키는 api")
	@PutMapping("/{articleId}")
	public ResponseEntity<?> like(@PathVariable int articleId) {
			try {
				boardService.updateLike(articleId);
			} catch (Exception e) {
				return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("좋아요 완료", HttpStatus.OK);
	}

	

	@ApiOperation(value = "게시글 정보 수정", notes = "게시글 정보를 수정 한다.")
	@ApiResponse(code = 200, message = "success")
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Article article) {
		logger.debug("Article.update.............. article:{}", article);
		try {
			boardService.update(article);

		} catch (Exception e) {
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 정보 삭제", notes = "게시글 정보를 삭제 한다.")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{articleId}")
	public ResponseEntity<String> remove(@PathVariable int articleId) {
		logger.debug("Book.delete.............. articleId:{}", articleId);
		try {
			boardService.remove(articleId);

		} catch (Exception e) {
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ResponseBody
	@ApiOperation(value = "articleId에 해당하는 게시글의 댓글 조회", notes = "articleId에 해당하는 게시글 댓글 조회")
	@GetMapping("/comments/{articleId}")
	public ResponseEntity<?> getComments(@PathVariable int articleId) {
		logger.debug("getComments.................articleId : {}", articleId);
		List<Comment> comments;
		
		try {
			comments = boardService.getCommentsByArticleId(articleId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		
		logger.debug("board.search............ comments:{}", comments);

		if (comments != null) {
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/comments")
	@ResponseBody
	public ResponseEntity<?> registComment(@RequestBody Comment comment) {
		logger.debug("Article.regist.............. comment:{}", comment);
		try {
			boardService.writeComment(comment);
			return new ResponseEntity<Void>(HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "댓글 수정", notes = "댓글을 수정 한다.")
	@ApiResponse(code = 200, message = "success")
	@PutMapping("/comments")
	public ResponseEntity<String> updateComment(@RequestBody Comment comment) {
		logger.debug("Article.updateComment.............. comment:{}", comment);
		try {
			boardService.updateComment(comment);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 한다.")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> removeComment(@PathVariable int commentId) {
		logger.debug("Book.removeComment.............. commentId:{}", commentId);
		try {
			boardService.deleteComment(commentId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<String>("처리 중 오류가 발생하였습니다", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		e.printStackTrace();
		logger.error("book.error...............msg:{}", e.getMessage());

		// error메세지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type", "application/json;charset=UTF-8");
		String errorMsg = "";
		if (e instanceof BoardException) {
			errorMsg = e.getMessage();
		} else {
			errorMsg = "처리 중 오류 발생";
		}
		return new ResponseEntity<String>(e.getMessage(), resHeader, HttpStatus.FAILED_DEPENDENCY);

	}
}
