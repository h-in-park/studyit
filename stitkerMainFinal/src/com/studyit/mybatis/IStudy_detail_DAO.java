package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface IStudy_detail_DAO {
	
	// 스터디 정보 가져오기
	public Study_detail_DTO studyDetail(String study_code);
	
	// 요일 정보 가져오기
	public ArrayList<Study_weekday_DTO> weekday(String study_code);
	
	// 일주일에 스터디 하는 횟수 가져오기
	public int studyCount(String study_code);
	
	// 현재 확인한 인원 수
	public int applyCount(String study_code);
	
	// 참가신청한 회원목록
	public ArrayList<Study_applyMem_DTO> applyMem(String study_code);
	
	// 확인 누르면 다시 현재 시간으로 신청날짜 업데이트
	public int updateDate(String apply_code);
	
	// 댓글 개수
	public int commentCount(String study_code);
	
	// 추천 개수
	public int recomCount(String study_code);
	
	// 비추천개수
	public int unRecomCount(String study_code);
	
	// 댓글 리스트
	public ArrayList<Study_comment_DTO> comment(@Param("study_code")String study_code, @Param("start")int start, @Param("end")int end);
	
	// 추천 등록
	public int insertRecom(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 비추천 등록
	public int insertUnRecom(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 댓글 등록
	public int insertComm(Study_comment_DTO dto);
	
	// 참여코드 가져오기
	public String getApplyCode(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 확인 버튼 클릭
	public int updateApplyDate(String apply_code);
	
	// 마감하기-날짜변경
	public int updateEndDate(@Param("study_code")String study_code);
	
	// 마감하기-참가에 인서트
	public int insertParti(@Param("apply_code")String apply_code);
	
	// 추천, 비추천 여부 확인
	public int checkRec(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 댓글 가져오기
	public Study_comment_DTO getComm(String comment_code);
	
	// 댓글 수정하기
	public int modifyComm(Study_comment_DTO dto);

	// 댓글 삭제
	public int deleteComm(@Param("comment_code")String comment_code);
	
	// 조회수 증가
	public int addHitCount(String study_code);
	
	// 신고 카테고리 가져오기
	public ArrayList<InterviewReportDTO> getReportCtg();
	
	// 신고 등록
	public int addReport(InterviewReportDTO dto);
	
	// 신고 여부 확인
	public int checkReport(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 개설자 코드 가져오기
	public String getWriterCode(String study_code);
	
	// 신청 및 취소 여부 확인(취소하려면 신청한 적은 있으면서, 취소한 적은 없어야 함.)
	public int checkApply(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 참여한 스터디 중 점수적용해야하는 스터디 목록(아직 적용안함 + 종료날짜2주지남)
	public ArrayList<String> getStudyList(String user_code);
	
	// 해당 스터디에 참여한 사용자코드 목록
	public ArrayList<String> getUserList(String study_code);
	
	// 스터디 평가 토대로 점수 적용
	public int addScore(@Param("study_code")String study_code, @Param("user_code")String user_code);
	
	// 해당 스터디 평가 완료 여부 업데이트
	public int updateStudyAssess(String study_code);
	
}
