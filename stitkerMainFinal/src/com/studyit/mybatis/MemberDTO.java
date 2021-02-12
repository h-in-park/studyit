package com.studyit.mybatis;

public class MemberDTO
{
	// 주요 속성 구성 
	private String id, study_code, rnum, 
	
	// 회원 평가
	//INTERASSESS_CODE    PARTI_ASSESS_CODE  RESP_CODE  ITEM_CODE  PARTI_CODE  INTERASSESS_DATE
	interassess_code, parti_code, resp_code, item_code, parti_assessed_code, interassess_date;
	// 평가시퀀스 	    내 코드   매우그렇다  항목       평가할 사람 코드        sysdate
	
	// 평가받을 사람 수 
	private int cnt; 
	
	// getter / setter 구성 
	public String getId()
	{
		return id;
	}

	public String getInterassess_code()
	{
		return interassess_code;
	}

	public void setInterassess_code(String interassess_code)
	{
		this.interassess_code = interassess_code;
	}

	public String getResp_code()
	{
		return resp_code;
	}

	public void setResp_code(String resp_code)
	{
		this.resp_code = resp_code;
	}

	public String getItem_code()
	{
		return item_code;
	}

	public void setItem_code(String item_code)
	{
		this.item_code = item_code;
	}


	public String getInterassess_date()
	{
		return interassess_date;
	}

	public void setInterassess_date(String interassess_date)
	{
		this.interassess_date = interassess_date;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getStudy_code()
	{
		return study_code;
	}

	public void setStudy_code(String study_code)
	{
		this.study_code = study_code;
	}

	public String getRnum()
	{
		return rnum;
	}

	public void setRnum(String rnum)
	{
		this.rnum = rnum;
	}

	public String getParti_assessed_code()
	{
		return parti_assessed_code;
	}

	public void setParti_assessed_code(String parti_assessed_code)
	{
		this.parti_assessed_code = parti_assessed_code;
	}

	public String getParti_code()
	{
		return parti_code;
	}

	public void setParti_code(String parti_code)
	{
		this.parti_code = parti_code;
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
