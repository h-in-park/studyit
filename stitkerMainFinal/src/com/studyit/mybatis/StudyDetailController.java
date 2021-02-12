package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudyDetailController {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 상세페이지 조회
	@RequestMapping(value="/studydetail.action", method=RequestMethod.GET)
	public String studyDetail(ModelMap model, HttpServletRequest request)
	{
		String result = null;
		result = "WEB-INF/views/Study_studyDetail.jsp";
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		
		String study_code = request.getParameter("study_code");
		HttpSession session = request.getSession();
		String user_code = (String)session.getAttribute("code");
		
		// 댓글 페이징 처리----------------
		Page page = new Page();
		
		// 페이지 번호 확인
		String pageNum = request.getParameter("pageNum");
		
		// 현재 페이지
		int currentPage;

		// 전체 데이터 갯수 구하기
		int dataCount = dao.commentCount(study_code);
		if (dataCount != 0)
		{
			// 총 페이지 수 계산
			int numPerPage = 5;
			int totalPage = page.getPageCount(numPerPage, dataCount);
			
			// 넘어온 페이지번호가 있으면 현재 페이지를 해당 숫자로 바꾸기
			if(pageNum != null)
				currentPage = Integer.parseInt(pageNum);
			else
				currentPage = totalPage;
			
			// 데이터 베이스에서 가져올 댓글의 시작과 끝
			int start = (currentPage-1) * numPerPage + 1;
			int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage;
			int start2 = dataCount%end+1;
			int end2 = start2+(end-start);
			
			// 페이징
			String listUrl = "studydetail.action?study_code="+study_code;
			String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);
			
			// 글 내용 보기 주소
			String articleUrl = "pageNum=" + currentPage;
			model.addAttribute("pageIndexList", pageIndexList);
			model.addAttribute("comment", dao.comment(study_code, start, end));
		}
		
		// 조회수 증가시키기
		dao.addHitCount(study_code);
		
		model.addAttribute("detail", dao.studyDetail(study_code));
		model.addAttribute("weekCount", dao.studyCount(study_code));
		model.addAttribute("applyCount", dao.applyCount(study_code));
		model.addAttribute("weekDay", dao.weekday(study_code));
		model.addAttribute("applyMem", dao.applyMem(study_code));
		model.addAttribute("commentCount", dao.commentCount(study_code));
		model.addAttribute("recomCount", dao.recomCount(study_code));
		model.addAttribute("unRecomCount", dao.unRecomCount(study_code));
		model.addAttribute("reportctg", dao.getReportCtg());
		if (user_code != null) {
			String check = "different";
			if (user_code.equals(dao.getWriterCode(study_code))) {
				check = "same";
			}
			model.addAttribute("recomCheck", dao.checkRec(study_code, user_code));
			model.addAttribute("chkReport", dao.checkReport(study_code, user_code));
			model.addAttribute("check", check);
			model.addAttribute("applyCheck", dao.checkApply(study_code, user_code));
		}
		
		
		return result;
	}
	
	// 추천 등록
	@RequestMapping(value="/insertStudyRecom.action", method = RequestMethod.GET)
	public String insertRecom(ModelMap model, HttpServletRequest request)
	{
		String result = null;
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		result = "redirect:studydetail.action";
		
		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어있지 않은 상황에서의 처리
			result = "redirect:loginform.action";
			return result;
		}
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
		
		dao.insertRecom(request.getParameter("study_code"), (String)session.getAttribute("code"));
		model.addAttribute("study_code", request.getParameter("study_code"));
		
		return result;
	}
	
	// 비추천 등록	
	@RequestMapping(value="/insertStudyUnRecom.action", method = RequestMethod.GET)
	public String isnertUnRecom(ModelMap model, HttpServletRequest request)
	{
		String result = null;
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		result = "redirect:studydetail.action";
		
		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어있지 않은 상황에서의 처리
			result = "redirect:loginform.action";
			return result;
		}
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
		
		dao.insertUnRecom(request.getParameter("study_code"), (String)session.getAttribute("code"));
		model.addAttribute("study_code", request.getParameter("study_code"));
		
		return result;
	}
	
	// 댓글 등록	
	@RequestMapping(value="/insertStudyComm.action", method = RequestMethod.POST)
	public String insertComm(ModelMap model, HttpServletRequest request, Study_comment_DTO dto)
	{
		String result = null;
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		result = "redirect:studydetail.action";

		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어있지 않은 상황에서의 처리
			result = "redirect:loginform.action";
			return result;
		}
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
		
		dao.insertComm(dto);
		model.addAttribute("study_code", request.getParameter("study_code"));
		
		return result;
	}
	
	// 스터디 신청
	@RequestMapping(value="/applyStudy.action", method = RequestMethod.GET)
	public String insertApply(ModelMap model, HttpServletRequest request)
	{
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
		
		String user_code = (String)session.getAttribute("code");
		String study_code = request.getParameter("study_code");
		
		result = "redirect:studydetail.action";
		model.addAttribute("study_code", study_code);
		
		HashMap<String, String> applyParam = new HashMap<String, String>();
		applyParam.put("study_code", study_code);
		applyParam.put("user_code", user_code);
		
		//try {
			sqlSession.selectOne("com.studyit.mybatis.IStudy_detail_DAO.insertApply", applyParam);
		//} catch (Exception e) {
		//	System.out.println(e.toString());
		//}
		return result;
	}
	
	// 스터디 신청 자격 확인
	@RequestMapping(value="/applyCheck.action", method = RequestMethod.POST)
	public String applyCheck(Model model, HttpServletRequest request)
	{
		String result = null;
		
		ICheckDAO dao = sqlSession.getMapper(ICheckDAO.class);
		String message = null;
		
		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어있지 않은 상황에서의 처리
			result = "redirect:loginform.action";
			return result;
		}
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)			
		
		String user_code = (String)session.getAttribute("code");
		String study_code = request.getParameter("study_code");
		
		if(dao.checkSuspend(user_code) != null && Integer.parseInt(dao.checkSuspend(user_code)) > 0 ) {
			message = "해당 계정은 계정정지상태이므로 스터디를 신청할 수 없습니다.";
		}
		else if(dao.checkThisStudy(study_code, user_code) > 0)
		{
			message = "해당 스터디는 현재 신청한 스터디이거나 신청한 이력이 존재하여 신청할 수 없습니다.";
		}
		else if (dao.checkStudyRank(study_code) > dao.checkUserRank(user_code)) {
			message = "참여가능 등급보다 등급이 낮아 해당 스터디를 신청할 수 없습니다.";
		}
		else if(dao.checkStudyCnt(user_code) != null && Integer.parseInt(dao.checkStudyCnt(user_code)) >= 3){
			message = "동시에 참여중인 스터디는 3개 이하만 가능합니다.";
		}
		
		model.addAttribute("message", message);
		result = "WEB-INF/views/StudyInsertCheck.jsp";
		return result;
	}
	
	// 스터디 취소
	@RequestMapping(value="/cancelStudy.action", method = RequestMethod.GET)
	public String insertCancel(ModelMap model, HttpServletRequest request)
	{
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
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		
		HashMap<String, String> cancelParam = new HashMap<String, String>();
		String apply_code = dao.getApplyCode(request.getParameter("study_code"), (String)session.getAttribute("code"));
		cancelParam.put("apply_code", apply_code);
		
		sqlSession.selectOne("com.studyit.mybatis.IStudy_detail_DAO.insertCancel", cancelParam);
		
		result = "redirect:studydetail.action";		
		model.addAttribute("study_code", request.getParameter("study_code"));
		
		return result;
	}
	
	// 확인 버튼 클릭
	@RequestMapping(value="/updateDate.action", method = RequestMethod.GET)
	public String updateDate(ModelMap model, HttpServletRequest request)
	{
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
			
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		String apply_code = dao.getApplyCode(request.getParameter("study_code"), (String)session.getAttribute("code"));
		dao.updateApplyDate(apply_code);
		
		result = "redirect:studydetail.action";		
		model.addAttribute("study_code", request.getParameter("study_code"));
		
		return result;
	}
	
	// 스터디 마감
	@RequestMapping(value="/fixStudy.action", method = RequestMethod.POST)
	public String fixStudy(ModelMap model, HttpServletRequest request)
	{
		String result = null;
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		String study_code = request.getParameter("study_code");
		String apply_code;

		// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
		HttpSession session = request.getSession();
		
		if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
		{
			// 로그인이 되어있지 않은 상황에서의 처리
			result = "redirect:loginform.action";
			return result;
		}
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
		
		String[] fixMem = request.getParameterValues("fixMem");
		for (int i = 0; i < fixMem.length; i++) {
			apply_code = dao.getApplyCode(study_code, fixMem[i]);
			dao.insertParti(apply_code);
		}
		
		dao.updateEndDate(study_code);
		
		result = "redirect:studydetail.action";		
		model.addAttribute("study_code", study_code);
		
		return result;
	}
	
	// 댓글 수정하기 위해 댓글 입력창에 댓글 가져오기
	@RequestMapping(value="/getstudycomm.action", method = RequestMethod.POST)
	public String getComm(Model model, HttpServletRequest request)
	{
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
		
		result = "WEB-INF/views/GetStudyComm.jsp";
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		
		Study_comment_DTO dto = dao.getComm(request.getParameter("comment_code"));
		model.addAttribute("comment", dto.getComments());
		
		return result;
	}
	
	// 댓글 수정
	@RequestMapping(value="/modifyStudyComm.action", method = RequestMethod.POST)
	public String modifyComm(Model model, HttpServletRequest request, Study_comment_DTO dto)
	{
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
		
		String study_code = request.getParameter("study_code");
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		
		dao.modifyComm(dto);
		
		result = "redirect:studydetail.action?study_code="+study_code;
		return result;
	}
	
	// 댓글 삭제
	@RequestMapping(value="/deleteStudyComm.action", method = RequestMethod.GET)
	public String deleteComm(ModelMap model, HttpServletRequest request)
	{
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
		
		IStudy_detail_DAO dao = sqlSession.getMapper(IStudy_detail_DAO.class);
		dao.deleteComm(request.getParameter("comment_code"));
		
		result = "redirect:studydetail.action?study_code="+request.getParameter("study_code");
		
		return result;
	}
	
	// 신고 등록
	@RequestMapping(value = "/studyreport.action", method = RequestMethod.POST)
	public String report(InterviewReportDTO dto, HttpServletRequest request) throws SQLException
	{
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
		
		result="redirect:studydetail.action?study_code="+dto.getPost_code();
		
		String user_code = (String)session.getAttribute("code");
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_code", user_code);
		param.put("post_code", dto.getPost_code());
		param.put("report_reason", dto.getReport_reason());
		param.put("post_report_ctg_code", dto.getPost_report_ctg_code());
		
		// 경고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.IStudy_detail_DAO.addReport", param);
		
		return result;
	}	

	
}
