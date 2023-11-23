package com.ssafy.tripoline.attraction.model.dto;

import lombok.Data;

@Data
public class HotPlace {
	private int contentId;
	private String title;
	private String addr1;
	private String tel;
	private String firstImage;
	private String overview;
	int likeCount;
//	
//	public static HotPlace of(Attraction attraction) {
//		return HotPlace.builder()
//				.contentId(attraction.getContentId())
//				.title(attraction.getTitle())
//				.addr1(attraction.getAddr1())
//				.tel(attraction.getTel())
//				.firstImage(attraction.getFirstImage())
//				.overview(attraction.getOverview())
//				.likeCount
//	}
}
