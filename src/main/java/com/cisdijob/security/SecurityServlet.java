package com.cisdijob.security;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cisdijob.model.entity.User;





//Writer:zy
//Date:2015-05-07
public class SecurityServlet extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(true);

		User currentUser = (User) session.getAttribute("currentUser");
		String url = request.getRequestURI();
		if (currentUser == null) {
			// 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
			if (url != null && !url.equals("")&& url.indexOf("/SMS_AR/resources/") < 0
					&& url.indexOf("/SMS_AR/login/") < 0	
					&& url.indexOf("/SMS_AR/views/") < 0
					) {
				   response.sendRedirect(request.getContextPath() + "/login/index");
				return;
			}
		}
		arg2.doFilter(arg0, arg1);
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}