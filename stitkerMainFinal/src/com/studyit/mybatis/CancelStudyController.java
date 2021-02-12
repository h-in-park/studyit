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
public class CancelStudyController
{
	 @Autowired
	 private SqlSession sqlSession;
	 
	 @RequestMapping(value = "/cancelstudy.action", method = RequestMethod.GET)
	  public String studentList(HttpServletRequest request, ModelMap model)
	  {
		 ICancelstudyDAO dao = sqlSession.getMapper(ICancelstudyDAO.class);
		 IMemberDAO dao2 = sqlSession.getMapper(IMemberDAO.class);
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");

		 // 리스트 출력
	     model.addAttribute("canlist", dao.list(studycode, id));
	     model.addAttribute("weekday", dao.weekday(studycode));
	     model.addAttribute("member", dao2.member(studycode, id));
	     return "WEB-INF/views/Mypage_cancelStudy.jsp";
	   }
	 
}
