package com.ssafy.tripoline.plan.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetailUpdateParam;
import com.ssafy.tripoline.plan.model.dto.PlanRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanUpdateParam;
import com.ssafy.tripoline.plan.model.service.PlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/planRest")
@Api(value="여행 계획을 위한 Rest-ful API", description = "Plan 정보")
@CrossOrigin(origins= {"*"})
@Slf4j
public class PlanRestController {
	
	private PlanService planService;
	
	public PlanRestController(PlanService planService) {
		this.planService = planService;
	}

	private static final String SUCCESS = "success";
	
	@ApiOperation(value = "새 계획 등록하기")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/plan")
	public ResponseEntity<?> registPlan(@RequestBody PlanRegistParam planParam) {
		log.debug("Plan.regist.............................planParam : {}", planParam);
		planService.createPlan(planParam);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "새 여행 계획 세부 사항 등록하기")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/planDetail")
	public ResponseEntity<?> registPlanDetail(@RequestBody PlanDetailRegistParam planDetailParam) {
		log.debug("PlanDetail.regist.............................planDetailParam : {}", planDetailParam);
		planService.createPlanDetail(planDetailParam);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "planId로 여행 계획 가져오기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/plan/{planId}")
	public ResponseEntity<?> getPlanById(@PathVariable int planId) {
		log.debug("Plan.searchPlanById.............................planId : {}", planId);
		Plan plan = planService.searchPlanById(planId);
		
		if (plan != null) {
			return new ResponseEntity<Plan>(plan, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "planDetailId로 여행 계획 세부사항 가져오기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/planDetail/{planDetailId}")
	public ResponseEntity<?> getPlanDetailById(@PathVariable int planDetailId) {
		log.debug("Plan.searchPlanDetailById.............................planDetailId : {}", planDetailId);
		PlanDetail planDetail = planService.searchPlanDetailById(planDetailId);

		if (planDetail != null) {
			return new ResponseEntity<PlanDetail>(planDetail, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "모든 여행 계획 목록 보기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/plan")
	public ResponseEntity<?> getAllPlans() {
		log.debug("plan.searchAll");
		List<Plan> plans = planService.searchAllPlans();

		if (plans != null && !plans.isEmpty()) {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "모든 여행 계획 세부사항 보기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/planDetail")
	public ResponseEntity<?> getAllPlanDetails() {
		log.debug("plan.searchAllPlanDetails");
		List<PlanDetail> planDetails = planService.searchAllPlanDetails();
		
		if (planDetails != null && !planDetails.isEmpty()) {
			return new ResponseEntity<List<PlanDetail>>(planDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "여행 계획에 대한 여행 세부 사항 보기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/planDetail/plan/{planId}")
	public ResponseEntity<?> getPlanDetails(@PathVariable int planId) {
		log.debug("plan.searchPlanDetailsByPlanId..............planId : {}", planId);
		List<PlanDetail> planDetails = planService.searchPlanDetailsByPlanId(planId);
		
		if (planDetails != null && !planDetails.isEmpty()) {
			return new ResponseEntity<List<PlanDetail>>(planDetails, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "회원에 대한 모든 여행 계획 보기")
	@ApiResponse(code = 200, message = "success")
	@GetMapping("/plan/member/{memberId}")
	public ResponseEntity<?> getAllMemberPlans(@PathVariable String memberId) {
		log.debug("plan.searchPlansByMemberId..............memberId : {}", memberId);
		List<Plan> plans = planService.searchPlansByMemberId(memberId);
		
		if (plans != null && !plans.isEmpty()) {
			return new ResponseEntity<List<Plan>>(plans, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "계획 수정하기")
	@ApiResponse(code = 200, message = "success")
	@PutMapping("/plan")
	public ResponseEntity<String> update( @RequestBody PlanUpdateParam planParam) {
		log.debug("plan.update..............planParam : {}", planParam);
		planService.updatePlan(planParam);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "계획 세부사항 수정")
	@ApiResponse(code = 200, message = "success")
	@PutMapping("/planDetail")
	public ResponseEntity<String> update(@RequestBody PlanDetailUpdateParam planDetailParam) {
		log.debug("plan.update..............planDetailId : {}", planDetailParam);
		log.debug("plan.update..............planDetailParam : {}", planDetailParam);
		planService.updatePlanDetail(planDetailParam);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "계획 삭제하기")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> removePlanById(@PathVariable int planId) {
		log.debug("plan.removePlanById..............planId : {}", planId);
		planService.deletePlan(planId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "계획 세부 사항 삭제하기")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/planDetail/{planDetailId}")
	public ResponseEntity<String> removePlanDetailById(@PathVariable int planDetailId) {
		log.debug("plan.removePlanDetailById..............planDetailId : {}", planDetailId);
		planService.deletePlanDetail(planDetailId);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		e.printStackTrace();
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type", "application/json;charset=UTF-8");
		String errorMsg = "";
		if (e instanceof TripolineException) {
			errorMsg = e.getMessage();
		} else {
			errorMsg = "처리 중 오류 발생";
		}
		return new ResponseEntity<String>(errorMsg, resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
}
