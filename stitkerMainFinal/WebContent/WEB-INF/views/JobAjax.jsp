<%@page import="com.studyit.mybatis.JobDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<JobDTO> list = (List<JobDTO>)request.getAttribute("jbmList");
	StringBuffer sb = new StringBuffer();
	String result = "";
	for(int i = 0; i<list.size(); i++)
	{
		sb.append("{\"job_mc_code\":\""+list.get(i).getJob_mc_code()+"\"");
		sb.append(", \"job_mc\":\""+list.get(i).getJob_mc()+"\"}");
		if(i != list.size()-1)
		{
			sb.append(",");
		}	
	}
	
	result = "[" + sb.toString() + "]";
	out.print(result);	
%>
