/*======================================
	FreeBoardCmtDTO.java
	- 자유 게시판 댓글 속성 자료형 클래스
======================================*/

package com.studyit.mybatis;

public class FreeBoardCmtDTO
{
	// 주요 속성 구성
	private String post_code, comment_code, comments, user_code, write_date, id;

	// getter / setter 구성
	public String getPost_code()
	{
		return post_code;
	}

	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}

	public String getComment_code()
	{
		return comment_code;
	}

	public void setComment_code(String comment_code)
	{
		this.comment_code = comment_code;
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
		this.write_date = write_date;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
