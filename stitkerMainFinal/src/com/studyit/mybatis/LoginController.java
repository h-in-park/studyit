/*=================================
	LoginController.java
	- 로그인 폼 컨트롤러
	- 로그인 액션 컨트롤러
	- 로그아웃 액션 컨트롤러
==================================*/

package com.studyit.mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
	@Autowired
	private SqlSession sqlSession;
	
	
   // 로그인 폼
   @RequestMapping(value = "/loginform.action", method = RequestMethod.GET)
   public String loginForm(HttpServletRequest request)
   {
      // 세션에 이전 페이지 주소 저장해놓기 
      
      String referer = request.getHeader("Referer");
      request.getSession().setAttribute("redirectURI", referer);
         
      if (request.getSession().getAttribute("code")==null)
      {
         return "/WEB-INF/views/LoginForm.jsp";
      }
      else
      {
         return "/WEB-INF/views/Main.jsp";
      }
      
   }
	
	
	// 로그인 액션
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(LoginDTO dto, HttpServletRequest request)
	{
		ILoginDAO dao = sqlSession.getMapper(ILoginDAO.class);
		
		
		String result = null;	 // 리턴할 뷰 페이지 주소를 담을 변수
		String code = null;		 // 세션에 setAttribute할 사용자_CODE 값을 담을 변수
		String id = dto.getId(); // 세션에 setAttribute할 사용자 id 
		String pw = dto.getPw(); // 세션에 setAttribute할 사용자 pw 
		
		// 테스트
		//System.out.println(id);
		//System.out.println(pw);
		
		// 로그인 처리 → 대상에 따른 로그인 처리 방식 구분
		if (dto.getId().substring(0, 1).equals("@"))
		{
			// 관리자 로그인
			code = dao.adminLogin(dto);
			
			// 로그인 성공 여부에 따른 분기
			if (code == null)
			{
				// 로그인 실패 → 로그인 폼을 다시 요청
				result = "redirect:loginform.action";
			}
			else
			{
				// 로그인 성공 → 세션 구성
				HttpSession session = request.getSession();
				session.setAttribute("code", code);
				session.setAttribute("userid", id);
				session.setAttribute("userpw", pw);
				session.setAttribute("admin", "admin");
				
				
				// ◆ 관리자로 로그인 성공 시 요청할 페이지 ◆
				String url = (String)request.getSession().getAttribute("redirectURI");
				result = "redirect:"+url;
			}
		}
		else
		{
			// 일반 사용자 로그인
			code = dao.userLogin(dto);
			
			// 로그인 성공 여부에 따른 분기
			if (code == null)
			{
				// 로그인 실패 → 로그인 폼을 다시 요청
				result = "redirect:loginform.action";
			}
			else
			{
				// 로그인 성공 → 세션 구성
				HttpSession session = request.getSession();
				session.setAttribute("code", code);
				session.setAttribute("userid", id);
				session.setAttribute("userpw", pw);
				session.setAttribute("admin", null);
				
				
				// ◆ 일반 사용자로 로그인 성공 시 요청할 페이지 ◆
				String url = (String)request.getSession().getAttribute("redirectURI");
				result = "redirect:"+url;
			}
		}
		
		return result;
	}
	
	
	// 로그아웃 액션 
	@RequestMapping(value = "/logout.action", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		session.removeAttribute("code");
		session.removeAttribute("id");
		session.removeAttribute("pw");
		session.removeAttribute("admin");
		
		return "redirect:studyit.action";
		
	}
}





