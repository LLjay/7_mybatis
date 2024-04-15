package com.kh.mybatis.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/list.bo", "/search.bo"})
// 이 패턴들에 대해서는 이 필터를 걸겠다
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("로그인 필터 통과");
		
		// 사용자 로그인 여부 확인
//		request.getSession() -> HttpServletRequest 메소드기 때문에 이대로는 사용 X
//		response.sendRedirect() -> HttpServletResponse 메소드라서
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		if (httpRequest.getSession().getAttribute("loginUser") == null) {
			httpResponse.sendRedirect(httpRequest.getContextPath()); // 메인으로
		} else {
			chain.doFilter(request, response);	
		}
//		가려는 곳이 list인지 search인지 모름
//		errorMsg면 디스패쳐로 보내던가 메인으로 보내서 alert 띄우던가
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
