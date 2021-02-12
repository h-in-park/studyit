/*=======================================
	FreeBoardController.java
	- 자유게시판 리스트 조회 컨트롤러
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
public class FreeBoardController
{
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="freeboardlist.action", method=RequestMethod.GET)
	public String list(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException
	{
		String result = null;

		// 로그인한 사람이 관리자인지 구분하여 넘겨주기 위함
		HttpSession session = request.getSession();		

		//요청 페이지(jsp)
		result = "/WEB-INF/views/FreeBoard_list.jsp";
		
		// DAO 인터페이스 인스턴스 생성
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		// Page클래스 인터페이스 생성
		Page page = new Page();
		
		// 페이징처리--------------------------------------------------------------------------
		
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
		
		//검색 기능을 통해 페이지가 요청되었을 경우...
		if(searchKey != null)
		{	
			// 넘어온 값이 GET 방식이라면...
			if(request.getMethod().equalsIgnoreCase("GET"))
			{
				// 디코드 처리
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
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
			searchValue = "";
		}
		
		// 전체 데이터 갯수 구하기 → 검색 기능 추가 이후
		BoardListDTO dto = new BoardListDTO();
		dto.setSearchKey(searchKey);
		dto.setSearchValue(searchValue);
		
		int dataCount = dao.getDataCount(searchKey, searchValue);
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
		
		
		// 페이징
		String param = "";
		String listUrl = "freeboardlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 검색값이 존재한다면...
		if(!searchValue.equals(""))
		{
			param += "?searchKey=" + searchKey;	
			param += "&searchValue=" + searchValue;
		}
		
		// 글 내용 보기 주소
		//String articleUrl = "pageNum=" + currentPage;
		String articleUrl;
		
		if(param.equals(""))
			articleUrl = "?pageNum=" + currentPage;	
		else
			articleUrl = param + "&pageNum=" + currentPage; 
		//--------------------------------------------------------------------------페이징처리
		dto.setStart(start2);
		dto.setEnd(end2);
		dto.setSearchKey(searchKey);
		dto.setSearchValue(searchValue);
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		//model.addAttribute("list", dao.list(start2, end2)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("list", dao.getList(dto));
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("totalPage", totalPage);
		//-- pageNum을 보내줘서 상세페이지 요청할 때 "&pageNum=1" 이런식으로 옆에 붙을 수 있게 추가해야 함.
		//   + 상세페이지에서 리스트로 돌아갈 때 list.action?pageNum=${param.pageNum} 이런식으로 요청해야 원래 있던 페이지로 돌아감.
		//  (목록으로 눌렀을 때 무조건 1페이지로 안가고 원래 있던 페이지로 가기 위함)
		
		return result;
		
	}
	
	// 자유게시판 게시글 작성 폼
	@RequestMapping(value = "/freeboard_insert_form.action", method = RequestMethod.GET)
	public String freeBoardInsertForm(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			result = "/WEB-INF/views/FreeBoard_write.jsp";
		}
		
		return result;
	}
	
	// 자유게시판 글 작성
	@RequestMapping(value = "/freeboard_insert.action", method = RequestMethod.GET)
	public String freeBoardInsert(HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		HttpSession session = request.getSession();
		String result = null;
		
		if (session.getAttribute("code")==null)
		{
			result = "redirect:loginform.action";
		}
		else
		{	
			FreeBoardDTO post = new FreeBoardDTO();
			
			String user_code = (String)session.getAttribute("code");
			String title = request.getParameter("title");
			String content = request.getParameter("editordata");
			
			post.setUser_code(user_code);
			post.setTitle(title);
			post.setContent(content);
			
			dao.freeBoardInsert(post);
			
			result = "redirect:freeboardlist.action";
		}
		
		return result;
	}
	
	
	// 자유게시판 아티클 조회 
	@RequestMapping(value = "/freeboard_detail.action", method = RequestMethod.GET)
	public String freeBoardDetail(Model model, HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		String post_code = request.getParameter("post_code");
		
		dao.hitcount(post_code);
		
		FreeBoardDTO article = dao.freeBoard_Detail(post_code);
		model.addAttribute("article", article);
		
		//String write_user_code = article.getUser_code();
		//HttpSession session = request.getSession();
		//session.setAttribute("write_user_code", write_user_code);
		
		
		ArrayList<FreeBoardCmtDTO> cmtList = dao.comment(post_code);
		model.addAttribute("cmtList", cmtList);
		
		int cmtCount = dao.commentCnt(post_code);
		model.addAttribute("cmtCount", cmtCount);
		
		String user_code = (String)request.getSession().getAttribute("code");
		int recCheck = 0;
		int rptCheck = 0;
		if (user_code != null)
		{
			recCheck = dao.recCheck(post_code, user_code);
			rptCheck = dao.rptCheck(post_code, user_code);
			
		}
		model.addAttribute("recCheck", recCheck);
		model.addAttribute("rptCheck", rptCheck);
		
		try
		{
			IInterviewDAO dao2 = sqlSession.getMapper(IInterviewDAO.class);
			model.addAttribute("reportctg", dao2.reportctg());
			
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		}
		

		
		return "/WEB-INF/views/FreeBoard_detail(작성자).jsp";
	}
	
	@RequestMapping(value = "/freeboardreport.action", method = RequestMethod.POST)
	public String report(InterviewDTO dto, HttpServletRequest request) throws SQLException
	{
		String page="redirect:freeboardreport.action";
		
		IInterviewDAO dao = sqlSession.getMapper(IInterviewDAO.class);
		
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
		sqlSession.selectOne("com.studyit.mybatis.IFreeBoardDAO.insertReport", param);
		
		return "redirect:freeboard_detail.action?post_code="+post_code;
	}
	
	// 게시글 추천 / 비추천
	@RequestMapping(value = "/freeboard_rec.action", method = RequestMethod.GET)
	public String rec(HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		String post_code = request.getParameter("post_code");
		int check = Integer.parseInt(request.getParameter("check")); // 추천은 1넘기고 비추천은 2넘김
		
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
					result =  "redirect:freeboard_detail.action?post_code=" + post_code;
				}
				else
				{
					dao.notrec(post_code, user_code); // 비추천 메소드 호출
					result =  "redirect:freeboard_detail.action?post_code=" + post_code;
				}
				
			}
		}
		
		return result;
	}
	
	// 자유게시판 댓글 작성
	@RequestMapping(value = "/addcomment.action", method = RequestMethod.GET)
	public String addComment(FreeBoardCmtDTO comment, HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
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
			result =  "redirect:freeboard_detail.action?post_code=" + post_code;
		}
		
		return result;
	}
	
	// 자유게시판 게시물 삭제
	@RequestMapping(value = "/freeboard_delete.action", method = RequestMethod.GET)
	public String removePost(HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		String post_code = request.getParameter("post_code");
		
		dao.freeBoardDelete(post_code);
				 
		return "redirect:freeboardlist.action";
	}
	
	// 자유게시판 게시글 수정 폼
	@RequestMapping(value = "/freeboard_update_form.action", method = RequestMethod.GET)
	public String freeBoardUpdateForm(HttpServletRequest request, Model model)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		String post_code = request.getParameter("post_code");
		//System.out.println(post_code);
		
		FreeBoardDTO post = dao.freeBoard_Detail(post_code);
		model.addAttribute("post", post);
		
		return "/WEB-INF/views/FreeBoard_update.jsp";
	}
	
	// 자유게시판 게시물 수정
	@RequestMapping(value = "/freeboard_update.action", method = RequestMethod.GET)
	public String updatePost(HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		String post_code = request.getParameter("post_code");
		String title = request.getParameter("title");
		String content = request.getParameter("editordata");
		
		FreeBoardDTO post = new FreeBoardDTO();
		post.setPost_code(post_code);
		post.setTitle(title);
		post.setContent(content);
		
		dao.freeBoardUpdate(post_code, title, content);
		
		return "redirect:freeboardlist.action";
	}
	
	@RequestMapping(value = "deletecomment.action", method = RequestMethod.GET)
	public String deleteComment(HttpServletRequest request)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		String post_code = request.getParameter("post_code");
		String comment_code = request.getParameter("comment_code");
		
		System.out.println(post_code);
		System.out.println(comment_code);
		
		dao.deleteComment(comment_code);
		
		return "redirect:freeboard_detail.action?post_code=" + post_code;
	}
	
	// 자유게시판 댓글 수정 폼
	@RequestMapping(value = "/updatecommentform.action", method = RequestMethod.GET)
	public String updateformComment(HttpServletRequest request, Model model)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		String post_code = request.getParameter("post_code");
		String comment_code = request.getParameter("comment_code");
		String comments = request.getParameter("comments");
		
		model.addAttribute("post_code", post_code);
		model.addAttribute("comment_code", comment_code);
		model.addAttribute("comments", comments);
		
		return "/WEB-INF/views/FreeBoard_updateComment.jsp";
	}
	
	// 자유게시판 댓글 수정 액션
	@RequestMapping(value = "/updatecomment.action", method = RequestMethod.GET)
	public String updateComment(HttpServletRequest request, Model model)
	{
		IFreeBoardDAO dao = sqlSession.getMapper(IFreeBoardDAO.class);
		
		String post_code = request.getParameter("post_code");
		String comment_code = request.getParameter("comment_code");
		String comments = request.getParameter("comments");
		
		dao.updateComment(comment_code, comments);
		
		return "redirect:freeboard_detail.action?post_code=" + post_code;
	}
	
	// 자유게시판 신고 등록 폼
	@RequestMapping(value = "freeboard_report_form.action", method = RequestMethod.GET)
	public String boardReport(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession();
		String post_code = request.getParameter("post_code");
		String title = request.getParameter("title");
		String userid = (String) session.getAttribute("userid");
		
		System.out.println(post_code);
		System.out.println(title);
		System.out.println(userid);
		
		model.addAttribute("post_code", post_code);
		model.addAttribute("title", title);
		model.addAttribute("userid", userid);
		
		//String rank = userid로 rank 얻어오기
		
		return "/WEB-INF/views/Board_report_register.jsp";
		
	}
	
}
