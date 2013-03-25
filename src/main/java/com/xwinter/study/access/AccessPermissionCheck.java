package com.xwinter.study.access;

/**
 * 权限控制接口，实际业务系统需要实现此接口并注入到{@link AccessInterceptor}
 * 
 * @author 袁晓冬
 * 
 */
public interface AccessPermissionCheck {
	/**
	 * 检查当前访问是否有权限
	 * 
	 * @param func
	 * @param sessionKey
	 * @return String 跳转页面
	 */
	String checkPermission(Function func, Object sessionKey);
}
