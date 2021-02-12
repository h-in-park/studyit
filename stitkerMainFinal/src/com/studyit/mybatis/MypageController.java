package com.studyit.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MypageController
{
	@Autowired
	 private SqlSession sqlSession;
	
	//
	@RequestMapping(value = "/studylist.action", method = RequestMethod.GET)
	public String studentList(ModelMap model, HttpServletRequest request)
	{
		String result = null;
		result = "WEB-INF/views/Mypage_study.jsp";

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userid");

		IMystudyDAO dao = sqlSession.getMapper(IMystudyDAO.class);

		// 리스트 출력
		model.addAttribute("nowlist", dao.nowlist(id));
		model.addAttribute("endlist", dao.endlist(id));
		model.addAttribute("upcominglist", dao.upcominglist(id));
		model.addAttribute("cancellist", dao.cancellist(id));

		// 데이터 개수 확인
		model.addAttribute("upcomingcount", dao.upcomingcount(id));
		model.addAttribute("nowcount", dao.nowcount(id));
		model.addAttribute("endcount", dao.endcount(id));
		model.addAttribute("cancelcount", dao.cancelcount(id));

		return result;
		// return "WEB-INF/views/Mypage_study.jsp";

	}
	
	// 마이페이지 
	@RequestMapping(value = "/mypage.action", method = RequestMethod.GET)
	public String myPage(HttpServletRequest request, ModelMap model)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();

		if (session.getAttribute("code") == null)
		{
			return "redirect:loginform.action";
		} else
		{
			String user_code = (String) session.getAttribute("code");
			MyInfoDTO myinfo = dao.myInfo(user_code);
			model.addAttribute("myinfo", myinfo);

			IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
			IStudyTypeDAO studyType = sqlSession.getMapper(IStudyTypeDAO.class);
			ILocLcDAO loclc = sqlSession.getMapper(ILocLcDAO.class);
			ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
			IJobDAO job = sqlSession.getMapper(IJobDAO.class);

			model.addAttribute("imList", interest.imList());
			model.addAttribute("stList", studyType.stList());
			model.addAttribute("llList", loclc.llList());
			model.addAttribute("lmList", locmc.lmList());

			model.addAttribute("jblList", job.jblList());
			model.addAttribute("jbmList", job.jbmList());

			model.addAttribute("jblmList", job.jblmList(myinfo.getJob_lc_code()));
			
			
			model.addAttribute("myscore", dao.myscore(user_code));
		}

		return "WEB-INF/views/Mypage.jsp";
	}
    
	// 내정보 수정 폼
	@RequestMapping(value = "/myinfomodifyform.action", method = RequestMethod.GET)
	public String updateform(Model model, HttpServletRequest request)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();

		if (session.getAttribute("code") == null)
		{
			return "redirect:loginform.action";
		} else
		{
			String user_code = (String) session.getAttribute("code");
			MyInfoDTO myinfo = dao.myInfo(user_code);
			model.addAttribute("myinfo", myinfo);
			
			IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
			IStudyTypeDAO studyType = sqlSession.getMapper(IStudyTypeDAO.class);
			ILocLcDAO loclc = sqlSession.getMapper(ILocLcDAO.class);
			ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);
			IJobDAO job = sqlSession.getMapper(IJobDAO.class);

			model.addAttribute("ilList", interest.ilList());
			model.addAttribute("imList", interest.ilmList(myinfo.getInterest_lc_code()));
			model.addAttribute("stList", studyType.stList());
			model.addAttribute("llList", loclc.llList());
			model.addAttribute("lmList", locmc.lmList2(myinfo.getLoc_lc_code()));

			model.addAttribute("jblList", job.jblList());
			model.addAttribute("jbmList", job.jbmList());

			model.addAttribute("jblmList", job.jblmList(myinfo.getJob_lc_code()));

		}

		return "WEB-INF/views/MypageModify.jsp";
	}
	 
	// 내정보 수정 액션 처리
	@RequestMapping(value = "/updatemyinfo.action", method = RequestMethod.GET)
	public String updateInfo(Model model, HttpServletRequest request)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();

		if (session.getAttribute("code") == null)
		{
			return "redirect:loginform.action";
		} else
		{
			String user_code = (String) session.getAttribute("code");
			MyInfoDTO myinfo = dao.myInfo(user_code);
			myinfo.setEmail(request.getParameter("email"));
			myinfo.setInterest_mc_code(request.getParameter("interest_mc_code"));
			myinfo.setLoc_mc_code(request.getParameter("loc_mc_code"));
			myinfo.setJob_mc_code(request.getParameter("job_mc_code"));
			myinfo.setStudy_type_code(request.getParameter("study_type_code"));

			dao.updateInfo(myinfo);
			model.addAttribute("myinfo", myinfo);

		}

		return "redirect:mypage.action";
	}
	
	// 비밀번호 변경 폼 1
	@RequestMapping(value = "/changepasswordform1.action", method = RequestMethod.GET)
	public String changePwForm1()
	{

		return "WEB-INF/views/MypageChangePwForm1.jsp";
	}
	
	// 비밀번호 변경 폼 2
	@RequestMapping(value = "/changepasswordform2.action", method = RequestMethod.POST)
	public String changePwForm2(ModelMap model, HttpServletRequest request)
	{
		String result = null;

		HttpSession session = request.getSession();
		String user_code = (String) session.getAttribute("code");
		String password = request.getParameter("password");

		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);

		String mypassword = dao.myPassword(user_code, password);

		if (mypassword != null)
		{
			if (mypassword.equals(password))
			{
				result = "WEB-INF/views/MypageChangePwForm2.jsp";
				return result;
			}
		}

		String errMsg = "비밀번호를 확인할 수 없습니다.";
		model.addAttribute("errMsg", errMsg);

		result = "WEB-INF/views/MypageChangePwForm1.jsp";
		return result;
	}
	
	// 비밀번호 변경 액션 처리
	@RequestMapping(value = "/changepassword.action", method = RequestMethod.POST)
	public String changePw(HttpServletRequest request)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);

		HttpSession session = request.getSession();
		String user_code = (String) session.getAttribute("code");
		String password = request.getParameter("newPassword");

		MyInfoDTO myinfo = dao.myInfo(user_code);
		myinfo.setPassword(password);
		dao.updatePw(myinfo);

		return "redirect:mypage.action";
	}
	 
	// 지역 대분류 AJAX처리
	@RequestMapping(value = "/mypage_regionajax.action")
	public String regionAjax(Model model, HttpServletRequest request)
	{
		String result = null;
		ILocMcDAO locmc = sqlSession.getMapper(ILocMcDAO.class);

		List<LocMcDTO> list = locmc.lmList2(request.getParameter("loc_lc_code"));

		model.addAttribute("lmList", list);

		result = "/WEB-INF/views/RegionAjax.jsp";

		return result;
	}
	
	// 직업 대분류 AJAX처리
	@RequestMapping(value = "/mypage_jobajax.action")
	public String jobAjax(Model model, HttpServletRequest request)
	{
		String result = null;
		IJobDAO dao = sqlSession.getMapper(IJobDAO.class);
		
		List<JobDTO> list = dao.jblmList(request.getParameter("job_lc_code"));
		
		model.addAttribute("jblmList", list);
		
		result = "/WEB-INF/views/JobAjax.jsp";
		
		return result;
	}

	// 관심분야 대분류 AJAX처리
	@RequestMapping(value = "/mypage_interestajax.action")
	public String interestAjax(Model model, HttpServletRequest request)
	{
		String result = null;
		IInterestDAO dao = sqlSession.getMapper(IInterestDAO.class);
		
		List<InterestDTO> list = dao.ilmList(request.getParameter("interest_lc_code"));

		model.addAttribute("imList", list);

		result = "/WEB-INF/views/InterestAjax.jsp";

		return result;
	}
	
	// 회원 탈퇴 폼
	@RequestMapping(value = "/mypage_withdraw_form1.action", method = RequestMethod.GET)
	public String withdrawform1(ModelMap model)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		ArrayList<WithdrawalCategoryDTO> list = dao.withdrawCategory();
		model.addAttribute("categoryList", list);
		
		return "WEB-INF/views/MypageConfirmPw.jsp";
	}
	
	// 회원 탈퇴 - 본인 확인 ajax
	@RequestMapping(value = "/withdrawajax.action", method = RequestMethod.GET)
	public String withdrawform2(ModelMap model, HttpServletRequest request) 
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();
		String user_code = (String) session.getAttribute("code");
		String ssn = request.getParameter("ssn");
		
		String myssn = dao.mySsn(user_code, ssn);
		String errMsg = "주민등록번호를 입력해주세요.";
		
		if (ssn!=null)
		{
			if (myssn.equals(ssn))
			{
				errMsg = "본인 확인 완료";
			}
			else
			{
				errMsg = "본인 확인 실패";
			}
		}
			
		model.addAttribute("errMsg", errMsg);
		
		return "WEB-INF/views/ConfirmPwResult.jsp";
	}
	
	// 회원 탈퇴 액션 처리
	@RequestMapping(value = "/mypage_withdraw.action", method = RequestMethod.POST)
	public String withdrawform3(Model model, HttpServletRequest request)
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();
		String user_code = (String) session.getAttribute("code");
		MyInfoDTO myinfo = dao.myInfo(user_code);
		
		String wdl_ctg_code = request.getParameter("wdl_ctg_code");
		String wdl_reason = request.getParameter("wdl_reason");
		String ssn = request.getParameter("ssn");
		
		WithdrawalInfoDTO dto = new WithdrawalInfoDTO();
		dto.setSsn(ssn);
		dto.setUser_code(user_code);
		dto.setWdl_ctg_code(wdl_ctg_code);
		dto.setWdl_reason(wdl_reason);
		
		dao.withdraw(dto);
		
		return "logout.action";
	}
	
	/*
	@RequestMapping(value = "/withdrawajax.action", method = RequestMethod.GET)
	public String withdrawform2(ModelMap model, HttpServletRequest request) 
	{
		IMypageDAO dao = sqlSession.getMapper(IMypageDAO.class);
		HttpSession session = request.getSession();
		String user_code = (String) session.getAttribute("code");
		String password = request.getParameter("password");
		
		String mypassword = dao.myPassword(user_code, password);
		String errMsg = "비밀번호를 입력해주세요";
		
		if (password!=null)
		{
			if (mypassword.equals(password))
			{
				errMsg = "비밀번호 확인 완료";
			}
			else
			{
				errMsg = "비밀번호를 확인할 수 없습니다.";
			}
		}
			
		model.addAttribute("errMsg", errMsg);
		
		return "WEB-INF/views/ConfirmPwResult.jsp";
	}
	*/
}
