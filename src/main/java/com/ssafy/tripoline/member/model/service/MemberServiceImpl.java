package com.ssafy.tripoline.member.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.tripoline.member.model.dao.MemberDao;
import com.ssafy.tripoline.member.model.dto.Member;
import com.ssafy.tripoline.member.model.dto.MemberException;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao dao;
	
	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public void regist(Member member) {
		try {
			int find = dao.search(member.getMemberId());
			if(find != 0)
				throw new MemberException("이미 등록된 회원입니다.");
			

			dao.regist(member);
		} catch (SQLException e) {
			throw new MemberException("회원 가입 중 오류 발생.");
		}
	}

	@Override
	public void update(Member member) {
		try {
			int find = dao.search(member.getMemberId());
			if(find== 0)
				throw new MemberException("존재하지 않는 회원입니다.");
			dao.update(member);
		} catch (SQLException e) {
			throw new MemberException("회원 정보 수정 중 오류 발생.");
		}
	}

	@Override
	public void remove(String memberId) {
		try {
			dao.remove(memberId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException("멤버 정보 삭제 중 오류 발생");
		}		
	}

	@Override
	public int search(String memberId) {
		try {
			int find = dao.search(memberId);
			if(find == 0) {
				throw new MemberException("해당 회원은 존재하지 않습니다.");
			}
			return 1;
		} catch (SQLException e) {
			throw new MemberException("회원 조회 중 오류 발생");
		}
	}

	@Override
	public Member login(String memberId, String memberPassword) {
		try {
			int find = dao.search(memberId);
			
			if(find == 0) {
				throw new MemberException("해당 회원은 조재하지 않습니다");
			}
			

				
			Member member = dao.login(memberId, memberPassword);
			if(member == null)
				throw new MemberException("회원 정보가 옳바르지 않습니다");
			
			
			
			
			return member;
		} catch (SQLException e) {
			throw new MemberException("회원 정보 조회 중 오류 발생");
		}
	}

	@Override
	public String findPassword(String memberId, String memberPhone) {
		String password = "";
		try {
			int find = dao.search(memberId);
			if(find == 0) {
				throw new MemberException("해당 회원은 조재하지 않습니다");
			}
			
			password = dao.findPassword(memberId, memberPhone);
		} catch (SQLException e) {
			throw new MemberException("비밀번호 조회 중 오류 발생");
		}
		return password;
	}


	
	


}
