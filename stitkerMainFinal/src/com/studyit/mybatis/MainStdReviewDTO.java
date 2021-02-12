/*================================================
	MainStdReviewDTO.java
	- 메인페이지 관련 클래스
	- 스터디 리뷰 게시판 속성 자료형 클래스
================================================*/

package com.studyit.mybatis;

public class MainStdReviewDTO
{
	// 주요 속성 구성
	private String postCode, wrtieDate, title, content, interMcCode, writer, studyCode;
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
	public String getWrtieDate()
	{
		return wrtieDate;
	}
	public void setWrtieDate(String wrtieDate)
	{
		this.wrtieDate = wrtieDate;
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
	public String getWriter()
	{
		return writer;
	}
	public void setWriter(String writer)
	{
		this.writer = writer;
	}
	public String getStudyCode()
	{
		return studyCode;
	}
	public void setStudyCode(String studyCode)
	{
		this.studyCode = studyCode;
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
	
}

