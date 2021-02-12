/*=====================
  StudyWriteDTO.java
======================*/

package com.studyit.mybatis;

public class StudyWriteDTO
{
	// 주요 속성 구성
	private String study_code, study_name, study_desc, interest_mc_code, study_type_code 
				 , start_date, end_date, loc_lc_code, loc_mc_code, min_mem_code, max_mem_code
				 , min_rank, user_code, close_date, write_date;
	private int hitcount;
	
	// getter / setter 구성
	public String getStudy_code()
	{
		return study_code;
	}
	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}
	public String getStudy_name()
	{
		return study_name;
	}
	public void setStudy_name(String study_name)
	{
		this.study_name = study_name;
	}
	public String getStudy_desc()
	{
		return study_desc;
	}
	public void setStudy_desc(String study_desc)
	{
		this.study_desc = study_desc;
	}
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}
	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}
	public String getStudy_type_code()
	{
		return study_type_code;
	}
	public void setStudy_type_code(String study_type_code)
	{
		this.study_type_code = study_type_code;
	}
	public String getStart_date()
	{
		return start_date;
	}
	public void setStart_date(String start_date)
	{
		this.start_date = start_date;
	}
	public String getEnd_date()
	{
		return end_date;
	}
	public void setEnd_date(String end_date)
	{
		this.end_date = end_date;
	}
	public String getLoc_mc_code()
	{
		return loc_mc_code;
	}
	public void setLoc_mc_code(String loc_mc_code)
	{
		this.loc_mc_code = loc_mc_code;
	}
	public String getMin_mem_code()
	{
		return min_mem_code;
	}
	public void setMin_mem_code(String min_mem_code)
	{
		this.min_mem_code = min_mem_code;
	}
	public String getMax_mem_code()
	{
		return max_mem_code;
	}
	public void setMax_mem_code(String max_mem_code)
	{
		this.max_mem_code = max_mem_code;
	}
	public String getMin_rank()
	{
		return min_rank;
	}
	public void setMin_rank(String min_rank)
	{
		this.min_rank = min_rank;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}
	public String getClose_date()
	{
		return close_date;
	}
	public void setClose_date(String close_date)
	{
		this.close_date = close_date;
	}
	public String getWrite_date()
	{
		return write_date;
	}
	public void setWrite_date(String write_date)
	{
		this.write_date = write_date;
	}
	public int getHitcount()
	{
		return hitcount;
	}
	public void setHitcount(int hitcount)
	{
		this.hitcount = hitcount;
	}
	public String getLoc_lc_code() {
		return loc_lc_code;
	}
	public void setLoc_lc_code(String loc_lc_code) {
		this.loc_lc_code = loc_lc_code;
	}
	

	
	
	
	
	

}
