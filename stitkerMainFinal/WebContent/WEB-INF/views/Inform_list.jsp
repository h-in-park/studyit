<%@page import="javax.sql.DataSource"%>
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
<title>IT기술정보공유</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/css/list.css">
<link rel="stylesheet" href="<%=cp %>/css/theme.default.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<!-- 테이블 정렬 및 스크롤 JS -->
<script type="text/javascript" src="<%=cp %>/js/jquery.tablesorter.min.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<style type="text/css">

#table2 th{text-align: center;}
#table2 th:nth-child(1){width: 5%;}
#table2 th:nth-child(2){width: 20%;}
#table2 th:nth-child(3){width: 35%;}
#table2 th:nth-child(4){width: 8%;}
#table2 th:nth-child(5){width: 12%;}
#table2 th:nth-child(6){width: 10%;}
#table2 th:nth-child(7){width: 10%;}

#table2 td{text-align: center;}
#table2 td:nth-child(3){text-align: left;}


</style>
<script type="text/javascript">

	
	$(document).ready(function()
	{
		$('#write').click(function()
		{ 
			var url = "informinsertform.action";    
			location.href = url; 
		});
 		
		 $("#table2").tablesorter(
		 { 
			 headers: 
			 {	// 조회수, 추천수, 날짜순 이외의 항목으로는 정렬할 수 없도록 설정
				'.num, .title, .ctg, .writer' : 
				{
			        sorter: false
			    }
			 }
		 });

	});
	


</script>
   
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content text-center"><br>
 
	<!-- 메뉴 구성 영역 -->
 	<div class="menu">
	<br> 
		<h1 class="text-center">정보공유</h1>
		<br><br><br>
		<nav>
			<ul id="menu">
				<li><a href="informlist.action" class="selected">IT기술정보공유</a></li>
				<li><a href="seminarlist.action" >세미나 및 공모전</a></li>
				<li><a href="interviewlist.action">면접/코딩테스트 후기</a></li>
				<li><a href="freeboardlist.action">자유게시판</a></li>
				<li><a href="questionlist.action">Q&A</a></li>
			</ul>
		</nav>
	</div>
   	
	
	<div class="main">
		
		<form action="informlist.action"  name="myForm">
		<table id="table1" class="table">
			<tr>
				<td>
					<select name="searchKey" id="searchKey" class="form-control">
						<option value="">전체</option>
						<option value="title">제목</option>
						<option value="id">작성자</option>
						<option value="content">내용</option>
						<!-- <option value="titlecontent">제목+내용</option> -->
					</select>
					<select name="searchCategory" id="searchCategory" class="form-control">
						<option value="">전체</option>
						 <c:forEach var="interest" items="${imList }">
							 <option value="${interest.interest_mc }">
							 	${interest.interest_mc }
						 </c:forEach>
					</select>
					<input type="text" name="searchValue" id="searchValue" class="form-control"
					placeholder="검색어를 입력하세요." style="width: 200px;">
					<input type="submit" value="검색" id="searchBtn" 
					class="btn btn-outline-primary btn-sm">	
				</td>
			</tr>
		</table>
	
		
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
 			<c:forEach var="inform" items="${getList }">
 			
				<tr>
					<td>${inform.post_num }</td>
					<td>${inform.interest_mc}</td>
					<td><a href="informdetail.action?post_code=${inform.post_code}">${inform.title}</a></td>
					<td>${inform.id}</td>
					<td>${inform.write_date }</td>
					<td>${inform.hitcount}</td>
					<td>${inform.rec }</td>
				</tr>
			
			</c:forEach>  
			</tbody>
		</table>
		
		<div id=btnSet>
			<input type="button" value="글쓰기" class="btn btn-outline-primary" id="write">
			<input type="button" value="새로고침" class="btn btn-outline-primary" 
			onclick="location.href='informlist.action'">	
		</div>
		
		<div id="footer">
			<p>
			<c:if test="${dataCount!=0}">
			     ${pageIndexList }
			</c:if>
			<c:if test="${dataCount==0}">
			     등록된 페이지가 없습니다.
			</c:if>
			</p>
		</div><!-- #footer -->
		</form>
	</div>	
	
		
	

	
   
  </div>
  
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>