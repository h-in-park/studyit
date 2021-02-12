/*=====================================
	IFindPwDAO.java
	- 인터페이스
	- 비밀번호 찾기 관련 메소드 정의
=====================================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IFindPwDAO
{
	// 임시비밀번호 발급
	public String rndPw(); 
	
	// 임시비밀번호 등록
	public int updatePw(@Param("userId") String userId, @Param("rndStr") String rndStr);
	
	// 비밀번호 찾기 질문 리스트
	public ArrayList<FindPwQueDTO> questionList();
	
	// 이메일로 찾기
	public FindPwQueDTO findPwByEmail(@Param("email") String email, @Param("userId") String userId);
	
	// 비밀번호 찾기 질문&답으로 찾기
	public FindPwQueDTO findPwByQna(@Param("quecode") String quecode, @Param("userAns") String userAns, @Param("userId") String userId);
	
	
}
