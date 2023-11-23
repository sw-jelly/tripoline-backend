package com.ssafy.tripoline.attraction.model.service;

import java.util.List;

import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;

public interface AttractionService {
	List<Attraction> searchAll();

	List<Attraction> searchByTitle(String keyword, int contentTypeId, int sidoCode, int gugunCode);
	
	List<Attraction> searchByLocation(int sidoCode, int gugunCode, int contentTypeId);

	List<Sido> searchSido();
	
	List<Gugun> searchGugun(int sidoCode);
	
	List<Attraction> getAttractionsSortedByLikeCount();
}
