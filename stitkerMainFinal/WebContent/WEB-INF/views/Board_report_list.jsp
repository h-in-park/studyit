<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board_report_list.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/ListPage.css">

<script type="text/javascript">

	function clickReport(post_code, reported_user_code, reported_handle_result) {
		window.location.href="reportdetail.action?post_code="+post_code+"&reported_user_code=" + reported_user_code + "&reported_handle_result=" + reported_handle_result + "&<c:out value='${articleUrl}'/>";
	}

</script>

</head>
<body>


<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content">
		<div class="menu">
		<br> 
			<h1 class="text-center">신고처리</h1>
			<br><br><br>
			<nav>
				<ul>
					<li><a href="participantreportlist.action">스터디원</a></li>
					<li><a href="boardreportlist.action" class="selected">게시물</a></li>			
				</ul>
			</nav>
		</div>
		
		<div class="content">		
			<h6 class="count">미처리 신고 <span class="badge rounded-pill bg-primary">${countUntreat }</span></h6>
			<div class="tableDiv">
				<table class="table table-hover">
					<thead>
					<tr class="first">
						<th>번호</th>
						<th>게시판</th>
						<th class="title">제목</th>
						<th>작성자</th>
						<th>처리결과</th>
					</tr>
					</thead>
					<c:forEach var="report" items="${list }">
					<c:set var="boardName" value="${fn:substring(report.post_code, 0, 2)}" />
						<tr onclick="clickReport('${report.post_code }', '${report.reported_user_code }', '${report.handle_result }')" class=${report.handle_result == "미처리"? "noResult" : "" }>
							<td>${report.rnum }</td>
							<td>
								<c:choose>
									<c:when test="${boardName eq 'BI'}">
										정보공유
									</c:when>
									<c:when test="${boardName eq 'BV'}">
										면접/코테
									</c:when>
									<c:when test="${boardName eq 'BS'}">
										세미나/공모전
									</c:when>
									<c:when test="${boardName eq 'BF'}">
										자유
									</c:when>
									<c:when test="${boardName eq 'BQ'}">
										질문
									</c:when>
									<c:when test="${boardName eq 'BA'}">
										답변
									</c:when>
									<c:when test="${boardName eq 'BR'}">
										스터디후기
									</c:when>
									<c:when test="${boardName eq 'SO'}">
										스터디
									</c:when>
								</c:choose>
							</td>
							<td>${report.title }</td>
							<td>${report.reported_id }</td>
							<td>${report.handle_result }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<div class="page">${pageIndexList }</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
		
</body>
</html>