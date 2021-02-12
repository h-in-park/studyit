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
<title>스터디 후기 수정</title>

<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/style.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<!-- Summernote-lite 가져오기 -->
<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/summernote-lite.css">
<script type="text/javascript">
	
		$(document).ready(function() {
			$('.summernote').summernote({
				height: 400,
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: "ko-KR",
				placeholder: '내용을 입력하세요.'
	  		});
			
			// 취소버튼 클릭 
			$("#cancel").click(function()
			{
				$(location).attr("href","studyreviewlist.action");
			});
	  
		});
		 
	
</script>
<style type="text/css">
table > tr> th
{
	margin-left:20px;
}

.right{
margin-left:40%;
}
</style>
</head>
<body>
<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content">
		<br>
		<div class="container">
		<h3>스터디 후기 수정</h3>
		<form action="review_update.action" method="post" name="myForm" role="form">
		<input type="hidden" value="${param.post_code }" name='post_code' >
		<table class="table">
		<tr>
			<th>
				제목
			</th>
			<td>
				<input type="text" class="form-control" value="${post.title}" placeholder="제목을 입력하세요" required="required" name="title" id="title">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea class="form-control summernote" placeholder="내용을 입력하세요"  style="height: 170px;" required="required" name="content" id="content">value="${post.content}"</textarea>
			</td>
		</tr>
		</table>
		<div class="right">
			<input type="submit" value="수정하기" class="btn btn-outline-primary">
			<input type="button" value="취소하기" class="btn btn-outline-primary" id="cancel">	
		</div>
		</form>
	</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>