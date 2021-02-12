<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석부</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	
		$(function()
		{
			// 출석 버튼 클릭 시 액션 처리
			$("#inBtn").click(function()
			{
				//alert("check~!!");
				$(location).attr("href", "attendin.action?studycode=" + $("#studycode").val() + "&parti_code=" + $("#parti_code").val());
			});
			
			
			// 퇴실 버튼 클릭 시 액션 처리
			$("#outBtn").click(function()
			{
				//alert("check~!!");
				$(location).attr("href", "attendout.action?studycode=" + $("#studycode").val() + "&parti_code=" + $("#parti_code").val()+"&attend_code=" + $("#attend_code").val());
			});
			
		});
		
</script>
</head>
<body>
<div class="wrapper" >
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content3 text-center" style="height: 400px">
 	<div class="title" style="height: 300px">
      <h3 class="titleTxt p-3 text-center">출석부</h3>
    </div>
    <input type="hidden" id="studycode" value="${param.studycode }">
    <input type="hidden" id="parti_code" value="${param.particode }">
    <input type="hidden" id="attend_code" value="${attendCode }">
  <div class="container" style="width:900px;">  
 <div style="height: 80%"><br><br>
	<table class="table" >
  <thead>
    <tr>
      <th scope="col" class="bg-light">일자 및 시간</th>
      <th scope="col" class="bg-light">출결정보</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="att" items="${attendance }">
    <tr>
      <th scope="row">${att.attend_date }(${att.start_time } ~ ${att.end_time })</th>
      <td>${att.clock_in } ~ ${att.clock_out }(${att.status })</td>
    </tr>
    </c:forEach> 
  </tbody>
</table> 
</div>
	<div class="bottom" style="height:20%; float:bottom">
	     <%--  <button type="button" id="inBtn"  value="${attendInCheck }" ${attendInCheck==0? "class=\"btn btn-outline-primary btn-lg me-5 \"" : "class=\"btn btn-outline-secondary btn-lg me-5 disabled\"" }
	      ${attendInCheck==0? "" : "disabled" }>출석</button> --%>
	      <c:choose>
	      <c:when test ="${check==\"today\" }">
	      	<button type="button" id="inBtn" class="btn btn-outline-primary btn-lg">출석</button>
   	        <button type="button" id="outBtn" class="btn btn-outline-primary btn-lg">퇴실</button>
	      </c:when>
	      <c:otherwise>
	      	<button type="button" id="inBtn" class="btn btn-outline-primary btn-lg" disabled="disabled">출석</button>
   	        <button type="button" id="outBtn" class="btn btn-outline-primary btn-lg" disabled="disabled">퇴실</button>
	      </c:otherwise>
	      </c:choose>
	</div>
</div>
 </div>
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>