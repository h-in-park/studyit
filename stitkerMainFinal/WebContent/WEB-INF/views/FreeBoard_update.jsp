<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeBoard_update.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=cp %>/css/menu.css">
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/Write.css">
<link rel="stylesheet" href="<%=cp %>/css/Layout.css">
<link rel="stylesheet" href="<%=cp %>/css/Support_write.css">

<!-- Summernote-lite 가져오기 -->
<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="css/summernote-lite.css">  
<style type="text/css">
.table .textareaTd {padding-right: 10%;}
</style>
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
				var result = confirm("수정을 취소 하시겠습니까?");
				
				if(result)
				{
					location.replace("freeboardlist.action");
				}
				
			});
			
		});
</script>

</head>
<body>

<div class="whole">
	<!-- 헤더 추가 -->
	<jsp:include page="header.jsp" flush="false"/>
	
	<!--  좌측 메뉴 구성 -->
	<div>
		<c:import url="BoardMenu.jsp"></c:import>
	</div>
	
	<!-- 페이지 내용 -->
	<div class="content"><br>
		<form action="freeboard_update.action" method="get" name="myForm" role="form" class="form-inline">
			<table class="table table-borderless" id="table">
				<p class="category">자유게시판 글 수정하기</p> 
				<tr>
					<th>
						제목
					</th>
					<td>
						<input type="text" class="form-control" value="${post.title }" required="required" 
						id="title" name="title">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="editordata" id="summernote" class="textareaTd ">${post.content }</textarea> 
					</td>
				</tr>
			</table>
			<input type="hidden" id="post_code" name="post_code" value="${post.post_code }"/>
			<div id="btnSet">
				<button type="submit" class="btn btn-outline-primary">수정하기</button>
				<input type="button" value="취소하기" class="btn btn-outline-primary" id="cancel">	
			</div>
		</form>
	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>

</body>	
</html>