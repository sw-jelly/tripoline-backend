package com.ssafy.tripoline.member.model.service;

import com.ssafy.tripoline.member.model.dto.Member;

public interface MemberService {

	void regist(Member member);

	void update(Member member);

	void remove(String memberId);

	int search(String memberId);

	Member login(String memberId, String password);

	String findPassword(String memberId, String memberPhone);

	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	Member userInfo(String userId) throws Exception;

}
