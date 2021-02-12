/*=====================================
	ILoginDAO.java
	- 인터페이스
	- 로그인 관련 메소드 정의
=====================================*/

package com.studyit.mybatis;

public interface ILoginDAO
{
	// 일반 사용자 로그인 
	public String userLogin(LoginDTO dto);
	
	// 관리자 로그인
	public String adminLogin(LoginDTO dto);
}
