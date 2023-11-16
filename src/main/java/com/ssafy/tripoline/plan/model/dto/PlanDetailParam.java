package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailParam {
	int planId;
	int contentId;
	Date visitDate;
	int visitOrder;

	public static PlanDetailParam of(PlanDetail planDetail) {
		return PlanDetailParam.builder()
				.planId(planDetail.getPlan().getPlanId())
				.contentId(planDetail.getAttraction().getContentId())
				.visitDate(planDetail.getVisitDate())
				.visitOrder(planDetail.getVisitOrder())
				.build();
				
	}
}
