package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IParticipant_report_list_DAO
{
	// 신고처리할 스터디원 리스트
	public ArrayList<Participant_report_list_DTO> list(@Param("start2")int start2, @Param("end2")int end2);
	
	// 신고처리할 스터디원 수
	public int countUntreat();
	
	// 모든 게시물 수 
	public int countAll();
	
}
