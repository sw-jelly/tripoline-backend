package com.ssafy.tripoline.member.model.dto;

import java.io.Serializable;
import java.sql.Date;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NonNull;

@Data
public class MemberInfo implements Serializable {
	@ApiParam(value = "회원 아이디", required = true)
	@NonNull // 필수속성 지정
	private String memberId;

	@ApiParam(value = "사용자 이름")
	private String memberName;

	@ApiParam(value = "사용자 이메일")
	private String memberEmail;

	@ApiParam(value = "사용자 성별")
	private char memberGender;

	@ApiParam(value = "사용자 연락처")
	private String memberPhone;
	
	@ApiParam(value = "사용자 사진")
	private String memberPhoto;

	@ApiParam(value = "사용자 권한")
	private int memberRole;

}
