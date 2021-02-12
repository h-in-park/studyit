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
<title>스터디원 평가</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="<%=cp %>/css/style.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>

<script type="text/javascript">

$(document).ready(function()
{
	//ajaxRequest();
		
	/* if(parseInt($("#assessCheck").text())  > 0 )
	{
		//$("#assessCheck").css("display", "none");
		alert("이미 평가 완료된 회원입니다.");
		return;		//--submit 액션 처리 중단
		
	} */
	
	$(".assessBtn").click(function()
	{
		ajaxRequest();
	});
	
});


function ajaxRequest()
{   
	$.post("ajax2.action", {parti_assessed_code : $(".parti_assessed_code").val(), parti_code :$(".parti_code" ).val(), studycode : $(".studycode").val() }, function(data)
	{
		if(data>0)
		{
			alert("이미 평가 완료된 회원입니다.");
			$(".smBtn").attr("readonly", true).attr("disabled",true);
			$("#modal").modal("hide");
			return;
		}
		
    }); 
}
  
</script>
</head>
<body>
<div class="wrapper"> 
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content text-center" style="height: 1000px">
	<div class="bg-dark col-12 titleas">
       <h3 class="titleTxt p-3">스터디원 평가</h3>
    </div>
    <div class="container"><br><br><br>
      
        <table class="table"> 
            <thead>
              <tr>
                <th scope="col" class="bg-light">번호</th>
                <th scope="col" class="bg-light">회원 아이디</th>
                <th scope="col" class="bg-light">회원 평가 여부</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="mem" items="${member }"> 
            <!--   <tr>
                <th scope="row">1</th>
                <td>hsm</td>
                <td><button type="button" id="eval" class="btn btn-outline-success col-4" disabled data-bs-toggle="button" autocomplete="off">완료</button></td>
              </tr> -->
              <tr>
                <th scope="row">${mem.rnum }</th>
                <td>${mem.id }</td>
                <td><!-- Button trigger modal -->
                  <form action="assessinsert.action" method="post" name="myForm"> 
                  <button type="button" name="assessBtn" id="assessBtn" class="btn btn-outline-primary col-4 assessBtn " 
                  data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                   평가하기
                  </button> 
                  
                  <!-- Modal -->
                 <input type="hidden" name="parti_code" id="parti_code" class="parti_code" value=${param.parti_code }>
                 <input type="hidden" name="studycode" id="studycode"  class="studycode" value=${param.studycode }>
                 <!-- 평가 받는 사람 코드 parti_assessed_code  -->
                 <input type="hidden" name="parti_assessed_code" id="parti_assessed_code" class="parti_assessed_code" value="${mem.parti_assessed_code }"> 
                  <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="staticBackdropLabel">스터디원 평가</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body"> 
                             해당 참가자는 과제 등 자신의 할 일을 성실히 수행했나요?<br>
                            <div>
								<input type="hidden" name="AI1" value="AI1">
                              <label>
                                  <input type="radio" name="ARa" value="AR1" checked >
                                  <span>매우 그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARa" value="AR2">
                                  <span>그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARa" value="AR3">
                                  <span>보통이다</span>
                              </label>
                              <label class="radio">
                                <input type="radio" name="ARa" value="AR4">
                                <span>그렇지 않다</span>
                            </label>
                            <label class="radio">
                              <input type="radio" name="ARa" value="AR5">
                              <span>매우 그렇지 않다</span>
                          </label>
                          </div>
                          <hr>
                             해당 참가자는 스터디원들을 배려하며<br> 원활한 소통을 위해 노력했나요? <br>
                            <div>
                            <input type="hidden" name="AI2" value="AI2">
                              <label>
                                  <input type="radio" name="ARb" value="AR1" checked>
                                  <span>매우 그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARb" value="AR2">
                                  <span>그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARb" value="AR3">
                                  <span>보통이다</span>
                              </label>
                              <label class="radio">
                                <input type="radio" name="ARb" value="AR4">
                                <span>그렇지 않다</span>
                            </label>
                            <label class="radio">
                              <input type="radio" name="ARb" value="AR5">
                              <span>매우 그렇지 않다</span>
                          </label>
                          </div>
                          <hr>                  
                            해당 참가자는 스터디 활동에 능동적으로 참여했나요? <br><div>
                            <input type="hidden" name="AI3" value="AI3">
                              <label>
                                  <input type="radio" name="ARc" value="AR1" checked>
                                  <span>매우 그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARc" value="AR2">
                                  <span>그렇다</span>
                              </label>
                              <label class="radio">
                                  <input type="radio" name="ARc" value="AR3">
                                  <span>보통이다</span>
                              </label>
                              <label class="radio">
                                <input type="radio" name="ARc" value="AR4">
                                <span>그렇지 않다</span>
                            </label>
                            <label class="radio">
                              <input type="radio" name="ARc" value="AR5">
                              <span>매우 그렇지 않다</span>
                          </label>
                          </div>
                        </div>
                        
                        <div class="modal-footer">
                         <!--  <button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">취소</button> -->
                          <button type="submit" id="smBtn" class="btn btn-outline-primary smBtn" >제출</button>
                        </div>
                     </div>
                    </div>
                  </div>
		        </form>
                 </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div> 
        <br><br>
     
    </div><!--main-content end-->   
     
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>