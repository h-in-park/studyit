/*===================================
	InterestDTO.java
	- 관심분야 관련 자료형 클래스
===================================*/

package com.studyit.mybatis;

public class InterestDTO
{
	// 주요 속성 구성
	String interest_mc_code, interest_mc, interest_lc_code, interest_lc;

	// getter / setter 구성
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}

	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}

	public String getInterest_mc()
	{
		return interest_mc;
	}

	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}

	public String getInterest_lc_code()
	{
		return interest_lc_code;
	}

	public void setInterest_lc_code(String interest_lc_code)
	{
		this.interest_lc_code = interest_lc_code;
	}

	public String getInterest_lc()
	{
		return interest_lc;
	}

	public void setInterest_lc(String interest_lc)
	{
		this.interest_lc = interest_lc;
	}
	
}
