package com.xwinter.study.access;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private String name;
	private String code;
	private List<Function> funs = new ArrayList<Function>();

	public List<Function> getFuns() {
		return funs;
	}

	public void addFuns(Function fun) {
		this.funs.add(fun);
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
