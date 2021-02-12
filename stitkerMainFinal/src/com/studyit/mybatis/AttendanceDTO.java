package com.studyit.mybatis;

public class AttendanceDTO
{
	private String attend_date,status, start_time, end_time, clock_in, clock_out, parti_code, attend_code, user_code, weekday_code, study_code, id;

	public String getClock_in()
	{
		return clock_in;
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

	public void setClock_in(String clock_in)
	{
		this.clock_in = clock_in;
	}

	public String getClock_out()
	{
		return clock_out;
	}

	public void setClock_out(String clock_out)
	{
		this.clock_out = clock_out;
	}

	public String getParti_code()
	{
		return parti_code;
	}

	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
	}

	public String getAttend_code()
	{
		return attend_code;
	}

	public void setAttend_code(String attend_code)
	{
		this.attend_code = attend_code;
	}

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getWeekday_code()
	{
		return weekday_code;
	}

	public void setWeekday_code(String weekday_code)
	{
		this.weekday_code = weekday_code;
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

	public String getAttend_date()
	{
		return attend_date;
	}

	public void setAttend_date(String attend_date)
	{
		this.attend_date = attend_date;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
}
