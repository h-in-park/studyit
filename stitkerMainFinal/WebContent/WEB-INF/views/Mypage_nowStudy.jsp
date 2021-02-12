<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행중인 스터디</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=cp %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
a {color:#000000;
text-decoration: none;}
</style>
<script type="text/javascript">
	
		$(function()
		{
			// 스터디 나가기 버튼 클릭 시 액션 처리
			$("#cancelBtn").click(function()
			{
				if(confirm("스터디를 취소하시겠습니까?"))
				{
					$(location).attr("href", "studycancel.action?studycode="+$("#cancelBtn").val());
				}
			});
			
			// 스터디원 신고 버튼 클릭 시 액션 처리
			$(".memreportBtn").click(function()
			{
				if(confirm("해당 스터디원을 신고하시겠습니까?"))
				{
					$(location).attr("href", "reportform.action?studycode="+$("#studycode").val()+"&parti_code="+$("#particode").val()+"&memid="+$(this).val());
				}
							
			});
			
			
			// 스터디 신고 버튼 클릭 시 액션 처리
			$("#reportBtn").click(function()
			{
				if(confirm("해당 스터디를 신고하시겠습니까?"))
				{
					//$(location).attr("href", "action?studycode="+$("#cancelBtn").val());
				}
					
			});
			
			// 스터디원 내보내기 버튼 클릭 시 액션 처리
			$(".memkicktBtn").click(function()
			{
				var kick_btn = document.getElementById("memkicktBtn");
				//alert("test");
				if(confirm("해당 스터디원을 내보내시겠습니까?"))
				{
				
					$(location).attr("href","kick.action?studycode="+$("#studycode").val()+"&parti_code="+$("#particode").val()+"&memid="+$(this).val());
					
				}
			});   
			
			$(".studyCnt").click(function(){
				ajaxRequest($(this).val());
			});
			
			
		});
		
		 
		function ajaxRequest(id)
		{   
			$.get("ajax.action", {id : id}, function(data)
			{
				alert('누적 스터디 수: ' + data +"개");	
		    });
		}

</script>	
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content">
   <div class="title">
        <h3 class="titleTxt p-3 text-center">진행중인 스터디</h3>
      </div>
      <div class="container"><br><br><br>
      <div class="row align-content-center">
          	<c:forEach var="now" items="${nowlist }">
          <div class="col-12">
          	<table class="table">
              <tr>
                <td class="fw-bold fs-5 bd">스터디명</td>
              	<td class="bd cen">${now.study_name }</td>
                <!-- <td class="bd cen">자바스크립트 스터디</td> -->
                <td class="fw-bold fs-5 bd">스터디 리더</td>
              	<td class="cen">${now.leader }</td>
              </tr>
              <tr>
                <td class="fw-bold fs-5 bd">기간</td>
                <!-- <td class="bd cen">2020/12/21 ~ 2021/01/25</td> -->
                <td class="bd cen">${now.period }</td>
                <td class="fw-bold fs-5 bd">장소</td>
                <td class="cen">${now.loc }</td>
                <!-- <td class="cen">서울특별시 홍대</td> -->
              </tr>
              <tr> 
                <td class="fw-bold fs-5 bd">요일</td>
                <td class="bd cen">${weekday }</td>
                <!-- <td class="bd cen">토요일, 일요일</td> -->
                <th class="bd fs-5">시간</th>
                <td class="cen">${now.start_time } ~ ${now.end_time }</td>
			<!--<td class="cen">13:00 ~ 15:00</td> -->
              </tr> 
              <tr>
               <td class="fw-bold bd fs-5">진행률</td>
               <td colspan="3"><div class="progress mt-2" style="width: 80%; margin: 0 auto;">
                <div class="progress-bar" role="progressbar" style="width: ${pgs}%;" aria-valuenow="${pgs }" aria-valuemin="0" aria-valuemax="100">${pgs }%</div>
              </div></td>
              </tr>
          </table>  
          </div>
           <div class="col-lg-5 col-md-7 col-sm-12 mt-3 mb-5" style="margin: 0 auto;">
              <button type="button" class="btn btn-outline-primary mb-2" onclick="location.href='attendance.action?studycode=${param.studycode }&particode=${now.parti_code}'">출석부</button>
               <button type="button" class="btn btn-outline-primary mb-2" data-bs-toggle="modal" data-bs-target="#memberList">
                스터디원 목록
              </button>
              <!-- Modal -->
              <div class="modal fade" id="memberList" tabindex="-1" aria-labelledby="memberListLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title fw-bold" id="memberListTitle">스터디원 목록</h5>
                    </div>
                    <div class="modal-body">
                    <input type="hidden" id="studycode" value="${param.studycode }">
                    <input type="hidden" id="particode" value="${parti_code }">
                      <table class="table">
                        <thead>
                          <tr> 
                            <th scope="col">번호</th>
                            <th scope="col">아이디</th>
                            <th scope="col">신고하기</th>
                            <th scope="col">내보내기</th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mem" items="${member }">
                          <tr>
                            <th scope="row">${mem.rnum }</th>
                            <td><button type="button" class="btn btn-outline-primary studyCnt"  value="${mem.id }">${mem.id }</button></td>
                            <td><button class="btn btn-outline-danger memreportBtn" value="${mem.id }">신고하기</button></td>
                            <td><button type="button"  class="btn btn-outline-warning memkicktBtn"  value="${mem.id }" >내보내기</button></td>
                          </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    </div>
                  </div>
                </div>
              </div>
              <button type="button" class="btn btn-outline-primary mb-2" id="cancelBtn" value="${param.studycode }" >스터디 나가기</button>
              <button type="button" id="reportBtn" value="${param.studycode }" class="btn btn-outline-danger mb-2">스터디 신고</button>
          </div> 
          </c:forEach> 
        <div>
          <br><br><br>
          <span class="fs-4 fw-bold">컨텐츠</span>
          <div class="list-group list-group-flush mt-3" style="width:95%">
          <div class="stdcon">
          <table class="table mb-5">
			  <thead>
			    <tr>
			      <th scope="col" class="bg-light" style="width:10%">번호</th>
			      <th scope="col" class="text-center bg-light" style="width:40%">제목</th>
			      <th scope="col" class="bg-light" style="width:20%">작성자</th>
			      <th scope="col" class="bg-light" style="width:20%">작성일</th>
			      <th scope="col" class="bg-light" style="width:10%">조회수</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var="con" items="${nowcontents }" >
			    <tr>
			      <th scope="row">${con.rnum }</th>
			      <td class="text-center"><a href="contentsdetail.action?studycode=${con.study_code }&contentcode=${con.content_code }">${con.title }</a></td>
			      <td>${con.id }</td>
			      <td>${con.write_date }</td>
			      <td>${con.hitcount }</td> 
			    </tr>
			    </c:forEach>
			  </tbody>
			</table> 
			</div> 
			<form action="contentwriteform.action?studycode=${param.studycode }" method="post">
			<input type="text" name="parti_code" id="parti_code" value="${parti_code }" style="display:none">
			<button type="submit" class="btn btn-outline-primary" style="width:90px; margin-top:-20px;">글쓰기</button><br>
			</form>
			<br>
			<div class="page">
			${pageIndexList }
			</div>
		<!-- 	<nav aria-label="Page navigation example" style="margin:0 auto;">
			  <ul class="pagination">
			    <li class="page-item"><a class="page-link" href="#">이전글</a></li>
			    <li class="page-item"><a class="page-link" href="#">1</a></li>
			    <li class="page-item"><a class="page-link" href="#">2</a></li>
			    <li class="page-item"><a class="page-link" href="#">3</a></li>
			    <li class="page-item"><a class="page-link" href="#">다음글</a></li>
			  </ul>
			</nav> -->
			
          </div>
            <br><br><br><br>
          </div>
          </div>
      </div>
    </div><!--main-content end-->
	     
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>




