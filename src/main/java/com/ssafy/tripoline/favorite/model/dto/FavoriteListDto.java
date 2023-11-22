package com.ssafy.tripoline.favorite.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteListDto {
	private int favoriteId;
	private String memberId;
	private int contentId;
	private String title;
	private String firstImage;
	private String addr1;
	
	public static FavoriteListDto of(Favorite favorite) {
		return FavoriteListDto.builder()
				.favoriteId(favorite.getFavoriteId())
				.memberId(favorite.getMember().getMemberId())
				.contentId(favorite.getAttraction().getContentId())
				.title(favorite.getAttraction().getTitle())
				.firstImage(favorite.getAttraction().getFirstImage())
				.addr1(favorite.getAttraction().getAddr1())
				.build();
	}
}
