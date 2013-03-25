package com.xwinter.study.access;

/**
 * 菜单下功能对象<br>
 * 对此对象进行授权
 * 
 * @author 袁晓冬
 * 
 */
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
