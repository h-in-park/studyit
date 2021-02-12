/*===============
 IInformDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

public interface IInterestDAO
{
	public ArrayList<InterestDTO> imList();

	//public ArrayList<InformDTO> infoList() throws SQLException;
	
	// 추가 - 대분류코드와 대분류이름만 얻어오는 메소드
	public ArrayList<InterestDTO> ilList();
	
	// 추가 - 대분류코드에 따른 중분류 얻어오는 메소드
	public ArrayList<InterestDTO> ilmList(@Param("interest_lc_code") String interest_lc_code);
}
