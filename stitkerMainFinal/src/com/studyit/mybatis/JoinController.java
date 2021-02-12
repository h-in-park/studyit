/*=========================
 JoinController.java
==========================*/
package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class JoinController
{
   // SqlSession 을 활용하여 마이바티스 객체 의존성(자동) 주입 
   @Autowired
   private SqlSession sqlSession;
   
   @Autowired
	IJoinAjaxDAO service;
   
   // 매개변수를 등록하는 과정에서 매개변수 목록에 적혀있는
   // 클래스의 객체 정보는 스프링이 제공한다. 
   
   // 사용자의 요청 주소와 메소드를 매핑하는 과정 필요.
   // RequestMapping(value = "/요청주소", method = 전송방식)
   // 이 때, 전송 방식은 submit 액션인 경우만 POST
   // 나머지 모든 전송 방식은 GET 으로 처리한다. 
   
   @RequestMapping(value ="/joininsertform.action", method = RequestMethod.GET)
   public String memberInsertForm(HttpServletRequest request, Model model)
   {
	  String result = null;
	   
      IJoinDAO dao = sqlSession.getMapper(IJoinDAO.class);
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      IStudyTypeDAO studyType = sqlSession.getMapper(IStudyTypeDAO.class);
      ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
      IMemNumDAO memnum = sqlSession.getMapper(IMemNumDAO.class);
      IRankDAO rank = sqlSession.getMapper(IRankDAO.class);
      IWeekDayDAO weekday = sqlSession.getMapper(IWeekDayDAO.class);
      IPwSrchDAO pwSrch = sqlSession.getMapper(IPwSrchDAO.class);
      IJobDAO job = sqlSession.getMapper(IJobDAO.class);
      ILocLcDAO loclc = sqlSession.getMapper(ILocLcDAO.class);
      
      
      model.addAttribute("imList", interest.imList());
      model.addAttribute("stList", studyType.stList());
      model.addAttribute("lmList", locmc.lmList());
      model.addAttribute("mnList", memnum.mnList());
      model.addAttribute("urList", rank.urList());
      model.addAttribute("wdList", weekday.wdList());
      model.addAttribute("pqList", pwSrch.pqList());
      model.addAttribute("jblList", job.jblList());
      model.addAttribute("jbmList", job.jbmList());
      model.addAttribute("llList", loclc.llList());
      
      
      

                  
      result = "/WEB-INF/views/Member_join.jsp";

      return result;
   }
   
   // 지역 대분류 AJAX처리
   @RequestMapping(value = "/joinregionajax.action", method = RequestMethod.POST)
   public String regionAjax(Model model, HttpServletRequest request)
   {
	   String result = null;
	   ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
	   
	   List<LocMcDTO> list = locmc.lmList2(request.getParameter("loc_lc_code"));
	   
	   model.addAttribute("lmList", list);
	   
	   result = "/WEB-INF/views/RegionAjax.jsp";
	   
	   return result;
   }
   
   // 직업 대분류 AJAX처리
   @RequestMapping(value = "/jobajax.action", method = RequestMethod.POST)
   public String jobAjax(Model model, HttpServletRequest request)
   {
	   String result = null;
	   IJobDAO job = sqlSession.getMapper(IJobDAO.class);
	   
	   List<JobDTO> list = job.jbmList2(request.getParameter("job_lc_code"));
	   
	   model.addAttribute("jbmList", list);
	   
	   result = "/WEB-INF/views/JobAjax.jsp";
	   
	   return result;
   }
   
   
   @RequestMapping(value ="/memberjoin.action", method = RequestMethod.GET)
   public String memberJoin(HttpServletRequest request, JoinDTO dto) throws Exception
   {
      HttpSession session = request.getSession();
      
      // HashMap 선언 및 파라미터 저장
      HashMap<String, String> param = new HashMap<String, String>();
      param.put("id", dto.getId());
      param.put("pw", dto.getPw());
      param.put("ssn", dto.getSsn());
      param.put("pw_srch_que_code", dto.getPw_srch_que_code());
      param.put("pw_srch_que_ans", dto.getPw_srch_que_ans());
      param.put("interest_mc_code", dto.getInterest_mc_code());
      param.put("loc_mc_code", dto.getLoc_mc_code());
      param.put("job_mc_code", dto.getJob_mc_code());
      param.put("email", dto.getEmail());
      param.put("name", dto.getName());
      param.put("study_type_code", dto.getStudy_type_code());
      
      
      // 회원가입 프로시저 호출
      sqlSession.selectOne("com.studyit.mybatis.IJoinDAO.memberJoin", param);
      System.out.println("회원가입 완료");
      
      
    
      return "redirect:loginform.action";
   }
   
   
  
   
 
   
  
     
   
   
   
   
}
   
   
  
   
   
   
