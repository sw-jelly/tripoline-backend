package com.ssafy.tripoline.favorite.model.dto;

import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.member.model.dto.Member;

import lombok.Data;

@Data
public class Favorite {
	private int favoriteId;
	
	private Member member;
	private Attraction attraction;
}
