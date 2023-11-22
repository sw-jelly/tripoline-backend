package com.ssafy.tripoline.favorite.model.service;

import java.util.List;

import com.ssafy.tripoline.favorite.model.dto.Favorite;
import com.ssafy.tripoline.favorite.model.dto.FavoriteListDto;
import com.ssafy.tripoline.favorite.model.dto.FavoriteParam;

public interface FavoriteService {
	Integer createFavorite(FavoriteParam favoriteParam);

	FavoriteListDto searchFavoriteById(int favoriteId);

	List<FavoriteListDto> getFavoritesByMemberId(String memberId);

	List<Favorite> getAllFavorites();

	int getFavoriteCountByContentId(int contentId);

	void deleteFavorite(int favoriteId);
}
