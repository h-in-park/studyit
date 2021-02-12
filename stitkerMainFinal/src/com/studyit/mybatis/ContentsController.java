package com.studyit.mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ContentsController
{
	 @Autowired
	 private SqlSession sqlSession;
	 
	 // 스터디 컨텐츠 상세 조회
	 @RequestMapping(value = "/contentsdetail.action", method = RequestMethod.GET)
	  public String detail(HttpServletRequest request, ModelMap model)
	  {
		 IContentsDAO dao2 = sqlSession.getMapper(IContentsDAO.class);
		 
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String contentcode = request.getParameter("contentcode");
		 
		 String studycode = request.getParameter("studycode");
		 //String rnum = request.getParameter("rnum");
		 dao2.hitcount(contentcode);
		 
		 // 컨텐츠 상세페이지 출력
	     model.addAttribute("detail",dao2.detail(studycode, contentcode));
	     request.setAttribute("studycode", studycode);
	     return "WEB-INF/views/Mypage_contents.jsp";
	   }
	 
	 	// 컨텐츠 작성폼 
	 	@RequestMapping(value = "/contentwriteform.action", method = RequestMethod.POST)
		public String contentWriteForm(HttpServletRequest request)
		{
			String result = null;
			String parti_code = request.getParameter("parti_code");
			//System.out.println(parti_code);
			request.setAttribute("parti_code", parti_code);
			String referer = request.getHeader("Referer");
			request.getSession().setAttribute("redirectURI", referer);
			
			result = "/WEB-INF/views/mypage_studyContent_write.jsp";
			return result;
		}
	 
	 	// 진행중 컨텐츠 입력
	 	@RequestMapping(value = "/contentinsert.action", method = RequestMethod.POST)
		public String contentInsert(HttpServletRequest request, ContentsDTO content)
		{
	 		String result = null;
	 		HttpSession session = request.getSession();
	 		IContentsDAO dao = sqlSession.getMapper(IContentsDAO.class);
			String id = (String)session.getAttribute("userid");
			String studycode = request.getParameter("studycode");
			System.out.println(studycode);
			dao.add(content);
			String url = (String)request.getSession().getAttribute("redirectURI");
			result = "redirect:"+url;
			return result;
			//return "redirect:nowstudy.action?studycode=" +studycode;
		}
	 	
	 	// 컨텐츠 수정폼 
 	 	@RequestMapping(value = "/contentmodifyform.action", method = RequestMethod.GET)
 		public String contentModifyForm(HttpServletRequest request,ModelMap model )
 		{
 			String result = null;
 			IContentsDAO dao = sqlSession.getMapper(IContentsDAO.class);
 			String contentcode = request.getParameter("content_code");
 			HttpSession session = request.getSession();
 		 	
 			String refer = request.getHeader("refer");
 			String id = (String)session.getAttribute("userid");
 			//System.out.println(contentcode);
 		 	String studycode = request.getParameter("studycode");
 			model.addAttribute("update", dao.update(contentcode));
 			String referer = request.getHeader("Referer");
			request.getSession().setAttribute("redirectURI", referer);
			
 			result = "/WEB-INF/views/Mypage_studyContent_modify.jsp";
 			return result;
 		}
	 	 
 	 	
	 	// 진행중 컨텐츠 수정 
	 	@RequestMapping(value="/contentmodify.action", method = RequestMethod.POST )
	    public String contentModify(HttpServletRequest request, ContentsDTO content)
	    {
	 	   String result = null;
	 	   IContentsDAO dao = sqlSession.getMapper(IContentsDAO.class);
	 	   HttpSession session = request.getSession();
	 	   String id = (String)session.getAttribute("userid");
	 	   String studycode = request.getParameter("studycode");
	 	   dao.modify(content);
	 	   System.out.println(studycode);
	 	   
	 	    String url = (String)request.getSession().getAttribute("redirectURI");
			result = "redirect:"+url;
			return result;
			
	       //return "redirect:nowstudy.action?studycode=" +studycode;
	    }
	    
	 	
	 	// 컨텐츠 삭제 
	    @RequestMapping(value="/contentdelete.action", method = RequestMethod.GET )
	    public String memberDelete(HttpServletRequest request, ContentsDTO content)
	    {	
	    	String result = null;
	       IContentsDAO dao = sqlSession.getMapper(IContentsDAO.class);
	       dao.remove(content);
	       String studycode = request.getParameter("studycode");
	       
	       String url = (String)request.getSession().getAttribute("redirectURI");
			result = "redirect:"+url;
	       return result;
			//return "redirect:nowstudy.action?studycode=" +studycode;
	    }
	 	
	    
	    
	    
	    
	    
	 	
			
	

	 
}
