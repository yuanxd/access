package com.xwinter.study.access;

import java.util.HashMap;
import java.util.Map;

import com.xwinter.study.access.entity.PageEntity;

/**
 * 权限管理，用于缓存权限扫描结果
 * 
 * @author 袁晓冬
 * 
 */
public class PermissionManager {
	/** 菜单缓存 */
	private final Map<String, PageEntity> menuMap = new HashMap<String, PageEntity>();

	private final Map<String, PageEntity> codeMap = new HashMap<String, PageEntity>();

	private static PermissionManager instance = new PermissionManager();

	/**
	 * 获取单例对象
	 * 
	 * @return
	 */
	public static PermissionManager getInstance() {
		return instance;
	}

	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	public Map<String, PageEntity> getMenuMap() {
		return menuMap;
	}

	/**
	 * 根据主键获取权限
	 * 
	 * @param key
	 * @return
	 */
	public PageEntity getPage(String key) {
		return menuMap.get(key);
	}

	/**
	 * 根据主键获取权限
	 * 
	 * @param key
	 * @return
	 */
	public PageEntity getPageByCode(String code) {
		return codeMap.get(code);
	}

	/**
	 * 添加菜单
	 * 
	 * @param key
	 * @param page
	 * @return
	 */
	public boolean addPage(String key, PageEntity page) {
		if (menuMap.containsKey(key)) {
			return true;
		}
		if (codeMap.containsKey(page.getCode())) {
			return true;
		}
		menuMap.put(key, page);
		codeMap.put(page.getCode(), page);
		return false;
	}
}
