/*======================================
	IFreeBoardDAO.java
	- 인터페이스
	- 자유게시판 관련 메소드 정의
======================================*/

package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface IFreeBoardDAO
{
	// 자유게시판 리스트 출력
	public ArrayList<FreeBoardDTO> freeBoardList();
	
	// 자유게시판 리스트 출력
	public ArrayList<FreeBoardDTO> list(@Param("start") int start, @Param("end") int end);
	
	// 자유게시판 리스트 출력
	public ArrayList<FreeBoardDTO> freeBoardList_hit();
	
	// 자유게시판 리스트 출력
	public ArrayList<FreeBoardDTO> freeBoardList_rec();
	
	// 자유게시판 조회수 업데이트
	public int hitcount(@Param("post_code") String post_code);
	
	// 자유게시판 게시글 상세
	public FreeBoardDTO freeBoard_Detail(@Param("post_code") String post_code);
	
	// 자유게시판 게시글 댓글
	public ArrayList<FreeBoardCmtDTO> comment(@Param("post_code") String post_code);
	
	// 자유게시판 게시글 댓글 수
	public int commentCnt(@Param("post_code") String post_code);
	
	// 게시글 추천
	public int rec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시글 비추천
	public int notrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 자유게시판 게시글 작성
	public int freeBoardInsert(FreeBoardDTO post);
	
	// 자유게시판 댓글 작성
	public int addComment(FreeBoardCmtDTO comment);
	
	// 자유게시판 게시물 수정
	public int freeBoardUpdate(@Param("post_code") String post_code, @Param("title") String title, @Param("content") String content);
	
	// 자유게시판 게시물 삭제
	public int freeBoardDelete(@Param("post_code") String post_code);
	
	// 자유게시판 댓글 삭제
	public int deleteComment(@Param("comment_code") String comment_code);
	
	// 자유게시판 댓글 수정
	public int updateComment(@Param("comment_code") String comment_code, @Param("comments") String comments);
	
	// 게시물 추천 여부 체크
	public int recCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시물 신고 여부 체크
	public int rptCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 전체 데이터 갯수 구하기
	public int count();
	
	// 게시물 신고
	public int insertReport(HashMap<String, String> param);
	
	// 검색 결과 데이터 갯수 구하기
	public int getDataCount(@Param("searchKey") String searchKey, @Param("searchValue") String searchValue);
	
	// 검색 페이지
	public ArrayList<InformDTO> getList(BoardListDTO dto);
	
}

