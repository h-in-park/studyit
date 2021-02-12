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
<title>Terms.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<style type="text/css">
.policy
{
	
	margin-left: 15%;
	margin-right: 15%;
	min-height: 650px;
	font-family: '맑은 고딕';
}
#Terms
{
	margin-top: 3%;
	margin-left: 5%;
	margin-right: 5%;
	margin-bottom: 3%;
}
</style>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" flush="false" />
		<div class="main-content">
		
			<div class="policy">
				<!-- 정책 탭 -->
				<nav>
				  <div class="nav nav-tabs" id="nav-tab" role="tablist">
				    <a class="nav-link active " id="nav-leader-tab" data-bs-toggle="tab" href="#nav-leader" role="tab" 
				    	aria-controls="nav-leader" aria-selected="true">스터디 리더 가이드</a>
				    <a class="nav-link" id="nav-entry-tab" data-bs-toggle="tab" href="#nav-entry" role="tab" 
				    	aria-controls="nav-entry" aria-selected="false">스터디 참가자 가이드</a>
				    <a class="nav-link" id="nav-service-tab" data-bs-toggle="tab" href="#nav-service" role="tab" 
				    	aria-controls="nav-service" aria-selected="false">서비스 이용약관</a>
				    <a class="nav-link" id="nav-info-tab" data-bs-toggle="tab" href="#nav-info" role="tab" 
				    	aria-controls="nav-info" aria-selected="false">개인정보 처리방침</a>
				  </div>
				</nav>
				
				<!-- 정책 내용 -->
				<div class="tab-content" id="nav-tabContent">
				  <!-- 스터디 리더 가이드 -->
				  <div class="tab-pane fade show active bg-light" id="nav-leader" role="tabpanel" aria-labelledby="nav-leader-tab">
					<jsp:include page="Terms_Leader.jsp" flush="false" /></div>
					
				  <!-- 스터디 참가자 가이드 -->
				  <div class="tab-pane fade bg-light" id="nav-entry" role="tabpanel" aria-labelledby="nav-entry-tab">
					<jsp:include page="Terms_Entry.jsp" flush="false" /></div>
					
				  <!-- 서비스 이용약관 -->
				  <div class="tab-pane fade bg-light" id="nav-service" role="tabpanel" aria-labelledby="nav-service-tab">
				  	<jsp:include page="Terms_Service.jsp" flush="false" /></div>
				  	
				  <!-- 개인정보 처리방침 -->
				  <div class="tab-pane fade bg-light" id="nav-info" role="tabpanel" aria-labelledby="nav-info-tab">
				  	<jsp:include page="Terms_info.jsp" flush="false" /></div>
				  	
				</div> <!-- end 정책 내용 -->
				
			</div><!-- .policy -->
	
		</div><!-- .main-content -->
		<jsp:include page="footer.jsp" flush="false" />
	</div>
</body>
</html>