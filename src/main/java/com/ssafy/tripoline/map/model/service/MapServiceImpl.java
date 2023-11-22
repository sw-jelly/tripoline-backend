package com.ssafy.tripoline.map.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.tripoline.map.model.dao.MapDao;
import com.ssafy.tripoline.map.model.dto.SidoGugunCodeDto;

@Service
public class MapServiceImpl implements MapService {
	
	private MapDao dao;
	
	@Autowired
	public MapServiceImpl(MapDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return dao.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return dao.getGugunInSido(sido);
	}

}
