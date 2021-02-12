/*========================
 InformListController.java
=======================*/
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
public class InformController
{
   // SqlSession 을 활용하여 마이바티스 객체 의존성(자동) 주입 
   @Autowired
   private SqlSession sqlSession;
   
   // 매개변수를 등록하는 과정에서 매개변수 목록에 적혀있는
   // 클래스의 객체 정보는 스프링이 제공한다. 
   
   // 사용자의 요청 주소와 메소드를 매핑하는 과정 필요.
   // RequestMapping(value = "/요청주소", method = 전송방식)
   // 이 때, 전송 방식은 submit 액션인 경우만 POST
   // 나머지 모든 전송 방식은 GET 으로 처리한다. 
   
   /*
   @RequestMapping(value = "/informlist.action", method = RequestMethod.GET)
   public String informList(Model model)
   {
      String result = null;
      
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
	  IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class); 
      
      model.addAttribute("count", inform.count());
      model.addAttribute("list" , inform.list());
      model.addAttribute("imList", interest.imList());
      
      result = "/WEB-INF/views/Inform_list.jsp";
      
      return result;
   }
   */

   
   @RequestMapping(value = "/informinsertform.action", method = RequestMethod.GET)
   public String informInsertForm(HttpServletRequest request, Model model)
   {
      String result = null;
      
	  IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class); 
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
      
      model.addAttribute("imList", interest.imList());
      
      // 글쓰기 버튼을 누르면 로그인 여부 판단 후 글 작성 페이지/로그인 페이지
      HttpSession session = request.getSession();
      if (session.getAttribute("code")!=null) // 로그인 사용자
      {
         if (session.getAttribute("admin")==null) // 어드민이 아닌 >> 일반 회원
         {
            String user_code = (String) session.getAttribute("code"); //세션에서 유저코드 얻어오기
                  
            //테스트
           // System.out.println(user_code); //--> UC1
            result = "/WEB-INF/views/Inform_write.jsp";
         }
         else if (session.getAttribute("admin")!=null)
         {
        	 String user_code = (String) session.getAttribute("code");
        	 result = "/WEB-INF/views/Inform_write.jsp";
         }
      }
      else
      {
    	  result = "redirect:loginform.action";
      }

      return result;
   }
   
   @RequestMapping(value ="/informinsert.action", method = RequestMethod.GET)
   public String informInsert(HttpServletRequest request, InformDTO inform)
   {
      IInformDAO dao = sqlSession.getMapper(IInformDAO.class);
      
      String title = request.getParameter("title");
	  String content = request.getParameter("content");
	  String interest_mc = request.getParameter("interest_mc");

	  HttpSession session = request.getSession();
	  String user_code = (String)session.getAttribute("code");
	  
	  inform.setUser_code(user_code); 
	  inform.setTitle(title);
	  inform.setContent(content); 
	  inform.setInterest_mc_code(interest_mc);
	  
	  dao.add(inform); 
      
      return "redirect:informlist.action";
   }
   
   // 게시글 상세 페이지(글 상세 정보 출력, 해당 글의 댓글 리스트 출력)
   @RequestMapping(value = "/informdetail.action", method = RequestMethod.GET)
   public String informDetail(HttpServletRequest request, Model model)
   {
      String result = null;
      
      String post_code = request.getParameter("post_code");
      
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
	  IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class); 
      ICmtInformDAO comment = sqlSession.getMapper(ICmtInformDAO.class);
      
      InformDTO dto = new InformDTO();
      
      HttpSession session = request.getSession();
	  String id = (String)session.getAttribute("userid");
	  String user_code = (String)session.getAttribute("code");
      
      
     
      
      // 댓글 페이징 처리----------------
   		Page page = new Page();
   		
   		// 페이지 번호 확인
   		String pageNum = request.getParameter("pageNum");
   		
   		// 현재 페이지
   		int currentPage;

   		// 전체 데이터 갯수 구하기
   		int dataCount = comment.cmtCount(post_code);
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
   			String listUrl = "informdetail.action?post_code=" + post_code;
   			String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);
   			
   			// 글 내용 보기 주소
   			String articleUrl = "pageNum=" + currentPage;
   			model.addAttribute("pageIndexList", pageIndexList);
   			model.addAttribute("cmtList", comment.cmtList(post_code, start, end));
   		}
   		
   		// 조회수 증가시키기
   		inform.hitcounts(post_code);
   		
	   	 model.addAttribute("detail" , inform.detail(post_code));
	     model.addAttribute("imList", interest.imList());
	     model.addAttribute("cmtCount", comment.cmtCount(post_code));
	     model.addAttribute("reportctg", inform.getReportCtg());
			if (user_code != null) 
			{
				String check = "different";
				if (user_code.equals((String)session.getAttribute("code"))) 
				{
					check = "same";
				}
				model.addAttribute("recomCheck", inform.checkRec(post_code, user_code));
				model.addAttribute("chkReport", inform.checkReport(post_code, user_code));
				model.addAttribute("check", check);
			}

   		     
      result = "/WEB-INF/views/Inform_detail.jsp?post_code=" + post_code;
      
      return result;
   }
   
   
   
   // 댓글 등록
   @RequestMapping(value ="/infocmtinsert.action", method = RequestMethod.POST)
   public String informCommentInsert(HttpServletRequest request, CmtInformDTO dto)
   {
      ICmtInformDAO dao = sqlSession.getMapper(ICmtInformDAO.class);
      String result = null;
      
      // 댓글 등록 버튼을 누르면 로그인 여부 판단 후 글 작성 페이지/로그인 페이지
      HttpSession session = request.getSession();
      
      String post_code = request.getParameter("post_code");
      
	  String user_code = (String)session.getAttribute("code");
	  String comments = request.getParameter("comments");  

	  dto.setUser_code(user_code); 
	  dto.setComments(comments);
	  dto.setPost_code(post_code);

	  dao.cmtAdd(dto); 
      
      return "redirect:informdetail.action?post_code=" + post_code;
   }
   
   
   // 정보 게시판 수정 폼
   @RequestMapping(value ="/informupdateform.action", method = RequestMethod.GET)
   public String informUpdateForm(HttpServletRequest request, Model model)
   {
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      String result = null;
      
      String post_code = request.getParameter("post_code");

      InformDTO post = inform.detail(post_code);
      model.addAttribute("post", post);
      model.addAttribute("imList", interest.imList());

      return "/WEB-INF/views/Inform_modify.jsp?post_code=" + post_code;
   }
   
   // 글 수정 처리
   @RequestMapping(value ="/informupdate.action", method = RequestMethod.GET)
   public String informUpdate(HttpServletRequest request, ModelMap model)
   {
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
      HttpSession session = request.getSession();
      
      String post_code = request.getParameter("post_code");
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      String interest_mc = request.getParameter("interest_mc");
      String pw = (String)session.getAttribute("userpw");

      
      InformDTO dto = new InformDTO();
      dto.setPost_code(post_code);
      dto.setTitle(title);
      dto.setContent(content);
      dto.setInterest_mc_code(interest_mc);
      
      model.addAttribute("pw", pw);
      
      int a = inform.modify(post_code, title, content, interest_mc);
      //System.out.println(a);
      
      return "redirect:informlist.action";

   }
   
   // 게시글 추천 / 비추천
   @RequestMapping(value = "/inform_rec.action", method = RequestMethod.GET)
   public String informRec(HttpServletRequest request, Model model)
   {
      IInformDAO dao = sqlSession.getMapper(IInformDAO.class);
      
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
         
         if (session.getAttribute("admin")==null)   // 로그인 사용자이면서 어드민이 아닐때만
         {
            if (check == 1)   // 추천버튼 눌렀으면
            {
               dao.rec(post_code, user_code); // 추천 메소드 호출
               result =  "redirect:informdetail.action?post_code=" + post_code;
            }
            else
            {
               dao.notrec(post_code, user_code); // 비추천 메소드 호출
               result =  "redirect:informdetail.action?post_code=" + post_code;
            }
            
         }
      }
      
      return result;
   }
   
   // 게시글 삭제
   @RequestMapping(value = "/informdelete.action", method = RequestMethod.GET)
	public String informDelete(InformDTO inform)
	{
		IInformDAO dao = sqlSession.getMapper(IInformDAO.class);
		
		dao.remove(inform);
		
		return "redirect:informlist.action";
	}
   
   
   // 댓글 삭제
   @RequestMapping(value = "/informcmtdelete.action", method = RequestMethod.GET)
  	public String informCmtDelete(HttpServletRequest request, CmtInformDTO dto)
  	{
  	
	   
	   ICmtInformDAO dao = sqlSession.getMapper(ICmtInformDAO.class);
	   IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
	   
  		dao.cmtRemove(dto);
  		
  		// 이전 페이지 새로고침
  		String referer = request.getHeader("Referer");
  	    return "redirect:"+ referer;

  	}
   
	
	
	    // 리스트 메소드 : 검색기능 있음, 페이징 처리
		@RequestMapping(value="informlist.action", method=RequestMethod.GET)
		public String list(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException
		{
			String result = null;

			// 로그인한 사람이 관리자인지 구분하여 넘겨주기 위함
			HttpSession session = request.getSession();		

			//요청 페이지(jsp)
			result = "/WEB-INF/views/Inform_list.jsp";
			
			// DAO 인터페이스 인스턴스 생성
			IInformDAO dao = sqlSession.getMapper(IInformDAO.class);
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
			
			// 총 페이지 수 계산
			int numPerPage = 10;
			int totalPage = page.getPageCount(numPerPage, dataCount);
			
			// 전체 페이지 수보다 표시할 페이지가 큰 경우
			// (그 사이 데이터 삭제해서 페이지 줄었을 경우) 표시할 페이지를 마지막 페이지로 구성
			if (currentPage > totalPage)
				currentPage = totalPage;
			
			// 데이터 베이스에서 가져올 게시물의 시작과 끝
			int start = (currentPage - 1) * numPerPage + 1;
			int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage;
			int start2 = dataCount % end + 1;
			int end2 = start2 + (end - start);
			
			// 페이징
			String param = "";
			
			String listUrl = "informlist.action";
			String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

			// 검색값이 존재한다면
			if(!searchValue.equals(""))
			{
				param += "?searchKey=" + searchKey;	
				param += "&searchCategory=" + searchCategory; 
				param += "&searchValue=" + searchValue;
			}	

			// 글 내용 보기 주소
			String articleUrl;
			
			if(param.equals(""))
				articleUrl = "?pageNum=" + currentPage;	
			else
				articleUrl = param + "&pageNum=" + currentPage; 
				

			//--------------------------------------------------------------------------페이징처리
			dto.setStart(start2);
			dto.setEnd(end2);
			dto.setSearchKey(searchKey);
			dto.setSearchCategory(searchCategory);
			dto.setSearchValue(searchValue);
			
			model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
			model.addAttribute("getList", dao.getList(dto));
			model.addAttribute("admin", (String)session.getAttribute("admin"));
			model.addAttribute("articleUrl", articleUrl);
			
			
			//System.out.println(dataCount);
			//System.out.println(start2);
			//System.out.println(end2);
			return result;
		}
		
		// 댓글 수정하기 위해 댓글 입력창에 댓글 가져오기
		@RequestMapping(value="/getinformcomm.action", method = RequestMethod.POST)
		public String getComm(Model model, HttpServletRequest request)
		{
			String result = null;
				
			
			result = "WEB-INF/views/InformComm.jsp";
			ICmtInformDAO dao = sqlSession.getMapper(ICmtInformDAO.class);
			
			CmtInformDTO dto = dao.getComm(request.getParameter("comment_code"));
			model.addAttribute("comment", dto.getComments());
			
		
			return result;
		}
		
		
		// 댓글 수정
		@RequestMapping(value="/modifyinformcomm.action", method = RequestMethod.POST)
		public String modifyComm(Model model, HttpServletRequest request, CmtInformDTO dto)
		{
			
			ICmtInformDAO dao = sqlSession.getMapper(ICmtInformDAO.class);

			String comments = request.getParameter("comments");  
			String comment_code = request.getParameter("comment_code");

			dto.setComment_code(comment_code);
			dto.setComments(comments);
			
			dao.cmtModify(dto);
		
			
			String referer = request.getHeader("Referer");
	  	    return "redirect:"+ referer;
		}
		
		// 신고 등록
		@RequestMapping(value = "/informreport.action", method = RequestMethod.POST)
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
			
			result="redirect:informdetail.action?post_code=" + dto.getPost_code();
			
			String user_code = (String)session.getAttribute("code");
			
			// HashMap 선언 및 파라미터 저장
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("user_code", user_code);
			param.put("post_code", dto.getPost_code());
			param.put("report_reason", dto.getReport_reason());
			param.put("post_report_ctg_code", dto.getPost_report_ctg_code());
			
			// 경고 등록 프로시저 호출
			sqlSession.selectOne("com.studyit.mybatis.IInformDAO.addReport", param);
			
			return result;
		}	
		
		
   
   
   

   
}