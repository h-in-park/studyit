/*======================================
	FreeBoardDTO.java
	- 자유 게시판 속성 자료형 클래스
======================================*/

package com.studyit.mybatis;

public class FreeBoardDTO
{
	// 주요 속성 구성
	private String post_num, post_code, user_code, write_date, title, content, user_name, user_id;
	private int hitcount, rec, notrec;
	
	// getter / setter 구성
	public String getUser_id()
	{
		return user_id;
	}
	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
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
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
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
