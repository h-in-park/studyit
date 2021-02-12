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
<title>면접/코딩테스트 후기</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/css/list.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/util.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<title>Mypage_evaluationComplete</title>
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<style type="text/css">

#table2 th{text-align: center;}
#table2 th:nth-child(1){width: 50px;}
#table2 th:nth-child(2){width: 140px;}
#table2 th:nth-child(3){width: 330px;}
#table2 th:nth-child(4){width: 60px;}
#table2 th:nth-child(5){width: 100px;}
#table2 th:nth-child(6){width: 60px;}
#table2 th:nth-child(7){width: 60px;}

#table2 td{text-align: center;}
#table2 td:nth-child(3){text-align: left;}

</style>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#interviewwrite").click(function()
	{
			$(location).attr("href","interview_writeform.action");	
		
		
	});
	
	$("#detail").click(function()
	{
			$(location).attr("href","interview_detail.action");	
				
	});
	
});

</script>
   
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content text-center"><br>
 
	<!--  메뉴 구성 영역 -->
   <c:import url="BoardMenu.jsp"></c:import>

	<div class="main">
		<form action="interviewlist.action" method="get">
		<table id="table1" class="table">
			<tr>
				<td>
					<select name="searchKey" id="searchKey" class="form-control">
						<option value="">전체</option>
						<option value="title">제목</option>
						<option value="id">작성자</option>
						<option value="content">내용</option>
					</select>
					<select name="searchCategory" id="searchCategory" class="form-control">
						<option value="">전체</option>
						<c:forEach var="interview" items="${interestlist }">
							<option value="${interview.interest_mc}">${interview.interest_mc }</option>	
						</c:forEach>
					</select>
					<input type="text" name="searchValue" id="searchValue" class="form-control"
					placeholder="검색어를 입력하세요." style="width: 200px;">
					<input type="submit" value="검색" id="searchBtn" class="btn btn-outline-primary btn-sm">		
				</td>
			</tr>
		</table>
		</form>
		
		<table id="table2" class="table table-striped table-hover tablesorter">
			<thead>
				<tr>
					<th class="num">번호</th>
					<th class="ctg">말머리</th>
					<th class="title">제목</th>
					<th class="writer">작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="interview" items="${list }">
				<tr onclick="window.location.href='interview_detail.action?post_code=${interview.post_code}&writer_code=${interview.user_code}${articleUrl }'">
					<td>${interview.post_num }</td>
					<td>${interview.interest_mc }</td>
					<td>${interview.title}</td>
					<td>${interview.id }</td>
					<td>${interview.write_date }</td>
					<td>${interview.hitcount }</td>
					<td>${interview.rec }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div id=btnSet>
			<input type="button" value="글쓰기" class="btn btn-outline-primary" id="interviewwrite">
			<input type="button" value="새로고침" class="btn btn-outline-primary" onclick="location.href='interviewList.action'">
		</div>
		
		<div id="footer">
		
			<p>${pageIndexList }</p>

		</div><!-- #footer -->
	</div>	
	
		
	

	</form>
   
  </div>
  
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>