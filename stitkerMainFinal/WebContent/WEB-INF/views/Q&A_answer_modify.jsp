<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/Write.css">

<link rel="stylesheet" href="css/Layout.css">

<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>	
<link rel="stylesheet" href="css/summernote-lite.css">

<script type="text/javascript">

	window.onload = function() {
		document.getElementById("title").focus();
	}
</script>

<script type="text/javascript">
	
		$(document).ready(function() {
			$('#summernote').summernote({
				height: 400,
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: "ko-KR",
				placeholder: '내용을 입력하세요.'
	  		});
			
			$("#cancel").click(function()
			{
				var result = confirm("취소 하시겠습니까?");
				
				if(result)
				{
					location.replace("qnaboard_detail.action?post_code=" + $("#qpost_code").val());
				}
			});
	  
		});
	
</script>

</head>
<body>
<div class="whole">
	<jsp:include page="header.jsp" flush="false"/>
	
	<!-- 좌측 메뉴 구성 영역 -->
	<c:import url="BoardMenu.jsp"></c:import>
	
	<div class="content">
		<p class="category">Q&A 답변 글 수정하기</p><br>
		
		<form action="qnaboard_answer_update.action" method="get" name="myForm" role="form" class="form-inline">
		
			<table class="table table-borderless" id="table">
				<tr>
					<th>
						제목
					</th>
					<td>
						<input type="text" class="form-control" placeholder="제목을 입력하세요" required="required" id="title" name="title"
						value="${post.title }">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea class="form-control" placeholder="내용을 입력하세요"  style="height: 170px;" required="required" name="editordata" id="summernote"
						>${post.content }</textarea>
					</td>
				</tr>
			</table>
			
			<div id="btnSet">
				<input type="hidden" id="qpost_code" name="qpost_code" value="${post.qpost_code }"/>
				<input type="hidden" name="post_code" value="${post.post_code }" />
				<input type="hidden" name="user_code" value="${post.user_code }"/>
				<input type="submit" value="수정하기" class="btn btn-outline-primary">
				<input type="button" value="취소하기" class="btn btn-outline-primary" id="cancel" >	
			</div>
			
		</form>
	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
</body>	
</html>