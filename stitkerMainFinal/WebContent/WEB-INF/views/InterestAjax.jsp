<%@page import="com.studyit.mybatis.InterestDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<InterestDTO> list = (List<InterestDTO>)request.getAttribute("imList");
	StringBuffer sb = new StringBuffer();
	String result = "";
	for(int i = 0; i<list.size(); i++)
	{
		sb.append("{\"interest_mc_code\":\""+list.get(i).getInterest_mc_code()+"\"");
		sb.append(", \"interest_mc\":\""+list.get(i).getInterest_mc()+"\"}");
		if(i != list.size()-1)
		{
			sb.append(",");
		}	
	}
	
	result = "[" + sb.toString() + "]";
	out.print(result);	
%>
