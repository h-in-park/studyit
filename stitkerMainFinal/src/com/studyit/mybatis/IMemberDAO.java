package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IMemberDAO
{
	// 스터디원 조회
	public ArrayList<NowstudyDTO> member(@Param("studycode") String studycode, @Param("id")String id);
	
	// 점수 입력 
	public void insertSQL(List<Map<String, String>> paramList) throws Exception;

	// 스터디원 상호평가 입력 
	public int memberassess(MemberDTO dto);
	
	// 내가 평가해야 하는 사람 수 
	public int count(@Param("studycode") String studycode, @Param("id")String id);
	
	// 평가했는지 확인하는 메소드
	public int count2(@Param("parti_code") String parti_code, @Param("parti_assessed_code") String parti_assessed_code);
	
	// 누적 스터디 수 조회(멤버)
	public int studyCnt(@Param("id") String id);
}
