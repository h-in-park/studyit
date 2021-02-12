<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_Q&A_list.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/ListPage2.css">

<script type="text/javascript">

	function clickQa(ask_code) {
		window.location.href="supportqadetail.action?ask_code="+ask_code+"&<c:out value='${articleUrl}'/>";
	}
	
	$().ready(function(){
		
		if("<c:out value='${admin}'></c:out>" == "")
			$(".admin").css("display", "none");
		
		else
			$(".member").css("display", "none");
	});
	

</script>
</head>
<body>

<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content">
		<div class="menu">
		<br><br>
			<h1 class="text-center">고객센터</h1>
			<br><br><br>
			<nav>
				<ul>
					<li><a href="supportnoticelist.action">공지사항</a></li>
					<li><a href="supportqalist.action" class="selected">1:1문의</a></li>
				</ul>
			</nav>
		</div>

		<div class="content">	
			<br>	
			<h6 class="count admin">답변 대기 <span class="badge rounded-pill bg-primary">${count }</span></h6>
			<br><br>
			<div class="tableDiv">
				<table class="table table-hover">
					<thead>
					<tr class="first">
						<th>번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>답변</th>
					</tr>
					</thead>
					<c:forEach var="qa" items="${list }" >
						<tr onclick="clickQa('${qa.ask_code}')" class=${admin=="admin" && qa.answer == "답변 대기"? "noResult" : "" }>
							<td>${qa.rnum }</td>
							<td>${qa.ask_title }</td>
							<td>${qa.ask_date }</td>
							<td>${qa.answer }</td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
			<a href="supportqawrite.action" class="btn btn-outline-primary member">글 작성하기</a>
			<br>
			<div class="page">${pageIndexList }</div>
		</div>	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
		
</body>
</html>