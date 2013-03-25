package com.xwinter.study.access;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面对象<br>
 * 用于菜单配置时显示菜单，暂不支持对其授权，请对其下级Function授权
 * 
 * @author 袁晓冬
 * 
 */
public class Page {
	private Page parentPage;

	private String name;

	private String code;

	/** 标记记录未使用 */
	private boolean used = false;

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	private Map<String, Function> funs = new HashMap<String, Function>();

	public void addFuns(String key, Function value) {
		this.funs.put(key, value);
		value.setPage(this);
	}

	public String getCode() {
		return code;
	}

	public Map<String, Function> getFuns() {
		return funs;
	}

	public String getName() {
		return name;
	}

	public Page getParentPage() {
		return parentPage;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentPage(Page parentPage) {
		this.parentPage = parentPage;
	}

	@Override
	public String toString() {
		return "Page [name=" + name + ", code=" + code + "]";
	}
}
