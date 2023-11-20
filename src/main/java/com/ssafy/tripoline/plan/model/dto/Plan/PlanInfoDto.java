package com.ssafy.tripoline.plan.model.dto.Plan;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanInfoDto {
	private int planId;
	private String planTitle;
	private Date startDate;
	private Date endDate;
	private String memberId;
	private String memberName;
	private int sidoCode;
	private int gugunCode;
	private String sidoName;
	private String gugunName;
	
	public static PlanInfoDto of(Plan plan) {
		return PlanInfoDto.builder()
				.planId(plan.getPlanId())
				.planTitle(plan.getPlanTitle())
				.startDate(plan.getStartDate())
				.endDate(plan.getEndDate())
				.memberId(plan.getMember().getMemberId())
				.memberName(plan.getMember().getMemberName())
				.sidoCode(plan.getSido().getSidoCode())
				.sidoName(plan.getSido().getSidoName())
				.gugunCode(plan.getGugun().getGugunCode())
				.gugunName(plan.getGugun().getGugunName())
				.build();
	}
}
