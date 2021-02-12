package com.studyit.mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report_list_Controller
{
	@Autowired
	private SqlSession sqlSession;
	
	// 신고처리할 스터디원 리스트 조회
	@RequestMapping (value="participantreportlist.action", method=RequestMethod.GET)
	public String memList(Model model, HttpServletRequest request)
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
		else if (session.getAttribute("admin") == null)		//-- 로그인은 되었지만 관리자가 아닌 상황
		{
			// 관리자가 아닌 상황 즉, 일반 사원일 때의 처리
			//-- 일반 사원으로 로그인되어있는 상황을 해제하고
			result = "redirect:logout.action";
			return result;
		}	
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
		
		result = "/WEB-INF/views/Participant_report_list.jsp";
		IParticipant_report_list_DAO dao = sqlSession.getMapper(IParticipant_report_list_DAO.class);
		
		Page page = new Page();
		
		// 페이지 번호 확인
		String pageNum = request.getParameter("pageNum");
		
		// 현재 페이지
		int currentPage = 1;
		
		// 넘어온 페이지번호가 있으면 현재 페이지를 해당 숫자로 바꾸기
		if(pageNum != null)
			currentPage = Integer.parseInt(pageNum);
		
		// 전체 데이터 갯수 구하기
		int dataCount = dao.countAll();
		
		// 총 페이지 수 계산
		int numPerPage = 10;
		int totalPage = page.getPageCount(numPerPage, dataCount);
		
		// 전체 페이지 수보다 표시할 페이지가 큰 경우
		// (그 사이 데이터 삭제해서 페이지 줄었을 경우) 표시할 페이지를 마지막 페이지로 구성
		if (currentPage > totalPage)
			currentPage = totalPage;
		
		// 데이터 베이스에서 가져올 게시물의 시작과 끝
		int start = (currentPage-1) * numPerPage + 1;
		int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage;
		int start2 = dataCount%end+1;
		int end2 = start2+(end-start);
		
		// 페이징
		String listUrl = "participantreportlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);
		
		// 글 내용 보기 주소
		String articleUrl = "pageNum=" + currentPage;
		
		
		model.addAttribute("pageIndexList", pageIndexList);
		model.addAttribute("list", dao.list(start2, end2));
		model.addAttribute("countUntreat", dao.countUntreat());
		model.addAttribute("articleUrl", articleUrl);
		
		return result;
	}
	

	// 신고처리할 게시판 신고 리스트 출력
	@RequestMapping(value="boardreportlist.action", method=RequestMethod.GET)
	public String boardList(Model model, HttpServletRequest request)
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
		else if (session.getAttribute("admin") == null)		//-- 로그인은 되었지만 관리자가 아닌 상황
		{
			// 관리자가 아닌 상황 즉, 일반 사원일 때의 처리
			//-- 일반 사원으로 로그인되어있는 상황을 해제하고
			result = "redirect:logout.action";
			return result;
		}	
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)
		
		result = "/WEB-INF/views/Board_report_list.jsp";
		IBoard_report_list_DAO dao = sqlSession.getMapper(IBoard_report_list_DAO.class);
		Page page = new Page();
		
		// 페이지 번호 확인
		String pageNum = request.getParameter("pageNum");
		
		// 현재 페이지
		int currentPage = 1;
		
		// 넘어온 페이지번호가 있으면 현재 페이지를 해당 숫자로 바꾸기
		if(pageNum != null)
			currentPage = Integer.parseInt(pageNum);
		
		// 전체 데이터 갯수 구하기
		int dataCount = dao.countAll();
		
		// 총 페이지 수 계산
		int numPerPage = 10;
		int totalPage = page.getPageCount(numPerPage, dataCount);
		
		// 전체 페이지 수보다 표시할 페이지가 큰 경우
		// (그 사이 데이터 삭제해서 페이지 줄었을 경우) 표시할 페이지를 마지막 페이지로 구성
		if (currentPage > totalPage)
			currentPage = totalPage;
		
		// 데이터 베이스에서 가져올 게시물의 시작과 끝
		int start = (currentPage-1) * numPerPage + 1;
		int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage;
		int start2 = dataCount%end+1;
		int end2 = start2+(end-start);
		
		// 페이징
		String listUrl = "participantreportlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);
		
		// 글 내용 보기 주소
		String articleUrl = "pageNum=" + currentPage;
		
		
		model.addAttribute("pageIndexList", pageIndexList);
		model.addAttribute("list", dao.list(start2, end2));
		model.addAttribute("countUntreat", dao.countUntreat());
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("countUntreat", dao.countUntreat());
		
		return result;
	}
	
	// 등록된 게시물 신고 리스트 조회
	@RequestMapping(value="/reportdetail.action", method=RequestMethod.GET)
	public String boardReportList(Model model, HttpServletRequest request)
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
		else if (session.getAttribute("admin") == null)		//-- 로그인은 되었지만 관리자가 아닌 상황
		{
			// 관리자가 아닌 상황 즉, 일반 사원일 때의 처리
			//-- 일반 사원으로 로그인되어있는 상황을 해제하고
			result = "redirect:logout.action";
			return result;
		}	
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
		
		result = "/WEB-INF/views/Report_detailpage.jsp";
		IReport_detail_DAO dao = sqlSession.getMapper(IReport_detail_DAO.class);
		
		model.addAttribute("list", dao.list(request.getParameter("post_code")));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		
		return result;
	}
	
	// 등록된 스터디원 신고 리스트 출력
	@RequestMapping(value="/memreportdetail.action", method=RequestMethod.GET)
	public String memReportList(Model model, HttpServletRequest request)
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
		else if (session.getAttribute("admin") == null)		//-- 로그인은 되었지만 관리자가 아닌 상황
		{
			// 관리자가 아닌 상황 즉, 일반 사원일 때의 처리
			//-- 일반 사원으로 로그인되어있는 상황을 해제하고
			result = "redirect:logout.action";
			return result;
		}	
		// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
		
		result = "/WEB-INF/views/Report_detailpage.jsp";
		IReport_detail_DAO dao = sqlSession.getMapper(IReport_detail_DAO.class);
		
		model.addAttribute("memList", dao.memList(request.getParameter("reported_parti_code")));
		model.addAttribute("pageNum", request.getParameter("pageNum"));
		
		
		return result;
	}
	
}
