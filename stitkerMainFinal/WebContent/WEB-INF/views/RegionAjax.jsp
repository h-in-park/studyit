<%@page import="com.studyit.mybatis.LocMcDTO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<LocMcDTO> list = (List<LocMcDTO>)request.getAttribute("lmList");
	StringBuffer sb = new StringBuffer();
	String result = "";
	for(int i = 0; i<list.size(); i++)
	{
		sb.append("{\"loc_mc_code\":\""+list.get(i).getLoc_mc_code()+"\"");
		sb.append(", \"loc_mc\":\""+list.get(i).getLoc_mc()+"\"}");
		if(i != list.size()-1)
		{
			sb.append(",");
		}	
	}
	
	result = "[" + sb.toString() + "]";
	out.print(result);	
%>
