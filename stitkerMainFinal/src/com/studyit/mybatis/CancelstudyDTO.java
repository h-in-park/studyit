package com.studyit.mybatis;

public class CancelstudyDTO
{
	// 주요 속성 구성 
	private String rnum, parti_code,attCheck, study_name, period, leader, weekday
					, auto_cancel,  user_code, loc, cancel_date;

	// getter / setter 구성
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


	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getLoc()
	{
		return loc;
	}

	public void setLoc(String loc)
	{
		this.loc = loc;
	}

	public String getAuto_cancel()
	{
		return auto_cancel;
	}

	public void setAuto_cancel(String auto_cancel)
	{
		this.auto_cancel = auto_cancel;
	}

	public String getCancel_date()
	{
		return cancel_date;
	}

	public void setCancel_date(String cancel_date)
	{
		this.cancel_date = cancel_date;
	}

	public String getParti_code()
	{
		return parti_code;
	}

	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
	}

	public String getAttCheck()
	{
		return attCheck;
	}

	public void setAttCheck(String attCheck)
	{
		this.attCheck = attCheck;
	}
}
