package com.ssafy.tripoline.member.model.service;

import com.ssafy.tripoline.member.model.dto.Member;

public interface MemberService {

	void regist(Member member);

	void update(Member member);

	void remove(String memberId);

	int search(String memberId);

	Member login(String memberId, String password);

	String findPassword(String memberId, String memberPhone);

//	Member login(String id, String pass) ;
//	Member search(String id) ;
//	void regist(Member user) ;
//	void update(Member user) ;
//	void remove(String id) ;
}
