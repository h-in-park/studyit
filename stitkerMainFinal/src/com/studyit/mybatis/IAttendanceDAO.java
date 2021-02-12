package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IAttendanceDAO
{
	// 오늘 요일 조회
	public String weekday(@Param("studycode") String studycode);
	
	// 출석부 조회
	public ArrayList<AttendanceDTO> list(@Param("parti_code") String parti_code);
	
	// 출석버튼 비활성화 
	//public int attendInCheck(@Param("parti_code") String parti_code);
	
	// 출석 
	public int attendIn(@Param("parti_code") String parti_code);
		
	// 출석코드 조회
	public String attendCode(@Param("parti_code") String parti_code);

	// 퇴실
	public int attendOut(AttendanceDTO dto);
}
