package com.xwinter.study.access;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xwinter.study.annotation.Access;

public class AccessInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod))
			return true;
		HandlerMethod hm = (HandlerMethod) handler;
		Access access = hm.getMethodAnnotation(Access.class);
		if (null == access) {
			return true;
		} else {
			// 从session中获取用户信息
			request.getSession().getAttribute("");
		}
		return true;
	}
}
