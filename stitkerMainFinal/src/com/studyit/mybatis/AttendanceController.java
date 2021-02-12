package com.studyit.mybatis;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AttendanceController
{
	 @Autowired
	 private SqlSession sqlSession;
	 
	 @RequestMapping(value = "/attendance.action", method = {RequestMethod.GET, RequestMethod.POST})
	  public String attendNowList(HttpServletRequest request, ModelMap model)
	  {
		 IAttendanceDAO attend = sqlSession.getMapper(IAttendanceDAO.class);
		 
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 String parti_code = request.getParameter("particode");
		 String attCheck= request.getParameter("attCheck");
		 
		 Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
			 // 1     2     3     4     5     6     7
		 final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
		 String today = week[oCalendar.get(Calendar.DAY_OF_WEEK) - 1];
		 String weekday = attend.weekday(studycode);
		 String check = "nottoday";  //오늘 출석하는 날인지 판단
		 if(weekday.equals(today))
			 check="today";

		 model.addAttribute("check", check);

		 // 출석 확인 
		 //int attendInCheck = attend.attendInCheck(parti_code);
		 //model.addAttribute("attendInCheck", attendInCheck);
	
		 // 출석부 코드 확인
		 String attendCode = attend.attendCode(parti_code);
		 model.addAttribute("attendCode", attendCode);
	
		 
		 // 리스트 출력
	     model.addAttribute("attendance",attend.list(parti_code));
	     
	     return "WEB-INF/views/Mypage_attendance.jsp";
	   }
	 
	 
	 
	 @RequestMapping(value = "/attendanceec.action", method = {RequestMethod.GET, RequestMethod.POST})
	  public String attendList(HttpServletRequest request, ModelMap model)
	  {
		 IAttendanceDAO attend = sqlSession.getMapper(IAttendanceDAO.class);
		 
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 String parti_code = request.getParameter("particode");
		 
		 // 리스트 출력
	     model.addAttribute("attendance",attend.list(parti_code));
	     
	     return "WEB-INF/views/Mypage_attendanceEC.jsp";
	   }
	 
	 // 출석 
	 @RequestMapping(value = "/attendin.action",method = RequestMethod.GET)
	  public String attendIn(HttpServletRequest request, ModelMap model)
	  {
		 IAttendanceDAO attend = sqlSession.getMapper(IAttendanceDAO.class);
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 String parti_code = request.getParameter("parti_code");
		 
		 
		 
		 attend.attendIn(parti_code);
		 return "redirect:nowstudy.action?studycode=" + studycode;
	  
	  }
	 
	 // 퇴실
	 @RequestMapping(value = "/attendout.action",method = RequestMethod.GET)
	  public String attendOut(HttpServletRequest request, ModelMap model)
	  {
		 IAttendanceDAO attend = sqlSession.getMapper(IAttendanceDAO.class);
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 String parti_code = request.getParameter("parti_code");
		 String attend_code = request.getParameter("attend_code"); 
		 
		 // HashMap 선언 및 파라미터 저장 
		 HashMap<String, String> param3 = new HashMap<String, String>();
		 param3.put("study_code", studycode);
		 param3.put("parti_code", parti_code);
		 param3.put("attend_code", attend_code);

		 // 스터디원 내보내기 프로시저 호출
		 sqlSession.selectOne("com.studyit.mybatis.IAttendanceDAO.attendOut",param3);
		 
		 
		 return "redirect:nowstudy.action?studycode=" + studycode;
	  }
		 
		 
}
