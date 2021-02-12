package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IBoard_report_list_DAO
{
	// 리스트 
	public ArrayList<Board_report_list_DTO> list(@Param("start2")int start2, @Param("end2")int end2);
	
	// 처리해야하는 신고게시물 개수
	public int countUntreat();
	
	// 모든 게시물 개수
	public int countAll();
}
