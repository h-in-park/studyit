package com.studyit.mybatis;

public class InterviewCommentDTO
{
	private String comment_code, post_num, post_code, comments, user_code,write_date, id;

	public String getComment_code()
	{
		return comment_code;
	}

	public void setComment_code(String comment_code)
	{
		this.comment_code = comment_code;
	}

	public String getPost_num()
	{
		return post_num;
	}

	public void setPost_num(String post_num)
	{
		this.post_num = post_num;
	}

	public String getPost_code()
	{
		return post_code;
	}

	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}

	public String getWrite_date()
	{
		return write_date;
	}

	public void setWrite_date(String write_date)
	{
		this.write_date = write_date.substring(0,10);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
