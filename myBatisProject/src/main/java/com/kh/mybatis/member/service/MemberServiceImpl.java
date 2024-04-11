package com.kh.mybatis.member.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService{
	
	private MemberDao mDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
//		아까 inputStream 이용해서 가져온 sqlsession
		
		int result = mDao.insertMember(sqlSession, m);
//		Connection 객체 대신 sqlSession이 그걸 만들어줄 거니까 sqlSession을 넘길 것
		
//		수동 트랜잭션 하겠다고 설정값을 JDBC로 해놨기 때문에 트랜잭션은 해줘야 함
//		commit, close 등은 다 sqlSession에 메소드로 들어있음
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return result;
	}
	
//	앞으로는 직접 Connection 등을 구현하지 않고 SqlSession 객체에 맡길 것
//	그 객체는 mybatis가 만들어줄 것

	@Override
	public Member loginMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		Member loginUser = mDao.loginMember(sqlSession, m);
		
		sqlSession.close();
		return loginUser;
	}
	

	
	
}
