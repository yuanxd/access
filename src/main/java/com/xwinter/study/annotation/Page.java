package com.xwinter.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单画面注解
 * @author winter
 *
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Page {

	/**
	 * 菜单名称，辅助菜单配置用
	 * @return
	 */
	public String name() default "";
	/**
	 * 菜单代码，权限控制关联
	 * @return
	 */
	public String code() ;

	/***
	 * 画面入口方法，可以有多个
	 * 
	 * @return
	 */
	public String[] entries() default "";
	/**
	 * 画面打开URL，辅助菜单配置用
	 * @return
	 */
	public String url() default "";
}
