/*=================
  CmtInformDTO.java
==================*/

package com.studyit.mybatis;

public class RankDTO
{
	// 주요 속성 구성
	private String rank_code, rank, min_score, max_score;

	
	// getter / setter 구성
	public String getRank_code()
	{
		return rank_code;
	}

	public void setRank_code(String rank_code)
	{
		this.rank_code = rank_code;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getMin_score()
	{
		return min_score;
	}

	public void setMin_score(String min_score)
	{
		this.min_score = min_score;
	}

	public String getMax_score()
	{
		return max_score;
	}

	public void setMax_score(String max_score)
	{
		this.max_score = max_score;
	}

	

	

	
	
	

}
