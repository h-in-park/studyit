/*===============
 IJobDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


public interface IJobDAO
{
	public ArrayList<JobDTO> jblList();
	public ArrayList<JobDTO> jbmList();
	public ArrayList<JobDTO> jbmList2(String job_lc_code);
	
	// 추가 - 대분류에 따른 중분류리스트 가져오기
	public ArrayList<JobDTO> jblmList(@Param("job_lc_code") String job_lc_code);
}
