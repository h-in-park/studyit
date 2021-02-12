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
<title>진행예정인 스터디</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=cp %>/css/style.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<script type="text/javascript">
	
		$(function()
		{
			// 참가 취소 버튼 클릭 시 액션 처리
			$("#cancelBtn").click(function()
			{
				if(confirm("스터디를 취소하시겠습니까?"))
				{
					$(location).attr("href", "studycancel.action?studycode="+$("#cancelBtn").val());
				}
			});
			
			// 스터디 수정 버튼 클릭 시 액션 처리
			$("#studyModify").click(function()
			{	
				 $(location).attr("href", "studycancel.action?studycode="+$("#studyModify").val());
			});
	
		});
		
</script>
</head>
<body> 
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content">
   <div class="title">
        <h3 class="titleTxt p-3 text-center">진행예정인 스터디</h3>
      </div>
      <div class="container"><br><br><br>
      <div class="row align-content-center">
          	<c:forEach var="up" items="${uplist }">
          <div class="col-12">
          	<table class="table">
              <tr>
                <td class="fw-bold fs-5 bd">스터디명</td>
                <td class="bd cen">${up.study_name }</td>
                <td class="fw-bold fs-5 bd">스터디 리더</td>
                <td class="cen">${up.leader }</td>
              </tr>
              <tr>
                <td class="fw-bold fs-5 bd">기간</td>
                <td class="bd cen">${up.period }</td>
                <td class="fw-bold fs-5 bd">장소</td>
                <td class="cen">${up.loc }</td>
              </tr>
              <tr>
                <td class="fw-bold fs-5 bd">요일</td>
                <td class="bd cen">${weekday }</td>
                <th class="bd fs-5">시간</th>
                <td class="cen">${up.start_time } ~ ${up.end_time }</td>
              </tr>
              <tr>
               <td class="fw-bold bd fs-5">진행률</td>
               <td colspan="3"><div class="progress mt-2" style="width: 80%; margin: 0 auto;">
                <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
              </div></td>
              </tr>
          </table>  
          </div>
           </c:forEach>
          <div class="col-lg-5 col-md-8 col-sm-12 mt-3" style="margin: 0 auto;">
          <button type="button" class="btn btn-outline-primary mb-2" onclick="location.href='attendance.action?particode=${up.parti_code}'">출석부</button>
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
                      <table class="table">
                        <thead>
                          <tr> 
                            <th scope="col">번호</th>
                            <th scope="col">아이디</th>
                            <th scope="col">참가한 스터디</th>
                            <th scope="col">신고하기</th>
                            <th scope="col">내보내기</th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mem" items="${member }">
                          <tr>
                            <th scope="row">${mem.rnum }</th>
                            <td>${mem.id }</td>
                            <td>3개</td>
                            <td><a class="btn btn-outline-danger" href="reportform.action?studycode=${param.studycode }&parti_code=${parti_code }&memid=${mem.id}" id="memreportBtn">신고하기</a></td>
                            <td><a class="btn btn-outline-warning" href="kick.action?studycode=${param.studycode }&parti_code=${parti_code }&memid=${mem.id}" id="memreportBtn">내보내기</a></td>
                          </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary mb-2" data-bs-dismiss="modal">닫기</button>
                    </div>
                  </div>
                </div>
              </div>
          <button type="button" class="btn btn-outline-primary mb-2" id="studyModify">스터디 수정</button>
          <button type="button" id="cancelBtn" class="btn btn-outline-primary mb-2"  value="${param.studycode }">스터디 참가 취소</button>
        </div>
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
			  <c:forEach var="con" items="${upcomingcontent }" >
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