package com.ssafy.tripoline.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Sido {
	@ApiParam(value="시도 코드", required = true)
	int sidoCode;
	@ApiParam(value="시도 명")
	String sidoName;
}
