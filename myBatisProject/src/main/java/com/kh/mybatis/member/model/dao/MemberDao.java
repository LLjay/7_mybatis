package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {
	
	public int insertMember(SqlSession sqlSession, Member m) {
		int result = sqlSession.insert("memberMapper.insertMember", m);
//		statement : 어떤 sql문을 수행할 것인지 key를 넘겨주는 것
//		Object parameter : 전송해줄 객체를 넘겨주는 것
//		memberMapper라는 namespace 안의 insertMember를 찾겠다
//		이걸 찾을 수 있는 이유는 mybatis-config 안에 어떤 매퍼 파일을 사용할 것인지 설정 해줬기 때문
		
//		mybatis가 sql문의 물음표도 다 채워줄 것
//		하지만 인공지능이 아니니 어떤 데이터를 넣을 것인지 알려줘야 함
//		물음표 대신 들어가는 건 getter setter 이름, getUserId -> userId로 적어주면 됨
//		대신 대소문자 잘 구분해야 mybatis가 식별
//		#{userId} 형태로 작성
//		-> 어떤 객체에서 가져와야 하는지를 알아야 이 값을 가져올 것, 그 객체 이름을 해당 id 가진 키에 parameterType으로 지정
//		여기서 Member라고 써줄 수 있는 이유는 typeAlias에 해당 패키지 Member를 별칭으로 Member라고 지칭 하겠다고 정해줬기 때문에

		return result;
	}
	
	public Member loginMember(SqlSession sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
//		ResultSet의 어떤 값이 어디로 잘 들어가야 하는지 매칭을 시켜줘야 함
//		resultMap은 따로 만들어야 함
//		해당 resultMap을 이용해서 어떤 객체를 담을지를 type에 기술
	}

}
