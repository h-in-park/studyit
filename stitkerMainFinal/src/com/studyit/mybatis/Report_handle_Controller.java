package com.studyit.mybatis;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report_handle_Controller
{
	@Autowired
	private SqlSession sqlSession;
	
	// 스터디원 경고 등록
	@RequestMapping(value="/insertwarning.action", method=RequestMethod.GET)
	public String insertWarning(HttpServletRequest request, ModelMap model)
	{
		String result = null;
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("reported_user_code", request.getParameter("reported_user_code"));
		
		// 경고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.IParticipant_report_list_DAO.insertWarning", param);
		
		// 신고처리 결과 등록
		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		dao.updateRhParti(request.getParameter("reported_parti_code"));
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "WarningSuccess");
		model.addAttribute("reported_parti_code", request.getParameter("reported_parti_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:memreportdetail.action";
		return result;
	}

	// 게시물 작성자 경고 등록
	@RequestMapping(value="/insertwarningboard.action", method=RequestMethod.GET)
	public String insertWarningBoard(HttpServletRequest request, ModelMap model)
	{
		String result = null;
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("reported_user_code", request.getParameter("reported_user_code"));
		
		// 경고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.IReport_handle_DAO.insertWarning", param);
		
		// 신고처리 결과 등록(게시판 별로 다름)
		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		String post_code = request.getParameter("post_code");
		
		if (post_code.substring(0, 2).equals("BI")) 
			dao.updateRhInform(post_code);
		else if(post_code.substring(0, 2).equals("BV"))
			dao.updateRhInterview(post_code);
		else if(post_code.substring(0, 2).equals("BS"))
			dao.updateRhSeminar(post_code);
		else if(post_code.substring(0, 2).equals("BF"))
			dao.updateRhFree(post_code);
		else if(post_code.substring(0, 2).equals("BQ"))
			dao.updateRhQuestion(post_code);
		else if(post_code.substring(0, 2).equals("BA"))
			dao.updateRhAnswer(post_code);
		else if(post_code.substring(0, 2).equals("BR"))
			dao.updateRhReview(post_code);
		else if(post_code.substring(0, 2).equals("SO"))
			dao.updateRhStudy(post_code);
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "WarningSuccess");
		model.addAttribute("post_code", request.getParameter("post_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:reportdetail.action";
		return result;
	}
	
	// 스터디원 신고수 초기화
	@RequestMapping(value="/updatereg.action", method=RequestMethod.GET)
	public String updateReg(HttpServletRequest request, ModelMap model)
	{
		String result = null;
		
		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		
		// 신고처리 데이터 삭제
		dao.deleteRhParti(request.getParameter("reported_parti_code"));
		
		// 신고등록 데이터 삭제
		dao.deleteRegParti(request.getParameter("reported_parti_code"));
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "UpdateSuccess");
		model.addAttribute("reported_parti_code", request.getParameter("reported_parti_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:memreportdetail.action";
		
		return result;
	}
	

	// 게시물 신고수 초기화
	@RequestMapping(value="/updateboardreg.action", method=RequestMethod.GET)
	public String updateBoardReg(HttpServletRequest request, ModelMap model)
	{
		String result = null;
		
		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		
		// 신고처리 데이터 삭제 + 신고등록 데이터 삭제
		String post_code = request.getParameter("post_code");
		
		if (post_code.substring(0, 2) == "BI")
		{	
			dao.deleteRhInform(post_code);
			dao.deleteRegInform(post_code);
		}	
		else if(post_code.substring(0, 2) == "BV")
		{	
			dao.deleteRhInterview(post_code);
			dao.deleteRegInterview(post_code);
		}	
		else if(post_code.substring(0, 2) == "BS")
		{	
			dao.deleteRhSeminar(post_code);
			dao.deleteRegSeminar(post_code);
		}
		else if(post_code.substring(0, 2) == "BF")
		{	
			dao.deleteRhFree(post_code);
			dao.deleteRegFree(post_code);
		}
		else if(post_code.substring(0, 2) == "BQ")
		{	
			dao.deleteRhQuestion(post_code);
			dao.deleteRegQuestion(post_code);
		}
		else if(post_code.substring(0, 2) == "BA")
		{	
			dao.deleteRhAnswer(post_code);
			dao.deleteRegAnswer(post_code);
		}
		else if(post_code.substring(0, 2) == "BR")
		{	
			dao.deleteRhReview(post_code);
			dao.deleteRegReview(post_code);
		}
		else if(post_code.substring(0, 2) == "SO")
		{	
			dao.deleteRhStudy(post_code);
			dao.deleteRegStudy(post_code);
		}
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "UpdateSuccess");
		model.addAttribute("post_code", request.getParameter("post_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:reportdetail.action";
		
		return result;
	}	
	
	// 스터디원 신고수 초기화 + 경고등록(허위신고)
	@RequestMapping(value="/updateAndInsertWarning.action", method=RequestMethod.GET)
	public String updateRegAndInsertWarning(HttpServletRequest request, ModelMap model)
	{
		String result = null;

		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		
		// 신고처리 데이터 삭제
		dao.deleteRhParti(request.getParameter("reported_parti_code"));
		// 신고등록 데이터 삭제
		dao.deleteRegParti(request.getParameter("reported_parti_code"));
		
		String[] report_user_code = request.getParameterValues("report_user_code");
		HashMap<String, String> param;
		
		// 경고등록(허위신고)
		for (int i = 0; i < report_user_code.length; i++)
		{
			param = new HashMap<String, String>();
			param.put("reported_user_code", report_user_code[i]);
			sqlSession.selectOne("com.studyit.mybatis.IParticipant_report_list_DAO.insertWarning", param);
		}
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "UpdateAndWarningSuccess");
		model.addAttribute("reported_parti_code", request.getParameter("reported_parti_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:memreportdetail.action";
		
		return result;
	}
	
	// 게시물 신고수 초기화 + 경고등록(허위신고)
	@RequestMapping(value="/updateAndInsertboardwarning.action", method=RequestMethod.GET)
	public String updateRegAndInsertBoardWarning(HttpServletRequest request, ModelMap model)
	{
		String result = null;

		IReport_handle_DAO dao = sqlSession.getMapper(IReport_handle_DAO.class);
		
		// 신고처리 데이터 삭제 + 신고등록 데이터 삭제
		String post_code = request.getParameter("post_code");
		
		if (post_code.substring(0, 2) == "BI")
		{	
			dao.deleteRhInform(post_code);
			dao.deleteRegInform(post_code);
		}	
		else if(post_code.substring(0, 2) == "BV")
		{	
			dao.deleteRhInterview(post_code);
			dao.deleteRegInterview(post_code);
		}	
		else if(post_code.substring(0, 2) == "BS")
		{	
			dao.deleteRhSeminar(post_code);
			dao.deleteRegSeminar(post_code);
		}
		else if(post_code.substring(0, 2) == "BF")
		{	
			dao.deleteRhFree(post_code);
			dao.deleteRegFree(post_code);
		}
		else if(post_code.substring(0, 2) == "BQ")
		{	
			dao.deleteRhQuestion(post_code);
			dao.deleteRegQuestion(post_code);
		}
		else if(post_code.substring(0, 2) == "BA")
		{	
			dao.deleteRhAnswer(post_code);
			dao.deleteRegAnswer(post_code);
		}
		else if(post_code.substring(0, 2) == "BR")
		{	
			dao.deleteRhReview(post_code);
			dao.deleteRegReview(post_code);
		}
		else if(post_code.substring(0, 2) == "SO")
		{	
			dao.deleteRhStudy(post_code);
			dao.deleteRegStudy(post_code);
		}
		
		String[] report_user_code = request.getParameterValues("report_user_code");
		HashMap<String, String> param;
		
		// 경고등록(허위신고)
		for (int i = 0; i < report_user_code.length; i++)
		{
			param = new HashMap<String, String>();
			param.put("reported_user_code", report_user_code[i]);
			sqlSession.selectOne("com.studyit.mybatis.com.studyit.mybatis.IReport_handle_DAO.insertWarning", param);
		}
		
		// 원래 있었던 페이지 호출
		model.addAttribute("result", "UpdateAndWarningSuccess");
		model.addAttribute("reported_parti_code", request.getParameter("reported_parti_code"));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		result = "redirect:memreportdetail.action";
		
		return result;
	}	
}
