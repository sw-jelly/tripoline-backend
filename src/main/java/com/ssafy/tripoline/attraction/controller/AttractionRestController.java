package com.ssafy.tripoline.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;
import com.ssafy.tripoline.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/attractionRest")
@Api(value="관광지 지역별 / 유형별 조회를 위한 Rest-ful API", description = "Attraction 정보")
@CrossOrigin(origins= {"*"})
public class AttractionRestController {
	private AttractionService attractionService;
	public AttractionRestController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@ApiOperation(value="관광지 명으로 관광지 찾기", notes = "시도코드, 구군코드와 관광지명을 통한 관광지 검색")
	@GetMapping("/searchByTitle")
	public ResponseEntity<?> searchByTitle(@RequestParam(defaultValue = "0") int sidoCode,
										   @RequestParam(defaultValue = "0") int gugunCode,
										   @RequestParam String keyword) {
		List<Attraction> attractions = attractionService.searchByTitle(keyword, sidoCode, gugunCode);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attractions", attractions);
		if (attractions != null && !attractions.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="장소 기반으로 원하는 카테고리의 관광지 찾기", notes = "시도코드와 구군코드, 관광지코드를 통한 관광지 검색")
	@GetMapping("/searchByLocation")
	public ResponseEntity<?> searchByLocation(@RequestParam(defaultValue = "0") int sidoCode, 
											  @RequestParam(defaultValue = "0") int gugunCode, 
											  @RequestParam(defaultValue = "0") int contentTypeId) {
		List<Attraction> attractions = attractionService.searchByLocation(sidoCode, gugunCode, contentTypeId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attractions", attractions);
		if (attractions != null && !attractions.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="시도 찾기", notes = "시도 코드, 시도 이름 조회")
	@GetMapping
	public ResponseEntity<?> searchSido() {
		List<Sido> sidos = attractionService.searchSido();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sidos", sidos);
		if (sidos != null && !sidos.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value="시도에 해당하는 구군 찾기", notes = "시도 코드 기반 구군 코드, 이름 조회")
	@GetMapping("/findGugun")
	public ResponseEntity<?> searchGugun(@RequestParam int sidoCode) {
		List<Gugun> guguns = attractionService.searchGugun(sidoCode);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("guguns", guguns);
		if (guguns != null && !guguns.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
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
