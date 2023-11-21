package com.ssafy.tripoline.member.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tripoline.member.model.dto.Member;
import com.ssafy.tripoline.member.model.dto.MemberInfo;
import com.ssafy.tripoline.member.model.dto.MemberBean;

@Mapper
public interface MemberDao {

	void regist(Member member) throws SQLException; // 회원가입

	void update(Member member) throws SQLException; // 회원정보 수정

	void remove(String memberId) throws SQLException; // 회원탈퇴

	int search(String memeberId) throws SQLException; // 회원조회

	Member login(String memberId, String memberPassword) throws SQLException; // 회원 로그인

	String findPassword(String memberId, String memberPhone) throws SQLException; // 회원 비밀번호 찾기

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;

	Member userInfo(String userId) throws SQLException;

	void insertProfilePicture(Map<String, Object> params) throws SQLException;

	void registImage(String memberId, String imageurl) throws SQLException;
	
	int totalCount(MemberBean bean) throws SQLException;
	
	List<MemberInfo> searchMembers(MemberBean bean) throws SQLException;

}
