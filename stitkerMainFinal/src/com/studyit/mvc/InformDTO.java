/*=================
  InformDTO.java
==================*/

package com.studyit.mvc;

public class InformDTO
{
	// 주요 속성 구성
	private String post_num, user_code, user_name, write_date, title, content, interest_mc_code
	             , interest_mc;
	private int hitcount, rec, notrec;
	
	// getter / setter 구성
	public String getPost_num()
	{
		return post_num;
	}
	public void setPost_num(String post_num)
	{
		this.post_num = post_num;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
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
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}
	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}
	public String getInterest_mc()
	{
		return interest_mc;
	}
	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}
	public int getHitcount()
	{
		return hitcount;
	}
	public void setHitcount(int hitcount)
	{
		this.hitcount = hitcount;
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
	
	
	
	
	
	

}
