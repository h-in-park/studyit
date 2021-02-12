package com.studyit.mybatis;

public class Board_report_list_DTO 
{
	private String rnum, reported_id, post_code, title, handle_result, handle_date, reported_user_code;

	public String getPost_code()
	{
		return post_code;
	}

	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getHandle_result()
	{
		return handle_result;
	}

	public void setHandle_result(String handle_result)
	{
		this.handle_result = handle_result;
	}

	public String getHandle_date()
	{
		return handle_date;
	}

	public void setHandle_date(String handle_date)
	{
		this.handle_date = handle_date;
	}

	public String getRnum()
	{
		return rnum;
	}

	public void setRnum(String rnum)
	{
		this.rnum = rnum;
	}

	public String getReported_user_code() {
		return reported_user_code;
	}

	public void setReported_user_code(String reported_user_code) {
		this.reported_user_code = reported_user_code;
	}

	public String getReported_id() {
		return reported_id;
	}

	public void setReported_id(String reported_id) {
		this.reported_id = reported_id;
	}
	
	
}
