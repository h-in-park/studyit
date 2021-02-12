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
<title>Report_detailpage</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>

<!-- 직접 설정한 CSS -->
<link rel="stylesheet" href="css/sumin/Layout.css">
<link rel="stylesheet" href="css/sumin/Report_detailPage.css">

<script type="text/javascript">
	 
	window.onload = function() {
		
		var result = "<c:out value='${param.result}'></c:out>";
		
		if (result == "WarningSuccess") {
			alert("경고 등록이 완료되었습니다.");
		}	
		else if(result == "UpdateSuccess"){
			alert("신고수 초기화가 완료되었습니다.");
			window.location.href="participantreportlist.action";
		}
		else if(result == "UpdateAndWarningSuccess"){
			alert("초기화 및 허위신고 처리가 완료되었습니다.");
			window.location.href="participantreportlist.action";
		}
	}		
	
	
	
	function clickBtn(text, flag) 
	{
		var message = "";
		var type = 1;
		var response;
		
		if (text=="글삭제")
		{
			message = "글을 삭제하시겠습니까? (제재 없음)";
			response = confirm(message);
			
			// 글 삭제만 함 : update 쿼리문 짜기
			if(response)
			{
				if (flag == 1) // 스터디원 신고
					return;
				else // 게시물 신고
				
				return;
			}
		}	
		else if (text=="경고1회")
		{	
			message = "대상자에게 경고 1회를 부여하겠습니까?";
			response = confirm(message);
			
			// 경고 1회
			if(response)
			{
				if (flag == 1) // 스터디원 신고
					document.getElementById("form").setAttribute( 'action', 'insertwarning.action' );	
				else // 게시물 신고
					document.getElementById("form").setAttribute( 'action', 'insertwarningboard.action' );
				
				document.getElementById("form").submit();				
			}
		}	
		else // 신고대상 아님
		{	
			message = "신고대상이 아니므로 신고수를 초기화하겠습니까?";
			response = confirm(message);
			
			if(response)
			{
				response = confirm("신고자들에게 허위신고로 경고 1회를 부여하겠습니까?");
				
				// 허위신고 + 신고수 초기화
				if(response)
				{
					if (flag == 1) // 스터디원 신고
						document.getElementById("form").setAttribute( 'action', 'updateandinsertwarning.action' );	
					else // 게시물 신고
						document.getElementById("form").setAttribute( 'action', 'updateandinsertboardwarning.action' );
					
					document.getElementById("form").submit();
				}
				// 신고수 초기화
				else
				{
					if (flag == 1) // 스터디원 신고
						document.getElementById("form").setAttribute( 'action', 'updatereg.action' );	
					else // 게시물 신고
						document.getElementById("form").setAttribute( 'action', 'updateboardreg.action' );
					
					document.getElementById("form").submit();
				}	
			}
		} 
	}
	
</script>

</head>
<body>

<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content">
		<div class="menu">
			<br>
			<h1 class="text-center">신고처리</h1>
			<br><br><br>
			<nav>
				<ul>
					<li><a href="participantreportlist.action" class=${memList == null? "" : "selected" }>스터디원</a></li>
					<li><a href="boardreportlist.action" class=${memList == null? "selected" : "" }>게시물</a></li>		
				</ul>
			</nav>
		</div>
		
		<div class="content">
			<p class="category">신고내역</p>
			<div class="tableDiv">
			<form id="form">
				<table class="table">
					<thead>
					<tr>
						<th>번호</th>
						<th>신고사유</th>
						<th>신고자</th>
						<th>신고일자</th>
					</tr>
					</thead>
					<c:forEach var="report" items="${list }">
						<tr>
							<td>${report.rnum }</td>
							<td>${report.report_ctg }</td>
							<td>${report.report_id }</td>
							<td>${report.report_date }</td>
						</tr>
						<c:if test="${report.report_reason != null }">
							<tr class="reason">
								<td colspan="4">
									<span class="reasonSpan">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
					  						<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
										</svg>
										${report.report_reason }
										<input type="hidden" name="report_user_code" value="${report.report_user_code }">
									</span>
								</td>
							</tr>
						</c:if>
					</c:forEach>
					
					<c:forEach var="report" items="${memList }">
						<tr>
							<td>${report.rnum }</td>
							<td>${report.report_ctg }</td>
							<td>${report.report_id }</td>
							<td>${report.report_date }</td>							
						</tr>
						<c:if test="${report.report_reason != null }">
							<tr class="reason">
								<td colspan="4">
									<span class="reasonSpan">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
					  						<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
										</svg>
										${report.report_reason }
										<input type="hidden" name="report_user_code" value="${report.report_user_code }">
									</span>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				
				<c:if test="${memList != null }">
					<input type="hidden" name="reported_parti_code" value="${param.reported_parti_code }"/>
				</c:if>
				
				<c:if test="${memList == null }">
					<input type="hidden" name="post_code" value="${param.post_code }"/>
				</c:if>
				
				<input type="hidden" name="reported_user_code" value="${param.reported_user_code }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				
				
				</form>
			</div>
			<br><br>
			<p class="category">해당 게시물</p>
			<div class="text-center boardDetail">
				게시물 상세페이지 그대로 가져오기
			</div>
			<br><br>
				<div class="buttons">
					<button type="submit" class="btn btn-outline-primary" ${param.reported_handle_result == '미처리'? null : 'disabled' } onclick="clickBtn(this.innerText, ${memList == null? 0 : 1})">글삭제</button>
					<button type="submit" class="btn btn-outline-primary" ${param.reported_handle_result == '미처리'? null : 'disabled' } onclick="clickBtn(this.innerText, ${memList == null? 0 : 1})">경고1회</button>
					<button type="submit" class="btn btn-outline-primary" ${param.reported_handle_result == '미처리'? null : 'disabled' } onclick="clickBtn(this.innerText, ${memList == null? 0 : 1})">신고대상아님</button>
					<button type="button" class="btn btn-outline-primary" onclick="window.location.href='${memList == null? 'boardreportlist.action' : 'participantreportlist.action'}?pageNum=${param.pageNum }'">목록으로</button>
				</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>