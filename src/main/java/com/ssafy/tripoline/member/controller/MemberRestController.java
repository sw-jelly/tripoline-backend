package com.ssafy.tripoline.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import com.ssafy.tripoline.member.model.dto.FindRequest;
import com.ssafy.tripoline.member.model.dto.LoginRequest;
import com.ssafy.tripoline.member.model.dto.Member;
import com.ssafy.tripoline.member.model.dto.MemberException;
import com.ssafy.tripoline.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController // Controller내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/memberRest") // 요청하는 자원(Domain)명을 붙인다.
@Api(value = "Member Rest API", description = "Member API 정보")
@CrossOrigin(origins = { "*" }) // 다른 서버에서 Ajax 요청이 와도 서비스 되도록 설정
//기본적으로 같은 서버, 같은 context(webApp) 끼리만 사용가능, 브라우저에서 prefly해주고 서버가 허용해줘야하는데 그것이 바로 crossOrigin
public class MemberRestController {
	private Logger logger = LoggerFactory.getLogger(MemberRestController.class);
	
	private MemberService memberService;

	private static final String SUCCESS = "success";
	
	private static final String Exist = "존재하는 사용자입니다";

	public MemberRestController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "사용자의 정보로 비밀번호 조회", notes = "사용자의 정보(아이디와 연락처)를 이용해 비밀번호를 찾아주는 API")
	@PostMapping("/find")
	public ResponseEntity<?> findPassword(@RequestBody FindRequest findRequest) {
		String password="";
		logger.debug("findPassword............ memberId:{}", findRequest.getMemberId());
		int find = memberService.search(findRequest.getMemberId());
		
		if (find == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		try {
			password = memberService.findPassword(findRequest.getMemberId(), findRequest.getMemberPhone());
			if(password==null)
				return new ResponseEntity<String>("사용자 정보가 옳바르지 않습니다", HttpStatus.UNAUTHORIZED);
		} catch (MemberException e) {
			return new ResponseEntity<String>("사용자 정보가 옳바르지 않습니다", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("사용자 비밀번호는" + password +"입니다",HttpStatus.OK);


	}

	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "meberId로 해당하는 유저가 존재하는지를 조회", notes = "사용자아이디(memberId)로 해당하는 사용자 정보가 있는지 조회")
	@GetMapping("/{memberId}")
	public ResponseEntity<?> search(@PathVariable String memberId) {
		logger.debug("member.search............ memberId:{}", memberId);
		int find = memberService.search(memberId);
		logger.debug("member.search............ find:{}", find);

		if (find != 0) {
			return new ResponseEntity<String>(Exist,HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "로그아웃 기능", notes = "로그아웃 처리하여 사용자의 세션정보를 초기화하는 API")
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>("로그아웃 처리 완료",HttpStatus.OK);
	}

	
	@ApiOperation(value="사용자 정보 등록", notes="사용자의 회원가입 요청 처리.")
	@ApiResponse(code = 200, message="success")
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody Member member){
		logger.debug("Member.regist.............. member:{}",member);
		memberService.regist(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 탈퇴", notes="사용자 정보를 DB에서 삭제 한다.")
	@ApiResponse(code = 200, message="success")
	@DeleteMapping("/{memberId}")
	public ResponseEntity<String> remove(@PathVariable String memberId){
		logger.debug("Member.delete.............. memberId:{}",memberId);
		memberService.remove(memberId);
		return new ResponseEntity<String>("사용자 삭제 완료", HttpStatus.OK);
	}
		

	@ApiOperation(value="사용자 정보 수정", notes="사용자 정보를 수정 한다.")
	@ApiResponse(code = 200, message="success")
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Member member){
		logger.debug("Member.update.............. member:{}",member);
		memberService.update(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value="회원 로그인", notes="사용자의 로그인 요청 처리.")
	@ApiResponse(code = 200, message="success")
	@PostMapping("/{memberId}")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		    String memberId = loginRequest.getMemberId();
		    String memberPassword = loginRequest.getMemberPassword();

		    logger.debug("Member.login.............. memberId: {}", memberId);

		    int find = memberService.search(memberId);

		    if (find == 0) {
		        return new ResponseEntity<String>("사용자 정보가 올바르지 않습니다", HttpStatus.UNAUTHORIZED);
		    }

		    try {
		        Member member = memberService.login(memberId, memberPassword);
		        if(member != null)
		            return new ResponseEntity<Member>(member, HttpStatus.OK);


		    } catch (Exception e) {
		        return new ResponseEntity<String>("로그인 실패", HttpStatus.UNAUTHORIZED);
		    }
		    
	        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		e.printStackTrace();
		logger.error("Member.error...............msg:{}", e.getMessage());

		// error메세지가 한글인 경우 깨지므로 한글 처리를 위한 설정
		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type", "application/json;charset=UTF-8");
		String errorMsg = "";
		if (e instanceof MemberException) {
			errorMsg = e.getMessage();
		} else {
			errorMsg = "처리 중 오류 발생";
		}
		return new ResponseEntity<String>(e.getMessage(), resHeader, HttpStatus.FAILED_DEPENDENCY);
	}
}
