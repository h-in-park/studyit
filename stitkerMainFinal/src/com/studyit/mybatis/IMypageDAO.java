/*===================================
	IMypageDAO.java
	- 인터페이스
	- 마이페이지 관련 메소드 정의
===================================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IMypageDAO
{
	// 로그인 한 사용자의 내 정보 조회
	public MyInfoDTO myInfo(@Param("user_code") String user_code);
	
	public int updateInfo(MyInfoDTO myinfo);
	
	// 비밀번호 조회 
	public String myPassword(@Param("user_code") String user_code, @Param("password") String password);
	
	// 비밀번호 업데이트 
	public int updatePw(MyInfoDTO myinfo);
	
	// 내 등급 정보 조회
	public MyScoreDTO myscore(@Param("user_code") String user_code);
	
	// 탈퇴 카테고리 조회
	public ArrayList<WithdrawalCategoryDTO> withdrawCategory();
	
	// 탈퇴 액션 처리
	public int withdraw(WithdrawalInfoDTO dto);
	
	// 주민등록번호 조회
	public String mySsn(@Param("user_code") String user_code, @Param("ssn") String ssn);
}
