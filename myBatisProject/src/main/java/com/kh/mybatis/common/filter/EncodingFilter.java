package com.kh.mybatis.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
// 어떤 요청에 필터를 걸어야 할지 정해주는 것
// 전체를 다 필터를 걸면 오류 나기 십상
//  /* -> 이 context로 들어오는 모든 요청은 여길 거치겠다
//  /admin/* -> admin으로 들어오는 모든 요청은 이렇게 하겠다

public class EncodingFilter implements Filter { // Filter라는 인터페이스 구현
// filter 파일로 만들어야 함
    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// 필터 종료 시 해줄 작업
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest는 ServletRequest의 자식 객체
		
		// 요청 가로채기
		
		// 특정 조건에 따라 다른 필터나 서블릿으로 요청 전달
		request.setCharacterEncoding("UTF-8");
//		서블릿으로 가는 요청 request
	
		System.out.println("인코딩 필터 통과");
		chain.doFilter(request, response);
//		다음으로 계속 이동해라, 가던 길을 그대로 가라 -> 필터를 거친 후 원래 가야 할 서블릿으로 가는 것
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 초기화 작업
	}

}
