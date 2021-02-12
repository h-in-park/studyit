package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IEndstudyDAO
{
	// 종료된 스터디 조회
	public ArrayList<EndstudyDTO> list(@Param("studycode") String studycode, @Param("id")String id);
	
	// 종료된 스터디 요일 조회
	public String weekday(String studycode);
	
	// 스터디 리더의 아이디 확인 
	public String leaderCheck();
	
	// 내가 받은 점수-> 등급으로(a,b,c,d,e),parti_code 조회
	public String grade(@Param("parti_code") String parti_code);
	
	// 스터디 평가점수 입력
	public int studyassess(EndstudyDTO dto);
	
	// 스터디 평가했는지 확인
	public int saCheck(@Param("parti_code") String parti_code);
	
	// 스터디 후기 작성했는지 확인 
	public int reviewCheck(@Param("parti_code") String parti_code);
}
