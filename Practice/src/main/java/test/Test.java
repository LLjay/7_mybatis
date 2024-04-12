package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
<MemberInsertController.java>
//회원 페이지 컨트롤러

// 회원가입 페이지 주소는 kh/member.join이나 이 서블릿의 주소는 insert.me 이므로 요청을 받아주지 못함
// /member.join으로 바꿀 것
@WebServlet("/member.join")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
  /**
   * @see HttpServlet#HttpServlet()
   */
  public MemberInsertController() {
      super();
      // TODO Auto-generated constructor stub
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST이므로 인코딩 해줘야 함
		// request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); 
		String userPwd = request.getParameter("userPwd"); 
		String userName = request.getParameter("userName"); 
		String phone = request.getParameter("phone"); 
		String email = request.getParameter("email");
		String address = request.getParameter("address"); 

		// 전달 파라미터는 email이 있고 interest가 없는데 email을 안 받아줘서?
		
		Member m = new Member(
				userId,
				userPwd,
				userName,
				phone,
				email,
				address
			);

		int result = new MemberService().insertMember(m);

		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원가입이 되었습니다.");

			// 보내줘야 하는 경로는 url이기 때문에 파일 이름만 쓰면 어느 경로로 찾아가야 하는지 모름
			// "main.jsp" -> request.getContextPath() + "main"
			// forward가 아니라 메인으로 보낼 것이기 때문에
			// response.sendRedirect(request.getContextPath() + "main");
			response.sendRedirect(request.getContextPath() + "main");

		} else {
			request.setAttribute("errorMsg", "회원가입 실패했습니다");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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