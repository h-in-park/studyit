package com.studyit.mybatis;

public class UpcomingDTO
{
	// 스터디 관련 주요 속성
	private String rnum, study_name, start_date, end_date, user_code, 
	parti_code, period,weekday, start_time, end_time, leader, study_code, loc;

	// getter / setter 
	public String getRnum()
	{
		return rnum;
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

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getParti_code()
	{
		return parti_code;
	}

	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
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

	public String getLeader()
	{
		return leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}

	public String getStudy_code()
	{
		return study_code;
	}

	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}
	
}
