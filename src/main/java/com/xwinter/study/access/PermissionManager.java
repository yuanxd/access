package com.xwinter.study.access;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限管理
 * 
 * @author 袁晓冬
 * 
 */
public class PermissionManager {
	/** 菜单缓存 */
	private final Map<String, Page> menuMap = new HashMap<String, Page>();

	private final Map<String, Page> codeMap = new HashMap<String, Page>();

	private static PermissionManager instance = new PermissionManager();

	public static PermissionManager getInstance() {
		return instance;
	}

	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	public Map<String, Page> getMenumap() {
		return menuMap;
	}

	/**
	 * 根据主键获取权限
	 * 
	 * @param key
	 * @return
	 */
	public Page getPage(String key) {
		return menuMap.get(key);
	}

	/**
	 * 根据主键获取权限
	 * 
	 * @param key
	 * @return
	 */
	public Page getByCode(String code) {
		return codeMap.get(code);
	}

	/**
	 * 设置是否可用
	 * 
	 * @param code
	 */
	public void usePage(String code) {
		Page page = codeMap.get(code);
		if (null != page && !page.isUsed()) {
			page.setUsed(true);
		}
	}

	/**
	 * 添加菜单
	 * 
	 * @param key
	 * @param page
	 * @return
	 */
	public boolean addPage(String key, Page page) {
		boolean has = false;
		if (menuMap.containsKey(key)) {
			has = true;
		}
		if (codeMap.containsKey(page.getCode())) {
			has = true;
		}
		menuMap.put(key, page);
		codeMap.put(page.getCode(), page);
		return has;
	}
}
