package com.studyit.mybatis;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudySearchController
{
	@Autowired
	 private SqlSession sqlSession;
	
	 // 스터디 리스트 조회 
	 @RequestMapping(value = "/studysearch.action",method = RequestMethod.GET)
	  public String studySearch(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException
	  {
		 String result = null; 
		 
		 HttpSession session = request.getSession();
		 String id = (String)session.getAttribute("userid");
		 
		 // 요청 페이지(jsp)
		 result = "WEB-INF/views/StudyList.jsp";

		 // dao 인스턴스 생성	
		 IStudySearchDAO dao = sqlSession.getMapper(IStudySearchDAO.class);
		 model.addAttribute("loc_lc" , dao.loc_lc());
		 model.addAttribute("loc_mc" , dao.loc_mc());
		 model.addAttribute("type", dao.study_type());
		 model.addAttribute("rank", dao.rank());
		 
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
		//System.out.println(searchKey);
		model.addAttribute("searchKey",searchKey);
		String searchValue = request.getParameter("searchValue");
		model.addAttribute("searchValue",searchValue);
		
		String loc_lc = request.getParameter("loc_lc");
		model.addAttribute("loc_Lc",loc_lc);
		String loc_mc = request.getParameter("loc_mc");
		model.addAttribute("loc_Mc",loc_mc);
		String study_type = request.getParameter("study_type");
		model.addAttribute("study_type",study_type);
		
	    if (searchKey == null || searchKey.equals("")) {
            searchKey = "study_name";
            searchValue = "";
         }
	    
	    if (loc_lc == null || loc_lc.equals("")) {
	    	loc_lc = "";
         }
	    if (loc_mc == null || loc_mc.equals("")) {
	    	loc_mc = "";
	    }
	    if (study_type == null || study_type.equals("")) {
	    	study_type = "";
	    }
	    
		
		// 검색 기능을 통해 페이지가 요청되었을 경우... 
		if(searchKey != null)
		{	
			// 넘어온 값이 GET 방식이라면...
			if(request.getMethod().equalsIgnoreCase("GET"))
			{
				// 디코드 처리
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		if(loc_mc !=null && loc_mc!=null && study_type!=null) 
		{
			if(study_type.equals("all"))
				study_type = "";
			if(loc_lc.equals("지역대분류"))
				loc_lc = "";
			if(loc_mc.equals("지역중분류") || loc_mc.equals("LM0"))
				loc_mc = "";
			//model.addAttribute("list",dao.categorySearch(loc_lc,loc_mc,studyType,start2, end2));
		}
		
		
		// 전체 데이터 갯수 구하기 - > 검색 기능 추가 이후
		StudySearchDTO dto = new StudySearchDTO();
		dto.setSearchKey(searchKey);
        dto.setSearchValue(searchValue);
        
        dto.setLoc_lc(loc_lc);
        dto.setStudy_type(study_type);
        dto.setLoc_mc(loc_mc);
        
        
		int dataCount = dao.count(searchKey,searchValue,loc_lc,loc_mc,study_type);
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
		
		//System.out.println(start2);
		//System.out.println(end2);
		
		// 페이징
     	String param = "";

		String listUrl = "studysearch.action";
		String pageIndexList = page.getIndexList(currentPage, totalPage, listUrl);

		 // 검색값이 존재한다면...
        if(!searchValue.equals(""))
        {
           param += "?searchKey=" + searchKey;   // &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
           param += "&searchValue=" + searchValue;
           param += "&loc_lc=" + loc_lc;
           param += "&loc_mc=" + loc_mc;
           param += "&study_type=" + study_type;
           
        }   

        // 글 내용 보기 주소
        String articleUrl;
        
        if(param.equals(""))
           articleUrl = "?pageNum=" + currentPage;   // 여기도 &,? 안붙여서 jsp에서 필요한 거로 붙여서 써야 함.
        else
           articleUrl = param + "&pageNum=" + currentPage; 

		
	
		//--------------------------------------------------------------------------페이징처리
		dto.setStart(start2);
        dto.setEnd(end2);
        dto.setSearchKey(searchKey);
        dto.setSearchValue(searchValue);
		dto.setLoc_lc(loc_lc);
		dto.setLoc_mc(loc_mc);
		dto.setStudy_type(study_type);
		
		model.addAttribute("pageIndexList", pageIndexList); //-- 밑에 숫자 넘겨주기(10개, 현재페이지는 클릭 안됨)
		model.addAttribute("list", dao.list(dto)); //-- 현재페이지에 해당하는 게시글 10개 가져오기(rnum이 start이상, end 이하이면 됨.)
		model.addAttribute("admin", (String)session.getAttribute("admin"));
		model.addAttribute("articleUrl", articleUrl);
		
		return result;

     }

}
