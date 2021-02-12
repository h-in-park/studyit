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
<title>Find_pw.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->

<!-- 로그인 관련 페이지 공통 css -->
<link rel="stylesheet" href="<%= cp %>/css/login.css" />

<!-- 아이디 찾기, 비밀번호 찾기 페이지 공통 css -->
<link rel="stylesheet" href="<%= cp %>/css/find.css" />
<style type="text/css">
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
			$("#find_byQnA").css("display", "none");
			$("#rdo_byQnA").prop('checked', false);
			$(".brDiv").css("display", "block");
		});
	
		$("#rdo_byQnA").click(function()
		{
			$("#findId_byEmail").css("display", "none");
			$("#find_byQnA").css("display", "block");
			$("#rdo_byEmail").prop('checked', false);
			$(".brDiv").css("display", "none");
			
		});

		
	}); 
		
</script>

</head>
<body>
<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content"><br>
   
		<div class="login">
			<div class="divTop">
				<h5>
					<strong>비밀번호 찾기</strong>
					<br>
					<small style="color: gray;">비밀번호 찾기 방법을 선택해주세요.</small>
				</h5>
			</div>
			<br>
			
			
			<!-- 이메일로 찾기 -->
			<form action="findbyemail.action" method="post">
				<div class="form-group byEmail">
					<div class="custom-control custom-radio">
						<input type="radio" name="rdo_byEmail" id="rdo_byEmail" class="custom-control-input">
						<label class="custom-control-label" for="rdo_byEmail">내 정보에 등록된 이메일로 찾기</label>
					</div>
					
					<div id="findId_byEmail" style="margin-top: 5px;">
						<div class="form-group">
							<input type="text" id="userId" name="userId" class="form-control" 
										placeholder="아이디를 입력해주세요." style="font-size: 10pt;" required="required"/>
							<input type="email" class="form-control" style="font-size: 10pt; margin-top:5px;" id="email" name="email"
										placeholder="email@example.com" required="required"/>
							<!-- errMsg -->
							<span style="color: red; font-size: small;">입력한 이메일과 일치하는 회원정보가 존재하지 않습니다.</span>
						</div>
						<button type="submit" class="btn btn-outline-primary" style="margin-top: 5px;">다음 단계</button>
						<br>
					</div>	
				</div>
			</form>
			
			<br>
			
			<div class="brDiv">
				<br>
			</div>
			
			
			
			<!-- 비밀번호 찾기 질문으로 찾기 -->
			<form action="findbyqna.action" method="post">
			
				<div class="form-group byQnA">
					<div class="custom-control custom-radio">
						<input type="radio" name="rdo_byQnA" id="rdo_byQnA" class="custom-control-input">
						<label class="custom-control-label" for="rdo_byQnA">비밀번호 찾기 질문으로 찾기</label>
					</div>
					
					<div id="find_byQnA">
						<div class="form-group">
							<input type="text" id="userId" name="userId" class="form-control" 
										placeholder="아이디를 입력해주세요." style="font-size: 10pt; margin-top:5px;" required="required"/>
							<select id="selectQue" name="selectQue" class="form-control" style="font-size: 10pt; margin-top:5px; margin-bottom: 5px;">
								<!-- <option value="">비밀번호 찾기 질문을 선택해 주세요.</option>
								<option value="PQ1">가장 좋아하는 동물은?</option>
								<option value="PQ2">가장 좋아하는 음식은? </option>
								<option value="PQ3">기억에 남는 장소는?</option>
								<option value="PQ4">가장 좋아하는 영화는?</option>
								<option value="PQ5">인상 깊게 읽은 책 이름은?</option>
								<option value="PQ6">자신의 보물 1호는?</option>
								<option value="PQ7">가장 친한 친구의 이름은?</option>
								<option value="PQ8">자신의 인생 좌우명은?</option> -->
								
								<c:forEach var="que" items="${queList }">
									<option value="${que.quecode }">${que.question }</option>
								</c:forEach> 
								
							</select>
							<input type="text" id="userAns" name="userAns" class="form-control" 
										placeholder="비밀번호 찾기 답을 입력해주세요." style="font-size: 10pt;" required="required"/>		
										
							<!-- errMsg ajax -->	
							<%-- <span style="color: red; font-size: small; display: ${err==null? inline : none}">${err }</span> --%>
						</div>
						<button id="btnbyQna" type="submit" class="btn btn-outline-primary" style="margin-top: 5px;">다음 단계</button>
						<br>
					</div>
				</div>	
			</form>	
			<br><br>
			
			
			<div class="divBot">
				<small>아이디를 찾으시나요? <a href="findidform.action">아이디 찾기</a></small>
			</div>
		</div><!-- end .login -->
     
     
     
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
</body>
</html>