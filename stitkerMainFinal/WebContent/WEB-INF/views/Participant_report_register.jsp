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
<title>Participant_report_register.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/Report_register.css">
 
<script type="text/javascript">
	
	/* 신고사유 보이기 */
	function show_textarea() {
		document.getElementById("report_reason").style.display="block";
	}
	
	/* 신고사유 감추기 */
	function hide_textarea() {
		document.getElementById("report_reason").style.display="none";
	}
	
</script>
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content "> 
    <div class="bg-dark col-12 title3">
        <h3 class="titleTxt p-3 text-center">스터디원 신고</h3>
     </div>
     <div class="container" style="height: 850px;">
     <br><br><br>
<!-- $.ajax() 로 target 을 #layer_pop  -->
<form action="reportinsert.action" method="get">
<input type="hidden" id="studycode" name="studycode" value="${param.studycode }">
<input type="hidden" id="parti_code" name="parti_code" value="${param.parti_code }">
<input type="hidden" name="userCode" id="userCode" value="${userCode }">
<input type="hidden" name="userCode2" id="userCode2" value="${userCode2 }">
<div>
	<div class="col-md-12 col-lg-12">
		<table class="table" style="width:100%">
			<tr>
				<th class="bg-light">유형</th>
				<td >스터디</td>
			</tr>
			<tr>
				<th class="bg-light">스터디명</th>
				<td>${studyname }</td>
			</tr>
			<tr>
				<th class="bg-light">신고할 스터디원</th>
				<td>${param.memid }</td>
			</tr>
		</table>
	</div>
</div> 
	<br><br> 
	<div class="col-12 center" style="width:100%">
	<div style="margin-left:40%">
	    <span class="message">위 스터디원에 대한 대표적인 신고사유 1개를 선택하세요.</span>
	    <br><br>
		<label><input type="radio" name="report_category" value="RC1" onclick="hide_textarea()"> 따돌림</label><br>
		<label><input type="radio" name="report_category" value="RC2" onclick="hide_textarea()"> 부정출석 </label><br>
		<label><input type="radio" name="report_category" value="RC3" onclick="hide_textarea()"> 태도불량 </label><br>
		<label><input type="radio" name="report_category" value="RC4" onclick="hide_textarea()"> 불쾌감을 주는 언행 </label><br>
		<label><input type="radio" name="report_category" value="RC5" onclick="hide_textarea()"> 목적에 벗어난 참여(다단계, 종교 등) </label><br>
		<label><input type="radio" name="report_category" value="RC6" onclick="show_textarea()"> 기타</label><br>
		<br>
		<textarea name="reason" id="reason" cols="40" rows="10" onclick="click_reason()" placeholder="구체적인 신고 사유를 입력해주세요."></textarea>
		<br>
		<span><button type="submit" class="btn btn-outline-primary">확인</button></span>
	</div>
	</div>
</form>	
</div>
</div><!-- container -->
<jsp:include page="footer.jsp" flush="false"/>
</div>
</body>
</html>

