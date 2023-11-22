package com.ssafy.tripoline.favorite.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.favorite.model.dto.FavoriteListDto;
import com.ssafy.tripoline.favorite.model.dto.FavoriteParam;
import com.ssafy.tripoline.favorite.model.service.FavoriteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favoriteRest")
@Api(value = "관광지 즐겨찾기를 위한 Rest-ful API", description = "Favorite 정보")
@CrossOrigin(origins = { "*" })
@Slf4j
public class FavoriteRestContoller {

	private FavoriteService favoriteService;

	public FavoriteRestContoller(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}

	private static final String SUCCESS = "success";

	@ApiOperation(value = "즐겨찾기 등록하기")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<?> registFavorite(@RequestBody FavoriteParam favoriteParam) {
		log.debug("Favorite.regist.............................favoriteParam : {}", favoriteParam);
		Integer generatedKey = favoriteService.createFavorite(favoriteParam);
		return new ResponseEntity<>(generatedKey, HttpStatus.CREATED);
	}

	@ApiOperation(value = "favoriteId로 즐겨찾기 가져오기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/{favoriteId}")
	public ResponseEntity<?> getFavoriteById(@PathVariable int favoriteId) {
		log.debug("Favorite.searchFavoriteById.............................favoriteId : {}", favoriteId);
		FavoriteListDto favorite = favoriteService.searchFavoriteById(favoriteId);

		if (favorite != null) {
			return new ResponseEntity<FavoriteListDto>(favorite, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원에 대한 모든 여행 계획 보기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/member/{memberId}")
	public ResponseEntity<?> getAllMemberfavorites(@PathVariable String memberId) {
		log.debug("favorite.searchFavoritesByMemberId..............memberId : {}", memberId);
		List<FavoriteListDto> favorites = favoriteService.getFavoritesByMemberId(memberId);
		
		if (favorites != null && !favorites.isEmpty()) {
			return new ResponseEntity<List<FavoriteListDto>>(favorites, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "특정 관광지의 즐겨찾기 개수 조회")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/count/{contentId}")
	public ResponseEntity<?> getFavoriteCountForAttraction(@PathVariable int contentId) {
	    log.debug("favorite.getFavoriteCountForAttraction..............contentId : {}", contentId);
	    int count = favoriteService.getFavoriteCountByContentId(contentId);
	    return new ResponseEntity<Integer>(count, HttpStatus.OK);
	}
	
	@ApiOperation(value = "계획 삭제하기")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{favoriteId}")
	public ResponseEntity<String> removefavoriteById(@PathVariable int favoriteId) {
		log.debug("favorite.removeFavoriteById..............favoriteId : {}", favoriteId);
		favoriteService.deleteFavorite(favoriteId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		e.printStackTrace();
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type", "application/json;charset=UTF-8");
		String errorMsg = "";
		if (e instanceof TripolineException) {
			errorMsg = e.getMessage();
		} else {
			errorMsg = "처리 중 오류 발생";
		}
		return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
}
