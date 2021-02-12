/*======================
  INowstudyDAO.java
 ======================*/
package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface INowstudyDAO
{

	// 스터디 데이터 리스트 확인 
	public ArrayList<NowstudyDTO> list(@Param("studycode") String studycode, @Param("id")String id);
	
	// 스터디 요일 조회 
	public String weekday(String studycode);
	
	// 스터디 리더의 아이디 확인 
	public String leaderCheck();
	
	// 진행률 조회
	public double pgs(@Param("studycode") String studycode, @Param("id")String id);
	
	// 스터디원 내보내기 
	public int kick(@Param("kicked_parti_code") String kicked_parti_code, @Param("parti_code") String parti_code);
	
	// 내 참가코드 확인
	public String applyCode(@Param("studycode") String studycode, @Param("id")String id);
	
	// 내보내기 확인
	public int kickCheck(@Param("kicked_parti_code") String kicked_parti_code, @Param("parti_code") String parti_code);
	
	// 스터디 나가기
	public int studycancel(@Param("apply_code") String apply_code);
	
	
}
