package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IUpcomingDAO
{
	// 스터디 데이터 리스트 확인 
	public ArrayList<UpcomingDTO> list(@Param("studycode") String studycode, @Param("id")String id);
	
	// 스터디 요일 조회 
	public String weekday(String studycode);
	
	// 스터디 리더의 아이디 확인 
	public String leaderCheck();
	
	// 진행률 조회
	public double pgs(@Param("studycode") String studycode, @Param("id")String id);
		
}
