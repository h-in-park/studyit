<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Study_report_register.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/Report_register.css">
<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="<%=cp %>/css/Report_register.css">

<script type="text/javascript">
	/* 신고사유 보이기 */
	function show_textarea() {
		document.getElementById("report_reason").style.display="block";
		document.getElementById("report_reason").required="required";
	}
	
	/* 신고사유 감추기 */
	function hide_textarea() {
		document.getElementById("report_reason").style.display="none";
		document.getElementById("report_reason").removeAttribute("required");
	}	
</script>
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content "> 
    <div class="bg-dark col-12 title3">
        <h3 class="titleTxt p-3 text-center">스터디 신고</h3>
     </div>
     <div class="container" style="height: 850px;">
     <br><br>
<br>
<form action="">
	<table class="table">
		<tr>
			<th>유형</th>
			<td>스터디</td>
		</tr>
		<tr>
			<th>스터디</th>
			<td>스터디이름불러와야함</td>
		</tr>
		<tr>
			<th>개설자</th>
			<td>개설자불러와야함</td>
		</tr>
	</table>
	<br>
<div class="col-12 center" style="width:100%">
	<div style="margin-left:40%">
    <span class="message">위 게시물에 대한 대표적인 신고사유 1개를 선택하세요.</span>
    <br><br>
    <div class="form-check" >
		<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input" required> 광고, 스팸(카테고리 가져오기)</label><br>
		<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input"> 욕설, 비방 </label><br>
		<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input"> 음란, 유해 </label><br>
		<label><input type="radio" name="report_category" onclick="show_textarea()" class="form-check-input"> 기타</label><br>
	</div>
	<br>
	<textarea name="report_reason" id="report_reason"  class="form-control textArea" style="display:none;"
	cols="40" rows="10" onclick="click_reason()" placeholder="구체적인 신고 사유를 입력해주세요."></textarea>
	<br>
	<button type="submit" class="btn btn-outline-primary" id="submitBtn">확인</button>
</div>
</div>
</form>

</div>
</div><!-- container -->
<jsp:include page="footer.jsp" flush="false"/>
</div>
</body>
</html>

