package com.ssafy.tripoline.favorite.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.favorite.model.dao.FavoriteDao;
import com.ssafy.tripoline.favorite.model.dto.Favorite;
import com.ssafy.tripoline.favorite.model.dto.FavoriteListDto;
import com.ssafy.tripoline.favorite.model.dto.FavoriteParam;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	private FavoriteDao favoriteDao;

	public FavoriteServiceImpl(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public Integer createFavorite(FavoriteParam favoriteParam) {
		try {
			favoriteDao.createFavorite(favoriteParam);
			return favoriteParam.getFavoriteId();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("즐겨찾기 등록 중 오류 발생");
		}
	}

	@Override
	public FavoriteListDto searchFavoriteById(int favoriteId) {
		try {
			return FavoriteListDto.of(favoriteDao.searchFavoriteById(favoriteId));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("즐겨찾기 조회 중 오류 발생");
		}
	}

	@Override
	public List<FavoriteListDto> getFavoritesByMemberId(String memberId) {
		try {
			return favoriteDao.getFavoritesByMemberId(memberId)
					.stream()
					.map(favorite -> FavoriteListDto.of(favorite))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("해당 회원의 즐겨찾기 조회 중 오류 발생");
		}
	}

	@Override
	public int getFavoriteCountByContentId(int contentId) {
		try {
			return favoriteDao.getFavoriteCountByContentId(contentId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("해당 관광지의 좋아요 수 조회 중 오류 발생");
		}
	}

	@Override
	public List<Favorite> getAllFavorites() {
		try {
			return favoriteDao.getAllFavorites();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("모든 즐겨찾기 조회 중 오류 발생");
		}
	}
	
	@Override
	public boolean hasFavoriteByMemberAndAttraction(String memberId, int contentId) {
        try {
        	Map<String, Object> paramMap = new HashMap<>();
        	paramMap.put("memberId", memberId);
        	paramMap.put("contentId", contentId);
        	System.out.println(paramMap);
            int count = favoriteDao.hasFavoriteByMemberAndAttraction(paramMap);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("즐겨찾기 여부 확인 중 오류 발생");
        }
	}

	/*
	 * update는 이용 가치가 없다고 판단하여 구현하지 않았습니다!
	 */

	@Override
	public void deleteFavorite(int favoriteId) {
		try {
			Favorite find = favoriteDao.searchFavoriteById(favoriteId);
			if (find == null)
				throw new TripolineException("존재하지 않는 PlanDetail입니다.");
			favoriteDao.deleteFavorite(favoriteId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TripolineException("즐겨찾기 삭제 중 오류 발생");
		}
	}
}
