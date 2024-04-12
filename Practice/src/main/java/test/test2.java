package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test2
 */

// 1. 요청 주소 변경
@WebServlet("/list.pd")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	/**
     	* @see HttpServlet#HttpServlet()
     	*/
    	public ItemController() {
        		super();
        		// TODO Auto-generated constructor stub
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Item> list = new ProductService().selectItemList();
		
		if (list.size() > 0) {
			// 2. 응답 방식을 forward가 아닌 ajax 방식으로 변경
			// 3. list 전송 시 형식이 json 형태가 되도록 변경
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
		} else {
			request.setAttribute("errorMsg", "상품목록 조회 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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