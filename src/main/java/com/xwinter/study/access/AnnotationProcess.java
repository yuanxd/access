package com.xwinter.study.access;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;

import com.xwinter.study.annotation.Access;

public class AnnotationProcess {
	private final ClassPathScanningCandidateComponentProvider scanner;
	/** 菜单缓存 */
	private static final Map<Class<?>, Menu> menuMap = new HashMap<Class<?>, Menu>();

	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	public static Map<Class<?>, Menu> getMenumap() {
		return menuMap;
	}
	
	public static Function getFunction(HandlerMethod hm) {
		Menu m = menuMap.get(hm.getBean().getClass());
		return null;
	}

	public AnnotationProcess() {
		this.scanner = new ClassPathScanningCandidateComponentProvider(false);
		this.scanner.addIncludeFilter(new AnnotationTypeFilter(Access.class));
		// this.scanner
		// .addIncludeFilter(new AnnotationTypeFilter(Controller.class));
	}

	public int scan(String... basePackages) {
		Assert.notEmpty(basePackages,
				"At least one base package must be specified");
		int count = 0;
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = scanner
					.findCandidateComponents(basePackage);
			for (BeanDefinition bdf : candidates) {
				try {
					Class<?> clazz = Class.forName(bdf.getBeanClassName());
					Access access = (Access) clazz.getAnnotation(Access.class);
					Menu m = new Menu();
					m.setCode(access.code());
					m.setName(access.name());
					for (Method method : clazz.getDeclaredMethods()) {
						Access mAccess = (Access) method
								.getAnnotation(Access.class);
						if (mAccess != null) {
							Function function = new Function();
							function.setCode(mAccess.code());
							function.setName(mAccess.name());
							function.setMenu(m);
						}
					}
					menuMap.put(clazz, m);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			System.err.println(candidates.size());
		}
		return count;
	}
}
