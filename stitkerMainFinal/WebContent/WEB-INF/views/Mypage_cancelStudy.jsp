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
<title>취소된 스터디</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=cp %>/css/style.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<script type="text/javascript">

	$(function()
	{
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
        <h3 class="titleTxt p-3 text-center">취소된 스터디</h3>
      </div>
      <div class="container"><br><br><br>
      <div class="row align-content-center">
         	<c:forEach var="can" items="${canlist }">
          <div class="col-12">
          	<table class="table">
              <tr>
                <td class="fw-bold fs-5 bd">스터디명</td>
                <td class="bd cen">${can.study_name }</td>
                <td class="fw-bold fs-5 bd">스터디 리더</td>
                <td class="cen">${can.leader }</td>
              </tr>
              <tr>
                <td class="fw-bold fs-5 bd">기간</td>
                <td class="bd cen">${can.period }</td>
                <td class="fw-bold fs-5 bd">취소한 날짜</td>
                <td class="cen">${can.cancel_date }</td>
              </tr>
              <tr>
                <td class="fw-bold fs-5 bd">요일</td>
                <td class="bd cen">${weekday }</td>
                <th class="bd fs-5">취소 유형</th>
                <td class="cen">${can.auto_cancel }</td>
              </tr>
          </table>  
          </div>
           <div class="col-lg-4 col-md-7 col-sm-12 mt-3 mb-5" style="margin: 0 auto;">
           
              <button type="button" class="btn btn-outline-primary mb-2" 
              onclick="location.href='attendanceec.action?attCheck=1&particode=${can.parti_code}'">출석부</button>
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
                    <c:choose>
                        	<c:when test="${empty member }">
	                          	<h4>현재 참가 중인 인원이 없습니다.</h4>
                        	</c:when>
                        	<c:otherwise>
                      <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">번호</th>
                            <th scope="col">아이디</th>
                            <th scope="col">평균 출석률</th>
                          </tr>
                        </thead>
                        <tbody>
							<c:forEach var="mem" items="${member }" >
	                          <tr>
	                            <th scope="row">${mem.rnum }</th>
	                            <td><button type="button" class="btn btn-outline-primary studyCnt"  value="${mem.id }">${mem.id }</button></td>
	                            <td>100%</td>
	                          </tr>
	                          </c:forEach>
                        </tbody>
                      </table>
							</c:otherwise>                        
                        </c:choose>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                    </div>
                  </div>
                </div>
              </div>
          </div>
             </c:forEach>
        <div>
 
            <br><br><br><br>
          </div>
          </div>
      </div>
    </div><!--main-content end-->
	     
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>