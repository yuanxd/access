package com.xwinter.study.access;

public class Function {
	private String name;
	private String code;
	private Page page;

	public Page getPage() {
		return page;
	}

	@Override
	public String toString() {
		return "Function [name=" + name + ", code=" + code + ", page=" + page
				+ "]";
	}

	public void setPage(Page page) {
		this.page = page;
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
