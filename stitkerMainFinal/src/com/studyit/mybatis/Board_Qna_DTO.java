/*====================================
	Board_QnA_DTO.java
	- 질문 답변 게시판 자료형 클래스
====================================*/

package com.studyit.mybatis;

public class Board_Qna_DTO
{
	// 주요 속성 구성
	private int level, qa, hitcount, ans_cnt, rec, notrec;
	private String post_code, write_date, title, content, qpost_code, interest_mc, post_num, id, user_code, interest_mc_code;
	
	// getter / setter 구성
	public int getLevel()
	{
		return level;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public int getQa()
	{
		return qa;
	}
	public void setQa(int qa)
	{
		this.qa = qa;
	}
	public int getHitcount()
	{
		return hitcount;
	}
	public void setHitcount(int hitcount)
	{
		this.hitcount = hitcount;
	}
	public String getPost_code()
	{
		return post_code;
	}
	public void setPost_code(String post_code)
	{
		this.post_code = post_code;
	}
	public String getWrite_date()
	{
		return write_date;
	}
	public void setWrite_date(String write_date)
	{
		this.write_date = write_date;
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
	public String getQpost_code()
	{
		return qpost_code;
	}
	public void setQpost_code(String qpost_code)
	{
		this.qpost_code = qpost_code;
	}
	public String getInterest_mc()
	{
		return interest_mc;
	}
	public void setInterest_mc(String interest_mc)
	{
		this.interest_mc = interest_mc;
	}
	public String getPost_num()
	{
		return post_num;
	}
	public void setPost_num(String post_num)
	{
		this.post_num = post_num;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public int getAns_cnt()
	{
		return ans_cnt;
	}
	public void setAns_cnt(int ans_cnt)
	{
		this.ans_cnt = ans_cnt;
	}
	public String getUser_code()
	{
		return user_code;
	}
	public void setUser_code(String user_code)
	{
		this.user_code = user_code;
	}
	public String getInterest_mc_code()
	{
		return interest_mc_code;
	}
	public void setInterest_mc_code(String interest_mc_code)
	{
		this.interest_mc_code = interest_mc_code;
	}
	public int getRec()
	{
		return rec;
	}
	public void setRec(int rec)
	{
		this.rec = rec;
	}
	public int getNotrec()
	{
		return notrec;
	}
	public void setNotrec(int notrec)
	{
		this.notrec = notrec;
	}
}
