/*===========================================
	Withdrawal_Info_DTO.java
	- 탈퇴 정보 관련 속성 자료형 클래스
===========================================*/

package com.studyit.mybatis;

public class WithdrawalInfoDTO
{
	// 주요 속성 구성
	private String wdl_date, wdl_ctg_code, ssn, user_code, wdl_reason;

	// getter / setter 구성
	public String getWdl_date()
	{
		return wdl_date;
	}

	public void setWdl_date(String wdl_date)
	{
		this.wdl_date = wdl_date;
	}

	public String getWdl_ctg_code()
	{
		return wdl_ctg_code;
	}

	public void setWdl_ctg_code(String wdl_ctg_code)
	{
		this.wdl_ctg_code = wdl_ctg_code;
	}

	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getWdl_reason()
	{
		return wdl_reason;
	}

	public void setWdl_reason(String wdl_reason)
	{
		this.wdl_reason = wdl_reason;
	}
	
}
