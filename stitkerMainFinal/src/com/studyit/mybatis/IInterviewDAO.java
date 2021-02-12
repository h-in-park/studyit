package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;


public interface IInterviewDAO
{
	public ArrayList<InterviewDTO> list(BoardListDTO dto) throws SQLException;
	public ArrayList<InterviewDTO> hitlist() throws SQLException;
	public ArrayList<InterviewDTO> reclist() throws SQLException;
	public ArrayList<InterviewDTO> interestlist() throws SQLException;
	public int count(BoardListDTO dto) throws SQLException;
	public int add(InterviewDTO dto) throws SQLException;
	public InterviewDTO searchlist(String post_code) throws SQLException;
	public int remove(String post_code) throws SQLException;
	public int hitcount(String post_code) throws SQLException;
	public int addComment(String post_code, String user_code, String comments) throws SQLException;
	public ArrayList<InterviewCommentDTO> commentlist(String post_code) throws SQLException;
	public int addRec(String post_code, String user_code) throws SQLException;
	public int addNotRec(String post_code, String user_code) throws SQLException;
	public int recCheck(String post_code, String user_code) throws SQLException;
	public ArrayList<InterviewReportDTO> reportctg() throws SQLException;
	public int interviewReport(String post_code, String user_code, String report_reason, String post_report_ctg_code) throws SQLException;
	public int insertReport(HashMap<String, String>param) throws SQLException;
	public int chkReport(@Param("post_code")String post_code, @Param("user_code")String user_code) throws SQLException;
	public int interviewModify(String post_code, String title, String interest_code, String content);
	
	// 댓글수
	public int commentCnt(@Param("post_code")String post_code);
	
	// 댓글 가져오기
	public String getComm(String comment_code);
	
	// 댓글 수정
	public int modifyComm(@Param("comment_code")String comment_code, @Param("comments")String comments);
	
	// 댓글 삭제
	public int deleteComm(String comment_code);
}
