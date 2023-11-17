package com.ssafy.tripoline.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NonNull;

@Data
public class Member implements Serializable {
	@ApiParam(value = "회원 아이디", required = true)
//	@NonNull // 필수속성 지정
	private String memberId;

//	@NonNull // 필수속성 지정
	@ApiParam(value = "사용자 비밀번호", required = true)
	private String memberPassword;

	@ApiParam(value = "사용자 이름")
	private String memberName;

	@ApiParam(value = "사용자 이메일")
	private String memberEmail;

	@ApiParam(value = "사용자 거주지 도시 정보")
	private int sidoCode;

	@ApiParam(value = "사용자 거주지 구군 정보")
	private int gugunCode;

	@ApiParam(value = "사용자 프로필 이미지")
	private String memberPhoto;

	@ApiParam(value = "사용자 생년월일")
	private Date memberBirthdate;

	@ApiParam(value = "사용자 성별")
	private char memberGender;

	@ApiParam(value = "사용자 연락처")
	private String memberPhone;

	@ApiParam(value = "사용자 권한")
	private int memberRole;

//	@ApiParam(value = "refreshToken")
	private String refreshToken;
}
