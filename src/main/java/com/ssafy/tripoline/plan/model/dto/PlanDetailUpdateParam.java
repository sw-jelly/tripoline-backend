package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailUpdateParam {
	
	int planDetailId;
	int planId;
	int contentId;
	Date visitDate;
	int visitOrder;

	public static PlanDetailUpdateParam of(PlanDetail planDetail) {
		return PlanDetailUpdateParam.builder()
				.planDetailId(planDetail.getPlanDetailId())
				.planId(planDetail.getPlan().getPlanId())
				.contentId(planDetail.getAttraction().getContentId())
				.visitDate(planDetail.getVisitDate())
				.visitOrder(planDetail.getVisitOrder())
				.build();
				
	}
}
