/*==========================
	MemberMain.java
	- 컨트롤러
==========================*/

package com.studyit.mybatis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class InterviewListController
{
	// mybatis 객체 의존성 (자동) 주입~!!!
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/interviewlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException, SQLException
	{
		String result = null;

		// 로그인한 사람이 관리자인지 구분하여 넘겨주기 위함
		HttpSession session = request.getSession();		

		//요청 페이지(jsp)
		result = "WEB-INF/views/Interview_list.jsp";
		
		// DAO 인터페이스 인스턴스 생성
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class); 
		
		// Page클래스 인터페이스 생성
		Page page = new Page();
		
		//페이징처리--------------------------------------------------------------------------
		// 페이지 번호 확인
		String pageNum = request.getParameter("pageNum");
		
		// 현재 페이지
		int currentPage = 1;
		
		// 넘어온 페이지번호가 있으면 현재 페이지를 해당 숫자로 바꾸기
		if(pageNum != null)
			currentPage = Integer.parseInt(pageNum);
		
		//-- 검색 처리 → 추가 구현
		// 검색 키와 검색 값 수신
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		String searchCategory = request.getParameter("searchCategory");
		
		//검색 기능을 통해 페이지가 요청되었을 경우...
		if(searchKey != null || searchCategory != null)
		{	
			// 넘어온 값이 GET 방식이라면...
			if(request.getMethod().equalsIgnoreCase("GET"))
			{
				// 디코드 처리
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
				searchCategory = URLDecoder.decode(searchCategory, "UTF-8");
			}
			//-- 이와 같은 처리는
			//   GET 은 한글 문자열을 인코딩 해서 전송하기 때문이다.
			if (searchKey == null || searchKey.equals("")) {
				searchKey = "title";
				searchValue = "";
			}
		}
		else
		{
			searchKey = "title";
			searchCategory = "";
			searchValue = "";
		}
		
		// 전체 데이터 갯수 구하기 → 검색 기능 추가 이후
		BoardListDTO dto = new BoardListDTO();
		dto.setSearchKey(searchKey);
		dto.setSearchCategory(searchCategory);
		dto.setSearchValue(searchValue);
		
		int dataCount = dao.count(dto);
		if (dataCount == 0)
			dataCount++;	
		
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
		String param = "";
		
		String listUrl = "interviewlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 검색값이 존재한다면...
		if(!searchValue.equals("") || !searchCategory.equals(""))
		{
			param += "&searchKey=" + searchKey;	// &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
			param += "&searchCategory=" + searchCategory; 
			param += "&searchValue=" + searchValue;
		}
		// 글 내용 보기 주소
		String articleUrl;
		
		if(param.equals(""))
			articleUrl = "&pageNum=" + currentPage;	// 여기도 &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
		else
			articleUrl = param + "&pageNum=" + currentPage; 
		

		//--------------------------------------------------------------------------페이징처리
		dto.setStart(start2);
		dto.setEnd(end2);
		dto.setSearchKey(searchKey);
		dto.setSearchCategory(searchCategory);
		dto.setSearchValue(searchValue);
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		model.addAttribute("list", dao.list(dto));
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("interestlist", dao.interestlist());

		return result;	

	}

	@RequestMapping(value = "/interview_writeform.action", method = RequestMethod.GET)
	public String interviewAddform(ModelMap model) throws SQLException
	{
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		model.addAttribute("interestlist",dao.interestlist());

		return "WEB-INF/views/Interview_write.jsp";

	}

	@RequestMapping(value = "/interview_write.action", method = RequestMethod.POST)
	public String interviewAdd(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String user_code = (String)session.getAttribute("code");
		dto.setUser_code(user_code);
		dto.setInterest_mc_code(request.getParameter("interest_mc_code"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		
		dao.add(dto);
		return page;
	}
	@RequestMapping(value = "/interview_detail.action", method = RequestMethod.GET)
	public String interviewdetail(ModelMap model, HttpServletRequest request, InterviewDTO dto) throws SQLException
	{
		String check = "different";
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		String writer_code = request.getParameter("writer_code");
		String user_code = (String)session.getAttribute("code"); 
		String post_code = request.getParameter("post_code");
		
		if (user_code != null) {
			if(writer_code.equals(user_code))
				check="same";
			int chkReport = dao.chkReport(post_code, user_code);
			model.addAttribute("chkReport", chkReport);
			model.addAttribute("recCheck",dao.recCheck(post_code, user_code));
		}
		
		dao.hitcount(post_code);

		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchCategory = request.getParameter("searchCategory");
		String searchValue = request.getParameter("searchValue");
		// 키가 없으면 title로 설정, 내용은 비어있음
		if(searchKey == null)
		{
			searchKey = "title";
			searchValue = "";
		}
		if(searchCategory == null)
			searchCategory = "";
		
		// 주소 구성
		String articleUrl = "?searchKey="+searchKey+"&searchCategory="+searchCategory+"&searchValue="+searchValue+"&pageNum="+pageNum;
		
		// 하나라도 null이 없는 것이 있으면 주소 보내기
		if (pageNum != null || searchKey != null || searchCategory != null || searchValue != null) {
			model.addAttribute("articleUrl", articleUrl);
		}
		model.addAttribute("check",check);
		model.addAttribute("Commentlist", dao.commentlist(post_code));
		model.addAttribute("list",dao.searchlist(post_code));
		model.addAttribute("reportctg", dao.reportctg());
		model.addAttribute("commentCnt", dao.commentCnt(post_code));
		
		return "WEB-INF/views/Interview_detail.jsp";

	}
	@RequestMapping(value = "/interview_modifyform.action", method = RequestMethod.GET)
	public String interviewModifyForm(ModelMap model,InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="WEB-INF/views/Interview_modify.jsp";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		String userpw = (String)session.getAttribute("userpw");
		
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		model.addAttribute("userpw",userpw);
		model.addAttribute("interest",dao.interestlist());
		String post_code = request.getParameter("post_code");
		model.addAttribute("list",dao.searchlist(post_code));
		
		return page;
	}
	
	@RequestMapping(value = "/modify_cancel.action", method = RequestMethod.GET)
	public String modifyCancel(ModelMap model) throws SQLException
	{
		return "redirect:interviewlist.action";
	}
	
	@RequestMapping(value = "/interview_delete.action", method = RequestMethod.GET)
	public String interviewDelete(ModelMap model,InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		
		String post_code = request.getParameter("post_code");
		dao.remove(post_code);
		return page;
	}
	
	@RequestMapping(value = "/interview_modify.action", method = RequestMethod.POST)
	public String interviewModify(ModelMap model,InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String post_code = request.getParameter("post_code");
		String title = request.getParameter("title");
		String interest_code = request.getParameter("interest_mc_code");
		String content = request.getParameter("content");
		String user_code = request.getParameter("user_code");
		
		dao.interviewModify(post_code, title, interest_code, content);
		
		return "redirect:interview_detail.action?post_code="+post_code + "&user_code=" + user_code;
	}

	@RequestMapping(value = "/rec.action", method = RequestMethod.GET)
	public String recAdd(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String user_code = (String)session.getAttribute("code");
		String write_user_code = (String)session.getAttribute("write_user_code");
		String post_code = request.getParameter("post_code");
		
		dao.addRec(post_code, user_code);
		return "redirect:interview_detail.action?post_code="+post_code + "&user_code=" +write_user_code;
	}
	@RequestMapping(value = "/notrec.action", method = RequestMethod.GET)
	public String notrecAdd(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String user_code = (String)session.getAttribute("code");
		String write_user_code = (String)session.getAttribute("write_user_code");
		String post_code = request.getParameter("post_code");
		
		dao.addNotRec(post_code, user_code);
		return "redirect:interview_detail.action?post_code="+post_code + "&user_code=" +write_user_code;
	}
	
	@RequestMapping(value = "/interviewreport.action", method = RequestMethod.POST)
	public String report(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:interviewlist.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String user_code = (String)session.getAttribute("code");
		String write_user_code = (String)session.getAttribute("write_user_code");
		String post_code = request.getParameter("post_code");	
		String post_report_ctg_code = request.getParameter("post_report_ctg_code");
		String report_reason = request.getParameter("report_reason");
		
		
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_code", user_code);
		param.put("post_code", post_code);
		param.put("report_reason", report_reason);
		param.put("post_report_ctg_code", post_report_ctg_code);
		
		// 경고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.IInterview.insertReport", param);
		
		return "redirect:interview_detail.action?post_code="+post_code + "&user_code=" +write_user_code;
	}
	
	
	@RequestMapping(value = "/insertinterviewcomm.action", method = RequestMethod.POST)
	public String commentsAdd(InterviewDTO dto, HttpServletRequest request, ModelMap model) throws SQLException
	{
		
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			String page = "redirect:loginform.action";
			return page;
		}
		
		
		String user_code = (String)session.getAttribute("code");
		String write_user_code = request.getParameter("user_code");
		String comments = request.getParameter("commentBox");
		String post_code = request.getParameter("post_code");
		
		dao.addComment(post_code, user_code, comments);
		
		
		
		return "redirect:interview_detail.action?post_code="+post_code + "&writer_code=" +write_user_code;
	}	
	
	// 댓글 수정하기 위해 댓글 입력창에 댓글 가져오기
	@RequestMapping(value="/getinterviewcomm.action", method = RequestMethod.POST)
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
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		String comment_code = dao.getComm(request.getParameter("comment_code"));
		model.addAttribute("comment", comment_code);
		
		return result;
	}
	
	// 댓글 수정
	@RequestMapping(value="/modifyinterviewcomm.action", method = RequestMethod.POST)
	public String modifyComm(Model model, HttpServletRequest request)
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
		
		String comment_code = request.getParameter("comment_code");
		String post_code = request.getParameter("post_code");
		String comments = request.getParameter("commentBox");
		String writer_code = request.getParameter("writer_code");
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
		dao.modifyComm(comment_code, comments);
		
		result = "redirect:interview_detail.action?post_code="+post_code+"&writer_code="+writer_code;
		return result;
	}
	
	// 댓글 삭제
	@RequestMapping(value="/deleteinterviewcomm.action", method = RequestMethod.GET)
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
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		dao.deleteComm(request.getParameter("comment_code"));
		
		result = "redirect:interview_detail.action?post_code="+request.getParameter("post_code")+"&writer_code="+request.getParameter("writer_code");
		
		return result;
	}
		
	
	

}
