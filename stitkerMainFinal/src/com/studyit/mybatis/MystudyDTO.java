package com.studyit.mybatis;

public class MystudyDTO
{
	// 주요 속성 구성 
	private String rnum, study_name, period, leader, weekday, start_time,
	end_time, study_code, id, study_num, loc, cancel_date;
	private int count, progress;

	// getter / setter 구성 


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

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public String getRnum()
	{
		return rnum;
	}

	public void setRnum(String rnum)
	{
		this.rnum = rnum;
	}

	public String getStudy_code()
	{
		return study_code;
	}

	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}

	public int getProgress()
	{
		return progress;
	}

	public void setProgress(int progress)
	{
		this.progress = progress;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStudy_num()
	{
		return study_num;
	}

	public void setStudy_num(String study_num)
	{
		this.study_num = study_num;
	}

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}

	public String getCancel_date()
	{
		return cancel_date;
	}

	public void setCancel_date(String cancel_date)
	{
		this.cancel_date = cancel_date;
	}
	
	
	
}
