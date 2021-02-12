/*==========================
	MemberMain.java
	- 컨트롤러
==========================*/

package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;

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
public class StudyReviewController
{
	// mybatis 객체 의존성 (자동) 주입~!!!
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/studyreviewlist.action", method = RequestMethod.GET)
	public String memberList(ModelMap model,  HttpServletRequest request) throws SQLException
	{
		String result = "WEB-INF/views/StudyReview_list.jsp";
		IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		String user_code = (String)session.getAttribute("code");
		
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
		
		// 전체 데이터 갯수 구하기
		int dataCount = dao.count();
		
		// 총 페이지 수 계산
		int numPerPage = 10;
		int totalPage = page.getPageCount(numPerPage, dataCount); 
		
		
		if (currentPage > totalPage)
			currentPage = totalPage;
		
		// 데이터 베이스에서 가져올 게시물의 시작과 끝
		int start = (currentPage-1) * numPerPage + 1; 
		int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage; 
		int start2 = dataCount%end+1; 
		int end2 = start2+(end-start); 
		
		
		// 페이징
		String listUrl = "studyreviewlist.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 글 내용 보기 주소
		String articleUrl = "pageNum=" + currentPage;
		//--------------------------------------------------------------------------페이징처리
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		model.addAttribute("list", dao.list(start2, end2)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("totalPage", totalPage);
		
		return result;
		
	}
	
	// 후기 상세 페이지 
	@RequestMapping(value="/reviewdetail.action", method = RequestMethod.GET)
	public String detail(ModelMap model,  HttpServletRequest request)
	{
		String result = "WEB-INF/views/StudyReview_detail(작성자).jsp";
		IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
		String post_code = request.getParameter("post_code");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		String user_code = (String)session.getAttribute("code");
		//System.out.println(user_code);
		dao.hitcount(post_code);
		
		StudyReviewDTO article = dao.detail(post_code);
		model.addAttribute("article", article);
		
		ArrayList<StudyReviewDTO> cmtList = dao.commentList(post_code);
		model.addAttribute("cmtList", cmtList);
		
		int cmtCount = dao.commentCnt(post_code);
		model.addAttribute("cmtCount", cmtCount);
		
		  int recCheck = 0;
		  int rptCheck = 0; 
		  if (user_code != null) 
		  {
			 recCheck = dao.recCheck(post_code, user_code); 
			 // rptCheck = dao.rptCheck(post_code,user_code);
		  
		  } 
		  model.addAttribute("recCheck", recCheck); 
		 // model.addAttribute("rptCheck", rptCheck);
		 
		
		return result;
	}

		// 후기 작성 폼
		@RequestMapping(value = "/review_insert_form.action", method = RequestMethod.GET)
		public String reviewInsertForm(HttpServletRequest request,ModelMap model) throws SQLException
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			HttpSession session = request.getSession();
			String result = null;
			String studycode = request.getParameter("studycode");
			if (session.getAttribute("code")==null)
			{
				result = "redirect:loginform.action";
			}
			else
			{	
				result = "/WEB-INF/views/StudyReview_write.jsp";
			}
			model.addAttribute("interest", dao.interestlist(studycode)); 
			return result;
		}
		
		// 후기 글 입력
		@RequestMapping(value = "/review_insert.action", method = RequestMethod.POST)
		public String reviewInsert(HttpServletRequest request)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			HttpSession session = request.getSession();
			String result = null;
			
			if (session.getAttribute("code")==null)
			{
				result = "redirect:loginform.action";
			}
			else
			{	
				StudyReviewDTO post = new StudyReviewDTO();
				
				String parti_code = request.getParameter("parti_code");
				//System.out.println(parti_code);
				String interest_mc_code = request.getParameter("interest_mc_code");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				post.setParti_code(parti_code);
				post.setInterest_mc_code(interest_mc_code);
				post.setTitle(title);
				post.setContent(content);
				
				dao.reviewInsert(post);
				
				result = "redirect:studyreviewlist.action";
			}
			
			return result;
		}
		
		 
		// 후기 수정 폼
		@RequestMapping(value = "/review_update_form.action", method = RequestMethod.GET)
		public String reviewUpdateForm(HttpServletRequest request, Model model)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			String post_code = request.getParameter("post_code");
			
			StudyReviewDTO post = dao.detail(post_code);
			model.addAttribute("post", post);
			
			return "/WEB-INF/views/StudyReview_modify.jsp";
		}
		
		// 후기 수정
		@RequestMapping(value = "/review_update.action", method = RequestMethod.POST)
		public String updatePost(HttpServletRequest request)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			String post_code = request.getParameter("post_code");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			StudyReviewDTO post = new StudyReviewDTO();
			
			post.setPost_code(post_code);
			post.setTitle(title);
			post.setContent(content);
			
			dao.modify(post_code, title, content);

			return "redirect:studyreviewlist.action";
		}
		
		
		// 후기 삭제
		@RequestMapping(value = "/review_delete.action", method = RequestMethod.GET)
		public String removePost(HttpServletRequest request)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			String post_code = request.getParameter("post_code");
			
			dao.delete(post_code);
					 
			return "redirect:studyreviewlist.action";
		}
		
		
		// 게시글 추천 / 비추천
		@RequestMapping(value = "/review_rec.action", method = RequestMethod.GET)
		public String rec(HttpServletRequest request)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
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
						result =  "redirect:reviewdetail.action?post_code=" + post_code;
					}
					else
					{
						dao.notrec(post_code, user_code); // 비추천 메소드 호출
						result =  "redirect:reviewdetail.action?post_code=" + post_code;
					}
					
				}
			}
			
			return result;
		}
		
		// 댓글 작성 
		@RequestMapping(value = "/reviewcmtinsert.action", method = RequestMethod.GET)
		public String addComment(FreeBoardCmtDTO comment, HttpServletRequest request)
		{
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
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
				result = "redirect:reviewdetail.action?post_code=" + post_code;
			}
			return result;
		}
		
		// 수정할 때 전에 작성한 댓글 조회
		@RequestMapping(value="/getreviewcomm.action", method = RequestMethod.POST)
		public String getComm(Model model, HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			String result = null;
			if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
			{
				// 로그인이 되어있지 않은 상황에서의 처리
				result = "redirect:loginform.action";
				return result;
			}
			
			result = "WEB-INF/views/GetReviewComment.jsp";
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);	
			
			StudyReviewDTO dto = dao.getComm(request.getParameter("comment_code"));
			model.addAttribute("comment", dto.getComments());
			
			return result;
		}
		
		// 댓글 수정
		@RequestMapping(value="/modifyReviewComm.action", method = RequestMethod.GET)
		public String modifyComm(Model model, HttpServletRequest request, StudyReviewDTO dto)
		{
			String result = null;
			HttpSession session = request.getSession();
			String post_code = request.getParameter("post_code");
			
			if (session.getAttribute("code") == null)	
			{
				// 로그인이 되어있지 않은 상황에서의 처리
				result = "redirect:loginform.action";
				return result;
			}
			
			String study_code = request.getParameter("study_code");
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			
			dao.modifyComm(dto);
			
			result = "redirect:reviewdetail.action?post_code="+post_code;
			return result;
		}
		
		// 댓글 삭제
		@RequestMapping(value="/deleteReviewComm.action", method = RequestMethod.GET)
		public String deleteComm(ModelMap model, HttpServletRequest request)
		{
			String result = null;
			
			// 세션 처리 과정 추가(로그인에 대한 확인 과정 추가) ---------------------------------------------------------
			HttpSession session = request.getSession();
			String post_code = request.getParameter("post_code");
			System.out.println(post_code);
			if (session.getAttribute("code") == null)	//-- 로그인이 되어있지 않은 상황
			{
				// 로그인이 되어있지 않은 상황에서의 처리
				result = "redirect:loginform.action";
				return result;
			}
			// --------------------------------------------------------- 세션 처리 과정 추가(로그인에 대한 확인 과정 추가)		
			
			IStudyReview dao = sqlSession.getMapper(IStudyReview.class);
			dao.deleteComm(request.getParameter("comment_code"));
			
			result = "redirect:reviewdetail.action?post_code="+post_code;
			
			return result;
		}
	 
	
}
