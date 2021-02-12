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
<title>Find_id.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>

<!-- 로그인 관련 페이지 공통 css-->
<link rel="stylesheet" href="<%=cp%>/css/login.css" />

<style type="text/css">
	#findId_byEmail span
	{
		display: none;
	}
	#findId_byEmail button
	{
		float: right;
	}
	.divBot a
	{
		text-decoration: none;
		color: gray;
	}
	.divBot a:hover
	{
		color: SlateBlue;
		font-weight: bold;
	}
</style>

<!-- 스크립트 처리 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#rdo_byEmail").click(function()
		{
			$("#findId_byEmail").css("display", "block");
		});

	});
</script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" flush="false" />
		<div class="main-content">
		<br>


			<div class="login">
				<div class="divTop">
					<h5>
						<strong>아이디 찾기</strong>
					</h5>
				</div>
				<br>

				<!-- 이메일로 아이디 찾기 -->
				<form action="findid.action" method="post">
					<div class="form-group">
						<label for=""> 
							<span style="color: black;"> 내 정보에 등록된 이메일로 찾기</span>
						</label>

						<div id="findId_byEmail" style="display: block; margin-top: 5px;">
							<div class="form-group">
								<input type="email" class="form-control" id="email" name="email"
								placeholder="email@example.com" required="required" /> 
								<span style="color: red; font-size: small;">입력한 이메일과 일치하는 회원정보가 존재하지 않습니다.</span>
								<br><br><br>
							</div>
							<button type="submit" class="btn btn-outline-primary">다음 단계</button>
							<br>
						</div>
					</div>
				</form>
				<br><br>

				<div class="divBot">
					<small>비밀번호를 찾으시나요? <a href="findpwform.action">비밀번호 찾기</a></small>
				</div>
				
			</div><!-- end .login -->


		</div><!-- end .main-content -->
		
		<jsp:include page="footer.jsp" flush="false" />
	</div><!-- end .wrapper -->
</body>
</html>