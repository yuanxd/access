package com.xwinter.study.access.entity;

/**
 * 菜单下功能对象<br>
 * 对此对象进行授权
 * 
 * @author 袁晓冬
 * 
 */
public class FunctionEntity {
	private String name;
	private String code;

	@Override
	public String toString() {
		return "Function [name=" + name + ", code=" + code + "]";
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
