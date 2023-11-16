package com.ssafy.tripoline.plan.model.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanRegistParam {
	String memberId;
	String planTitle;
	Date startDate;
	Date endDate;
	int sidoCode;
	int gugunCode;
	
	public static PlanRegistParam of(Plan plan) {
		return PlanRegistParam.builder()
				.memberId(plan.getMember().getMemberId())
				.planTitle(plan.getPlanTitle())
				.startDate(plan.getStartDate())
				.endDate(plan.getEndDate())
				.sidoCode(plan.getSido().getSidoCode())
				.gugunCode(plan.getGugun().getGugunCode())
				.build();
	}
}
