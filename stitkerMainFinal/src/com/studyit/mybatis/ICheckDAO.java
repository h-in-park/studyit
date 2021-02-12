package com.studyit.mybatis;

import org.apache.ibatis.annotations.Param;

public interface ICheckDAO {
	
	// 스터디 개설 자격 확인
	// 등급 확인
	public String checkRank(String user_code);
	
	// 참여중인 스터디 갯수 확인
	public String checkStudyCnt(String user_code);
	
	// 계정정지됐는지 확인
	public String checkSuspend(String user_code);
	
	// 스터디 참여 자격 확인
	// 이미 참여한 스터디인지 확인
	public int checkThisStudy(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 유저 등급 확인
	public int checkUserRank(String user_code);
	
	// 스터디 최소등급 확인
	public int checkStudyRank(String study_code);
}
