/*=====================================
	LoginDTO.java
	- 로그인 관련 속성 자료형 클래스
=====================================*/

package com.studyit.mybatis;

public class LoginDTO
{
	private String code, id, pw;

	// getter / setter 구성
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}
}
