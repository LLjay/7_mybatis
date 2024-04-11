package com.kh.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	// 마이바티스
	
	public static SqlSession getSqlSession() {
		// mybatis-config.xml -> 읽어들이기
		// 해당 db와 접속된 SqlSession 객체 생성해서 반환하기
		
		SqlSession sqlSession = null;
//		여기에 담아서 return 하는 것
		
		// SqlSession 생성하기 위해서 SqlSessionFactory 객체 필요
		// SqlSessionFactory 생성하기 위해서 SqlSessionFactoryBuilder 필요
		
		// mybatis session 경로 넣어주는 것
		String resource = "/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//			sqlSession = sqlSessionFactory.openSession(false); // boolean autoCommit -> 자동 커밋 여부(true면 자동 커밋)
			sqlSession = new SqlSessionFactoryBuilder().build(inputStream).openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
//		원래 JDBC Connection 객체 만들고 하던 것들을 여기서 자동으로 다 해준다고 생각하면 됨
	}

}
