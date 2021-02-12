/*===============================================
	MyPostDTO.java
	- 마이페이지 내가 쓴 글 관련 자료형 클래스
===============================================*/

package com.studyit.mybatis;

public class MyPostDTO
{
	// 주요 속성 구성
	private String board_name, post_code, title, rec, cmt, hitcount, user_code;

	// getter / setter 구성
	public String getBoard_name()
	{
		return board_name;
	}

	public void setBoard_name(String board_name)
	{
		this.board_name = board_name;
	}

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

	public String getRec()
	{
		return rec;
	}

	public void setRec(String rec)
	{
		this.rec = rec;
	}

	public String getCmt()
	{
		return cmt;
	}

	public void setCmt(String cmt)
	{
		this.cmt = cmt;
	}

	public String getHitcount()
	{
		return hitcount;
	}

	public void setHitcount(String hitcount)
	{
		this.hitcount = hitcount;
	}

	public String getUser_code()
	{
		return user_code;
	}

	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}
	
}
