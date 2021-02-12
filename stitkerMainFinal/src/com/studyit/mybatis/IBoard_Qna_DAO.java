/*========================================
	IBoard_QnA_DAO.java
	- 인터페이스
	- 질문 답변 게시판 관련 메소드 정의
========================================*/

package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface IBoard_Qna_DAO
{
	// 검색 관련 메소드 ----------------------------------------------------------------
	
	// Q&A 게시판 리스트 키워드 검색 - 전체
	public ArrayList<Board_Qna_DTO> qnaSearch(@Param("searchValue") String searchValue);
	
	// Q&A 게시판 리스트 키워드 검색 - 제목
	public ArrayList<Board_Qna_DTO> qnaTitleSearch(@Param("searchValue") String searchValue);
	
	// Q&A 게시판 리스트 키워드 검색 - 제목, 카테고리
	public ArrayList<Board_Qna_DTO> qnaCtgTitleSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// Q&A 게시판 리스트 키워드 검색 - 작성자, 카테고리
	public ArrayList<Board_Qna_DTO> qnaCtgWriterSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// Q&A 게시판 리스트 키워드 검색 - 내용, 카테고리
	public ArrayList<Board_Qna_DTO> qnaCtgContentSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);

	// Q&A 게시판 리스트 키워드 검색 - 제목&내용, 카테고리
	public ArrayList<Board_Qna_DTO> qnaCtgTnCSearch(@Param("searchValue") String searchValue
			, @Param("searchCategory") String searchCategory);
	


	// Q&A 게시판 리스트 키워드 검색 - 작성자
	public ArrayList<Board_Qna_DTO> qnaWriterSearch(@Param("searchValue") String searchValue);

	// Q&A 게시판 리스트 키워드 검색 - 내용
	public ArrayList<Board_Qna_DTO> qnaContentSearch(@Param("searchValue") String searchValue);

	// Q&A 게시판 리스트 키워드 검색 - 제목&내용
	public ArrayList<Board_Qna_DTO> qnaTnCSearch(@Param("searchValue") String searchValue);
	
	// Q&A 게시판 리스트 키워드 검색 - 카테고리
	public ArrayList<Board_Qna_DTO> qnaCtgSearch(@Param("searchCategory") String searchCategory);
	

	// ---------------------------------------------------------------- 검색 관련 메소드 
	
	
	// 전체 데이터 갯수 구하기
	public int count();
	
	// 검색 결과 데이터 갯수 구하기
	public int getDataCount(@Param("searchKey") String searchKey, @Param("searchCategory") String searchCategory,@Param("searchValue") String searchValue);
	
	// 검색 페이지
	public ArrayList<InformDTO> getList(BoardListDTO dto);
	
	// 말머리 리스트
	public ArrayList<InterestDTO> imList();
	
	// 질문 게시판 리스트 출력
	public ArrayList<Board_Qna_DTO> qnaList(@Param("start") int start, @Param("end") int end);
	
	// 질문 게시판 글 작성 액션
	public int qnaInsert(Board_Qna_DTO dto);
	
	// 질문 게시판 조회수 증가 액션
	public int hitcount(@Param("post_code") String post_code);
	
	// 질문 게시판 디테일 페이지
	public Board_Qna_DTO qnaDetail(@Param("post_code") String post_code);
	
	// 질문 게시물 수정
	public int qnaBoardUpdate(Board_Qna_DTO dto);
	
	// 질문 게시물 삭제
	public int qnaBoardDelete(@Param("post_code") String post_code);
	
	// 질문 게시판 댓글
	public ArrayList<FreeBoardCmtDTO> commentList(@Param("post_code") String post_code);
	//--
	// 질문 게시판 댓글 수
	public int commentCnt(@Param("post_code") String post_code);
	
	// 질문 게시판 추천
	public int rec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 질문 게시판 비추천
	public int notrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 질문 게시판  댓글 작성
	public int addComment(FreeBoardCmtDTO comment);
	
	// 질문 게시물 추천여부체크
	public int recCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 질문 게시물 신고 여부 체크
	public int rptCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 답변 게시물 작성 액션
	public int answerInsert(Board_Qna_DTO dto);
	
	// 답변 게시물 리스트
	public ArrayList<Board_Qna_DTO> answerList(@Param("post_code") String post_code);
	
	// 답변 게시물 삭제 액션
	public int answerDelete(@Param("post_code") String post_code);
	
	// 답변 게시물 수정
	public int answerUpdate(Board_Qna_DTO dto);
	
	// 답변 게시물 추천
	public int answerRec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 답변 게시물 비추천
	public int answerNotrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 답변 게시물 추천여부체크
	public int AnswerRecCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 답변 게시물 신고 여부 체크
	public int AnswerRptCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 질문에 달린 답변들의 포스트 코드 조회
	public ArrayList<String> answerCode(@Param("post_code") String post_code);
	
	// 게시물 신고
	public int insertReport(HashMap<String, String> param);
}
