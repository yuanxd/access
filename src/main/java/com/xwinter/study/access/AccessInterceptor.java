package com.xwinter.study.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xwinter.study.access.entity.FunctionEntity;

/**
 * 权限拦截器
 * 
 * @author 袁晓冬
 * 
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {
	/** 权限检查 */
	private IAccessPermissionCheck permissionCheck;

	/** session中存储用户身份的key */
	private String sessionKey = "";

	/**
	 * spring mvc访问方法前处理权限认证<br>
	 * {@inheritDoc}
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod))
			return true;
		HandlerMethod hm = (HandlerMethod) handler;
		FunctionEntity function = AnnotationProcess.getFunction(hm);
		if (null == function) {
			return true;
		} else {
			// 从session中获取用户信息
			if (null != permissionCheck) {
				String toPage = permissionCheck.checkPermission(function,
						request.getSession().getAttribute(sessionKey));
				if (null == toPage || toPage.length() == 0) {
					return true;
				}
				response.sendRedirect(request.getSession().getServletContext().getContextPath()+toPage);
				return false;
			}
		}
		return true;
	}

	/**
	 * spring 注入
	 * 
	 * @param permissionCheck
	 */
	public void setPermissionCheck(IAccessPermissionCheck permissionCheck) {
		this.permissionCheck = permissionCheck;
	}

	/**
	 * spring 注入
	 * 
	 * @param sessionKey
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
