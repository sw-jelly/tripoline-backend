package com.ssafy.tripoline.plan.model.dto.PlanDetail;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailParam {
	
	int planDetailId;
	int planId;
	int contentId;
	Date visitDate;
	int visitOrder;
	String memo;

	public static PlanDetailParam of(PlanDetail planDetail) {
		return PlanDetailParam.builder()
				.planDetailId(planDetail.getPlanDetailId())
				.planId(planDetail.getPlan().getPlanId())
				.contentId(planDetail.getAttraction().getContentId())
				.visitDate(planDetail.getVisitDate())
				.visitOrder(planDetail.getVisitOrder())
				.memo(planDetail.getMemo())
				.build();
				
	}
}
