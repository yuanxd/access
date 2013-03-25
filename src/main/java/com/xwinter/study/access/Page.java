package com.xwinter.study.access;

import java.util.HashMap;
import java.util.Map;

public class Page {
	private String name;

	@Override
	public String toString() {
		return "Page [name=" + name + ", code=" + code + "]";
	}

	private String code;
	private Map<String, Function> funs = new HashMap<String, Function>();

	public Map<String, Function> getFuns() {
		return funs;
	}

	public void addFuns(String key, Function value) {
		this.funs.put(key, value);
		value.setPage(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
