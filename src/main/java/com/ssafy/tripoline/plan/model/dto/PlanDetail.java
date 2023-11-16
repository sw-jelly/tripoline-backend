package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import com.ssafy.tripoline.attraction.model.dto.Attraction;

import lombok.Data;

@Data
public class PlanDetail {
	private int planDetailId;
	private Date visitDate;
	private int visitOrder;
	
	private Plan plan;
	private Attraction attraction;
}
