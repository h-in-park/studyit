/*===============
 ICmtSeminarDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


public interface ICmtSeminarDAO
{
	// 댓글 등록
	public int cmtAdd(CmtSeminarDTO cmtSeminar);
	
	// 댓글 리스트
	public ArrayList<CmtSeminarDTO> cmtList(@Param("post_code")String post_code, @Param("start")int start, @Param("end")int end);
	
	// 댓글 수 확인
	public int cmtCount(String post_code);
	
	// 댓글 수정
	public int cmtModify(CmtSeminarDTO cmtSeminar);
	
	// 댓글 삭제
	public int cmtRemove(CmtSeminarDTO cmtSeminar);
	
	// 댓글 상세
	public CmtSeminarDTO cmtDetail(String comment_code);
	
	// 댓글 가져오기
	public CmtSeminarDTO getComm(String comment_code);
	
	// 신고 등록
	public int addReport(InterviewReportDTO dto);
	
	

}
