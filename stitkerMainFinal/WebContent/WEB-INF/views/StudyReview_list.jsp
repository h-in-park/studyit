<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 후기</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/studylist.css">
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- 테이블 정렬 JS -->
<script type="text/javascript" src="<%=cp %>/js/jquery.tablesorter.min.js"></script>
<!-- 테이블 정렬 css -->
<link rel="stylesheet" href="<%=cp %>/css/theme.default.min.css">
<style type="text/css">
a {color:#000000;
text-decoration: none;}
</style>
<style type="text/css">
.searchLeft > table > tbody > tr > td
{
	width:170px;
}
span {
color:#000;}
table > tbody > tr 
{
	height: 50px;
}
</style>
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#table2").tablesorter(
		{ 
			headers: 
			{	// 조회수, 추천수, 날짜순 이외의 항목으로는 정렬할 수 없도록 설정
				'.num1, .title1, .writer1' : 
				{
					sorter: false
				}
			}
		});
		
	});
</script>
</head>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content3">
   <div class="title4" style="height: 350px">
     <h3 class="titleTxt text-center">스터디 후기</h3>
   </div>
   <div class="container">
	<div style="width:100%">
	<form action="studysearch.action" name="myForm2">
	</form>
	</div>
	<div class="mt-5 mb-5">
		<table id="table2" class="table table-striped tablesorter">
		<thead>
		<tr>
			<th class="num1">번호</th>
			<th class="title1">제목</th>
			<th class="writer1">작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
			<th>비추천수</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="studyreview" items="${list }">
		<tr>
			<td>${studyreview.post_num }</td>
			<td><a href="reviewdetail.action?post_code=${studyreview.post_code }">${studyreview.title }</a></td>
			<td>${studyreview.user_name }</td>
			<td>${studyreview.write_date }</td>
			<td>${studyreview.hitcount }</td>
			<td>${studyreview.rec }</td>
			<td>${studyreview.notrec }</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
	</div>
	<div class="page">
	${pageIndexList }
	</div>
	   
   </div>
  </div>
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>