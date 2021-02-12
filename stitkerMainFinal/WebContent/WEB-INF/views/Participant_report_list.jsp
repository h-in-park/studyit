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
<title>Participant_report_list.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/ListPage.css">

<script type="text/javascript">

	function clickReport(reported_parti_code, reported_user_code) 
	{
		window.location.href="memreportdetail.action?reported_parti_code=" + reported_parti_code + "&reported_user_code=" + reported_user_code + "&<c:out value='${articleUrl}'/>";
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
					<li><a href="participantreportlist.action" class="selected">스터디원</a></li>
					<li><a href="boardreportlist.action">게시물</a></li>			
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
						<th>대상자</th>
						<th class="title">스터디이름</th>
						<th>처리결과</th>
					</tr>
					</thead>
					<c:forEach var="report" items="${list }">
						<tr onclick="clickReport('${report.reported_parti_code}', '${report.reported_user_code }')" class=${report.handle_result == "미처리"? "noResult" : "" }>
							<td>${report.rnum }</td>
							<td>${report.reported_id }</td>
							<td>${report.study_name }</td>
							<td>${report.handle_result }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<div class="page">${pageIndexList }</div>
			<br><br>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>