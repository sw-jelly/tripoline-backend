package com.ssafy.tripoline.attraction.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.attraction.model.dao.AttractionDao;
import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;

@Service
public class AttractionServiceImpl implements AttractionService {
	
	private AttractionDao dao;
	public AttractionServiceImpl(AttractionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<Attraction> searchAll() {
		try {
			return dao.searchAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("관광지 정보 전체 조회 중 오류 발생");
		}
	}

	@Override
	public List<Attraction> searchByTitle(String keyword, int contentTypeId, int sidoCode, int gugunCode) {
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("contentTypeId", contentTypeId);
			paramMap.put("sidoCode", sidoCode);
			paramMap.put("gugunCode", gugunCode);
			paramMap.put("keyword", keyword);
			return dao.searchByTitle(paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("관광지 정보 검색 중 오류 발생");
		}
	}

	@Override
	public List<Attraction> searchByLocation(int sidoCode, int gugunCode, int contentTypeId) {
		try {
			return dao.searchByLocation(sidoCode, gugunCode, contentTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("위치 기반 관광지 정보 조회 중 오류 발생");
		}
	}

	@Override
	public List<Sido> searchSido() {
		try {
			return dao.searchSido();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("시/도 조회 중 오류 발생");
		}
	}

	@Override
	public List<Gugun> searchGugun(int sidoCode) {
		try {
			return dao.searchGugun(sidoCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("구/군 조회 중 오류 발생");
		}
	}

}
