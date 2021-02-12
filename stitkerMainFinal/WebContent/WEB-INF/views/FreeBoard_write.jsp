<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FreeBoard_write.jsp</title>
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
				var result = confirm("작성을 취소 하시겠습니까?");
				
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
	<jsp:include page="header.jsp" flush="false"/>
	<!--  메뉴 구성 영역 -->
   <div>
   <c:import url="BoardMenu.jsp"></c:import>
   </div>
	
	<div class="content">
		<br>
		<form action="freeboard_insert.action" method="get" name="myForm" role="form" class="form-inline">
			<table class="table table-borderless" id="table">
			<!-- 	<tr>
					<td colspan="2"> -->
						<p class="category">자유게시판 글 작성하기</p> 
				<!-- 	</td>
				</tr> -->
				<tr>
					<th>
						제목
					</th>
					<td>
						<input type="text" class="form-control" placeholder="제목을 입력하세요" required="required" 
						id="title" name="title">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="editordata" id="summernote" class="textareaTd "></textarea> 
					</td>
				</tr>
			</table>
			<div id="btnSet">
				<input type="submit" value="작성하기" class="btn btn-outline-primary">
				<input type="button" value="취소하기" class="btn btn-outline-primary" id="cancel">	
			</div>
		</form>
	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>

</body>	
</html>