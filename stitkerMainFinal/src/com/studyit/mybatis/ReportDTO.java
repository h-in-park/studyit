package com.studyit.mybatis;

public class ReportDTO
{
	// 주요 속성 구성 (스터디원신고)
	private String userCode, study_name, study_code,report_category, userCode2, reason, parti_code;
					// 신고자                                  // 신고 당하는 자         // 신고자 

	public String getStudy_name()
	{
		return study_name;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public String getUserCode2()
	{
		return userCode2;
	}

	public void setUserCode2(String userCode2)
	{
		this.userCode2 = userCode2;
	}

	public void setStudy_name(String study_name)
	{
		this.study_name = study_name;
	}

	public String getStudy_code()
	{
		return study_code;
	}

	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}


	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getReport_category()
	{
		return report_category;
	}

	public void setReport_category(String report_category)
	{
		this.report_category = report_category;
	}

	public String getParti_code()
	{
		return parti_code;
	}

	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
	} 
	
}
