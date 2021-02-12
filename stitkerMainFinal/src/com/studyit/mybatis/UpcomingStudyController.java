package com.studyit.mybatis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UpcomingStudyController
{
	 @Autowired
	 private SqlSession sqlSession;
	 
	 @RequestMapping(value = "/upcomingstudy.action", method = RequestMethod.GET)
	  public String studentList(HttpServletRequest request, ModelMap model)
	  {
		 String result = null;
		 result = "WEB-INF/views/Mypage_upcomingStudy.jsp";
		 
		 IUpcomingDAO dao = sqlSession.getMapper(IUpcomingDAO.class);
		 IContentsDAO contents = sqlSession.getMapper(IContentsDAO.class);
		 IMemberDAO dao3 = sqlSession.getMapper(IMemberDAO.class);
		 HttpSession session = request.getSession();
		 if(session.getAttribute("code") == null) 
		 {
			return "redirect:loginform.action";
		 }
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 String parti_code = contents.particode(studycode, id);

		 
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
		
		// 전체 데이터 갯수 구하기
		int dataCount = contents.count(studycode);
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
		String listUrl = "upcomingstudy.action?studycode=" + studycode;
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 글 내용 보기 주소
		String articleUrl = "pageNum=" + currentPage;
		//--------------------------------------------------------------------------페이징처리
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		model.addAttribute("upcomingcontent", contents.contentslist(studycode,start2, end2)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		model.addAttribute("member", dao3.member(studycode, id));
		
		 // 리스트 출력
	     model.addAttribute("uplist", dao.list(studycode, id));
	     model.addAttribute("weekday", dao.weekday(studycode));
	     request.setAttribute("parti_code", parti_code);
	     return result;
	     
	   }
	 
	 	
	 
}
