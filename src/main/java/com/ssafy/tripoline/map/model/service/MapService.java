package com.ssafy.tripoline.map.model.service;

import java.util.List;

import com.ssafy.tripoline.map.model.dto.SidoGugunCodeDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	
}
