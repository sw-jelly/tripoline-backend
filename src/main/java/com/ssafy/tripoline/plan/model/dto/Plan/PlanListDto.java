package com.ssafy.tripoline.plan.model.dto.Plan;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanListDto {
	private int planId;
	private String planTitle;
	private Date startDate;
	private Date endDate;
	private String sidoName;
	private String gugunName;
	
	public static PlanListDto of(Plan plan) {
		return PlanListDto.builder()
				.planId(plan.getPlanId())
				.planTitle(plan.getPlanTitle())
				.startDate(plan.getStartDate())
				.endDate(plan.getEndDate())
				.sidoName(plan.getSido().getSidoName())
				.gugunName(plan.getGugun().getGugunName())
				.build();
	}
}
