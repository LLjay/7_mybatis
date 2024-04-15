package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.Reply;
import com.kh.mybatis.board.service.BoardService;
import com.kh.mybatis.board.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		/*
		 * 스켈레톤 디자인 : 골격을 먼저 보여준 후 그 안의 데이터가 다 로딩 되면 보여주는 방식
			AJAX는 페이지의 일부분을 변경하고 싶을 때 사용하는 것
			처음 댓글은 페이지 요청했을 때 담아서 주면 됨, 기존처럼 댓글은 따로 AJAX로 받는 게 아님

			댓글도 같이 가져올 때 두 개를 가져와야 함
			컨트롤러에서 가져오거나 서비스에서 가져오거나
			트랜잭션이 필요 없는 것들은 서비스 단에서 분리해주는 게 좋음
			컨트롤러에서 서비스를 여러 번 호출하는 것은 전혀 상관 없음, 메소드는 여러 번 중복되어 사용될 수 있음
		 */
		BoardService bService = new BoardServiceImpl();
		// 조회수 증가 + 상세조회
		Board b = bService.increaseCount(boardNo);
		
		if (b != null) {
			ArrayList<Reply> list = bService.selectReplyList(boardNo);
			
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
