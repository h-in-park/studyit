package com.studyit.mybatis;

public class MainStudyDTO
{
	// 주요 속성 구성
	private String studycode, usercode, writedate, title, content;
	private String studyTypeCode, studyType, interMcCode, interMc, locMcCode, locMc;
	private String startDate, endDate, closeDate;
	private int hitcount, cnt;
	
	// getter /setter 구성
	public String getStudycode()
	{
		return studycode;
	}
	public void setStudycode(String studycode)
	{
		this.studycode = studycode;
	}
	public String getUsercode()
	{
		return usercode;
	}
	public void setUsercode(String usercode)
	{
		this.usercode = usercode;
	}
	public String getWritedate()
	{
		return writedate;
	}
	public void setWritedate(String writedate)
	{
		this.writedate = writedate;
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
	public String getStudyTypeCode()
	{
		return studyTypeCode;
	}
	public void setStudyTypeCode(String studyTypeCode)
	{
		this.studyTypeCode = studyTypeCode;
	}
	public String getStudyType()
	{
		return studyType;
	}
	public void setStudyType(String studyType)
	{
		this.studyType = studyType;
	}
	public String getInterMcCode()
	{
		return interMcCode;
	}
	public void setInterMcCode(String interMcCode)
	{
		this.interMcCode = interMcCode;
	}
	public String getInterMc()
	{
		return interMc;
	}
	public void setInterMc(String interMc)
	{
		this.interMc = interMc;
	}
	public String getLocMcCode()
	{
		return locMcCode;
	}
	public void setLocMcCode(String locMcCode)
	{
		this.locMcCode = locMcCode;
	}
	public String getLocMc()
	{
		return locMc;
	}
	public void setLocMc(String locMc)
	{
		this.locMc = locMc;
	}
	public String getStartDate()
	{
		return startDate;
	}
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	public String getEndDate()
	{
		return endDate;
	}
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	public String getCloseDate()
	{
		return closeDate;
	}
	public void setCloseDate(String closeDate)
	{
		this.closeDate = closeDate;
	}
	public int getHitcount()
	{
		return hitcount;
	}
	public void setHitcount(int hitcount)
	{
		this.hitcount = hitcount;
	}
	public int getCnt()
	{
		return cnt;
	}
	public void setCnt(int cnt)
	{
		this.cnt = cnt;
	}
}
