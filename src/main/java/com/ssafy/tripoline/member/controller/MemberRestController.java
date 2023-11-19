package com.ssafy.tripoline.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tripoline.member.model.dto.FindRequest;
import com.ssafy.tripoline.member.model.dto.LoginRequest;
import com.ssafy.tripoline.member.model.dto.Member;
import com.ssafy.tripoline.member.model.dto.MemberException;
import com.ssafy.tripoline.member.model.service.MemberService;
import com.ssafy.tripoline.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.models.media.MediaType;
import lombok.RequiredArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController // Controller내에서 작성하는 모든 메서드에 기본적으로 @ResponseBody로 출력됨.
@RequestMapping("/memberRest") // 요청하는 자원(Domain)명을 붙인다.
@Api(value = "Member Rest API", description = "Member API 정보")
@CrossOrigin(origins = "*") // 허용할 도메인을 명시
//기본적으로 같은 서버, 같은 context(webApp) 끼리만 사용가능, 브라우저에서 prefly해주고 서버가 허용해줘야하는데 그것이 바로 crossOrigin
public class MemberRestController {
	private Logger logger = LoggerFactory.getLogger(MemberRestController.class);

	private MemberService memberService;

	private JWTUtil jwtUtil;

	private static final String SUCCESS = "success";

	private static final String Exist = "존재하는 사용자입니다";

    @Value("${upload.path}")
    private String uploadPath;
	
	public MemberRestController(MemberService memberService, JWTUtil jwtUtil) {
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}


	    
	@ApiOperation(value = "회원 로그인", notes = "사용자의 로그인 요청 처리.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
		String memberId = loginRequest.getMemberId();
		String memberPassword = loginRequest.getMemberPassword();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		logger.debug("Member.login.............. memberId: {}", memberId);
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			Member member = memberService.login(memberId, memberPassword);
			if (member != null) {
				String accessToken = jwtUtil.createAccessToken(memberId);
				String refreshToken = jwtUtil.createRefreshToken(memberId);
				logger.debug("access token : {}", accessToken);
				logger.debug("refresh token : {}", refreshToken);

				memberService.saveRefreshToken(memberId, refreshToken);

				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			logger.debug("로그인 에러 발생 : {}", e.getMessage());
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@ApiOperation(value="회원사진 업로드", notes="회원의 사진을 업로드")
	@PostMapping("/profileimage")
    @CrossOrigin(origins = "*") // 요청을 허용할 도메인 명시
	public ResponseEntity<String> handleProfilePictureUpload(
            @RequestParam("formData") MultipartFile file,
            @RequestParam("memberId") String memberId)  {
		
//        String userHome = System.getProperty("user.home");


        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please provide a file to upload");
        }

        try {
            // 사용자 ID를 기반으로 저장 디렉토리 생성
            String uploadDir = uploadPath + File.separator + memberId;
            File uploadDirectory = new File(uploadDir);

            
            if (!uploadDirectory.exists()) {
                if (uploadDirectory.mkdirs()) {
                    System.out.println("Directory created successfully");
                } else {
                    System.err.println("Failed to create directory");
                }
            }

            // 파일 이름 중복 방지를 위해 유니크한 파일명 생성
            String fileName = file.getOriginalFilename();

            // 파일 저장 경로 설정
            File filePath = new File(uploadDir, fileName);
            
            
            if (filePath.exists()) {
                if (filePath.delete()) {
                    System.out.println("Existing file deleted successfully");
                } else {
                    System.err.println("Failed to delete existing file");
                }
            }
           
            // 파일 복사
            file.transferTo(filePath);

            // 파일 URL 반환 또는 필요한 응답 처리
            String fileUrl = "/tripoline/assets/img/" + memberId + "/" + fileName;

            // 프로필 사진 정보를 데이터베이스에 저장
            
            try {
				memberService.saveProfilePicture(memberId, fileName, fileUrl);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	            return ResponseEntity.status(500).body("Failed to upload the file");
			}
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 중 오류가 발생하면 예외 처리
            return ResponseEntity.status(500).body("Failed to upload the file");
        }
    }

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{memberId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("memberId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String memberId,
			HttpServletRequest request) {
		logger.debug("memberId : {} ", memberId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				Member member = memberService.userInfo(memberId);
				resultMap.put("userInfo", member);
				status = HttpStatus.OK;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "meberId로 해당하는 유저가 존재하는지를 조회", notes = "사용자아이디(memberId)로 해당하는 사용자 정보가 있는지 조회")
	@GetMapping("/{memberId}")
	public ResponseEntity<?> search(@PathVariable String memberId) {
		logger.debug("member.search............ memberId:{}", memberId);
		int find = memberService.search(memberId);
		logger.debug("member.search............ find:{}", find);

		if (find != 0) {
			return new ResponseEntity<String>(Exist, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 충돌방지를 위해 pathVariable로 param을 전달, pathVariable로 주는 데이터는 생략 불가
	@ApiOperation(value = "사용자의 정보로 비밀번호 조회", notes = "사용자의 정보(아이디와 연락처)를 이용해 비밀번호를 찾아주는 API")
	@PostMapping("/find")
	public ResponseEntity<?> findPassword(@RequestBody FindRequest findRequest) {
		String password = "";
		logger.debug("findPassword............ memberId:{}", findRequest.getMemberId());
		int find = memberService.search(findRequest.getMemberId());

		if (find == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		try {
			password = memberService.findPassword(findRequest.getMemberId(), findRequest.getMemberPhone());
			if (password == null)
				return new ResponseEntity<String>("사용자 정보가 옳바르지 않습니다", HttpStatus.UNAUTHORIZED);
		} catch (MemberException e) {
			return new ResponseEntity<String>("사용자 정보가 옳바르지 않습니다", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>("사용자 비밀번호는" + password + "입니다", HttpStatus.OK);

	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{memberId}")
	public ResponseEntity<?> removeToken(
			@PathVariable("memberId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(memberId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody Member member, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		logger.debug("token : {}, member : {}", token, member);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(member.getMemberId()))) {
				String accessToken = jwtUtil.createAccessToken(member.getMemberId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "사용자 정보 등록", notes = "사용자의 회원가입 요청 처리.")
	@ApiResponse(code = 200, message = "success")
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody Member member) {
		logger.debug("Member.regist.............. member:{}", member);
		memberService.regist(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "사용자 정보를 DB에서 삭제 한다.")
	@ApiResponse(code = 200, message = "success")
	@DeleteMapping("/{memberId}")
	public ResponseEntity<String> remove(@PathVariable String memberId) {
		logger.debug("Member.delete.............. memberId:{}", memberId);
		memberService.remove(memberId);
		return new ResponseEntity<String>("사용자 삭제 완료", HttpStatus.OK);
	}

	@ApiOperation(value = "사용자 정보 수정", notes = "사용자 정보를 수정 한다.")
	@ApiResponse(code = 200, message = "success")
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Member member) {
		logger.debug("Member.update.............. member:{}", member);
		memberService.update(member);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
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
