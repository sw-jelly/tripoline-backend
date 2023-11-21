package com.ssafy.tripoline.plan.model.dto.PlanDetail;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanDetailListDto {
	private int planDetailId;
	private int planId;
	private int contentId;
	private Date visitDate;
	private int visitOrder;
	private String title;
	private String tel;
	private String addr1;
	private String firstImage;
	private String memo;
	private double latitude;
	private double longitude;
	
	
	public static PlanDetailListDto of(PlanDetail planDetail) {
		return PlanDetailListDto.builder()
				.planDetailId(planDetail.getPlanDetailId())
				.planId(planDetail.getPlan().getPlanId())
				.contentId(planDetail.getAttraction().getContentId())
				.visitDate(planDetail.getVisitDate())
				.visitOrder(planDetail.getVisitOrder())
				.title(planDetail.getAttraction().getTitle())
				.tel(planDetail.getAttraction().getTel())
				.addr1(planDetail.getAttraction().getAddr1())
				.firstImage(planDetail.getAttraction().getFirstImage())
				.memo(planDetail.getMemo())
				.latitude(planDetail.getAttraction().getLatitude())
				.longitude(planDetail.getAttraction().getLongitude())
				.build();
	}
}
