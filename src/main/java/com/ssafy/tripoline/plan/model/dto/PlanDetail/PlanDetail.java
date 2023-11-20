package com.ssafy.tripoline.plan.model.dto.PlanDetail;

import java.sql.Date;

import com.ssafy.tripoline.attraction.model.dto.Attraction;
import com.ssafy.tripoline.plan.model.dto.Plan.Plan;

import lombok.Data;

@Data
public class PlanDetail {
	private int planDetailId;
	private Date visitDate;
	private int visitOrder;
	private String memo;
	
	private Plan plan;
	private Attraction attraction;
}
