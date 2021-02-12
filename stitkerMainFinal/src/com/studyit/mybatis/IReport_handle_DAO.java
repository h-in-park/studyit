package com.studyit.mybatis;

import java.util.HashMap;

public interface IReport_handle_DAO {
	
	// 스터디원 경고 등록
	public int insertWarning(HashMap<String, String>param);
	
	// 신고처리 업데이트
	public int updateRhParti(String reported_parti_code);
	
	// 신고등록 삭제
	public int deleteRegParti(String reported_parti_code);
	
	// 신고처리 삭제
	public int deleteRhParti(String reported_parti_code);
	
	
	// 정보공유---------------------------------------------
	// 신고처리 업데이트
	public int updateRhInform(String post_code);
	
	// 신고등록 삭제
	public int deleteRegInform(String post_code);
	
	// 신고처리 삭제
	public int deleteRhInform(String post_code);

	
	// 면접,코테 후기-----------------------------------------
	// 신고처리 업데이트
	public int updateRhInterview(String post_code);
	
	// 신고등록 삭제
	public int deleteRegInterview(String post_code);
	
	// 신고처리 삭제
	public int deleteRhInterview(String post_code);
	

	// 세미나 ---------------------------------------------
	// 신고처리 업데이트
	public int updateRhSeminar(String post_code);
	
	// 신고등록 삭제
	public int deleteRegSeminar(String post_code);
	
	// 신고처리 삭제
	public int deleteRhSeminar(String post_code);
	

	// 자유게시판---------------------------------------------
	// 신고처리 업데이트
	public int updateRhFree(String post_code);
	
	// 신고등록 삭제
	public int deleteRegFree(String post_code);
	
	// 신고처리 삭제
	public int deleteRhFree(String post_code);
	

	// 질문게시판---------------------------------------------
	// 신고처리 업데이트
	public int updateRhQuestion(String post_code);
	
	// 신고등록 삭제
	public int deleteRegQuestion(String post_code);
	
	// 신고처리 삭제
	public int deleteRhQuestion(String post_code);
	

	// 답변게시판---------------------------------------------
	// 신고처리 업데이트
	public int updateRhAnswer(String post_code);
	
	// 신고등록 삭제
	public int deleteRegAnswer(String post_code);
	
	// 신고처리 삭제
	public int deleteRhAnswer(String post_code);
	

	// 스터디후기---------------------------------------------
	// 신고처리 업데이트
	public int updateRhReview(String post_code);
	
	// 신고등록 삭제
	public int deleteRegReview(String post_code);
	
	// 신고처리 삭제
	public int deleteRhReview(String post_code);
	

	// 스터디게시판---------------------------------------------
	// 신고처리 업데이트
	public int updateRhStudy(String study_code);
	
	// 신고등록 삭제
	public int deleteRegStudy(String study_code);
	
	// 신고처리 삭제
	public int deleteRhStudy(String study_code);
}
