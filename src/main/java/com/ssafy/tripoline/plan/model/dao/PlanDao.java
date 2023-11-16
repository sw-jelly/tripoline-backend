package com.ssafy.tripoline.plan.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailParam;
import com.ssafy.tripoline.plan.model.dto.PlanParam;

@Mapper
public interface PlanDao {
	void createPlan(PlanParam psd) throws SQLException;

	void createPlanDetail(PlanDetailParam planDetail) throws SQLException;
	
	Plan searchPlanById(int planId) throws SQLException;
	
	PlanDetail searchPlanDetailById(int planDetailId) throws SQLException;
	
	List<Plan> searchAllPlans() throws SQLException;
	
	List<PlanDetail> searchAllPlanDetails() throws SQLException;

	List<PlanDetail> searchPlanDetailsByPlanId(int planId) throws SQLException;

	List<Plan> searchPlansByMemberId(String memberId) throws SQLException;

	boolean canWriteReview(int planId) throws SQLException;

	void updatePlan(Map<String, Object> paramMap) throws SQLException;

	void updatePlanDetail(Map<String, Object> paramMap) throws SQLException;

	void deletePlan(int planId) throws SQLException;

	void deletePlanDetail(int planDetailId) throws SQLException;
}
