package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailRegistParam {
	int planId;
	int contentId;
	Date visitDate;
	int visitOrder;

	public static PlanDetailRegistParam of(PlanDetail planDetail) {
		return PlanDetailRegistParam.builder()
				.planId(planDetail.getPlan().getPlanId())
				.contentId(planDetail.getAttraction().getContentId())
				.visitDate(planDetail.getVisitDate())
				.visitOrder(planDetail.getVisitOrder())
				.build();
				
	}
}
