package com.xwinter.study.access;

import java.lang.reflect.Method;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;

import com.xwinter.study.access.entity.FunctionEntity;
import com.xwinter.study.access.entity.PageEntity;
import com.xwinter.study.annotation.Function;
import com.xwinter.study.annotation.Page;

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
	public static FunctionEntity getFunction(HandlerMethod hm) {
		PageEntity page = PermissionManager.getInstance().getPage(
				hm.getBean().getClass().getName());
		if (null == page) {
			return null;
		}
		return page.getFuns().get(hm.getMethod().toGenericString());
	}

	public AnnotationProcess() {
		this.scanner = new ClassPathScanningCandidateComponentProvider(false);
		this.scanner.addIncludeFilter(new AnnotationTypeFilter(Page.class));
	}

	/**
	 * 处理一个被Page注解的类
	 * 
	 * @param clazz
	 * @page 注解的class类
	 */
	private void dealClass(Class<?> clazz) {
		Page access = (Page) clazz.getAnnotation(Page.class);
		if (null == access) {
			return;
		}
		// 读取注解信息
		String code = access.code();
		String name = access.name();
		String url = access.url();
		String[] entries = access.entries();
		// 设置code默认值为class名首字母小写
		if (null == code || code.length() == 0) {
			code = clazz.getSimpleName();
			code = Character.toLowerCase(clazz.getSimpleName().charAt(0))
					+ clazz.getSimpleName().substring(1);
		}
		// 设置name属性为空时设置name等于code
		if (null == name || name.length() == 0) {
			name = code;
		}
		PageEntity page = new PageEntity();
		page.setCode(code);
		page.setName(name);
		page.setUrl(url);
		page.setEntries(entries);
		// 处理注解方法
		for (Method method : clazz.getDeclaredMethods()) {
			FunctionEntity functionEntity = null;
			Function function = (Function) method.getAnnotation(Function.class);
			if (null == function) {
				if (entries != null) {
					for (String entry : entries) {
						if (method.toGenericString().equals(entry)) {
							functionEntity = new FunctionEntity();
							functionEntity.setCode(page.getCode());
							break;
						}
					}
				} else {
					continue;
				}
			} else {
				functionEntity = new FunctionEntity();
				String funCode = function.code();
				// 方法编码未设置时，设置为方法名
				if (null == funCode || funCode.length() == 0) {
					funCode = method.getName();
				}
				functionEntity.setCode(funCode);
			}
			if (null != functionEntity) {
				page.addFuns(method.toGenericString(), functionEntity);
			}

		}

		if (PermissionManager.getInstance().addPage(clazz.getName(), page)) {
			throw new RuntimeException("duplicate page:" + page);
		}
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
					dealClass(clazz);
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
