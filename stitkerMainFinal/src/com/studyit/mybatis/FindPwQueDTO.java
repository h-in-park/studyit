/*============================================
	FindPwQueDTO.java
	- 비밀번호 찾기 질문&답 자료형 클래스
=============================================*/

package com.studyit.mybatis;

public class FindPwQueDTO
{
	// 주요 속성 구성
	private String userId, quecode, question, answer, email;


	// getter / setter 구성
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getQuecode()
	{
		return quecode;
	}

	public void setQuecode(String quecode)
	{
		this.quecode = quecode;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
}
