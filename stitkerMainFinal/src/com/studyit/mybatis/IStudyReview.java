package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

public interface IStudyReview
{
	// 후기 리스트 조회
	public ArrayList<StudyReviewDTO> list(@Param("start") int start, @Param("end") int end);
	
	// 해당 스터디 INTEREST_MC_CODE 조회
	public ArrayList<StudyReviewDTO> interestlist(@Param("studycode") String studycode);
	 
	// 게시글 상세 조회
	public StudyReviewDTO detail(@Param("post_code") String post_code);
	
	// 전체 게시물 수 
	public int count() throws SQLException;
	
	// 게시글 추천
	public int rec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시글 비추천
	public int notrec(@Param("post_code") String post_code, @Param("user_code") String user_code);
	
	// 게시물 추천 여부 체크
	public int recCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);
		
	// 게시물 신고 여부 체크
	public int rptCheck(@Param("post_code") String post_code, @Param("user_code") String user_code);		
	
	// 조회수 업데이트
	public int hitcount(@Param("post_code") String post_code);
	
	// 키워드 검색 - 전체
	public ArrayList<StudyReviewDTO> totalSearch(@Param("searchValue") String searchValue);
	
	// 키워드 검색 - 제목
	public ArrayList<StudyReviewDTO> titleSearch(@Param("searchValue") String searchValue);
	
	// 키워드 검색 - 작성자
	public ArrayList<StudyReviewDTO> writerSearch(@Param("searchValue") String searchValue);
	
	// 키워드 검색 - 내용
	public ArrayList<StudyReviewDTO> contentSearch(@Param("searchValue") String searchValue);
	
	// 키워드 검색 - 제목&내용
	public ArrayList<StudyReviewDTO> tncSearch(@Param("searchValue") String searchValue);
	
	// 게시글 작성
	public int reviewInsert(StudyReviewDTO post);
	
	// 게시물 수정
	public int modify(@Param("post_code") String post_code, @Param("title") String title, @Param("content") String content);
	
	// 게시물 삭제
	public int delete(@Param("post_code") String post_code);
	
	// 댓글 수
	public int commentCnt(@Param("post_code") String post_code);
	
	// 댓글 조회
	public ArrayList<StudyReviewDTO> commentList(@Param("post_code") String post_code);
		
	// 댓글 작성
	public int addComment(FreeBoardCmtDTO comment);
	
	// 댓글 가져오기
	public StudyReviewDTO getComm(String comment_code);
		
	// 댓글 수정하기
	public int modifyComm(StudyReviewDTO dto);

	// 댓글 삭제
	public int deleteComm(@Param("comment_code")String comment_code);
		

}
