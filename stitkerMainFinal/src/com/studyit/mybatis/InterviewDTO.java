package com.studyit.mybatis;

public class InterviewDTO
{
	private String post_num, id, write_date, title, content, interest_mc,post_code, user_code, interest_mc_code;
	private int rec,notrec,hitcount;
	
	public String getPost_code()
	{
		return post_code;
	}
	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}
	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}
	// getter / setter 구성
	public String getPost_num()
	{
		return post_num;
	}
	public void setPost_num(String post_num)
	{
		this.post_num = post_num;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
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
	
	
	
	
	
}
