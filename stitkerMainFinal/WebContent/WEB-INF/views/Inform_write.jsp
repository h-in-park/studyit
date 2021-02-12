<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IT기술정보공유 글 작성</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>

<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>

<link rel="stylesheet" href="css/Write.css">
<link rel="stylesheet" href="css/Layout.css">

<!-- summernote 적용 -->
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
	
	 });
		
		
	/* 	$(document).ready(function() {
	
		    $("#cancel").click(function()
    		{
    			var result = confirm("취소 하시겠습니까?");
    			
    			if(result)
    			{
    				location.replace("Inform_write.jsp");
    			}
    		});
		
		 }); */

	
</script>

</head>
<body>

<div class="whole">
	<jsp:include page="header.jsp" flush="false"/>
	<!-- 메뉴 구성 영역 -->
 	<div class="menu">
	<br> 
		<h1 class="text-center">정보공유</h1>
		<br><br><br>
		<nav>
			<ul id="menu">
				<li><a href="informlist.action" class="selected">IT기술정보공유</a></li>
				<li><a href="seminarlist.action" >세미나 및 공모전</a></li>
				<li><a href="interviewlist.action">면접/코딩테스트 후기</a></li>
				<li><a href="freelist.action">자유게시판</a></li>
				<li><a href="questionlist.action">Q&A</a></li>
			</ul>
		</nav>
	</div>
	
	<div class="content">
		<br>
		<p class="category">기술정보공유 글 작성하기</p>
		 <form method="get" action="informinsert.action" name="myForm" enctype="multipart/form-data">
			<input type="hidden" name="code" value="${sessionScope.code}">
			<table class="table table-borderless" id="table">
			<tr>
				<th>
					말머리
				</th>
				<td>
					<select name="interest_mc" id="searchCategory" class="form-control" required="required">
						<option value="">선택</option>
						 <c:forEach var="interest" items="${imList }">
							 <option value="${interest.interest_mc_code }">
							 	${interest.interest_mc }
							 </option>
						 </c:forEach>
					</select>
				</td>
			</tr>
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
				<td class="textareaTd" colspan="2">
                     <textarea name="content" id="summernote"></textarea>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>   
</div>

</body>	
</html>