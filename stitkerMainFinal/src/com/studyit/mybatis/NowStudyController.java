package com.studyit.mybatis;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class NowStudyController
{
	 @Autowired
	 private SqlSession sqlSession;
	 
	 @RequestMapping(value = "/nowstudy.action", method = RequestMethod.GET)
	  public String studentList(HttpServletRequest request, ModelMap model)
	  {
		 String result = null;
		 result = "WEB-INF/views/Mypage_nowStudy.jsp";
		 
		 INowstudyDAO dao = sqlSession.getMapper(INowstudyDAO.class);
		 IContentsDAO contents = sqlSession.getMapper(IContentsDAO.class);
		 IMemberDAO dao3 = sqlSession.getMapper(IMemberDAO.class);
		 IReportDAO report = sqlSession.getMapper(IReportDAO.class);
		 
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
		String listUrl = "nowstudy.action?studycode=" + studycode;
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		// 글 내용 보기 주소
		String articleUrl = "pageNum=" + currentPage;
		//--------------------------------------------------------------------------페이징처리
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		model.addAttribute("nowcontents", contents.contentslist(studycode,start2, end2)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		
		 
		 // 리스트 출력
	     model.addAttribute("nowlist", dao.list(studycode, id));
	     model.addAttribute("weekday", dao.weekday(studycode));
	     model.addAttribute("pgs", dao.pgs(studycode, id));
	     //model.addAttribute("nowcontents",contents.contentslist(studycode));
	     model.addAttribute("member", dao3.member(studycode, id));
	     request.setAttribute("parti_code", parti_code);
	     
	     return result;
	   }
		
	 // 스터디원 신고 폼 
	 @RequestMapping(value = "/reportform.action",method = RequestMethod.GET)
	  public String memReport(HttpServletRequest request, ModelMap model)
	  {
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 
		 INowstudyDAO dao = sqlSession.getMapper(INowstudyDAO.class);
		 IReportDAO report = sqlSession.getMapper(IReportDAO.class);
		 
		 //EXEC PRC_MEM_RPT_REG_INSERT(신고등록날짜, 신고하는사람'UC3', 카테고리'RC3', 이유'너무 공격적이에요ㅠ', 신고받는사람'UC2', 스터디코드'SO2');
		 String memId = request.getParameter("memid");
		 String parti_code = request.getParameter("parti_code");
		 String studycode = request.getParameter("studycode");
		 String reported_parti_code = report.partiCode(studycode, memId);

		 // 신고 여부 확인 
		 int reportCheck = report.reportCheck(parti_code, reported_parti_code);
		 model.addAttribute("reportCheck", reportCheck);
		 
		 model.addAttribute("studyname", report.studyName(studycode));
		 
		 // 신고 하는 사람 user_code
		 model.addAttribute("userCode",report.userCode(studycode, id));

		 // 신고 당하는 사람 user_code, parti_code
		 model.addAttribute("userCode2",report.userCode(studycode, memId));
		 model.addAttribute("reported_parti_code" , report.partiCode(studycode, memId));

		 
		 return "WEB-INF/views/Participant_report_register.jsp";
		 
	  }
	 
	 // 스터디원 신고 등록 
	 @RequestMapping(value = "/reportinsert.action", method=RequestMethod.GET)
	  public String reportInsert(HttpServletRequest request, ModelMap model)
	  {
		 
		 // HashMap 선언 및 파라미터 저장 
		 HashMap<String, String> param = new HashMap<String, String>();
		 param.put("studycode",request.getParameter("studycode"));
		 param.put("userCode",request.getParameter("userCode"));
		 param.put("userCode2",request.getParameter("userCode2"));
		 param.put("report_category",request.getParameter("report_category"));
		 param.put("reason",request.getParameter("reason"));

		 // 스터디원 신고 프로시저 호출
		 sqlSession.selectOne("com.studyit.mybatis.IReportDAO.report",param);
		 
		 return "redirect:nowstudy.action?studycode=" +param.get("studycode");
	  }
	 
	 
	 // 내보내기 
	 @RequestMapping(value = "/kick.action",method = RequestMethod.GET)
	  public String kick(HttpServletRequest request, ModelMap model)
	  {
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 
		 INowstudyDAO dao = sqlSession.getMapper(INowstudyDAO.class);
		 IReportDAO report = sqlSession.getMapper(IReportDAO.class);
		 
		 // 내보내기 당하는 사람 id
		 String memId = request.getParameter("memid");
		 String parti_code = request.getParameter("parti_code");
		 String studycode = request.getParameter("studycode");
		 
		 // 내보내기 당하는 사람
		 String kicked_parti_code = report.partiCode(studycode, memId);
		 
		 int kickCheck = dao.kickCheck(kicked_parti_code, parti_code);
		 request.setAttribute("kickCheck", kickCheck);
		 
		 // HashMap 선언 및 파라미터 저장 
		 HashMap<String, String> param = new HashMap<String, String>();
		 param.put("parti_code", parti_code);
		 param.put("kicked_parti_code", kicked_parti_code);

		 // 스터디원 내보내기 프로시저 호출
		 sqlSession.selectOne("com.studyit.mybatis.INowstudyDAO.kick",param);
		 
		 return "redirect:nowstudy.action?studycode=" + studycode;
		 
	  }
	 
	 // 스터디 나가기
	 @RequestMapping(value = "/studycancel.action",method = RequestMethod.GET)
	  public String studycancel(HttpServletRequest request, ModelMap model)
	  {
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 String studycode = request.getParameter("studycode");
		 
		 INowstudyDAO dao = sqlSession.getMapper(INowstudyDAO.class);
		 
		 String apply_code = dao.applyCode(studycode, id);
		 
		 // HashMap 선언 및 파라미터 저장 
		 HashMap<String, String> param2 = new HashMap<String, String>();
		 param2.put("apply_code", apply_code);

		 // 스터디 취소 프로시저 호출
		 sqlSession.selectOne("com.studyit.mybatis.INowstudyDAO.studycancel",param2);
		 
		 return "redirect:studylist.action";
		 
	  }
	 
		 
		 
	 
}
