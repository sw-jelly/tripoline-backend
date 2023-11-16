package com.ssafy.tripoline.plan.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetailUpdateParam;
import com.ssafy.tripoline.plan.model.dto.PlanRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanUpdateParam;

@Mapper
public interface PlanDao {
	void createPlan(PlanRegistParam planRegistParam) throws SQLException;

	void createPlanDetail(PlanDetailRegistParam planDetail) throws SQLException;
	
	Plan searchPlanById(int planId) throws SQLException;
	
	PlanDetail searchPlanDetailById(int planDetailId) throws SQLException;
	
	List<Plan> searchAllPlans() throws SQLException;
	
	List<PlanDetail> searchAllPlanDetails() throws SQLException;

	List<PlanDetail> searchPlanDetailsByPlanId(int planId) throws SQLException;

	List<Plan> searchPlansByMemberId(String memberId) throws SQLException;

	boolean canWriteReview(int planId) throws SQLException;

	void updatePlan(PlanUpdateParam planUpdateParam) throws SQLException;

	void updatePlanDetail(PlanDetailUpdateParam planDatailUpdateParam) throws SQLException;

	void deletePlan(int planId) throws SQLException;

	void deletePlanDetail(int planDetailId) throws SQLException;
}
