package com.ssafy.tripoline.plan.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.tripoline.TripolineException;
import com.ssafy.tripoline.plan.model.dao.PlanDao;
import com.ssafy.tripoline.plan.model.dto.Plan;
import com.ssafy.tripoline.plan.model.dto.PlanDetail;
import com.ssafy.tripoline.plan.model.dto.PlanDetailRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanDetailUpdateParam;
import com.ssafy.tripoline.plan.model.dto.PlanListDto;
import com.ssafy.tripoline.plan.model.dto.PlanRegistParam;
import com.ssafy.tripoline.plan.model.dto.PlanUpdateParam;

@Service
public class PlanServiceImpl implements PlanService {

    private PlanDao planDao;
    

    public PlanServiceImpl(PlanDao planDao) {
		super();
		this.planDao = planDao;
	}

	@Override
    public void createPlan(PlanRegistParam planParam) {
        try {
            planDao.createPlan(planParam);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("Plan 생성 중 오류 발생");
        }
    }

    @Override
    public void createPlanDetail(PlanDetailRegistParam planDetailParam) {
        try {
            planDao.createPlanDetail(planDetailParam);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("PlanDetail 생성 중 오류 발생");
        }
    }

    @Override
    public Plan searchPlanById(int planId) {
        try {
            return planDao.searchPlanById(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("Plan 조회 중 오류 발생");
        }
    }

    @Override
    public PlanDetail searchPlanDetailById(int planDetailId) {
        try {
            return planDao.searchPlanDetailById(planDetailId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("PlanDetail 조회 중 오류 발생");
        }
    }

    @Override
    public List<Plan> searchAllPlans() {
        try {
            return planDao.searchAllPlans();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("모든 Plan 조회 중 오류 발생");
        }
    }

    @Override
    public List<PlanDetail> searchAllPlanDetails() {
        try {
            return planDao.searchAllPlanDetails();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("모든 PlanDetail 조회 중 오류 발생");
        }
    }

    @Override
    public List<PlanDetail> searchPlanDetailsByPlanId(int planId) {
        try {
            return planDao.searchPlanDetailsByPlanId(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("Plan에 속한 PlanDetail 조회 중 오류 발생");
        }
    }

    @Override
    public List<PlanListDto> searchPlansByMemberId(String memberId) {
        try {
        	return planDao.searchPlansByMemberId(memberId)
        			.stream()
        			.map(plan -> PlanListDto.of(plan))
        			.collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("특정 회원의 Plan 조회 중 오류 발생");
        }
    }

    @Override
    public boolean canWriteReview(int planId) {
        try {
            return planDao.canWriteReview(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("리뷰 작성 가능 여부 확인 중 오류 발생");
        }
    }

    @Override
    public void updatePlan(PlanUpdateParam planParam) {
        try {
        	Plan find = planDao.searchPlanById(planParam.getPlanId());
        	if (find == null) throw new TripolineException("존재하지 않는 Plan입니다.");
            planDao.updatePlan(planParam);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("Plan 업데이트 중 오류 발생");
        }
    }

    @Override
    public void updatePlanDetail(PlanDetailUpdateParam planDetailParam) {
        try {
        	PlanDetail find = planDao.searchPlanDetailById(planDetailParam.getPlanDetailId());
        	if (find == null) throw new TripolineException("존재하지 않는 PlanDetail입니다.");
        	Map<String, Object> paramMap = new HashMap<String, Object>();
            planDao.updatePlanDetail(planDetailParam);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("PlanDetail 업데이트 중 오류 발생");
        }
    }

    @Override
    public void deletePlan(int planId) {
        try {
        	Plan find = planDao.searchPlanById(planId);
        	if (find == null) throw new TripolineException("존재하지 않는 Plan입니다.");
            planDao.deletePlan(planId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("Plan 삭제 중 오류 발생");
        }
    }

    @Override
    public void deletePlanDetail(int planDetailId) {
        try {
        	PlanDetail find = planDao.searchPlanDetailById(planDetailId);
        	if (find == null) throw new TripolineException("존재하지 않는 PlanDetail입니다.");
            planDao.deletePlanDetail(planDetailId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TripolineException("PlanDetail 삭제 중 오류 발생");
        }
    }
}
