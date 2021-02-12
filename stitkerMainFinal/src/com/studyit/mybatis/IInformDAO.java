/*===============
 IInformDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


public interface IInformDAO
{
	// 정보 게시판 글 추가
	public int add(InformDTO inform);
	
	// 정보 게시판 리스트 출력
	public ArrayList<InformDTO> list();
	
	//public ArrayList<InformDTO> getList(@Param("start") int start, @Param("end") int end);
	
	// 검색 페이지
	public ArrayList<InformDTO> getList(BoardListDTO dto);
	
	// 정보 게시판 글 수 카운트
	public int count();
	public int getDataCount(@Param("searchKey") String searchKey, @Param("searchCategory") String searchCategory
			,  @Param("searchValue") String searchValue);
	
	
	// 정보 게시판 글 수정
	public int modify(@Param("post_code") String post_code, @Param("title")String title
			  , @Param("content") String content, @Param("interest_mc_code") String interest_mc_code);
	
	// 정보 게시판 글 삭제
	public int remove(InformDTO inform);
	
	// 조회수 업데이트 
	public void hitcounts(String post_code);
	
	// 게시글 추천
	public int rec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시글 비추천
	public int notrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// public String interestMc(@Param("interest_mc") String interest_mc);
	
	// 정보 게시판 상세페이지 출력
	public InformDTO detail(String post_code);

	/*
	// 정보 게시판 리스트 키워드 검색 - 제목, 카테고리
	public ArrayList<InformDTO> informCtgTitleSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// 정보 게시판 리스트 키워드 검색 - 작성자, 카테고리
	public ArrayList<InformDTO> informCtgWriterSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// 정보 게시판 리스트 키워드 검색 - 내용, 카테고리
	public ArrayList<InformDTO> informCtgContentSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// 정보 게시판 리스트 키워드 검색 - 제목&내용, 카테고리
	public ArrayList<InformDTO> informCtgTnCSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);
	
	// 정보 게시판 리스트 키워드 검색 - 제목
	public ArrayList<InformDTO> informTitleSearch(@Param("searchValue") String searchValue);

	// 정보 게시판 리스트 키워드 검색 - 작성자
	public ArrayList<InformDTO> informWriterSearch(@Param("searchValue") String searchValue);

	// 정보 게시판 리스트 키워드 검색 - 내용
	public ArrayList<InformDTO> informContentSearch(@Param("searchValue") String searchValue);

	// 정보 게시판 리스트 키워드 검색 - 제목&내용
	public ArrayList<InformDTO> informTnCSearch(@Param("searchValue") String searchValue);
	
	// 정보 게시판 리스트 키워드 검색 - 카테고리
	public ArrayList<InformDTO> informCtgSearch(@Param("searchCategory") String searchCategory);
	*/
	
	// 신고 카테고리 가져오기
	public ArrayList<InterviewReportDTO> getReportCtg();
	
	// 신고 등록
	public int addReport(InterviewReportDTO dto);
	
	// 신고 여부 확인
	public int checkReport(@Param("post_code")String post_code, @Param("user_code")String user_code);
	
	// 추천, 비추천 여부 확인
	public int checkRec(@Param("post_code")String post_code, @Param("user_code")String user_code);

	
	
	
}
