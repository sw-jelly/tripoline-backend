package com.ssafy.tripoline.plan.model.dto.Plan;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanParam {
	int planId;
	String memberId;
	String planTitle;
	Date startDate;
	Date endDate;
	int sidoCode;
	int gugunCode;
	
	public static PlanParam of(Plan plan) {
		return PlanParam.builder()
				.planId(plan.getPlanId())
				.memberId(plan.getMember().getMemberId())
				.planTitle(plan.getPlanTitle())
				.startDate(plan.getStartDate())
				.endDate(plan.getEndDate())
				.sidoCode(plan.getSido().getSidoCode())
				.gugunCode(plan.getGugun().getGugunCode())
				.build();
	}
}
