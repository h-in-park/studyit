package com.studyit.mybatis;

public class Report_detail_DTO
{
	// 주요 속성 구성
	private String rnum, report_ctg, report_reason, report_date, report_id, report_user_code;
	
	// getter, setter 구성
	public String getRnum()
	{
		return rnum;
	}

	public void setRnum(String rnum)
	{
		this.rnum = rnum;
	}

	public String getReport_ctg()
	{
		return report_ctg;
	}

	public void setReport_ctg(String post_report_ctg)
	{
		this.report_ctg = post_report_ctg;
	}

	public String getReport_reason()
	{
		return report_reason;
	}

	public void setReport_reason(String report_reason)
	{
		this.report_reason = report_reason;
	}

	public String getReport_date()
	{
		return report_date;
	}

	public void setReport_date(String report_date)
	{
		this.report_date = report_date;
	}

	public String getReport_id()
	{
		return report_id;
	}

	public void setReport_id(String report_id)
	{
		this.report_id = report_id;
	}

	public String getReport_user_code()
	{
		return report_user_code;
	}

	public void setReport_user_code(String report_user_code)
	{
		this.report_user_code = report_user_code;
	}
	
	
}
