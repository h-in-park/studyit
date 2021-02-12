package com.studyit.mybatis;

import org.apache.ibatis.annotations.Param;

public interface IReportDAO
{
	public String studyName(@Param("studycode") String studycode);
	
	// 신고하는 사람, 당하는 사람 유저코드 조회 
	public String userCode(@Param("studycode") String studycode, @Param("id") String id);

	// 신고 당하는 사람 parti_code
	public String partiCode(@Param("studycode") String studycode, @Param("id") String id);
	
	public int report(ReportDTO dto);
	
	public int reportCheck(@Param("parti_code") String parti_code, @Param("reported_parti_code") String reported_parti_code);
	
}
