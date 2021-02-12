/*=========================
 JoinController.java
==========================*/
package com.studyit.mybatis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class JoinAjaxController {

	@Autowired
	IJoinAjaxDAO service;

	// 아이디 중복 체크
	@RequestMapping(value = "/idcheck.action", method = RequestMethod.GET)
	@ResponseBody
	public String idCheck(HttpServletRequest request) throws Exception 
	{
		
		String id = request.getParameter("id");
		int result=service.idCheck(id);
		return Integer.toString(result);
	}
	
}
   
   
  
   
   
   
