/*====================================================
	MyInfoDTO.java
	- 마이페이지 내 정보 관련 속성 자료형 클래스
====================================================*/

package com.studyit.mybatis;

public class MyInfoDTO
{
	// 주요 속성 구성
	private String user_code, ssn
	, interest_lc_code, interest_mc_code, interest_mc
	, loc_lc_code, loc_mc_code, loc_mc
	, job_lc_code, job_mc_code, job_mc
	, email, name, regdate
	, study_type_code, study_type
	, password;

	// getter / setter 구성
	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getInterest_lc_code()
	{
		return interest_lc_code;
	}

	public void setInterest_lc_code(String interest_lc_code)
	{
		this.interest_lc_code = interest_lc_code;
	}

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

	public String getLoc_lc_code()
	{
		return loc_lc_code;
	}

	public void setLoc_lc_code(String loc_lc_code)
	{
		this.loc_lc_code = loc_lc_code;
	}

	public String getLoc_mc_code()
	{
		return loc_mc_code;
	}

	public void setLoc_mc_code(String loc_mc_code)
	{
		this.loc_mc_code = loc_mc_code;
	}

	public String getLoc_mc()
	{
		return loc_mc;
	}

	public void setLoc_mc(String loc_mc)
	{
		this.loc_mc = loc_mc;
	}

	public String getJob_lc_code()
	{
		return job_lc_code;
	}

	public void setJob_lc_code(String job_lc_code)
	{
		this.job_lc_code = job_lc_code;
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

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRegdate()
	{
		return regdate;
	}

	public void setRegdate(String regdate)
	{
		this.regdate = regdate;
	}

	public String getStudy_type_code()
	{
		return study_type_code;
	}

	public void setStudy_type_code(String study_type_code)
	{
		this.study_type_code = study_type_code;
	}

	public String getStudy_type()
	{
		return study_type;
	}

	public void setStudy_type(String study_type)
	{
		this.study_type = study_type;
	}
}
