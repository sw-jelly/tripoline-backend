package com.ssafy.tripoline.plan.model.service;

import java.util.List;

import com.ssafy.tripoline.plan.model.dto.Plan.Plan;
import com.ssafy.tripoline.plan.model.dto.Plan.PlanInfoDto;
import com.ssafy.tripoline.plan.model.dto.Plan.PlanListDto;
import com.ssafy.tripoline.plan.model.dto.Plan.PlanParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetail.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetail.PlanDetailListDto;
import com.ssafy.tripoline.plan.model.dto.PlanDetail.PlanDetailParam;

public interface PlanService {
	Integer createPlan(PlanParam planParam);

	Integer createPlanDetail(PlanDetailParam planDetailParam);
	
	Integer savePlanDetail(PlanDetailParam planDetailParam);
	
	PlanInfoDto searchPlanById(int planId);
	
	PlanDetail searchPlanDetailById(int planDetailId);
	
	List<Plan> searchAllPlans();
	
	List<PlanDetail> searchAllPlanDetails();

	List<PlanDetailListDto> searchPlanDetailsByPlanId(int planId);

	List<PlanListDto> searchPlansByMemberId(String memberId);

	boolean canWriteReview(int planId);

	void updatePlan(PlanParam planParam);

	void updatePlanDetail(PlanDetailParam planDetailParam);

	void deletePlan(int planId);

	void deletePlanDetail(int planDetailId);
}
