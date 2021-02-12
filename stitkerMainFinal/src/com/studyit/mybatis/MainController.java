package com.studyit.mybatis;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController
{
	@Autowired
	private SqlSession sqlSession;
	
	// 메인 페이지 
	@RequestMapping(value = "/studyit.action", method = RequestMethod.GET)
	public String mainPage(Model model, HttpServletRequest request) 
	{
		IMainDAO dao = sqlSession.getMapper(IMainDAO.class);
		
		String result = null;
		
		ArrayList<MainInformDTO> informTop5 = dao.boardInformTop5();
		model.addAttribute("informTop5", informTop5);
		
		ArrayList<MainStdReviewDTO> stdReviewTop3 = dao.boardStdReviewTop3();
		model.addAttribute("stdReviewTop3", stdReviewTop3);
		
		ArrayList<MainStudyDTO> hitStdTop3 = dao.hitStudy();
		model.addAttribute("hitStdTop3", hitStdTop3);
		
		// 로그인 여부 판단 후 메인페이지 스터디 목록 보여주기
		HttpSession session = request.getSession();
		if (session.getAttribute("code")!=null)
		{
			if (session.getAttribute("admin")==null)
			{
				String usercode = (String) session.getAttribute("code");
				
				//테스트
				//System.out.println(usercode);
				
				ArrayList<MainStudyDTO> interStdTop3 = dao.interStudy(usercode);
				model.addAttribute("interStdTop3", interStdTop3);
			}
		}
		
		int stdCntInt = dao.stdCount();
		String stdCnt = NumberFormat.getInstance().format(stdCntInt);
		model.addAttribute("stdCnt", stdCnt);
		
		int memCnt = dao.memCount();
		model.addAttribute("memCnt", memCnt);
		
		double avgStdAssess = dao.avgAssess();
		model.addAttribute("avgStdAssess", avgStdAssess);
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월 dd일");
		Calendar time = Calendar.getInstance();
		String today = format.format(time.getTime());
		model.addAttribute("today", today);
		
		result = "/WEB-INF/views/Main.jsp";
		
		return result;
	}
	
	// footer - 가이드 및 이용약관
	@RequestMapping(value = "/studyit_terms.action", method = RequestMethod.GET)
	public String Terms(Model model, HttpServletRequest request) 
	{
		IMainDAO dao = sqlSession.getMapper(IMainDAO.class);
		
		String result = null;
		

		result = "/WEB-INF/views/Terms.jsp";
		
		return result;
	}
	
	
	
}
