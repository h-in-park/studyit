package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface ICancelstudyDAO
{
	// 취소된 스터디 조회
	public ArrayList<CancelstudyDTO> list(@Param("studycode") String studycode, @Param("id")String id);
	
	// 취소된 스터디 요일 조회
	public String weekday(String studycode);
	
	// 스터디 리더의 아이디 확인 
	public String leaderCheck();
	
}
