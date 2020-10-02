package com.hy.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;

public class MyFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest httpRequest=(HttpServletRequest) request;
			//HttpServletResponse httpResponse=(HttpServletResponse) response;
			String token = httpRequest.getHeader("token");
			System.err.println(token);
			chain.doFilter(request,response);
			
	}

}
