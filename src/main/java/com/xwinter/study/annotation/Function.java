package com.xwinter.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单下功能配置
 * 
 * @author winter
 * 
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Function {
	/**
	 * 功能代码
	 * @return
	 */
	public String code() default "";
	/**
	 * 功能名称
	 * @return
	 */
	public String name() default "";
}
