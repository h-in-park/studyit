/*===================================
	IMainDAO.java
	- 인터페이스
	- 메인페이지 관련 메소드 정의
===================================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IMainDAO
{
	// 누적 스터디 개수
	public int stdCount();
	// 누적 스터디 멤버 수 
	public int memCount();
	
	// 평균 스터디 평점
	public double avgAssess();
	
	// 정보공유 게시판 추천수 상위 5개 리스트
	public ArrayList<MainInformDTO> boardInformTop5();
	
	// 스터디후기 게시판 추천수 상위 3개 리스트
	public ArrayList<MainStdReviewDTO> boardStdReviewTop3();
	
	// 스터디 게시판 조회수 상위 3개 리스트
	public ArrayList<MainStudyDTO> hitStudy();
	
	// 관심분야 스터디 3개
	public ArrayList<MainStudyDTO> interStudy(@Param("usercode") String usercode);
	
}
