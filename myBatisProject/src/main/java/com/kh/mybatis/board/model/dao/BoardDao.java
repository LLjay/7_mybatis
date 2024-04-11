package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.vo.PageInfo;

public class BoardDao {
	
	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
//		지금은 전체 리스트 객체가 아니라 숫자 하나만 가져올 것
//	    동적으로 받아야 할 무언가가 없음
//		따라서 selectOne의 인자 하나짜리
//		resultType 원시타입을 써주려면 다른 별칭이 필요함, mybatis 홈페이지에 있음
//		어차피 결과값은 ResultSet으로 올 테니까 그 타입을 int로 변환해줘 라고 지정해주는 것
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi){
		// 마이바티스에서는 페이징 처리를 위해 rowBounds라는 클래스를 제공
		/*
		 * rowBounds에 제공해야 할 데이터 두 가지 offset, limit
		 * offset : 건너뛰고 조회할 게시글 수
		 * 
		 * currentPage : 1		1~5		 	 0
		 * currentPage : 2		6~10		 5
		 * currentPage : 3		11~15		 10
		 */
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
//		인피니티 스크롤 방식 구할 때 이런 식으로 많이 씀
//		몇 개를 건너뛰고 그 다음부터 몇 개
//		인피니티는 정렬한 것 마지막에서부터 몇 개로 씀
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
//		굳이 전달해야 할 값이 없다면 null로 넣어도 됨
//		이전 쿼리처럼 내가 직접 페이징 처리 해줄 필요 없이 가져오는 쿼리만 하면 됨
//		반환값이 List로 나오게 되어 있으므로 다운 캐스팅 해야 함
//		sqlSession.selectList(namespace 값, null, rowBounds);
		
	}

}
