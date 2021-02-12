/*===========================================
	WithdrawalCategoryDTO.java
	- 탈퇴 카테고리 관련 속성 자료형 클래스
===========================================*/

package com.studyit.mybatis;

public class WithdrawalCategoryDTO
{
	// 주요 속성 구성
	private String wdl_ctg_code, wdl_ctg;
	
	// getter / setter 구성
	public String getWdl_ctg_code()
	{
		return wdl_ctg_code;
	}

	public void setWdl_ctg_code(String wdl_ctg_code)
	{
		this.wdl_ctg_code = wdl_ctg_code;
	}

	public String getWdl_ctg()
	{
		return wdl_ctg;
	}

	public void setWdl_ctg(String wdl_ctg)
	{
		this.wdl_ctg = wdl_ctg;
	}
	
}
