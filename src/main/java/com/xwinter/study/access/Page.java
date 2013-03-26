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
	/** 记录ID，只有注册的菜单才会有 */
	private String id;
	/** 上级ID，只有注册的菜单才会有 */
	private String pid;
	/** 菜单名称 */
	private String name;
	/** 菜单编码 */
	private String code;
	/** 功能链接 */
	private String url;
	/** 菜单状态 */
	private int status;

	/** 标记记录未使用，注册的菜单为true */
	private boolean used = false;

	private Map<String, Function> funs = new HashMap<String, Function>();

	public void addFuns(String key, Function value) {
		this.funs.put(key, value);
	}

	public String getCode() {
		return code;
	}

	public Map<String, Function> getFuns() {
		return funs;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPid() {
		return pid;
	}

	public int getStatus() {
		return status;
	}

	public String getUrl() {
		return url;
	}

	public boolean isUsed() {
		return used;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "Page [name=" + name + ", code=" + code + "]";
	}
}
