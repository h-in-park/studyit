/*========================
 SeminarController.java
=======================*/
package com.studyit.mybatis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SeminarController
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
   

   
   // 세미나 및 공모전 글쓰기 폼
   @RequestMapping(value = "/seminarinsertform.action", method = RequestMethod.GET)
   public String seminarInsertForm(HttpServletRequest request, Model model)
   {
	      String result = null;
	      
		  ISeminarDAO seminar = sqlSession.getMapper(ISeminarDAO.class); 
	      ISeminarCtgDAO seminarCtg = sqlSession.getMapper(ISeminarCtgDAO.class);
	      
	      model.addAttribute("scList", seminarCtg.scList());
	      
	      // 글쓰기 버튼을 누르면 로그인 여부 판단 후 글 작성 페이지/로그인 페이지
	      HttpSession session = request.getSession();
	      if (session.getAttribute("code")!=null) // 로그인 사용자
	      {
	         if (session.getAttribute("admin")==null) // 어드민이 아닌 >> 일반 회원
	         {
	            String user_code = (String) session.getAttribute("code"); //세션에서 유저코드 얻어오기

	            result = "/WEB-INF/views/Seminar_write.jsp";
	         }
	      }
	      else
	      {
	    	  result = "redirect:loginform.action";
	      }

	      return result;
   }
   
   // 세미나 및 공모전 글 등록
   @RequestMapping(value ="/seminarinsert.action", method = RequestMethod.GET)
   public String seminarInsert(HttpServletRequest request, SeminarDTO seminar)
   {
	   ISeminarDAO dao = sqlSession.getMapper(ISeminarDAO.class);
	      
       String title = request.getParameter("title");
	   String content = request.getParameter("content");
	   String seminar_category_code = request.getParameter("seminarCtg");
	   String start_date = request.getParameter("start_date");
	   String end_date = request.getParameter("end_date");

 	   HttpSession session = request.getSession();
 	   String user_code = (String)session.getAttribute("code");
 	  

	  
 	   seminar.setUser_code(user_code);
 	   seminar.setTitle(title);
 	   seminar.setContent(content); 
 	   seminar.setSeminar_category_code(seminar_category_code);
 	   seminar.setStart_date(start_date);
 	   seminar.setEnd_date(end_date);
	  
	   dao.add(seminar); 
      
       return "redirect:seminarlist.action";
   }
   
   
   // 게시글 삭제
   @RequestMapping(value = "/seminardelete.action", method = RequestMethod.GET)
	public String informDelete(SeminarDTO seminar)
	{
		ISeminarDAO dao = sqlSession.getMapper(ISeminarDAO.class);
		
		dao.remove(seminar);
		
		return "redirect:seminarlist.action";
	}
   
   // 게시글 상세 페이지(글 상세 정보 출력, 해당 글의 댓글 리스트 출력)
   @RequestMapping(value = "/seminardetail.action", method = RequestMethod.GET)
   public String informDetail(HttpServletRequest request, Model model)
   {
      String result = null;
      
      String post_code = request.getParameter("post_code");
      
      ISeminarDAO seminar = sqlSession.getMapper(ISeminarDAO.class);
	  ISeminarCtgDAO seminarCtg = sqlSession.getMapper(ISeminarCtgDAO.class); 
	  ICmtSeminarDAO comment = sqlSession.getMapper(ICmtSeminarDAO.class);
      
      SeminarDTO dto = new SeminarDTO();
      
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
   			String listUrl = "seminardetail.action?post_code=" + post_code;
   			String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);
   			
   			// 글 내용 보기 주소
   			String articleUrl = "pageNum=" + currentPage;
   			model.addAttribute("pageIndexList", pageIndexList);
   			model.addAttribute("cmtList", comment.cmtList(post_code, start, end));
   		}
   		
   		// 조회수 증가시키기
   		seminar.hitcounts(post_code);
   		
	   	 model.addAttribute("detail" , seminar.detail(post_code));
	     model.addAttribute("scList", seminarCtg.scList());
	     model.addAttribute("cmtCount", comment.cmtCount(post_code));
	     model.addAttribute("reportctg", seminar.getReportCtg());
	     model.addAttribute("post_code", post_code);
			if (user_code != null) 
			{
				String check = "different";
				if (user_code.equals((String)session.getAttribute("code"))) 
				{
					check = "same";
				}
				model.addAttribute("recomCheck", seminar.checkRec(post_code, user_code));
				model.addAttribute("chkReport", seminar.checkReport(post_code, user_code));
				model.addAttribute("check", check);
			}

   		     
			result = "/WEB-INF/views/Seminar_detail.jsp";
      
      return result;
   }
   
   
   // 게시글 수정 폼
   @RequestMapping(value ="/seminarupdateform.action", method = RequestMethod.GET)
   public String informUpdateForm(HttpServletRequest request, Model model)
   {
      ISeminarDAO seminar = sqlSession.getMapper(ISeminarDAO.class);
      ISeminarCtgDAO seminarCtg = sqlSession.getMapper(ISeminarCtgDAO.class);
      String result = null;
      
      String post_code = request.getParameter("post_code");

      SeminarDTO post = seminar.detail(post_code);
      model.addAttribute("post", post);
      model.addAttribute("scList", seminarCtg.scList());

      return "/WEB-INF/views/Seminar_modify.jsp?post_code=" + post_code;
   }
   
   // 게시글 수정
   @RequestMapping(value ="/seminarupdate.action", method = RequestMethod.GET)
   public String informUpdate(HttpServletRequest request)
   {
      ISeminarDAO seminar = sqlSession.getMapper(ISeminarDAO.class);
      
      String post_code = request.getParameter("post_code");
      String title = request.getParameter("title");
      String content = request.getParameter("content");
      String seminar_category = request.getParameter("seminar_category");

      
      SeminarDTO dto = new SeminarDTO();
      dto.setPost_code(post_code);
      dto.setTitle(title);
      dto.setContent(content);
      dto.setSeminar_category_code(seminar_category);
      
      int a = seminar.modify(post_code, title, content, seminar_category);

      
      return "redirect:seminarlist.action";


      
   }
   
   // 댓글 등록
   @RequestMapping(value ="/semicmtinsert.action", method = RequestMethod.POST)
   public String informCommentInsert(HttpServletRequest request, CmtSeminarDTO dto)
   {
      ICmtSeminarDAO dao = sqlSession.getMapper(ICmtSeminarDAO.class);
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
      
      return "redirect:seminardetail.action?post_code=" + post_code;
   }
   
   // 댓글 삭제
   @RequestMapping(value = "/seminarcmtdelete.action", method = RequestMethod.GET)
  	public String informCmtDelete(HttpServletRequest request, CmtSeminarDTO dto)
  	{
	   
	   ICmtSeminarDAO dao = sqlSession.getMapper(ICmtSeminarDAO.class);
	   //IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
	   
  		dao.cmtRemove(dto);

  		// 이전 페이지 새로고침
  		String referer = request.getHeader("Referer");
  	    return "redirect:"+ referer;

  	}
   
   // 게시글 추천 / 비추천
   @RequestMapping(value = "/seminar_rec.action", method = RequestMethod.GET)
   public String rec(HttpServletRequest request, Model model)
   {
      ISeminarDAO dao = sqlSession.getMapper(ISeminarDAO.class);
      
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
               result =  "redirect:seminardetail.action?post_code=" + post_code;
            }
            else
            {
               dao.notrec(post_code, user_code); // 비추천 메소드 호출
               result =  "redirect:seminardetail.action?post_code=" + post_code;
            }
            
         }
      }
      
      return result;
   }
   	
   /*
   // 키워드 검색 
	@RequestMapping(value = "/seminar_keyword_search.action", method = RequestMethod.GET)
	public String KeywordSearch(HttpServletRequest request, Model model)
	{
		ISeminarDAO dao = sqlSession.getMapper(ISeminarDAO.class);
		HttpSession session = request.getSession();
		String result = null;
		ArrayList<SeminarDTO> list = dao.list();
		model.addAttribute("list", list);
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		String searchCategory = request.getParameter("searchCategory");
		
		
		// 검색키는 있고, 카테고리는 선택하지 않았을 경우
		if (!searchKey.equals(""))
		{
			if (searchKey.equals("title"))
			{
				list = dao.seminarTitleSearch(searchValue);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("name"))
			{
				list = dao.seminarWriterSearch(searchValue);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("content"))
			{
				list = dao.seminarContentSearch(searchValue);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("titlecontent"))
			{
				list = dao.seminarTnCSearch(searchValue);
				model.addAttribute("list", list);
			}
			
		}
		
		// 검색키는 없고 카테고리만 선택
		if (searchKey.equals("") && !searchCategory.equals(""))
		{
			list = dao.seminarCtgSearch(searchCategory);
			model.addAttribute("list", list);
		}
		
		
		// 검색키 / 카테고리 둘 다 선택
		if (!searchKey.equals("") && !searchCategory.equals(""))
		{
			if (searchKey.equals("title"))
			{
				list = dao.seminarCtgTitleSearch(searchValue, searchCategory);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("name"))
			{
				list = dao.seminarCtgWriterSearch(searchValue, searchCategory);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("content"))
			{
				list = dao.seminarCtgContentSearch(searchValue, searchCategory);
				model.addAttribute("list", list);
			}
			else if (searchKey.equals("titlecontent"))
			{
				list = dao.seminarCtgTnCSearch(searchValue, searchCategory);
				model.addAttribute("list", list);
			}
		}
		
		return "/WEB-INF/views/Seminar_list.jsp";
	}
	*/
   
   		// 컨트롤러 안에 리스트 메소드 : 검색기능 있음.
		@RequestMapping(value="seminarlist.action", method=RequestMethod.GET)
		public String list(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException
		{
			String result = null;

			// 로그인한 사람이 관리자인지 구분하여 넘겨주기 위함
			HttpSession session = request.getSession();		

			//요청 페이지(jsp)
			result = "/WEB-INF/views/Seminar_list.jsp";
			
			// DAO 인터페이스 인스턴스 생성
			ISeminarDAO dao = sqlSession.getMapper(ISeminarDAO.class);
			ISeminarCtgDAO seminarCtg = sqlSession.getMapper(ISeminarCtgDAO.class); 
			
			model.addAttribute("scList", seminarCtg.scList());
			
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
			int start = (currentPage-1) * numPerPage + 1;
			int end = currentPage * numPerPage > dataCount? dataCount : currentPage * numPerPage;
			int start2 = dataCount%end+1;
			int end2 = start2+(end-start);
			
			// 페이징
			String param = "";
			
			String listUrl = "seminarlist.action";
			String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

			// 검색값이 존재한다면...
			if(!searchValue.equals(""))
			{
				param += "?searchKey=" + searchKey;	// &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
				param += "&searchCategory=" + searchCategory; 
				param += "&searchValue=" + searchValue;
			}	

			// 글 내용 보기 주소
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
			model.addAttribute("getList", dao.getList(dto));
			model.addAttribute("admin", (String)session.getAttribute("admin"));
			model.addAttribute("articleUrl", articleUrl);
			return result;
		}
	
	

	// 댓글 수정하기 위해 댓글 입력창에 댓글 가져오기
	@RequestMapping(value="/getseminarcomm.action", method = RequestMethod.POST)
	public String getComm(Model model, HttpServletRequest request)
	{
		String result = null;
			
		
		result = "WEB-INF/views/SeminarComm.jsp";
		ICmtSeminarDAO dao = sqlSession.getMapper(ICmtSeminarDAO.class);
		
		CmtSeminarDTO dto = dao.getComm(request.getParameter("comment_code"));
		model.addAttribute("comment", dto.getComments());
		
	
		return result;
	}
	
	
	// 댓글 수정
	@RequestMapping(value="/modifyseminarcomm.action", method = RequestMethod.POST)
	public String modifyComm(Model model, HttpServletRequest request, CmtSeminarDTO dto)
	{
		
		ICmtSeminarDAO dao = sqlSession.getMapper(ICmtSeminarDAO.class);

		String comments = request.getParameter("comments");  
		String comment_code = request.getParameter("comment_code");

		dto.setComment_code(comment_code);
		dto.setComments(comments);
		
		dao.cmtModify(dto);
	
		
		String referer = request.getHeader("Referer");
  	    return "redirect:"+ referer;
	}
	
	// 신고 등록
	@RequestMapping(value = "/seminarreport.action", method = RequestMethod.POST)
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
		
		result="redirect:seminardetail.action?post_code=" + dto.getPost_code();
		
		String user_code = (String)session.getAttribute("code");
		
		// HashMap 선언 및 파라미터 저장
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("user_code", user_code);
		param.put("post_code", dto.getPost_code());
		param.put("report_reason", dto.getReport_reason());
		param.put("post_report_ctg_code", dto.getPost_report_ctg_code());
		
		// 경고 등록 프로시저 호출
		sqlSession.selectOne("com.studyit.mybatis.ISeminarDAO.addReport", param);
		
		return result;
	}	
			
   

   
   
   

   
}