//package com.ssafy.tripoline.plan.model.dao;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.sql.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ssafy.tripoline.plan.model.dto.Plan;
//import com.ssafy.tripoline.plan.model.dto.PlanDetail;
//import com.ssafy.tripoline.plan.model.dto.PlanDetailParam;
//import com.ssafy.tripoline.plan.model.dto.PlanParam;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//@Transactional // 테스트 완료 후 rollback
//class PlanDaoTest {
//
//	@Autowired
//	private PlanDao dao;
//
//	@Test
//	public void daoTest() throws Exception {
//		assertNotNull(dao);
//		// 테스트 코드 작성
//	}
//
//	@Test
//	void testCreatePlan() throws Exception {
//		PlanParam plan = PlanParam.builder()
//				.memberId("gosjorb")
//				.planTitle("테스트 여행3")
//				.startDate(new Date(2023, 12, 1))
//				.endDate(new Date(2023, 12, 8))
//				.sidoCode(1)
//				.gugunCode(2)
//				.build();
//		
//		dao.createPlan(plan);
//	}
//
//	@Test
//	void testCreatePlanDetail() throws Exception {
//		PlanDetailParam planDetail = PlanDetailParam.builder()
//				.planId(1)
//				.contentId(126486)
//				.visitDate(new Date(2023, 12, 1))
//				.visitOrder(1)
//				.build();
//		dao.createPlanDetail(planDetail);
//	}
//
//	@Test
//	void testGetPlanById() throws Exception {
//		int id = 2;
//		Plan plan = dao.searchPlanById(id);
//		log.debug("--------------plan : {}", plan);
//		assertNotNull(plan);
//	}
//
//	@Test
//	void testGetPlanDetailById() throws Exception {
//		int id = 2;
//		PlanDetail planDetail = dao.searchPlanDetailById(id);
//		log.debug("--------------plan : {}", planDetail);
//		assertNotNull(planDetail);
//	}
//
//	@Test
//	void testSearchAllPlans() throws Exception {
//		List<Plan> plans = dao.searchAllPlans();
//		log.debug("--------------searchAllPlans : {}", plans);
//		assertNotNull(plans);
//	}
//
//	@Test
//	void testSearchAllPlanDetails() throws Exception {
//		List<PlanDetail> planDetails = dao.searchAllPlanDetails();
//		log.debug("--------------SearchAllPlanDetails : {}", planDetails);
//		assertNotNull(planDetails);
//	}
//
//	@Test
//	void testGetPlanDetails() throws Exception {
//		List<PlanDetail> planDetails = dao.searchPlanDetailsByPlanId(1);
//		log.debug("--------------planDetails : {}", planDetails);
//		assertNotNull(planDetails);
//	}
//
//	@Test
//	void testGetPlansByMemberId() throws Exception {
//		String id = "gosjorb";
//		List<Plan> plans = dao.searchPlansByMemberId(id);
//		log.debug("--------------plans : {}", plans);
//		assertNotNull(plans);
//	}
//
////	@Test
////	void testCanWriteReview() {
////		fail("Not yet implemented");
////	}
//
////	@Test
////	void testUpdatePlan() throws Exception {
////		Plan plan = dao.searchPlanById(2);
////		log.debug("plan : ", plan);
////		PlanRegistParam planParam = PlanRegistParam.of(plan);
////		planParam.setPlanTitle("난 이게 좋아");
////		
////		Map<String, Object> paramMap = new HashMap<>();
////		paramMap.put("planId", 2);
////		paramMap.put("planParam", planParam);
////		dao.updatePlan(paramMap);
////		log.debug("--------------updatePlan : {}",dao.searchPlanById(2));
////	}
////
////	@Test
////	void testUpdatePlanDetail() throws Exception {
////		PlanDetail planDetail = dao.searchPlanDetailById(10);
////		log.debug("planDetail : ", planDetail);
////		PlanDetailRegistParam planDetailParam = PlanDetailRegistParam.of(planDetail);
////		planDetailParam.setVisitOrder(2);
////		
////		Map<String, Object> paramMap = new HashMap<>();
////		paramMap.put("planDetailId", 1);
////		paramMap.put("planDetailParam", planDetailParam);
////		dao.updatePlanDetail(paramMap);
////		log.debug("--------------updatePlanDetail : {}", dao.searchPlanDetailById(2));
////	}
//
//	@Test
//	void testDeletePlan() throws Exception {
//		dao.deletePlan(1);
//	}
//
//	@Test
//	void testDeletePlanDetail() throws Exception {
//		dao.deletePlanDetail(1);
//	}
//
//}
