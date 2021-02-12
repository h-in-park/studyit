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
<title></title>
<style type="text/css">
	.headerNav > a, span
	{
		text-decoration: none;
		color: gray;
	}
	.headerNav > a:hover
	{
		color: SlateBlue;
		font-weight: bold;
	}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand m-sm-3" href="studyit.action"> <img
				src="images/logo3.png" alt="" width="150px" height="70px">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<!-- <li class="nav-item">
                <a class="nav-link" aria-current="page" href="#">스터딧소개</a>
              </li> -->
					<li class="nav-item"><a class="nav-link" href="studysearch.action">스터디 찾기</a></li>
					<li class="nav-item"><a class="nav-link" href="studyinsertform.action">스터디 개설</a></li>
					<li class="nav-item"><a class="nav-link" href="studyreviewlist.action">스터디 후기</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							정보공유</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="informlist.action">IT기술정보 공유</a></li>
							<li><a class="dropdown-item" href="seminarlist.action">세미나/공모전 알림</a></li>
							<li><a class="dropdown-item" href="interviewlist.action">면접/코딩테스트 후기</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="freeboardlist.action">자유게시판</a></li>
							<li><a class="dropdown-item" href="questionlist.action">Q&A</a></li>
						</ul>
				</ul>
				<form class="d-flex">
					<c:choose>
						<c:when test="${sessionScope.code==null }">
							<span style="color: Slateblue; font-size: 15pt; margin-right: 15px;"> 스터딧과 함께해보세요 
							<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">
							  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
							  <path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
							</svg>
							</span>
						</c:when>
						<c:when test="${sessionScope.code!=null && sessionScope.admin==null }">
							<span style="color: Slateblue; font-size: 15pt; margin-right: 15px;">${sessionScope.userid } 님 어서오세요 
							<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-emoji-laughing" viewBox="0 0 16 16">
							  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
							  <path d="M12.331 9.5a1 1 0 0 1 0 1A4.998 4.998 0 0 1 8 13a4.998 4.998 0 0 1-4.33-2.5A1 1 0 0 1 4.535 9h6.93a1 1 0 0 1 .866.5zM7 6.5c0 .828-.448 0-1 0s-1 .828-1 0S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 0-1 0s-1 .828-1 0S9.448 5 10 5s1 .672 1 1.5z"/>
							</svg>
							</span>
						</c:when>
						<c:otherwise>
							<span style="color: Slateblue; font-size: 15pt; margin-right: 15px;">관리자님 어서오세요 
							<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-emoji-sunglasses" viewBox="0 0 16 16">
							  <path d="M4.968 9.75a.5.5 0 1 0-.866.5A4.498 4.498 0 0 0 8 12.5a4.5 4.5 0 0 0 3.898-2.25.5.5 0 1 0-.866-.5A3.498 3.498 0 0 1 8 11.5a3.498 3.498 0 0 1-3.032-1.75zM7 5.116V5a1 1 0 0 0-1-1H3.28a1 1 0 0 0-.97 1.243l.311 1.242A2 2 0 0 0 4.561 8H5a2 2 0 0 0 1.994-1.839A2.99 2.99 0 0 1 8 6c.393 0 .74.064 1.006.161A2 2 0 0 0 11 8h.438a2 2 0 0 0 1.94-1.515l.311-1.242A1 1 0 0 0 12.72 4H10a1 1 0 0 0-1 1v.116A4.22 4.22 0 0 0 8 5c-.35 0-.69.04-1 .116z"/>
							  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-1 0A7 7 0 1 0 1 8a7 7 0 0 0 14 0z"/>
							</svg>
							</span>
						</c:otherwise>
					</c:choose>
				</form>
				<nav class="headerNav">
					 
					<span class="bar">|</span>
					<c:choose>
						<c:when test="${sessionScope.code==null }">
							<a href="loginform.action">로그인</a>
							<span class="bar">|</span>
							<a href="joininsertform.action">회원가입</a>
							<span class="bar">|</span>
							<a href="supportnoticelist.action">고객센터</a>
						</c:when>
						<c:when test="${sessionScope.admin==null }">
							<a href="logout.action">로그아웃</a>
							<span class="bar">|</span>
							<a href="mypage.action">마이페이지</a>
							<span class="bar">|</span>
							<a href="supportnoticelist.action">고객센터</a>
						</c:when>
						<c:otherwise>
							<a href="logout.action">로그아웃</a>
							<span class="bar">|</span>
							<a href="participantreportlist.action">신고처리</a>
							<span class="bar">|</span>
							<a href="supportnoticelist.action">문의관리</a>
						</c:otherwise>
					</c:choose>
					 
				</nav>
			</div>
		</div>
	</nav>

</body>
</html>