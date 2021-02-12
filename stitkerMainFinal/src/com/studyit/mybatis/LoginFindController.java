/*=================================
	LoginFindController.java
	- 아이디 찾기 폼 컨트롤러
	- 아이디 찾기 액션 컨트롤러
	- 비밀번호 찾기 폼 컨트롤러
	- 비밀번호 찾기 액션 컨트롤러
==================================*/

package com.studyit.mybatis;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginFindController
{
	@Autowired
	private SqlSession sqlSession;
	
	// 아이디 찾기 폼
	@RequestMapping(value = "/findidform.action", method = RequestMethod.GET)
	public String findIdForm()
	{
		return "/WEB-INF/views/Find_id.jsp";
	}
	
	// 아이디 찾기 액션
	@RequestMapping(value = "/findid.action", method = RequestMethod.POST)
	public String findId(Model model, HttpServletRequest request)
	{
		IFindIdDAO dao = sqlSession.getMapper(IFindIdDAO.class);
		
		String email = request.getParameter("email");
		
		model.addAttribute("email", email);
		model.addAttribute("id", dao.findId(email));
		
		return "/WEB-INF/views/Find_id_result.jsp";
	}
	
	// 비밀번호 찾기 폼
	@RequestMapping(value = "/findpwform.action", method = RequestMethod.GET)
	public String findPwForm(Model model)
	{
		IFindPwDAO dao = sqlSession.getMapper(IFindPwDAO.class);
		
		ArrayList<FindPwQueDTO> queList = new ArrayList<FindPwQueDTO>();
		
		queList = dao.questionList();
		
		//테스트
		/*
		for (FindPwQueDTO findPwQueDTO : queList)
		{
			System.out.println(findPwQueDTO.getQuestion());
		}
		*/
		
		model.addAttribute("queList", queList);
		
		return "WEB-INF/views/Find_pw.jsp";
	}
	
	// 비밀번호 찾기 액션 - 이메일
	@RequestMapping(value = "/findbyemail.action", method = RequestMethod.POST)
	public String FindPwByEmail(Model model, HttpServletRequest request)
	{
		IFindPwDAO dao = sqlSession.getMapper(IFindPwDAO.class);
		
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String result = null;
		
		FindPwQueDTO dto = dao.findPwByEmail(email, userId);
		
		if (email.equals(dto.getEmail()))
		{
			String rndStr = dao.rndPw();
			
			dao.updatePw(userId, rndStr);
			
			model.addAttribute("rndStr", rndStr);
			
			result = "/WEB-INF/views/Find_pw_result.jsp";
		}
		else
		{
			result = "WEB-INF/views/Find_pw.jsp";
		}
		
		return result;
	}
	
	// 비밀번호 찾기 액션 - 비밀번호 찾기 질문&답
	@RequestMapping(value = "/findbyqna.action", method = RequestMethod.POST)
	public String FindPwByQue(Model model, HttpServletRequest request)
	{
		IFindPwDAO dao = sqlSession.getMapper(IFindPwDAO.class);
		
		String result = null;
		
		String userId = request.getParameter("userId");
		String userAns = request.getParameter("userAns");
		String quecode = request.getParameter("selectQue"); 
		
		// 테스트
		//System.out.println(userId);
		//System.out.println(userAns);
		
		FindPwQueDTO dto = dao.findPwByQna(quecode, userAns, userId);
		
		System.out.println(quecode);
		System.out.println(dto.getQuecode());
		
		System.out.println(userAns);
		System.out.println(dto.getAnswer());
		
		if (quecode.equals(dto.getQuecode()) && userAns.equals(dto.getAnswer()))
		{
			String rndStr = dao.rndPw();
			dao.updatePw(userId, rndStr);
			
			model.addAttribute("rndStr", rndStr);
			
			result = "/WEB-INF/views/Find_pw_result.jsp";
		}
		else
		{
			result = "WEB-INF/views/Find_pw.jsp";
		}
		// 동적으로 생성되는 셀렉트 박스에서 values 얻어오는 법..
		// 걍 셀렉트박스 네임으로 겟파라미터해서 받아오면되네;
		
		//result = "비밀번호 찾기 질문&답이 일치하지 않습니다.";
		
		//model.addAttribute("err", result);
		
		return result;
	}
	
	
}
