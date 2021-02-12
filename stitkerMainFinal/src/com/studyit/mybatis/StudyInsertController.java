/*=========================
 StudyInsertController.java
==========================*/
package com.studyit.mybatis;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudyInsertController
{
   // SqlSession 을 활용하여 마이바티스 객체 의존성(자동) 주입 
   @Autowired
   private SqlSession sqlSession;
   
   // 매개변수를 등록하는 과정에서 매개변수 목록에 적혀있는
   // 클래스의 객체 정보는 스프링이 제공한다. 
   
   // 사용자의 요청 주소와 메소드를 매핑하는 과정 필요.
   // RequestMapping(value = "/요청주소", method = 전송방식)
   // 이 때, 전송 방식은 submit 액션인 경우만 POST
   // 나머지 모든 전송 방식은 GET 으로 처리한다. 
   
   // 스터디 개설 폼 이동
   @RequestMapping(value ="/studyinsertform.action", method = RequestMethod.GET)
   public String studyInsertForm(HttpServletRequest request, Model model)
   {
	  String result = null;
	   
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      IStudyTypeDAO studyType = sqlSession.getMapper(IStudyTypeDAO.class);
      ILocLcDAO loclc = sqlSession.getMapper(ILocLcDAO.class);
      IMemNumDAO memnum = sqlSession.getMapper(IMemNumDAO.class);
      IRankDAO rank = sqlSession.getMapper(IRankDAO.class);
      IWeekDayDAO weekday = sqlSession.getMapper(IWeekDayDAO.class);
      
      result = "/WEB-INF/views/Study_write.jsp";
      
      model.addAttribute("imList", interest.imList());
      model.addAttribute("stList", studyType.stList());
      model.addAttribute("llList", loclc.llList());
      model.addAttribute("mnList", memnum.mnList());
      model.addAttribute("urList", rank.urList());
      model.addAttribute("wdList", weekday.wdList());
      
	  // 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
	  HttpSession session = request.getSession();
	
	  if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
	  {
		  // 로그인이 되어있지 않은 상황에서의 처리
		  result = "redirect:loginform.action";
		  return result;
	  }
  	  // --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
      
      return result;
   }
   
   // 지역 대분류 AJAX처리
   @RequestMapping(value = "/studyregionajax.action")
   public String regionAjax(Model model, HttpServletRequest request)
   {
	   String result = null;
	   ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
	   
	   List<LocMcDTO> list = locmc.lmList2(request.getParameter("loc_lc_code"));
	   
	   model.addAttribute("lmList", list);
	   
	   result = "/WEB-INF/views/RegionAjax.jsp";
	   
	   return result;
   }
   
 
   // 스터디 등록
   @RequestMapping(value ="/studyinsert.action", method = RequestMethod.GET)
   public String studyInsert(HttpServletRequest request, StudyWriteDTO dto)
   {
      
      dto.setStudy_desc(dto.getStudy_desc().replaceAll("\n", "<br>"));
      String result = null;
      
	  // 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
	  HttpSession session = request.getSession();
	
	  if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
	  {
		  // 로그인이 되어있지 않은 상황에서의 처리
		  result = "redirect:loginform.action";
		  return result;
	  }
  	  // --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)      
      
	  result = "redirect:studysearch.action";
	  
      // HashMap 선언 및 파라미터 저장
      HashMap<String, String> param = new HashMap<String, String>();
      param.put("study_name", dto.getStudy_name());
      param.put("study_desc", dto.getStudy_desc());
      param.put("interest_mc_code", dto.getInterest_mc_code());
      param.put("study_type_code", dto.getStudy_type_code());
      param.put("start_date", dto.getStart_date());
      param.put("end_date", dto.getEnd_date());
      param.put("loc_mc_code", dto.getLoc_mc_code());
      param.put("min_mem_code", dto.getMin_mem_code());
      param.put("max_mem_code", dto.getMax_mem_code());
      param.put("min_rank", dto.getMin_rank());
      param.put("user_code", (String)session.getAttribute("code"));
      
      // 스터디 오픈 등록 프로시저 호출
      sqlSession.selectOne("com.studyit.mybatis.IStudyWriteDAO.studyOpenAdd", param);
      String study_code = param.get("study_code");
      
      // HashMap 선언 및 파라미터 저장
      HashMap<String, String> param2 = null;
      String[] selectWeek = request.getParameterValues("selectWeek[]");
      
      for(int i = 0; i < selectWeek.length/2; i++)
      {
    	  if (!selectWeek[i*2].equals("")) 
    	  {
    		  param2 = new HashMap<String, String>();
    		  param2.put("study_code", study_code);
    	      param2.put("weekday_code", "WD"+(i+1));
    	      param2.put("start_time", selectWeek[i*2]);
    	      param2.put("end_time", selectWeek[i*2+1]);
    	      
    	      // 스터디 요일 등록 프로시저 호출
    	      sqlSession.selectOne("com.studyit.mybatis.IStudyWriteDAO.meetDayAdd", param2);
		  }
      }
      return result;
   }
   
   // 스터디 수정 폼 이동
   @RequestMapping(value="/studymodifyform.action", method = RequestMethod.GET)
   public String studyModifyForm(Model model, HttpServletRequest request)
   {
	   String result = null;
	  
	  IStudyWriteDAO dao = sqlSession.getMapper(IStudyWriteDAO.class);
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      IStudyTypeDAO studyType = sqlSession.getMapper(IStudyTypeDAO.class);
      ILocLcDAO loclc = sqlSession.getMapper(ILocLcDAO.class);
      ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
      IMemNumDAO memnum = sqlSession.getMapper(IMemNumDAO.class);
      IRankDAO rank = sqlSession.getMapper(IRankDAO.class);
      IWeekDayDAO weekday = sqlSession.getMapper(IWeekDayDAO.class);
      
      result = "/WEB-INF/views/Study_write.jsp";
      
	  // 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
	  HttpSession session = request.getSession();
	
	  if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
	  {
		  // 로그인이 되어있지 않은 상황에서의 처리
		  result = "redirect:loginform.action";
		  return result;
	  }
  	  // --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) 
      
      StudyWriteDTO study = dao.searchStudy(request.getParameter("study_code"));
      study.setStudy_desc(study.getStudy_desc().replaceAll("<br>", "\n"));
      model.addAttribute("imList", interest.imList());
      model.addAttribute("stList", studyType.stList());
      model.addAttribute("llList", loclc.llList());
      model.addAttribute("lmList", locmc.lmList2(study.getLoc_lc_code()));
      model.addAttribute("mnList", memnum.mnList());
      model.addAttribute("urList", rank.urList());
      model.addAttribute("wdList", weekday.wdList());
      model.addAttribute("study", study);
      model.addAttribute("meetdayList", dao.searchMeetday(request.getParameter("study_code")));
      model.addAttribute("study_code", request.getParameter("study_code"));
	  result = "WEB-INF/views/Study_modify.jsp";
	   
	  return result;
   }
   
   // 스터디 수정 처리
   @RequestMapping(value="/studymodify.action", method = RequestMethod.POST)
   public String studyModify(Model model, HttpServletRequest request, StudyWriteDTO dto)
   {
	   String result = null;
	   result = "redirect:studysearch.action";
	   IStudyWriteDAO dao = sqlSession.getMapper(IStudyWriteDAO.class);
       
	  // 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
	  HttpSession session = request.getSession();
		
	  if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
	  {
		  // 로그인이 되어있지 않은 상황에서의 처리
		  result = "redirect:loginform.action";
		  return result;
	  }
	  // --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) 
		  
      // 스터디 오픈 수정
	  dto.setStudy_desc(dto.getStudy_desc().replaceAll("\n", "<br>"));
      dao.modify(dto);
      String study_code = dto.getStudy_code();
      
      // HashMap 선언 및 파라미터 저장
      HashMap<String, String> param2 = null;
      String[] selectWeek = request.getParameterValues("selectWeek[]");
      // 시간 삭제하기 입력
      dao.deleteMeetday(study_code);
      for(int i = 0; i < selectWeek.length/2; i++)
      {
    	  if (!selectWeek[i*2].equals("")) 
    	  {
    		  param2 = new HashMap<String, String>();
    		  param2.put("study_code", study_code);
    	      param2.put("weekday_code", "WD"+(i+1));
    	      param2.put("start_time", selectWeek[i*2]);
    	      param2.put("end_time", selectWeek[i*2+1]);
    	      
    	      // 스터디 요일 등록 프로시저 호출
    	      sqlSession.selectOne("com.studyit.mybatis.IStudyWriteDAO.meetDayAdd", param2);
		  }
      }
	  
	   return result;
   }
   
   // 스터디 개설 자격 여부 확인
   @RequestMapping(value="/checkstudyinsert.action", method=RequestMethod.POST)
   public String checkInsert(Model model, HttpServletRequest request)
   {
	   String result = null;
	   String message = null;
	   ICheckDAO dao = sqlSession.getMapper(ICheckDAO.class);
	   
	   // 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
	   HttpSession session = request.getSession();
		
	   if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
	   {
		   // 로그인이 되어있지 않은 상황에서의 처리
		   result = "redirect:loginform.action";
		   return result;
	   }
	   // --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) 	   
	   
	   // 4등급 이상인지 체크
	   String user_code = (String)session.getAttribute("code");
	   
	   if (dao.checkRank(user_code) == null) {
		   
		   message = "4등급 이상만 스터디를 개설할 수 있습니다.";
	   }
	   // 계정정지된 회원인지 체크
	   else if (dao.checkSuspend(user_code) != null && Integer.parseInt(dao.checkSuspend(user_code)) > 0 ) {
		   message = "해당 계정은 계정정지상태이므로 스터디를 개설할 수 없습니다.";
	   }
	   
	   // 이미 진행중인 갯수 체크
	   else if (dao.checkStudyCnt(user_code) != null && Integer.parseInt(dao.checkStudyCnt(user_code)) >= 3) {
		message = "동시에 참여중인 스터디는 3개 이하만 가능합니다.";
	   }
	   model.addAttribute("message", message);
	   result = "/WEB-INF/views/StudyInsertCheck.jsp";
	   return result;
   }
}
   
   
  
   
   
   
