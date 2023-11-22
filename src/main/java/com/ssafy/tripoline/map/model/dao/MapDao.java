package com.ssafy.tripoline.map.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.map.model.dto.SidoGugunCodeDto;

@Mapper
public interface MapDao {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	
}
