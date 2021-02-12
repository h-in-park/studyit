package com.studyit.mybatis;

public class StudySearchDTO
{
	// 주요 속성 구성 
	private String rnum, study_name, period, leader, weekday, start_time, study_code2,
	end_time, study_code, id, grade, study_desc, study_type,min_mem, max_mem,min_rank, interest_mc, 
	loc_lc, loc_mc ,loc_lc_code, study_type_code, rank, rank_code, searchKey,searchValue ;

	private int start, end;
	
	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getEnd()
	{
		return end;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public String getLoc_lc_code()
	{
		return loc_lc_code;
	}

	public String getSearchKey()
	{
		return searchKey;
	}

	public void setSearchKey(String searchKey)
	{
		this.searchKey = searchKey;
	}

	public String getSearchValue()
	{
		return searchValue;
	}

	public void setSearchValue(String searchValue)
	{
		this.searchValue = searchValue;
	}

	public void setLoc_lc_code(String loc_lc_code)
	{
		this.loc_lc_code = loc_lc_code;
	}

	// getter / setter 구성 
	public String getRnum()
	{
		return rnum;
	}

	public String getStudy_desc()
	{
		return study_desc;
	}

	public void setStudy_desc(String study_desc)
	{
		this.study_desc = study_desc;
	}

	public String getStudy_type()
	{
		return study_type;
	}

	public void setStudy_type(String study_type)
	{
		this.study_type = study_type;
	}

	public String getMin_mem()
	{
		return min_mem;
	}

	public void setMin_mem(String min_mem)
	{
		this.min_mem = min_mem;
	}

	public String getMax_mem()
	{
		return max_mem;
	}

	public void setMax_mem(String max_mem)
	{
		this.max_mem = max_mem;
	}

	public String getMin_rank()
	{
		return min_rank;
	}

	public void setMin_rank(String min_rank)
	{
		this.min_rank = min_rank;
	}

	public String getInterest_mc()
	{
		return interest_mc;
	}

	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}

	public String getLoc_mc()
	{
		return loc_mc;
	}

	public void setLoc_mc(String loc_mc)
	{
		this.loc_mc = loc_mc;
	}

	public void setRnum(String rnum)
	{
		this.rnum = rnum;
	}

	public String getStudy_name()
	{
		return study_name;
	}

	public void setStudy_name(String study_name)
	{
		this.study_name = study_name;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}

	public String getLeader()
	{
		return leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}

	public String getWeekday()
	{
		return weekday;
	}

	public void setWeekday(String weekday)
	{
		this.weekday = weekday;
	}

	public String getStart_time()
	{
		return start_time;
	}

	public void setStart_time(String start_time)
	{
		this.start_time = start_time;
	}

	public String getEnd_time()
	{
		return end_time;
	}

	public void setEnd_time(String end_time)
	{
		this.end_time = end_time;
	}

	public String getStudy_code()
	{
		return study_code;
	}

	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getGrade()
	{
		return grade;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getStudy_code2()
	{
		return study_code2;
	}

	public void setStudy_code2(String study_code2)
	{
		this.study_code2 = study_code2;
	}

	public String getLoc_lc()
	{
		return loc_lc;
	}

	public void setLoc_lc(String loc_lc)
	{
		this.loc_lc = loc_lc;
	}

	public String getStudy_type_code()
	{
		return study_type_code;
	}

	public void setStudy_type_code(String study_type_code)
	{
		this.study_type_code = study_type_code;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getRank_code()
	{
		return rank_code;
	}

	public void setRank_code(String rank_code)
	{
		this.rank_code = rank_code;
	}
	
	
	
}
