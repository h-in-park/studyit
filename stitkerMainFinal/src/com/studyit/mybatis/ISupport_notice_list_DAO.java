package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface ISupport_notice_list_DAO
{
	// 공지사항 리스트
	public ArrayList<Support_notice_list_DTO> list(@Param("start")int start, @Param("end")int end);
	
	// 조회수 증가
	public void addHitCount(@Param("notice_code")String notice_code);
	
	// 공지사항 상세페이지
	public Support_notice_list_DTO detail(@Param("notice_code")String notice_code);
	
	// 공지사항 등록
	public int insert(Support_notice_list_DTO dto);
	
	// 공지사항 수정
	public int modify(Support_notice_list_DTO dto);
	
	// 공지사항 삭제
	public int delete(String notice_code);
	
	// 공지사항 찾기
	public Support_notice_list_DTO searchCode(String notice_code);
	
	// 전체 게시물 개수 
	public int count();
}