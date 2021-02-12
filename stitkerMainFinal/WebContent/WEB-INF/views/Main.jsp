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
<title>Main.jsp</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<link rel="stylesheet" href="<%= cp %>/css/mainpage.css" />
<script type="text/javascript">
	
	$(function()
	{
		$(".btnStudy").click(function()
		{
			$(location).attr("href", "studysearch.action");
		});
		
		$(".btnInform").click(function()
		{
			$(location).attr("href", "informlist.action");
		});
		
		$(".btnReview").click(function()
		{
			$(location).attr("href", "studyreviewlist.action");
		});
	});
	
	
</script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="header.jsp" flush="false" />
		<div class="main-content" style="margin-left:auto; margin-right:auto;">


			<div class="bg-light studyit" >
				<br><br>
				<!-- 스터딧 장점 -->
				<div class="content_Table">
					<div class="content_TableBody">
						<div class="content_Row">
							<div class="content_Cell">
								<h1 style="text-align: left; padding-left: 20%;">
									<span id="highlight">스터딧</span><br /> <span id="titlea">어떤	점이 좋나요?</span>
								</h1>
							</div>
						</div>
						<div class="content_Row">
							<div class="content_Cell">

								<svg xmlns="http://www.w3.org/2000/svg" width="48" height="48"
									fill="currentColor" class="bi bi-hand-thumbs-up"
									viewBox="0 0 16 16">
				 				<path
										d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z" />
							</svg>


							</div>
							<div class="content_Cell">

								<svg xmlns="http://www.w3.org/2000/svg" width="45" height="45"
									fill="currentColor" class="bi bi-person-check-fill"
									viewBox="0 0 16 16">
								<path fill-rule="evenodd"
										d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z" />
								<path
										d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
							</svg>

							</div>
							<div class="content_Cell">

								<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50"
									fill="currentColor" class="bi bi-layout-text-window-reverse"
									viewBox="0 0 16 16">
								<path
										d="M13 6.5a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h5a.5.5 0 0 0 .5-.5zm0 3a.5.5 0 0 0-.5-.5h-5a.5.5 0 0 0 0 1h5a.5.5 0 0 0 .5-.5zm-.5 2.5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1h5z" />
								<path
										d="M14 0a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12zM2 1a1 1 0 0 0-1 1v1h14V2a1 1 0 0 0-1-1H2zM1 4v10a1 1 0 0 0 1 1h2V4H1zm4 0v11h9a1 1 0 0 0 1-1V4H5z" />
							</svg>

							</div>
						</div>
						<div class="content_Row">
							<div class="content_Cell boldCell">신뢰성</div>
							<div class="content_Cell boldCell">맞춤형</div>
							<div class="content_Cell boldCell">유용성</div>
						</div>

						<div class="content_Row">
							<div class="content_Cell">
								<span>노쇼 걱정 그만!<br>믿음이 가는 스터디
								</span>
							</div>
							<div class="content_Cell">
								<span>내게 딱 맞는 스터디!<br>관심 분야를 설정해요
								</span>
							</div>
							<div class="content_Cell">
								<span>유용한 정보들이 한곳에!<br>IT정보공유 게시판
								</span>
							</div>
						</div>
					</div><!-- content_TableBody -->
				</div><!-- content_Table -->
				<br /><br /><br />
			</div>

			<br><br><br />

			
			<div class="studyList"><!-- 스터디 목록 -->
				<div class="content_Table">
					<div class="content_TableBody">
						<div class="content_Row">
							<div class="content_Cell">
								<h1 style="text-align: left; padding-left: 20%;">
									<c:choose>
										<c:when test="${sessionScope.code==null || sessionScope.admin!=null }">
											<span id="highlight">스터딧</span><br /> <span id="titlea">인기있는 스터디</span>
										</c:when>
										<c:otherwise>
											<span id="highlight">스터딧</span><br /> <span id="titlea">관심분야 스터디</span>
										</c:otherwise>
									</c:choose>
									
								</h1>
							</div>
							<div class="content_Cell"></div>
							<div class="content_Cell">
								<button class="btn btn-outline-primary btnStudy">
									전체 보기 <span class="btnTxt">></span>
								</button>
							</div>
						</div>
					</div><!-- content_TableBody -->
				</div><!-- content_Table -->

				<div class="container">
					<div class="row" style="width: 79%; margin-left: auto; margin-right: auto;">
						<c:choose><%-- 비로그인 사용자, 어드민에게 보여지는 메인 스터디 목록 --%>
							<c:when test="${sessionScope.code==null || sessionScope.admin!=null}">
								<c:forEach var="std" items="${hitStdTop3 }">
									<div class="col-4" style="width: 330px; margin-bottom: 15px; ">
										<div class="card" style="height: 420px;">
											<div class="card-header" style="color: gray; font-size: small;">열려있는 스터디</div>
											<img src="images/study${std.cnt }.jpg" alt="" style="max-height: 215px;"/>
											<div class="card-body">
												<h5 class="card-title">${std.title }</h5>
												<p class="card-text">${std.content }</p>
												<div class="bot" style="position: absolute; clear: left; float: left; left: 15px; bottom: 15px; ">
													<a href="studydetail.action?study_code=${std.studycode }">More</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:when>
							
							<c:otherwise><%-- 로그인한 사용자의 관심분야 스터디가 보여진다. --%>
								<c:forEach var="interstd" items="${interStdTop3 }">
									<div class="col-4" style="width: 330px; margin-bottom: 15px; ">
										<div class="card" style="height: 420px;">
											<div class="card-header" style="color: gray; font-size: small;">열려있는 스터디</div>
											<img src="images/study${interstd.cnt }.jpg" alt="" style="max-height: 215px;"/>
											<div class="card-body">
												<h5 class="card-title">${interstd.title }</h5>
												<p class="card-text">${interstd.content }</p>
												<div class="bot" style="position: absolute; clear: left; float: left; left: 15px; bottom: 15px; ">
													<a href="studydetail.action?study_code=${std.studycode }">More</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<br />
				</div><!-- end .container -->
			</div><!-- end 스터디 목록 -->
			<br /><br /><br />
			
			
			
			<div class="bg-light informPost">	
			<br /><br />	
				<div class="content_Table">
					<div class="content_TableBody">
						<div class="content_Row Row3">
							<div class="content_Cell" id="cell1">
								<h1 style="text-align: left; padding-left: 20%;">
									<span id="highlight">스터딧</span><br /> <span id="titlea">IT
										정보공유 게시판</span>
								</h1>
							</div>
							<div class="content_Cell"></div>
							<div class="content_Cell" style="padding-bottom: 0px;">
								<button class="btn btn-outline-primary btnInform">
									전체 보기 <span class="btnTxt">></span>
								</button>
							</div>
						</div>
					</div>
					<!-- content_TableBody -->
				</div>
				<!-- content_Table -->
				
				<div class="table" style="width: 65%; margin-left: auto; margin-right: auto; ">
					<table class="table table-hover" id="table1" style="text-align: center; max-width: 1000px; margin-left: auto; margin-right: auto;">
						<thead>
							<tr class="trTitle" >
								<th>말머리</th>
								<th>제목</th>
								<th>작성자</th>
								<th>추천수</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<!-- 
							<tr>
								<td>박혜인</td>
								<td>고급 개발자가 되는 법</td>
								<td>298</td>
								<td>132</td>
							</tr>
							-->
							<c:forEach var="post" items="${informTop5 }">
								<tr>
									<td>${post.interest_mc }</td>
									<td><a href="informdetail.action?post_code=${post.postCode}">${post.title }</td>
									<td>${post.writer }</td>
									<td>${post.rec }</td>
									<td>${post.hitcount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<br /><br />
			</div>
			
			<div class="studyReview">
				<br />
				<br />
				<!-- 스터디 후기 -->
				<div class="content_Table">
					<div class="content_TableBody">
						<div class="content_Row">
							<div class="content_Cell">
								<h1 style="text-align: left; padding-left: 20%;">
									<span id="highlight">스터딧</span><br /> <span id="titlea">모임은
										어땠나요?</span>
								</h1>
							</div>
							<div class="content_Cell"></div>
							<div class="content_Cell">
								<button class="btn btn-outline-primary btnReview">
									전체 보기 <span class="btnTxt">></span>
								</button>
							</div>
						</div>
					</div>
					<!-- content_TableBody -->
				</div>
				<!-- content_Table -->



				<div class="container">
					<div class="row" style="width: 79%; margin-left: auto; margin-right: auto;">
						<!-- 
						<div class="col-4" style="width: 330px;">
							<div class="card">
								<div class="card-header" style="color: gray; font-size: small;">스터디 후기</div>
								<img src="images/study.jpg" alt="" style="height: 215px;"/>
								<div class="card-body">
									<h5 class="card-title">제목</h5>
									<p class="card-text">Lorem ipsum dolor sit amet,
										consectetur adipiscing elit. Nullam egestas sed sem ut
										malesuada.</p>
									<a href="#">More</a>
								</div>
							</div>
						</div>
						-->
						<c:forEach var="post" items="${stdReviewTop3 }">
							<div class="col-4" style="width: 330px">
								<div class="card" style="height: 200px;">
									<div class="card-header" style="color: gray; font-size: small;">스터디 후기</div>
									<!-- <img src="images/study3.jpg" alt="" style="height: 215px;"/> -->
									<div class="card-body">
										<h5 class="card-title">${post.title }</h5>
										<p class="card-text">${post.content }</p>
										<div class="bot" style="position: absolute; clear: left; float: left; left: 15px; bottom: 15px; ">
											<a href="reviewdetail.action?post_code=${post.postCode}">More</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				
				</div>


				<br /> <br />
			</div><!-- 스터디 후기 -->
			<br /> <br />



			<div class="footer2 bg-light studyfooter">
				<div class="content_Table">
					<div class="content_TableBody">
						<div class="content_Row">
							<div class="content_Cell boldCell"></div>
							<div class="content_Cell boldCell"></div>
							<div class="content_Cell boldCell"></div>
						</div>
						<div class="content_Row">
							<div class="content_Cell">
								<h1>
									<span id="titlea">누적 스터디 멤버</span>
								</h1>
							</div>
							<div class="content_Cell">
								<h1>
									<span id="titlea">평균 만족도</span>
								</h1>
							</div>
							<div class="content_Cell">
								<h1>
									<span id="titlea">누적 스터디 개설</span>
								</h1>
							</div>

						</div>
						<div class="content_Row">
							<div class="content_Cell">
								<h1>
									<span id="highlight">${memCnt }+</span>
								</h1>
							</div>
							<div class="content_Cell">
								<h1>
									<span id="highlight">${avgStdAssess }/5.0</span>
								</h1>
							</div>
							<div class="content_Cell">
								<h1>
									<!-- <span id="highlight">3000+</span> -->
									<span id="highlight">${stdCnt }+</span>
								</h1>
							</div>
						</div>
						<div class="content_Row" style="">
						<div class="content_Cell">
							<!-- <span style="color: #ccc">(2021년 1월 4일기준)</span> -->
							<span style="color: #ccc">(${today } 기준)</span>
						</div>
						<div class="content_Cell">
							<span style="color: #ccc">(스터디 평균 만족도)</span>
						</div>
						<div class="content_Cell">
							<span style="color: #ccc">(${today } 기준)</span>
						</div>
					</div>
					</div>
					<!-- content_TableBody -->
				</div>
				<!-- content_Table -->
				<br />
			</div>
		</div>
		<jsp:include page="footer.jsp" flush="false" />
	</div>
</body>
</html>