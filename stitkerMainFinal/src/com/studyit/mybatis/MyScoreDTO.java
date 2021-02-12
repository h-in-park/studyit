/*==========================================
	MyScoreDTO.java
	- 사용자 등급 점수 관련 자료형 클래스
==========================================*/

package com.studyit.mybatis;

public class MyScoreDTO
{
	// 주요 속성 구성
	private String myscore, myrank, percentage;

	// getter / setter 구성
	public String getMyscore()
	{
		return myscore;
	}

	public void setMyscore(String myscore)
	{
		this.myscore = myscore;
	}

	public String getMyrank()
	{
		return myrank;
	}

	public void setMyrank(String myrank)
	{
		this.myrank = myrank;
	}

	public String getPercentage()
	{
		return percentage;
	}

	public void setPercentage(String percentage)
	{
		this.percentage = percentage;
	}
}
