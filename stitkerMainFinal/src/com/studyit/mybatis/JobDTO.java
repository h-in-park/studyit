/*===========
 JobDTO.java
=============*/

package com.studyit.mybatis;

public class JobDTO
{
	// 주요 속성 구성
	private String job_lc_code, job_lc, job_mc_code, job_mc;

	// getter / setter 구성
	public String getJob_lc_code()
	{
		return job_lc_code;
	}

	public void setJob_lc_code(String job_lc_code)
	{
		this.job_lc_code = job_lc_code;
	}

	public String getJob_lc()
	{
		return job_lc;
	}

	public void setJob_lc(String job_lc)
	{
		this.job_lc = job_lc;
	}

	public String getJob_mc_code()
	{
		return job_mc_code;
	}

	public void setJob_mc_code(String job_mc_code)
	{
		this.job_mc_code = job_mc_code;
	}

	public String getJob_mc()
	{
		return job_mc;
	}

	public void setJob_mc(String job_mc)
	{
		this.job_mc = job_mc;
	}
	
	
}
