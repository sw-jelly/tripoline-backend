package com.ssafy.tripoline.plan.model.service;

import java.util.List;

import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetailUpdateParam;
import com.ssafy.tripoline.plan.model.dto.PlanListDto;
import com.ssafy.tripoline.plan.model.dto.PlanRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanUpdateParam;

public interface PlanService {
	void createPlan(PlanRegistParam planParam);

	void createPlanDetail(PlanDetailRegistParam planDetailParam);
	
	Plan searchPlanById(int planId);
	
	PlanDetail searchPlanDetailById(int planDetailId);
	
	List<Plan> searchAllPlans();
	
	List<PlanDetail> searchAllPlanDetails();

	List<PlanDetail> searchPlanDetailsByPlanId(int planId);

	List<PlanListDto> searchPlansByMemberId(String memberId);

	boolean canWriteReview(int planId);

	void updatePlan(PlanUpdateParam planParam);

	void updatePlanDetail(PlanDetailUpdateParam planDetailParam);

	void deletePlan(int planId);

	void deletePlanDetail(int planDetailId);
}
