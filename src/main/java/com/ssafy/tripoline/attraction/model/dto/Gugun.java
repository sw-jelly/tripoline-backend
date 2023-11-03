package com.ssafy.tripoline.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Gugun {
	@ApiParam(value="시도 코드", required = true)
	int sidoCode;
	@ApiParam(value="구군 코드", required = true)
	int gugunCode;
	@ApiParam(value="구군 명")
	String gugunName;
}
