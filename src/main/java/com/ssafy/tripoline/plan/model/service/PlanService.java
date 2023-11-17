package com.ssafy.tripoline.plan.model.service;

import java.util.List;

import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailParam;
import com.ssafy.tripoline.plan.model.dto.PlanListDto;
import com.ssafy.tripoline.plan.model.dto.PlanParam;

public interface PlanService {
	Integer createPlan(PlanParam planParam);

	Integer createPlanDetail(PlanDetailParam planDetailParam);
	
	Plan searchPlanById(int planId);
	
	PlanDetail searchPlanDetailById(int planDetailId);
	
	List<Plan> searchAllPlans();
	
	List<PlanDetail> searchAllPlanDetails();

	List<PlanDetail> searchPlanDetailsByPlanId(int planId);

	List<PlanListDto> searchPlansByMemberId(String memberId);

	boolean canWriteReview(int planId);

	void updatePlan(PlanParam planParam);

	void updatePlanDetail(PlanDetailParam planDetailParam);

	void deletePlan(int planId);

	void deletePlanDetail(int planDetailId);
}
