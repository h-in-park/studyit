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
<title>마이페이지</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>

<script type="text/javascript" src="<%=cp %>/js/util.js"></script>
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css">
<title>Mypage_evaluationComplete</title>
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script type="text/javascript">

	$(function()
	{
		/* 내 스터디 보러가기 탭 클릭시 */
		$("#mystudy-tab").click(function()
		{
			$(location).attr("href", "studylist.action");
			
		});
		
		/* 내 정보 수정하기 버튼 클릭시 */
		$(".btnModifyform").click(function()
		{
			$(location).attr("href", "myinfomodifyform.action");
		});
		
		/* 비밀번호 변경하기 버튼 클릭시 */
		$(".btnModifyPw").click(function()
		{
			$(location).attr("href", "changepasswordform1.action");
		});
				
		
	});
</script>	
<style type="text/css">
	.tablemi th
	{
		height: 55px;
		
	}
	.tablemi td 
	{
		width: 200px; 
	}
	.tablemi th, .tablemi td 
	{
		vertical-align: middle;
	}
</style>
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content text-center"> 
    <div class="bg-dark col-12 title2">
        <h3 class="titleTxt p-3">마이페이지</h3>
      </div>
      <div class="container" style="height: 700px;"><br><br><br>
        <ul class="nav nav-tabs col-12" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <a class="nav-link active" id="myinfo-tab" data-bs-toggle="tab" href="#myinfo" role="tab" aria-controls="myinfo" aria-selected="true">내 정보</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link" id="mypost-tab" data-bs-toggle="tab" href="#mypost" role="tab" aria-controls="mypost" aria-selected="false">내가 쓴 글</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link" id="mystudy-tab" data-bs-toggle="tab" href="" role="tab" aria-controls="mystudy" aria-selected="false">내 스터디 보러가기</a>
          </li>
        </ul>
        
        <div class="tab-content" id="myTabContent">
          
          
          <!-- 내 정보 조회 -->
          <div class="tab-pane fade show active mt-4 Myinfo" id="myinfo" role="tabpanel" aria-labelledby="myinfo-tab">
          
            <table id="table1" class="table tablemi">
				<tr>
					<th colspan="8" class="bg-light">내정보</th>
				</tr>
				<tr>
					 <th colspan="2" class="bg-light">이름</th>
					 <td colspan="2">${myinfo.name }</td>
					 <th colspan="2" class="bg-light">이메일</th>
					 <td colspan="2">${myinfo.email }</td>
				</tr>
				<tr>
					 <th colspan="2" class="bg-light">지역</th>
					 <td colspan="2">${myinfo.loc_mc }</td>
					 <th colspan="2" class="bg-light">직업</th>
					 <td colspan="2" >${myinfo.job_mc }</td>
				</tr>
				<tr>
					 <th colspan="2" class="bg-light">관심 스터디유형</th>
					 <td colspan="2" >${myinfo.study_type }</td>
					 <th colspan="2" class="bg-light">관심 스터디분야</th>
					 <td colspan="2" >${myinfo.interest_mc }</td>
				</tr>
				<tr>
					 <th colspan="2" class="bg-light">가입일</th>
					 <td colspan="2">${myinfo.regdate }</td>
					 <th colspan="2" class="bg-light">등급</th>
					 <td colspan="2">
					 	<c:choose>
					 		<c:when test="${myscore.myrank==\"숲(1등급)\" }">
					 			<img src="images/valley.png" alt="" style="max-height: 30px;"/>
					 		</c:when>
					 		<c:when test="${myscore.myrank==\"나무(2등급)\" }">
					 			<img src="images/tree.png" alt="" style="max-height: 30px;"/>
					 		</c:when>
					 		<c:when test="${myscore.myrank==\"열매(3등급)\" }">
					 			<img src="images/apple.png" alt="" style="max-height: 30px;"/>
					 		</c:when>
					 		<c:when test="${myscore.myrank==\"꽃(4등급)\" }">
					 			<img src="images/sunflower.png" alt="" style="max-height: 30px;"/>
					 		</c:when>
					 		<c:when test="${myscore.myrank==\"새싹(5등급)\" }">
					 			<img src="images/plant.png" alt="" style="max-height: 30px;"/>
					 		</c:when>
					 		<c:otherwise>
					 			<img src="images/acorn.png" alt="" style="max-height: 30px;"/>
					 		</c:otherwise>
					 	</c:choose>
					 	
					 	${myscore.myrank }
						 <div class="progress mt-2" style="width: 80%; margin: 0 auto;">
							<div class="progress-bar" role="progressbar"
								style="width: ${myscore.percentage }%;" aria-valuenow="${myscore.percentage }"
								aria-valuemin="0" aria-valuemax="100">${myscore.percentage }%
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="8" style="height: 33px;" class="bg-light">
						<button type="button" class="btn btn-outline-primary btn-sm btnModifyform">내 정보 수정</button>
						<button type="button" class="btn btn-outline-primary btn-sm btnModifyPw" style="margin-left: 30px;">비밀번호 변경</button>
						
					</th>
				</tr>
			</table>
			
          </div><!-- end .Myinfo -->
          
          
          
          <!-- 내가 쓴 글 -->  
          <div class="tab-pane fade active mt-4" id="mypost" role="tabpanel" aria-labelledby="mypost-tab">
			<table id="table1" class="table tablemi tablemp">
				
				<tr>
					 <th class="bg-light">게시판</th>
					 <th class="bg-light" style="width: 45%;">제목</th>
					 <th class="bg-light">추천</th>
					 <th class="bg-light">댓글</th>
					 <th class="bg-light">조회</th>
					 
				</tr>
				<tr onclick="">
					 <td>자유게시판</td>
					 <td>안녕하세요</td>
					 <td>1</td>
					 <td>0</td>
					 <td>0</td>
				</tr>
				
			</table>
          </div>
          
        </div><!-- end #myTabContent -->
        
      </div><br><br>
      
    </div><!--main-content end-->
     
	<jsp:include page="footer.jsp" flush="false"/>
	</div>
</body>
</html>