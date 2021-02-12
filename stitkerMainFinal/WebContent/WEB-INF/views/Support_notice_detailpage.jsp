<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_notice_detailpage.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/DetailPage.css">

<script type="text/javascript">

	function modify(notice_code, pageNum) 
	{
		window.location.href="supportnoticemodifyf.action?notice_code="+notice_code + "&pageNum=" + pageNum;
	}
	
	function deleteN(notice_code)
	{
		if (confirm("해당 글을 삭제하시겠습니까?")) 
			window.location.href="supportnoticedelete.action?notice_code="+notice_code;	
	}

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
			<table class="table">
				<tr>
					<th>제목</th>
					<td colspan="5">${detail.notice_title }</td>
				</tr>
				<tr class="title">
					<th>작성자</th>
					<c:set var="length">${fn:length(detail.admin_id) }</c:set> 
					<td>관리자${fn:substring(detail.admin_id, 6, length) }</td>
					<th>작성일</th>
					<td>${detail.notice_date }</td>
					<th>조회수</th>
					<td>${detail.hitcount }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5">
						<div class="table_content">
							<span class="text">${detail.notice_content }</span>
						</div>
					</td>
				</tr>
			</table>
			<br>
			<div class="buttons">
			<c:if test="${admin == 'admin'}">
				<button type="button" class="btn btn-outline-primary" onclick="modify('${detail.notice_code}', ${param.pageNum})">수정하기</button>
				<button type="button" class="btn btn-outline-primary" onclick="deleteN('${detail.notice_code}')">삭제하기</button>
			</c:if>
				<button type="button" class="btn btn-outline-primary" onclick="window.location.href='supportnoticelist.action?pageNum=${param.pageNum}'">목록으로</button>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>