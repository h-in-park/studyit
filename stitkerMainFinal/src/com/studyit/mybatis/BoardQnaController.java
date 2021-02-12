/*========================================
	BoardQnaController.java
	- 컨트롤러
	- 질문 답변 게시판 관련 메소드
========================================*/

package com.studyit.mybatis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class BoardQnaController
{
	@Autowired
	private SqlSession sqlSession;
	
	// 컨트롤러 안에 리스트 메소드 : 검색기능 없음.
	@RequestMapping(value="questionlist.action", method=RequestMethod.GET)
	public String list(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException
	{
		String result = null;
		
		// 로그인한 사람이 관리자인지 구분하여 넘겨주기 위함
		HttpSession session = request.getSession();		

		//요청 페이지(jsp)
		result = "/WEB-INF/views/Q&A_list.jsp";
		
		// DAO 인터페이스 인스턴스 생성
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class); 
		model.addAttribute("imList", interest.imList());
		
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
		
		int dataCount = dao.getDataCount(searchKey, searchCategory, searchValue);
		if (dataCount == 0)
			dataCount++;
		
		// 전체 데이터 갯수 구하기
		//int dataCount = dao.count();
		
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
		
		System.out.println(start2);
		System.out.println(end2);
		
		// 페이징
		String param = "";
		String listUrl = "questionlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 검색값이 존재한다면...
		if(!searchValue.equals(""))
		{
			param += "?searchKey=" + searchKey;	
			param += "&searchCategory=" + searchCategory; 
			param += "&searchValue=" + searchValue;
		}
		
		// 글 내용 보기 주소
		//String articleUrl = "pageNum=" + currentPage;
		String articleUrl;
		
		if(param.equals(""))
			articleUrl = "?pageNum=" + currentPage;	// 여기도 &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
		else
			articleUrl = param + "&pageNum=" + currentPage; 
		//--------------------------------------------------------------------------페이징처리
		dto.setStart(start2);
		dto.setEnd(end2);
		dto.setSearchKey(searchKey);
		dto.setSearchCategory(searchCategory);
		dto.setSearchValue(searchValue);
		
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		//model.addAttribute("list", dao.qnaList(start2, end2)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("list", dao.getList(dto));
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("totalPage", totalPage);
		
		//-- pageNum을 보내줘서 상세페이지 요청할 때 "&pageNum=1" 이런식으로 옆에 붙을 수 있게 추가해야 함.
		//   + 상세페이지에서 리스트로 돌아갈 때 list.action?pageNum=${param.pageNum} 이런식으로 요청해야 원래 있던 페이지로 돌아감.
		//  (목록으로 눌렀을 때 무조건 1페이지로 안가고 원래 있던 페이지로 가기 위함)
		
		return result;
		
	}
	
	// Q&A 리스트 출력 액션
	//@RequestMapping(value = "/questionlist.action", method = RequestMethod.GET)
	public String qnaList(Model model)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		//ArrayList<Board_Qna_DTO> qnaList = dao.qnaList();
		//model.addAttribute("qnaList", qnaList);
		
		//IInterestDAO dao1 = sqlSession.getMapper(IInterestDAO.class);
		//ArrayList<InterestDTO> imList = dao1.imList();
		//model.addAttribute("imList", imList);
		
		return "/WEB-INF/views/Q&A_list.jsp";
	}
	
	// Q&A 게시판 글 작성 폼
	@RequestMapping(value = "/qnaboard_insert_form.action", method = RequestMethod.GET)
	public String qnaInsertForm(Model model, HttpServletRequest request)
	{
		IInterestDAO dao = sqlSession.getMapper(IInterestDAO.class);
		ArrayList<InterestDTO> imList = dao.imList();
		model.addAttribute("imList", imList);
		
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			result = "/WEB-INF/views/Q&A_question_write.jsp";
		}
		
		return result;		
	}

	// Q&A 게시판 글 작성 액션
	@RequestMapping(value = "/qnaboard_insert.action", method = RequestMethod.GET)
	public String freeBoardInsert(HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			Board_Qna_DTO post = new Board_Qna_DTO();
			
			String user_code = (String)session.getAttribute("code");
			String interest_mc_code = request.getParameter("interest_mc");
			String title = request.getParameter("title");
			String content = request.getParameter("editordata");
						
			post.setUser_code(user_code);
			post.setInterest_mc_code(interest_mc_code);
			post.setTitle(title);
			post.setContent(content);
			
			dao.qnaInsert(post);
			
			result = "redirect:questionlist.action";
		}
		
		return result;
	}
	
	// Q&A 게시판 아티클 조회 
	@RequestMapping(value = "/qnaboard_detail.action", method = RequestMethod.GET)
	public String freeBoardDetail(Model model, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		
		String post_code = request.getParameter("post_code");
		dao.hitcount(post_code);
		
		Board_Qna_DTO post = dao.qnaDetail(post_code);
		model.addAttribute("post", post);
		
		ArrayList<FreeBoardCmtDTO> cmtList = dao.commentList(post_code);
		model.addAttribute("cmtList", cmtList);
		
		int cmtCount = dao.commentCnt(post_code);
		model.addAttribute("cmtCount", cmtCount);
		
		String user_code = (String)request.getSession().getAttribute("code");
		int recCheck = 0, answerRecCheck = 0;
		int rptCheck = 0, answerRptCheck = 0;
		if (user_code != null)
		{
			recCheck = dao.recCheck(post_code, user_code);
			rptCheck = dao.rptCheck(post_code, user_code);
			// post_code가 현재 질문 코드인데, 답변 코드 가져와야함.
			// 지금 상태 : 답변들중 하나라도 추천했으면 모든 답변들의 추천버튼 비활성화 됨.
			answerRecCheck = dao.AnswerRecCheck(post_code, user_code);
			//answerRptCheck = dao.AnswerRptCheck(post_code, user_code);
		}
		model.addAttribute("recCheck", recCheck);
		model.addAttribute("rptCheck", rptCheck);
		model.addAttribute("answerRecCheck", answerRecCheck);
		//model.addAttribute("answerRptCheck", answerRptCheck);
		
		ArrayList<Board_Qna_DTO> answerList = dao.answerList(post_code);
		model.addAttribute("answerList", answerList);

		try
		{
			IInterviewDAO dao2 = sqlSession.getMapper(IInterviewDAO.class);
			model.addAttribute("reportctg", dao2.reportctg());
			
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		
		return "/WEB-INF/views/Q&A_detail.jsp";
	}
	
	@RequestMapping(value = "/qnareport.action", method = RequestMethod.POST)
	public String report(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:qnareport.action";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code") == null) 
		{
			page = "redirect:loginform.action";
			return page;
		}
		
		String user_code = (String)session.getAttribute("code");
		String post_code = request.getParameter("post_code");	
		String post_report_ctg_code = request.getParameter("post_report_ctg_code");
		String report_reason = request.getParameter("report_reason");
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_code", user_code);
		param.put("post_code", post_code);
		param.put("report_reason", report_reason);
		param.put("post_report_ctg_code", post_report_ctg_code);
		
		// 신고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.IBoard_Qna_DAO.insertReport", param);
		
		return "redirect:qnaboard_detail.action?post_code="+post_code;
	}
	
	// Q&A 게시판 게시글 수정 폼
	@RequestMapping(value = "/qnaboard_update_form.action", method = RequestMethod.GET)
	public String freeBoardUpdateForm(HttpServletRequest request, Model model)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		String post_code = request.getParameter("post_code");
		//System.out.println(post_code);
		
		IInterestDAO dao1 = sqlSession.getMapper(IInterestDAO.class);
		ArrayList<InterestDTO> imList = dao1.imList();
		model.addAttribute("imList", imList);
		
		Board_Qna_DTO post = dao.qnaDetail(post_code);
		model.addAttribute("post", post);
		
		return "/WEB-INF/views/Q&A_question_modify.jsp";
	}
	
	// Q&A 게시판 게시글 수정
	@RequestMapping(value = "/qnaboard_update.action", method = RequestMethod.GET)
	public String updatePost(HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		String post_code = request.getParameter("post_code");
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		String interest_mc_code = request.getParameter("interest_mc");
		
		Board_Qna_DTO post = dao.qnaDetail(post_code);
		post.setTitle(title);
		post.setContent(content);
		post.setInterest_mc_code(interest_mc_code);
		
		dao.qnaBoardUpdate(post);
		
		return "redirect:qnaboard_detail.action?post_code=" + post_code;
	}
	
	// Q&A 게시판 게시글 삭제
	@RequestMapping(value = "/qnaboard_delete.action", method = RequestMethod.GET)
	public String removePost(HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		String post_code = request.getParameter("post_code");
		
		dao.qnaBoardDelete(post_code);
				 
		return "redirect:questionlist.action";
	}
	
	// 게시글 추천 / 비추천
	@RequestMapping(value = "/qnaboard_rec.action", method = RequestMethod.GET)
	public String rec(HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		
		String post_code = request.getParameter("post_code");
		int check = Integer.parseInt(request.getParameter("check"));
		
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null) // 비로그인 사용자는 로그인 페이지로
		{
			result =  "redirect:loginform.action";
		}
		else
		{
			String user_code = (String) session.getAttribute("code");
			
			if (session.getAttribute("admin")==null)	// 로그인 사용자이면서 어드민이 아닐때만
			{
				if (check == 1)	// 추천버튼 눌렀으면
				{
					dao.rec(post_code, user_code); // 추천 메소드 호출
					result =  "redirect:qnaboard_detail.action?post_code=" + post_code;
				}
				else
				{
					dao.notrec(post_code, user_code); // 비추천 메소드 호출
					result =  "redirect:qnaboard_detail.action?post_code=" + post_code;
				}
			}
		}
		return result;
	}
	
	// Q&A 게시판 댓글 작성
	@RequestMapping(value = "/qna_comment_insert.action", method = RequestMethod.GET)
	public String addComment(FreeBoardCmtDTO comment, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			String post_code = request.getParameter("post_code");
			String commentBox = request.getParameter("commentBox");
			String user_code = (String) session.getAttribute("code");
						
			comment.setPost_code(post_code);
			comment.setComments(commentBox);
			comment.setUser_code(user_code);
			
			dao.addComment(comment);
			result =  "redirect:qnaboard_detail.action?post_code=" + post_code;
		}
		return result;
	}
	
	// Q&A 게시판 답변 작성 폼
	@RequestMapping(value = "/answer_insert_form.action", method = RequestMethod.GET)
	public String answerInsertForm(Model model, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			String qpost_code = request.getParameter("post_code");
			model.addAttribute("qpost_code", qpost_code);

			result = "/WEB-INF/views/Q&A_answer_write.jsp";
		}
		
		return result;		
	}
	
	// Q&A 게시판 답변 작성 액션
	@RequestMapping(value = "/answer_insert.action", method = RequestMethod.GET)
	public String answerInsert(Model model, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		Board_Qna_DTO post = new Board_Qna_DTO();
		
		String qpost_code = request.getParameter("qpost_code");
		String user_code = (String)session.getAttribute("code");
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		
		post.setQpost_code(qpost_code);
		post.setUser_code(user_code);
		post.setTitle(title);
		post.setContent(content);
		
		dao.answerInsert(post);
		
		result = "redirect:qnaboard_detail.action?post_code=" + qpost_code;
		
		return result;		
	}
	
	// Q&A 답변 삭제 액션
	@RequestMapping(value = "/qnaboard_answer_delete", method = RequestMethod.GET)
	public String answerDelete(Model model, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		String post_code = request.getParameter("post_code");
		String qpost_code = request.getParameter("qpost_code");
		
		dao.answerDelete(post_code);
		
		result = "redirect:qnaboard_detail.action?post_code=" + qpost_code;
		
		return result;		
	}
	
	// Q&A 답변 수정 폼
	@RequestMapping(value = "/qnaboard_answer_update_form.action", method = RequestMethod.GET)
	public String updatePostForm(Model model, HttpServletRequest request)
	{
		String post_code = request.getParameter("post_code");
		String qpost_code = request.getParameter("qpost_code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board_Qna_DTO post = new Board_Qna_DTO();
		
		post.setPost_code(post_code);
		post.setQpost_code(qpost_code);
		post.setTitle(title);
		post.setContent(content);
		
		model.addAttribute("post", post);
		
		return "/WEB-INF/views/Q&A_answer_modify.jsp";
	}
	
	// Q&A 답변 수정 액션
	@RequestMapping(value = "/qnaboard_answer_update.action", method = RequestMethod.GET)
	public String updatePost(Model model, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		String user_code = (String) session.getAttribute("code");
		
		String qpost_code = request.getParameter("qpost_code");
		String post_code = request.getParameter("post_code");
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		
		Board_Qna_DTO post = new Board_Qna_DTO();
		post.setPost_code(post_code);
		post.setQpost_code(qpost_code);
		post.setTitle(title);
		post.setContent(content);
		post.setUser_code(user_code);
		
		dao.answerUpdate(post);
		
		return "redirect:qnaboard_detail.action?post_code=" + qpost_code;
	}
	
	// Q&A 게시판 게시물 신고 폼 -- ▲
	@RequestMapping(value = "/qnaboard_report_form.action", method = RequestMethod.GET)
	public String boardReportForm(Model model, HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{
			String post_code = request.getParameter("post_code");
			result = "/WEB-INF/views/Board_report_register.jsp";
		}
	
		return result;
	}
	
	// Q&A 답변 추천/비추천 액션  
	@RequestMapping(value = "/qnaboard_answer_rec.action", method = RequestMethod.GET)
	public String recAnswer(HttpServletRequest request)
	{
		IBoard_Qna_DAO dao = sqlSession.getMapper(IBoard_Qna_DAO.class);
		String qpost_code = request.getParameter("qpost_code");
		String post_code = request.getParameter("post_code");
		int check = Integer.parseInt(request.getParameter("check")); 
		// 추천은 1넘기고 비추천은 2넘김
		
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null) 
		{
			result =  "redirect:loginform.action";
		}
		else
		{
			String user_code = (String) session.getAttribute("code");
			
			if (session.getAttribute("admin")==null)	
			{
				if (check == 1)	
				{
					dao.answerRec(post_code, user_code); 
					result = "redirect:qnaboard_detail.action?post_code=" + qpost_code;
				}
				else
				{
					dao.answerNotrec(post_code, user_code);
					result = "redirect:qnaboard_detail.action?post_code=" + qpost_code;
				}
			}
		}
		return result;
	}	
}
