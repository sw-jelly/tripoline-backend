package com.ssafy.tripoline.attraction.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data 
public class Attraction {
	@ApiParam(value="관광지 ID", required = true)
	private int contentId;
	@ApiParam(value="관광지 타입 ID")
	private int contentTypeId;
	@ApiParam(value="관광지 이름")
	private String title;
	@ApiParam(value="관광지 주소 1")
	private String addr1;
	@ApiParam(value="관광지 주소 2")
	private String addr2;
	@ApiParam(value="관광지 우편번호")
	private String zipcode;
	@ApiParam(value="관광지 전화번호")
	private String tel;
	@ApiParam(value="관광지 이미지 1")
	private String firstImage;
	@ApiParam(value="관광지 이미지 2")
	private String firstImage2;
	@ApiParam(value="관광지 시도 코드")
	private int sidoCode;
	@ApiParam(value="관광지 구군 코드")
	private int gugunCode;
	@ApiParam(value="관광지 위도")
	private double latitude;
	@ApiParam(value="관광지 경도")
	private double longitude;
	@ApiParam(value="관광지 설명")
	private String overview;
}
