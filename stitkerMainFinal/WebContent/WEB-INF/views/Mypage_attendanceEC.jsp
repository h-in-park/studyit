<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출석부</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="wrapper" >
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content3 text-center" style="height: 400px">
 	<div class="title" style="height: 300px">
      <h3 class="titleTxt p-3 text-center">출석부</h3>
    </div>
  <div class="container" style="width:900px;">  
 <div style="height: 80%"><br><br>
	<table class="table" >
  <thead>
    <tr>
      <th scope="col" class="bg-light">일자 및 시간</th>
      <th scope="col" class="bg-light">출결정보</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach var="att" items="${attendance }">
    <tr>
      <th scope="row">${att.attend_date }(${att.start_time } ~ ${att.end_time })</th>
      <td>${att.clock_in } ~ ${att.clock_out }(${att.status })</td>
    </tr>
    </c:forEach> 
  </tbody>
</table>
</div>
</div>
 </div>
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>