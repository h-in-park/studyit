/*===============
 ISeminarDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


public interface ISeminarDAO
{
	// 게시글 추가
	public int add(SeminarDTO seminar);
	
	// 게시글 리스트 출력
	public ArrayList<SeminarDTO> list();
	
	// 검색 페이지
	public ArrayList<SeminarDTO> getList(BoardListDTO dto);
	
	// 게시글 수 카운트
	public int count();
	
	// 글 수 카운트
	public int getDataCount(@Param("searchKey") String searchKey, @Param("searchCategory") String searchCategory
			, @Param("searchValue") String searchValue);
	
	// 게시글 수정
	public int modify(@Param("post_code") String post_code, @Param("title")String title
			  , @Param("content") String content, @Param("seminar_category_code") String seminar_category_code);
	
	// 게시글 삭제
	public int remove(SeminarDTO seminar);
	
	// 조회수 증가
	public int hitcounts(String post_code);
		
	// 게시글 추천
	public int rec(@Param("post_code") String post_code, @Param("user_code") String user_code);
		
	// 게시글 비추천
	public int notrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시글 상세페이지 출력
	public SeminarDTO detail(String post_code);
	
	// 신고 카테고리 가져오기
	public ArrayList<InterviewReportDTO> getReportCtg();
	
	// 신고 등록
	public int addReport(InterviewReportDTO dto);
	
	// 신고 여부 확인
	public int checkReport(@Param("post_code")String post_code, @Param("user_code")String user_code);
	
	// 추천, 비추천 여부 확인
	public int checkRec(@Param("post_code")String post_code, @Param("user_code")String user_code);
}
