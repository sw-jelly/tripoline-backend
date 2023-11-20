package com.ssafy.tripoline.plan.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.plan.model.dto.Plan.Plan;
import com.ssafy.tripoline.plan.model.dto.Plan.PlanParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetail.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetail.PlanDetailParam;

@Mapper
public interface PlanDao {
	int createPlan(PlanParam planParam) throws SQLException;

	int createPlanDetail(PlanDetailParam planDetail) throws SQLException;
	
	Plan searchPlanById(int planId) throws SQLException;
	
	PlanDetail searchPlanDetailById(int planDetailId) throws SQLException;
	
	List<Plan> searchAllPlans() throws SQLException;
	
	List<PlanDetail> searchAllPlanDetails() throws SQLException;

	List<PlanDetail> searchPlanDetailsByPlanId(int planId) throws SQLException;

	List<Plan> searchPlansByMemberId(String memberId) throws SQLException;

	boolean canWriteReview(int planId) throws SQLException;

	void updatePlan(PlanParam planParam) throws SQLException;

	void updatePlanDetail(PlanDetailParam planDatailParam) throws SQLException;

	void deletePlan(int planId) throws SQLException;

	void deletePlanDetail(int planDetailId) throws SQLException;
}
