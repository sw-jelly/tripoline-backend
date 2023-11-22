package com.ssafy.tripoline.favorite.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoriteParam {
	int favoriteId;
	String memberId;
	int contentId;
	
	public static FavoriteParam of(Favorite favorite) {
		return FavoriteParam.builder()
				.favoriteId(favorite.getFavoriteId())
				.memberId(favorite.getMember().getMemberId())
				.contentId(favorite.getAttraction().getContentId())
				.build();
	}
}
