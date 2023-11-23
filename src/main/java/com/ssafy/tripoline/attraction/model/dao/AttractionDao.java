package com.ssafy.tripoline.attraction.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;

@Mapper
public interface AttractionDao {
	List<Attraction> searchAll() throws SQLException;

	List<Attraction> searchByTitle(Map<String, Object> paramMap) throws SQLException;
	
	List<Attraction> searchByLocation(int sidoCode, int gugunCode, int contentTypeId) throws SQLException;

	List<Sido> searchSido() throws SQLException;
	
	List<Gugun> searchGugun(int sidoCode) throws SQLException;
	
	List<Attraction> getAttractionsSortedByLikeCount() throws SQLException;
}
