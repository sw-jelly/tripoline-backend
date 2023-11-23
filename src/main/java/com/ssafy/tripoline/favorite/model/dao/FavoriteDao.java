package com.ssafy.tripoline.favorite.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.favorite.model.dto.Favorite;
import com.ssafy.tripoline.favorite.model.dto.FavoriteParam;

@Mapper
public interface FavoriteDao {
	int createFavorite(FavoriteParam favoriteParam) throws SQLException;

	Favorite searchFavoriteById(int favoriteId) throws SQLException;

	List<Favorite> getFavoritesByMemberId(String memberId) throws SQLException;

	List<Favorite> getAllFavorites() throws SQLException;
	
	int getFavoriteCountByContentId(int contentId) throws SQLException;

	void updateFavorite(FavoriteParam favoriteParam) throws SQLException;

	void deleteFavorite(int favoriteId) throws SQLException;
	
	int hasFavoriteByMemberAndAttraction(Map<String, Object> paramMap) throws SQLException;
}
