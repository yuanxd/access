package com.xwinter.study.access.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面对象<br>
 * 用于菜单配置时显示菜单，暂不支持对其授权，请对其下级Function授权
 * 
 * @author 袁晓冬
 * 
 */
public class PageEntity {
	/** 菜单名称 */
	private String name;
	/** 菜单编码 */
	private String code;
	/** 功能链接 */
	private String url;
	/** 入口方法 */
	private String[] entries;

	/**
	 * 菜单下功能列表
	 */
	private Map<String, FunctionEntity> funs = new HashMap<String, FunctionEntity>();

	public void addFuns(String key, FunctionEntity value) {
		this.funs.put(key, value);
	}

	public String getCode() {
		return code;
	}

	public String[] getEntries() {
		return entries;
	}

	public Map<String, FunctionEntity> getFuns() {
		return funs;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEntries(String[] entries) {
		this.entries = entries;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page [name=" + name + ", code=" + code + "]";
	}
}
