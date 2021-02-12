<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 컨텐츠</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="<%=cp %>/css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- Summernote-lite 가져오기 -->
<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="css/summernote-lite.css">

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
	
	$(function()
	{
		// 수정 버튼 클릭 시 액션 처리
		$("#updateBtn").click(function()
		{
			//alert()
			$(location).attr("href", "contentmodifyform.action?studycode="+$('#studycode').val() + "&content_code=" + $(this).val());
			
		});
		
		// 삭제 버튼 클릭 시 액션 처리 
		$("#deleteBtn").click(function()
		{
			if(confirm("정말 삭제하시겠습니까?"))
			{
				$(location).attr("href", "contentdelete.action?studycode="+$('#studycode').val() + "&content_code=" + $(this).val());	
			}
		});
		
		$("#backBtn").click(function()
		{
			$(location).attr("href","nowstudy.action?studycode="+$(this).val());
		}); 
		
	});
	
</script>

<style type="text/css">

.table .textareaTd {padding-right: 10%;}

</style>
</head>
<body>
<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content2">
		<div class="container">
		<br><br><br>
		<form action="" name="myForm">
		<input type="hidden" id="studycode" name="studycode" value="${param.studycode }" >
		<c:forEach var="con" items="${detail }">
		<table class="table"> 
			<tr>
				 <th class="bg-light" style="width:20%">제목</th>
				 <td style="width:40%; text-align: center;">${con.title }</td>
				 <th class="bg-light" style="width:20%">작성자</th>
				 <td style="width:20%; text-align: center;">${con.id }</td>
			</tr>
			<tr>
				 <th class="bg-light">작성일</th>
				 <td style="text-align: center;">${con.write_date }</td>
				 <th class="bg-light">조회수</th>
				 <td style="text-align: center;">${con.hitcount }</td>
			</tr>
			<tr>
				<td colspan="4" style="height: 300px; padding:20px">${con.content }</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="BoardDetail_noLine">
						이전글 : 없음.
					</div><!-- .BoardDetail_noLine -->
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div class="BoardDetail_noLine">
						다음글 : (2) 스터디 일정 필독!!
					</div><!-- .BoardDetail_noLine -->
				</td>
			</tr>
		</table>
		<br><br>
		<button type="button" id="backBtn" class="btn btn-outline-primary btn-sm" value="${param.studycode }">리스트</button> 
		<c:if test="${con.id == sessionScope.userid }">
		<button  type="button" id="deleteBtn" value="${con.content_code }"  class="btn btn-outline-primary btn-sm float-end mb-5">삭제</button>
		<button  type="button" id="updateBtn" value="${con.content_code }"  class="btn btn-outline-primary btn-sm float-end me-2 mb-5">수정</button>
		</c:if>
	</c:forEach>
	</form>
    </div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</div>	
</body>
</html>