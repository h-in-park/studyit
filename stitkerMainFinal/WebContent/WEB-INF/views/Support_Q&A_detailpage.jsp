<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support_Q&A_detailpage.jsp</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/DetailPage.css">
<link rel="stylesheet" href="css/sumin/Q&A_detailPage.css">

<script type="text/javascript">
	
	function clickAnswer(ask_code) {
		
		var btn = document.getElementById("answerBtn").innerHTML;
		
		if (btn == "답변등록하기" || btn == "답변수정완료") {
			return true;
		}
		
		answer.style.display = "block";
		
		if (btn == "답변하기")
			document.getElementById("answerBtn").innerHTML = "답변등록하기";
		else if(btn == "답변수정하기")
			document.getElementById("answerBtn").innerHTML = "답변수정완료";
		
		document.getElementById("ask_answer").setAttribute("required", "");
		
		return false;
		
	}
	
	$().ready(function(){
		
		if("<c:out value='${admin}'></c:out>" == "")
			$(".admin").css("display", "none");
		
		else
			$(".member").css("display", "none");
	});
	
	function clickDelete(ask_code) {
		
		if ("<c:out value='${qa.answer}'></c:out>" == "답변 대기" && confirm("해당 게시물을 정말 삭제하시겠습니까?")) 
		{
			window.location.href='supportqadeleteq.action?ask_code='+ ask_code;		
		}
		else
			alert("답변이 등록된 게시물은 삭제할 수 없습니다.");
		
	}
	
	function clickModify(ask_code) {
		
		if ("<c:out value='${qa.answer}'></c:out>" == "답변 대기") 
		{
			window.location.href='supportqamodifyqform.action?ask_code='+ ask_code;	
		}
		else
			alert("답변이 등록된 게시물은 수정할 수 없습니다.");
		
	}

</script>
</head>
<body>

<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content"><br>
		<div class="menu">
		<br> 
			<h1 class="text-center">고객센터</h1>
			<br><br><br>
			<nav>
				<ul>
					<li><a href="supportnoticelist.action">공지사항</a></li>
					<li><a href="supportqalist.action" class="selected">1:1문의</a></li>
				</ul>
			</nav>
		</div>
		
		<div class="content">
		<form action="supportqainserta.action" method="POST" onsubmit="return clickAnswer('${qa.ask_code}')">
			<table class="table">
				<tr>
					<th>제목</th>
					<td colspan="6">${qa.ask_title }</td>
				</tr>
				<tr class="title">
					<th>작성자</th>
					<td>${qa.id }</td>
					<th>작성일</th>
					<td>${qa.ask_date }</td>
					<th>답변</th>
					<td>${qa.answer }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="6">
						<div class="table_content">
							<span class="text">${qa.ask_content }</span>
						</div>
					</td>
				</tr>
				<tr>
					<th>답변</th>
					<td colspan="6">
						<div class="answer_content">
							<span class="text">${qa.answer == "답변 대기"? "현재 등록된 답변이 없습니다." : qa.ask_answer }</span>
						</div>
					</td>
				</tr>
			</table>
			<div class="answer" id="answer">
				<span class="icon">
				<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
  					<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
				</svg>
				</span>
				<textarea rows="6" placeholder="답변을 입력하세요." name="ask_answer" class="form-control" id="ask_answer">${qa.ask_answer }</textarea>
			</div>
			<br>
			<div class="buttons member">
				<button type="button" class="btn btn-outline-primary" onclick="clickModify('${qa.ask_code}')">수정하기</button>
				<button type="button" class="btn btn-outline-primary" onclick="clickDelete('${qa.ask_code}')">삭제하기</button>
				<button type="button" class="btn btn-outline-primary" onclick="window.location.href='supportqalist.action?pageNum=${param.pageNum}'">목록으로</button>
			</div>
			<div class="buttons admin">
				<button type="button" class="btn btn-outline-primary" onclick="window.location.href='supportqalist.action'">목록으로</button>
				<button type="submit" class="btn btn-outline-primary" id="answerBtn">${qa.answer == "답변 대기"? "답변하기" : "답변수정하기" }</button>
			</div>
			<input type="hidden" name="admin_code" value="${qa.admin_code }">
			<input type="hidden" name="ask_code" value="${qa.ask_code }">
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>