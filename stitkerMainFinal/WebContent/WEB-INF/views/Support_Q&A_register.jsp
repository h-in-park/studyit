<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_Q&A_register.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/Support_write.css">

<script type="text/javascript">

	window.onload = function() {
		document.getElementById("title").focus();
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
					<li><a href="supportnoticelist.action">공지사항</a></li>
					<li><a href="supportqalist.action" class="selected">1:1문의</a></li>
				</ul>
			</nav>
		</div>
		<div class="content">		
			<p class="category">1:1 질문등록</p>
			<br>
			<form action="supportqainsertq.action" method="post">
			<div class="tableDiv">
				<table class="table table-borderless">
					<tr>
						<th>제목</th>
						<td>
							<input type="text" class="form-control write" id="title" placeholder="제목을 입력하세요" name="ask_title" required>
						</td>
					</tr>
					<tr>
						<th>질문</th>
						<td>
							<textarea rows="20" cols="80" id="content" class="form-control write" placeholder="내용을 입력하세요." name="ask_content" required></textarea>
							<br><br>
							<div class="buttons">
								<button type="submit" class="btn btn-outline-primary">등록하기</button>
								<button type="button" class="btn btn-outline-primary" onclick="window.location.href='supportqalist.action'">취소하기</button>
							</div>
							<br><br>
						</td>
					</tr>
				</table>
			</div>
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</div>	
</body>
</html>
