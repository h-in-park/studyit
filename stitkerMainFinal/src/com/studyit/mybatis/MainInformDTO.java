/*================================================
	MainInformDTO.java
	- 메인페이지 관련 클래스
	- IT정보공유 게시판 속성 자료형 클래스
================================================*/

package com.studyit.mybatis;

public class MainInformDTO
{
	private String postCode, userCode, writer, writeDate, title, content, interMcCode, interest_mc;
	private int hitcount, rec;
	
	// getter / setter 구성
	public String getPostCode()
	{
		return postCode;
	}
	public void setPostCode(String postCode)
	{
		this.postCode = postCode;
	}
	public String getUserCode()
	{
		return userCode;
	}
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public String getWriteDate()
	{
		return writeDate;
	}
	public void setWriteDate(String writeDate)
	{
		this.writeDate = writeDate;
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
	public String getInterMcCode()
	{
		return interMcCode;
	}
	public void setInterMcCode(String interMcCode)
	{
		this.interMcCode = interMcCode;
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
	public String getInterest_mc()
	{
		return interest_mc;
	}
	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}
}
