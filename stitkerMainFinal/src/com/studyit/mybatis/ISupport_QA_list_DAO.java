package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface ISupport_QA_list_DAO
{
	// 1:1 질문 리스트(관리자)
	public ArrayList<Support_QA_list_DTO> listAdmin(@Param("start")int start, @Param("end")int end);
	
	// 1:1 질문 리스트(회원)
	public ArrayList<Support_QA_list_DTO> listMember(@Param("id")String id, @Param("start")int start, @Param("end")int end);
	
	// 1:1 질문 상세페이지	
	public Support_QA_list_DTO detail(String ask_code);
	
	// 1:1 답변해야하는 게시물 갯수 
	public int countA();
	
	// 1:1 질문 등록
	public int insertQ(Support_QA_list_DTO dto);
	
	// 1:1 답변 등록
	public int insertA(Support_QA_list_DTO dto);
	
	// id로 user_code 가져오기
	public String findUserCode(String id);
	
	// 1:1 답변 수정하기
	public int modifyQ(Support_QA_list_DTO dto);
	
	// 글 가져오기
	public Support_QA_list_DTO searchCode(String ask_code);
	
	// 1:1 질문 삭제
	public int deleteQ(String ask_code);
	
	// 총 게시물 갯수(관리자 로그인)
	public int countAdmin();
	
	// 총 게시물 갯수(일반회원)
	public int countMember(@Param("id")String id);
	
}
