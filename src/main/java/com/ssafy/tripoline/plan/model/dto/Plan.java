package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import com.ssafy.tripoline.attraction.model.dto.Gugun;
import com.ssafy.tripoline.attraction.model.dto.Sido;
import com.ssafy.tripoline.member.model.dto.Member;

import lombok.Data;

@Data
public class Plan {
	private int planId;
	private String planTitle;
	private Date startDate;
	private Date endDate;
	
	private Member member;
	private Sido sido;
	private Gugun gugun;
}
