package com.xwinter.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessPage {
	public String code() default "";

	public String name() default "";

	/**
	 * 访问url地址
	 * @return
	 */
	public String url() default "";

	/***
	 * 画面入口方法
	 * 
	 * @return
	 */
	public String mainPage() default "mainPage";
}
