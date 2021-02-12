package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController
{
	@Autowired
	 private SqlSession sqlSession;
	 
	// 스터디원 평가 페이지  
 	@RequestMapping(value = "/assessform.action", method = {RequestMethod.GET, RequestMethod.POST})
	public String contentWriteForm(HttpServletRequest request,ModelMap model)
	{
		String result = null;
		String parti_code = request.getParameter("parti_code");
		IMemberDAO member = sqlSession.getMapper(IMemberDAO.class);
		//System.out.println(parti_code);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		request.setAttribute("parti_code", parti_code);
		String studycode = request.getParameter("studycode");
		model.addAttribute("member", member.member(studycode, id));
		
		
		
		// 평가 해야하는 인원 수 
		int count = member.count(studycode, id);
		model.addAttribute("count", count);
		
		result = "/WEB-INF/views/Mypage_MemberAssess.jsp";
		return result;
	}
 
 	// 스터디원 평가 입력
 	@RequestMapping(value = "/assessinsert.action", method = {RequestMethod.GET, RequestMethod.POST})
	public String contentInsert(HttpServletRequest request, ContentsDTO content, ModelMap model)
	{
 		HttpSession session = request.getSession();
 		IContentsDAO dao = sqlSession.getMapper(IContentsDAO.class);
 		IMemberDAO member = sqlSession.getMapper(IMemberDAO.class);
 		
		String id = (String)session.getAttribute("userid");
		String studycode = request.getParameter("studycode");
		//System.out.println(studycode);
		String parti_code = request.getParameter("parti_code"); // 나의 진행 코드 
		String parti_assessed_code = request.getParameter("parti_assessed_code");  // 평가 당하는 사람 
		
		
		// getParameter
		String AI1 = request.getParameter("AI1");  // 해당 참가자는 과제 등 자신의 할 일을 성실히 수행했나요?
		String ARa = request.getParameter("ARa");  // 점수1
		
		String AI2 = request.getParameter("AI2");  // 해당 참가자는 스터디원들을 배려하며 원활한 소통을 위해 노력했나요?
		String ARb = request.getParameter("ARb");  // 점수2
		
		String AI3 = request.getParameter("AI3");  // 해당 참가자는 스터디 활동에 능동적으로 참여했나요?
		String ARc = request.getParameter("ARc");  // 점수3
	
		//System.out.println("parti_code : " + parti_code);
		//System.out.println("parti_assessedcode : " + parti_assessed_code);
		//System.out.println(ARa);
		//System.out.println(ARb);
		//System.out.println(ARc);
		
		MemberDTO assess1 = new MemberDTO();
		assess1.setParti_code(parti_code);
		assess1.setParti_assessed_code(parti_assessed_code);
		assess1.setResp_code(ARa);
		assess1.setItem_code(AI1); 
		int a = member.memberassess(assess1);
		//System.out.println(a);
		
		MemberDTO assess2 = new MemberDTO();
		assess2.setParti_code(parti_code);
		assess2.setParti_assessed_code(parti_assessed_code);
		assess2.setResp_code(ARb);
		assess2.setItem_code(AI2);
		member.memberassess(assess2);
		
		MemberDTO assess3 = new MemberDTO();
		assess3.setParti_code(parti_code);
		assess3.setParti_assessed_code(parti_assessed_code);
		assess3.setResp_code(ARc);
		assess3.setItem_code(AI3);
		member.memberassess(assess3);
		
		return "redirect:assessform.action?studycode=" +studycode+"&parti_code="+parti_code ;
		
	}
 	
 	@RequestMapping(value = "/ajax2.action", method = {RequestMethod.GET, RequestMethod.POST})
 	@ResponseBody
 	public String assessCheck(HttpServletRequest request,ModelMap model)
	{
		// 데이터 수신
		String parti_assessed_code = request.getParameter("parti_assessed_code");
		System.out.println("parti_assessed_code : " + parti_assessed_code);
		String parti_code = request.getParameter("parti_code");
		System.out.println("parti_code : " + parti_code);
		String studycode = request.getParameter("studycode");
		
		
		IMemberDAO member = sqlSession.getMapper(IMemberDAO.class);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		
		
		// 평가했는지 확인 
		int assessCheck = 0;
		
		assessCheck  = member.count2(parti_code,parti_assessed_code);
		model.addAttribute("assessCheck" , assessCheck);
			
		return Integer.toString(assessCheck);
		
	}
 	
 	@RequestMapping(value = "/ajax.action", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
 	public String studyCnt(HttpServletRequest request,ModelMap model, MemberDTO dto)
	{
 		IMemberDAO member = sqlSession.getMapper(IMemberDAO.class);

 		// id 수신
 		String id = request.getParameter("id");
		
 		int data = sqlSession.selectOne("com.studyit.mybatis.IMemberDAO.studyCnt", id);
 		
		//int data =  member.studyCnt(id);
		
		return Integer.toString(data);
 	
	}
 	
	 
}
