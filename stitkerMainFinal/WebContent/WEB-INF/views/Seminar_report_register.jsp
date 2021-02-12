<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 신고 등록</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Report_register.css">

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
<br>
<form action="seminar_report_add.action">
	<div class="contents">
	<h3 class = "title">신고하기</h3>
	<br>
	<table class="table">
	<input type="hidden" name="post_code" value="${dateil.post_code }">
		 <tr>
			<th>유형</th>
			<td>게시물</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${detail.title }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${detail.user_name }</td>
		</tr>
	</table>
	<br>
	<span class="message">위 게시물에 대한 대표적인 신고사유 1개를 선택하세요.</span>
	<br><br>
		<div class="form-check">
			<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input" required> 광고, 스팸(카테고리 가져오기)</label><br>
			<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input"> 욕설, 비방 </label><br>
			<label><input type="radio" name="report_category" onclick="hide_textarea()" class="form-check-input"> 음란, 유해 </label><br>
			<label><input type="radio" name="report_category" onclick="show_textarea()" class="form-check-input"> 기타</label><br>
		</div>		
		<br>
	
</div>	
	<textarea name="report_reason" id="report_reason" class="form-control textArea" style="display: none;"
	cols="30" rows="10" onclick="click_reason()" placeholder="구체적인 신고 사유를 입력해주세요."></textarea>
	<br>
	<button type="submit" class="btn btn-outline-primary">확인</button>
</form>

</body>
</html>

