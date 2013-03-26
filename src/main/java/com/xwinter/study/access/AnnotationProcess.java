package com.xwinter.study.access;

import java.lang.reflect.Method;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;

import com.xwinter.study.annotation.AccessFunc;
import com.xwinter.study.annotation.AccessPage;

/**
 * 注解解析与缓存
 * 
 * @author 袁晓冬
 * 
 */
public class AnnotationProcess {
	/** 类扫描 */
	private final ClassPathScanningCandidateComponentProvider scanner;

	/**
	 * 获取当前访问权限对象
	 * 
	 * @param hm
	 * @return
	 */
	public static Function getFunction(HandlerMethod hm) {
		Page page = PermissionManager.getInstance().getPage(
				hm.getBean().getClass().getName());
		if (null == page) {
			return null;
		}
		return page.getFuns().get(hm.getMethod().toGenericString());
	}

	public AnnotationProcess() {
		this.scanner = new ClassPathScanningCandidateComponentProvider(false);
		this.scanner
				.addIncludeFilter(new AnnotationTypeFilter(AccessPage.class));
	}

	/**
	 * 扫描包中权限相关注解并生成权限对象缓存
	 * 
	 * @param basePackages
	 * @return
	 */
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
					AccessPage access = (AccessPage) clazz
							.getAnnotation(AccessPage.class);
					Page menu = new Page();
					String code = access.code();
					String name = access.name();
					String url = access.url();
					if (null == code || code.length() == 0) {
						code = clazz.getSimpleName();
						code = Character.toLowerCase(clazz.getSimpleName()
								.charAt(0))
								+ clazz.getSimpleName().substring(1);
					}
					if (null == name || name.length() == 0) {
						name = code;
					}
					menu.setCode(code);
					menu.setName(name);
					menu.setUrl(url);
					for (Method method : clazz.getDeclaredMethods()) {
						AccessFunc mAccess = (AccessFunc) method
								.getAnnotation(AccessFunc.class);
						if (mAccess != null) {
							Function function = new Function();
							String fCode = mAccess.code();
							if (null == fCode || fCode.length() == 0) {
								fCode = method.getName();
							}
							String fName = mAccess.name();
							if (null == fName || fName.length() == 0) {
								fName = fCode;
							}
							function.setCode(fCode);
							function.setName(fName);
							menu.addFuns(method.toGenericString(), function);
						}
					}
					boolean addres = PermissionManager.getInstance().addPage(
							clazz.getName(), menu);
					if (addres) {
						throw new RuntimeException("duplicate page:" + menu);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			System.err.println(candidates.size());
		}
		buildCache();
		return count;
	}

	/**
	 * 整理缓存
	 */
	private void buildCache() {
	}
}
