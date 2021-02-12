<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_notice_write.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- Summernote-lite 가져오기 -->
<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="css/summernote-lite.css">

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/Support_write.css">

<script type="text/javascript">

	$(document).ready(function() {
		$('#summernote').summernote({
			height: 500,
			minHeight: null,
			maxHeight: null,
			focus: true,
			lang: "ko-KR",
			placeholder: '내용을 입력하세요.'
  		});
  
	});
	
	function insertNotice() {
		
		var content = document.getElementById("summernote").value;
		var title = document.getElementById("title").value;
		
		if(title != "" && (content == "" || content == " " || content == null))
		{	
			alert("내용을 입력하세요.");
			return false;
		}	
		else if (title == "")
			return false;
		
			return true;
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
			<p class="category">공지사항 작성</p>
			<br>
			<form action="supportnoticeinsert.action" method="post" onsubmit="return insertNotice()">
			<div class="tableDiv">
				<table class="table table-borderless">
					<tr>
						<th>제목</th>
						<td>
							<input type="text" class="form-control write" id="title" placeholder="제목을 입력하세요" name="notice_title" required>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="textareaTd">
							<textarea name="notice_content" id="summernote" ></textarea>
							<br><br>
							<div class="buttons">
								<button type="submit" class="btn btn-outline-primary" >등록하기</button>
								<button type="button" class="btn btn-outline-primary" onclick="window.location.href='supportnoticelist.action'">목록으로</button>
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