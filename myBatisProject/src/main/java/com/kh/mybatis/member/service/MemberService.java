package com.kh.mybatis.member.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {
	// 구현체에 impl을 붙이기도 하고 service 객체에 impl을 붙여도 됨
	// service는 직접 사용하는 거니까
	// 추상 메소드를 가지고 있는
	
	// 나중에 spring으로 가면 잘 나눠줌
	
	// 인터페이스의 목적은 구현
	public abstract int insertMember(Member m);
	// 이 내용은 구현체가 정함
	// 이걸 가져다 쓰는 건 컨트롤러, 서비스의 인터페이스이므로
	// 
	
	public abstract Member loginMember(Member m);
}
