package com.studyit.mybatis;

public class StudyReviewDTO
{
	private String post_num,post_code,interest_mc_code, user_name, parti_code, write_date, title, content, comment_code, comments, interest_mc,id;
	private int rec,notrec,hitcount;
	
	// getter / setter  구성
	public String getPost_num()
	{
		return post_num;
	}
	public String getParti_code()
	{
		return parti_code;
	}
	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
	}
	public void setPost_num(String post_num)
	{
		this.post_num = post_num;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getWrite_date()
	{
		return write_date;
	}
	public void setWrite_date(String write_date)
	{
		this.write_date = write_date;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getInterest_mc()
	{
		return interest_mc;
	}
	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}
	public int getRec()
	{
		return rec;
	}
	public void setRec(int rec)
	{
		this.rec = rec;
	}
	public int getNotrec()
	{
		return notrec;
	}
	public void setNotrec(int notrec)
	{
		this.notrec = notrec;
	}
	public int getHitcount()
	{
		return hitcount;
	}
	public void setHitcount(int hitcount)
	{
		this.hitcount = hitcount;
	}
	public String getPost_code()
	{
		return post_code;
	}
	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}
	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	public String getComment_code()
	{
		return comment_code;
	}
	public void setComment_code(String comment_code)
	{
		this.comment_code = comment_code;
	}
	
	@Override
	public String toString()
	{
		return "StudyReviewDTO [title=" + title + ", comment_code=" + comment_code + ", comments=" + comments + "]";
	}
	
	
	
	
	
}
