package com.ssafy.tripoline.member.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public void saveRefreshToken(String memberId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", memberId);
		map.put("token", refreshToken);
		dao.saveRefreshToken(map);		
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberId", userId);
		map.put("token", null);
		dao.deleteRefreshToken(map);		
	}

	@Override
	public Member userInfo(String memberId) throws Exception {
		return dao.userInfo(memberId);
	}

	@Override
	public void saveProfilePicture(String memberId, String fileName, String filePath) throws Exception {
		
	        Map<String, Object> params = new HashMap<>();
	        params.put("memberId", memberId);
	        params.put("fileName", fileName);
	        params.put("filePath", filePath);
	        dao.insertProfilePicture(params);
	}

	@Override
	public void saveImage(String memberId, String file) throws Exception {
		dao.registImage(memberId, file);
	}


	
	


}
