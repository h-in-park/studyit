<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_notice_list.jsp</title>

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
	
	function clickNotice(notice_code) 
	{
		window.location.href="supportnoticedetail.action?notice_code="+notice_code+"&<c:out value='${articleUrl}'/>";
	}
	
	$().ready(function() {
		if ("<c:out value='${admin}'></c:out>" != "admin") {
			
			$(".admin").css("display","none");
		}
	});

</script>
</head>
<body>

<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content"><br>
		<div class="menu">
		<br> 
			<h1 class="text-center">고객센터</h1>
			<br><br><br>
			<nav>
				<ul>
					<li><a href="supportnoticelist.action" class="selected">공지사항</a></li>
					<li><a href="supportqalist.action">1:1문의</a></li>
				</ul>
			</nav>
		</div>

		<div class="content">	
			<br><br><br>
			<div class="tableDiv">
				<table class="table table-hover">
					<thead>				
					<tr class="first">
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
					</thead>
					
					<c:forEach var="notice" items="${list }">
						<tr onclick="clickNotice('${notice.notice_code}')">
							<td>${notice.rnum }</td>
							<td>${notice.notice_title }</td>
							<c:set var="length">${fn:length(notice.admin_id) }</c:set> 
							<td>관리자${fn:substring(notice.admin_id, 2, length) }</td>
							<td>${notice.notice_date }</td>
							<td>${notice.hitcount }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<a href="supportnoticewrite.action" class="btn btn-outline-primary admin" role="button">글 작성하기</a>
			<br>
			<div class="page">${pageIndexList }</div>
		</div>	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
		
</body>
</html>