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
<title>내스터디</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
<style type="text/css">
a {color:#000000;
text-decoration: none;}
</style>
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content text-center"> 
    <div class="bg-dark col-12 title">
        <h3 class="titleTxt p-3">내 스터디</h3>
      </div>
      <div class="container" style="height: 700px;"><br><br><br>
        <ul class="nav nav-tabs col-12" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <a class="nav-link active" id="now-tab" data-bs-toggle="tab" href="#now" role="tab" aria-controls="now" aria-selected="true">진행 중인 스터디</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link" id="upcoming-tab" data-bs-toggle="tab" href="#upcoming" role="tab" aria-controls="upcoming" aria-selected="false">진행 예정인 스터디</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link" id="end-tab" data-bs-toggle="tab" href="#end" role="tab" aria-controls="end" aria-selected="false">종료된 스터디</a>
          </li>
          <li class="nav-item" role="presentation">
            <a class="nav-link" id="cancel-tab" data-bs-toggle="tab" href="#cancel" role="tab" aria-controls="cancel" aria-selected="false">취소된 스터디</a>
          </li>
        </ul>
        <div class="tab-content" id="myTabContent">
          <br>
          <div class="tab-pane fade show active mt-4" id="now" role="tabpanel" aria-labelledby="now-tab">
            <c:if test="${nowcount == 0 }">
				<h2>진행중인 스터디가 없습니다.</h2>
			</c:if>
			<c:if test="${nowcount >0 }">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="fs-5">스터디 번호</th>
                  <th scope="col" class="fs-5">스터디 이름</th>
                  <th scope="col" class="fs-5">스터디 기간</th>
                  <th scope="col" class="fs-5">스터디 리더</th>
                  <th scope="col" class="fs-5">장소</th>
                  <th scope="col" class="fs-5">요일</th>
                </tr>
              </thead>
              <tbody>
                <!-- <tr>
                  <td scope="row">1</td>
                  <td><a href="#">코딩천재 hsm과 함께하는 자바스터티</a></td>
                  <td>2020/12/01 ~ 2021/01/23</td>
                  <td>hsm11</td>
                  <td>토</td>
                  <td>13:00 ~ 14:00</td>
                </tr>
              -->
                <c:forEach var="now" items="${nowlist }">
                <tr>
                	<td>${now.study_num }</td>
                	<td id="studyCode"><a href="nowstudy.action?studycode=${now.study_code }">${now.study_name }</a></td>
                	<td>${now.period }</td>
                	<td>${now.leader }</td>
                	<td>${now.loc }</td>
                	<td>${now.weekday }</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
            </c:if>
          </div>
          <br><br>
          <div class="tab-pane fade" id="upcoming" role="tabpanel" aria-labelledby="upcoming-tab">
            <c:if test="${upcomingcount == 0 }">
				<H2>진행 예정인 스터디가 없습니다.</H2>
			</c:if>
			<c:if test="${upcomingcount>0 }">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="fs-5">스터디 번호</th>
                  <th scope="col" class="fs-5">스터디 이름</th>
                  <th scope="col" class="fs-5">스터디 기간</th>
                  <th scope="col" class="fs-5">스터디 리더</th>
                  <th scope="col" class="fs-5">장소</th>
                  <th scope="col" class="fs-5">요일</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="up" items="${upcominglist }">
              <tr>
              	<td>${up.study_num }</td>
              	<td id="studyCode"><a href="upcomingstudy.action?studycode=${up.study_code }">${up.study_name }</a></td>
              	<td>${up.period }</td>
              	<td>${up.leader }</td>
              	<td>${up.loc }</td>
              	<td>${up.weekday }</td>
              </tr>
              </c:forEach>
                <!-- <tr>
                  <td scope="row">1</td>
                  <td><a href="#">코딩천재 hsm과 함께하는 자바스터티</a></td>
                  <td>2020/12/01 ~ 2021/01/23</td>
                  <td>hsm11</td>
                  <td>토</td>
                  <td>13:00 ~ 14:00</td>
                </tr>
             -->
              </tbody>
            </table>
            </c:if>
          </div>
          <div class="tab-pane fade" id="end" role="tabpanel" aria-labelledby="end-tab">
            <c:if test="${endcount == 0 }">
				<H2>종료된 스터디가 없습니다.</H2>
			</c:if>
			<c:if test="${endcount>0 }">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col" class="fs-5">스터디 번호</th>
                  <th scope="col" class="fs-5">스터디 이름</th>
                  <th scope="col" class="fs-5">스터디 기간</th>
                  <th scope="col" class="fs-5">스터디 리더</th>
                  <th scope="col" class="fs-5">장소</th>
                  <th scope="col" class="fs-5">요일</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="end" items="${endlist }">
                	<tr>
                	<td>${end.study_num }</td>
                	<td id="studyCode"><a href="endstudy.action?studycode=${end.study_code }">${end.study_name }</a></td>
                	<td>${end.period }</td>
                	<td>${end.leader }</td>
                	<td>${end.loc }</td>
                	<td>${end.weekday }</td>
                	</tr>
                </c:forEach>
              </tbody>
            </table>
            </c:if>
          </div>
          <div class="tab-pane fade" id="cancel" role="tabpanel" aria-labelledby="cancel-tab">
            <c:if test="${cancelcount == 0 }">
				<h2>취소된 스터디가 없습니다.</h2>
			</c:if>
			<c:if test="${cancelcount >0 }">
			<table class="table">
              <thead>
                <tr>
                  <th scope="col" class="fs-5">스터디 번호</th>
                  <th scope="col" class="fs-5">스터디 이름</th>
                  <th scope="col" class="fs-5">스터디 기간</th>
                  <th scope="col" class="fs-5">스터디 리더</th>
                  <th scope="col" class="fs-5">스터디 취소 날짜</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="can" items="${cancellist }">
                	<tr>
                	<td>${can.study_num }</td>
                	<td><a href="cancelstudy.action?studycode=${can.study_code }">${can.study_name }</a></td>
                	<td>${can.period }</td>
                	<td>${can.leader }</td>
                	<td>${can.cancel_date }</td>
                	</tr>
                </c:forEach>
              </tbody>
            </table>			
          </c:if>
          </div>
        </div>
      </div><br><br>
    </div><!--main-content end-->
     
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>