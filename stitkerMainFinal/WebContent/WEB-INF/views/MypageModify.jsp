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
<title>내 정보 수정</title>
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
		/* 수정하기 버튼 클릭시 */
		$(".btnModify").click(function()
		{
			//alert("확인");
		});
		
		/* 비밀번호 변경하기 버튼 클릭시 */
		$(".btnModifyPw").click(function()
		{
			alert("확인");
			/* $(".Myinfo").css("display", "none"); */
		});
		
		/* 취소하기 버튼 클릭시 */
		$(".btnModiCan").click(function()
		{
			$(location).attr("href", "mypage.action");
		});
		
		/* 회원 탈퇴 버튼 클릭시 */
		$(".btnwithdraw").click(function()
		{
			$(location).attr("href", "mypage_withdraw_form1.action")
		});
		
		// 지역대분류 선택시 중분류 나오게
		$("#regionGroup1").change(function(){
			
			var selectedLc = "loc_lc_code="+this.value;
			
			$.ajax(
			{
				type : "POST"
				, url : "mypage_regionajax.action"
				, data : selectedLc
				, dataType : "json"
				, success : function(jsonArr)
				{
					// 가져온 데이터들로 옵션 구성
					var out = "";
					out += "<option value=\"\">선택</option>";
					for(var idx=0; idx<jsonArr.length; idx++)
					{
						var value = jsonArr[idx].loc_mc_code;
						var name = jsonArr[idx].loc_mc;
	
						out += "<option value=\""+ value +"\">";
						out += name;
						out += "</option>";
					}
					// 출력
					$("#regionGroup2").html(out); 
				}
				, error:function(request,status,error){
		        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   	}
			});
		});
		
		// 현재직업대분류 선택시 중분류 나오게
		$("#jobGroup1").change(function(){
			
			// 중분류 보이기
			//$("#regionGroup2").css("display","inline");
			// 넘길 대분류 코드
			var selectedLc = "job_lc_code="+this.value;
			
			$.ajax(
			{
				type : "POST"
				, url : "mypage_jobajax.action"
				, data : selectedLc
				, dataType : "json"
				, success : function(jsonArr)
				{
					// 가져온 데이터들로 옵션 구성
					var out = "";
					out += "<option value=\"\">선택</option>";
					for(var idx=0; idx<jsonArr.length; idx++)
					{
						var value = jsonArr[idx].job_mc_code;
						var name = jsonArr[idx].job_mc;
	
						out += "<option value=\""+ value +"\">";
						out += name;
						out += "</option>";
					}
					// 출력
					$("#jobGroup2").html(out); 
				}
				, error:function(request,status,error){
		        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   	}
			});
		});
		
		// 관심분야대분류 선택시 중분류 나오게
		$("#interestGroup1").change(function(){
			
			var selectedLc = "interest_lc_code="+this.value;
			
			$.ajax(
			{
				type : "POST"
				, url : "mypage_interestajax.action"
				, data : selectedLc
				, dataType : "json"
				, success : function(jsonArr)
				{
					// 가져온 데이터들로 옵션 구성
					var out = "";
					out += "<option value=\"\">선택</option>";
					for(var idx=0; idx<jsonArr.length; idx++)
					{
						var value = jsonArr[idx].interest_mc_code;
						var name = jsonArr[idx].interest_mc;
	
						out += "<option value=\""+ value +"\">";
						out += name;
						out += "</option>";
					}
					// 출력
					$("#interestGroup2").html(out); 
				}
				, error:function(request,status,error){
		        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   	}
			});
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
    
        <div class="tab-content" id="myTabContent"><br>
          
          <!-- 내 정보 수정 폼 -->
          <div class="modifyform" id="Modifyinfo" >
          	<form action="updatemyinfo.action">
	            <table id="table1" class="table tablemi" style="width: 70%; margin: auto;">
					<tr>
						<th colspan="8" class="bg-light">내정보</th>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">이름</th>
						 <td colspan="4">
						 	<input type="text" class="form-control" value="${myinfo.name }" name="name" required="required"/>
						 </td>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">이메일</th>
						 <td colspan="4">
						 	<input type="email" class="form-control" value="${myinfo.email }" name="email" required="required"/>
						 </td>
					</tr>
					<!-- <tr>
						 <th colspan="4" rowspan="2" class="bg-light">현재직업</th>
						 <th colspan="2" class="bg-light">
						 	대분류
						 </th>
						 <th colspan="2" class="bg-light">
						 	중분류
						 </th>
					</tr> -->
					<tr>
						 <th colspan="4" class="bg-light">현재직업</th>
						 <td colspan="2">
						 	<select class="form-control" name="job_lc_code" id="jobGroup1">
						 		<c:forEach var="job_lc" items="${jblList }">
									 <option value="${job_lc.job_lc_code }" ${job_lc.job_lc_code==myinfo.job_lc_code ? "selected = \"selected\"" : "" }>
									 	${job_lc.job_lc }
									 </option>
								 </c:forEach>
						 	</select>
						 </td>
						 <td colspan="2">
						 	<select class="form-control" name="job_mc_code" id="jobGroup2">
						 		<c:forEach var="job_mc" items="${jblmList }">
									 <option value="${job_mc.job_mc_code }" ${job_mc.job_mc_code==myinfo.job_mc_code ? "selected = \"selected\"" : "" }>
									 	${job_mc.job_mc }
									 </option>
								 </c:forEach>
						 	</select>
						 </td>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">관심 스터디유형</th>
						 <td colspan="4">
						 	<select class="form-control" name="study_type_code">
						 		<c:forEach var="studyType" items="${stList }">
								 <option value="${studyType.study_type_code }" ${studyType.study_type_code==myinfo.study_type_code ? "selected = \"selected\"" : "" }>
								 	${studyType.study_type }
								 </option>
							 </c:forEach>
						 	</select>
						 </td>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">관심분야</th>
						 <td colspan="2">
						 	<select class="form-control" name="interest_lc_code" id="interestGroup1">
						 		<c:forEach var="interestlc" items="${ilList }">
								 <option value="${interestlc.interest_lc_code }" ${interestlc.interest_lc_code==myinfo.interest_lc_code ? "selected = \"selected\"" : "" }>
								 	${interestlc.interest_lc }
								 </option>
							 </c:forEach>
						 	</select>
						 </td>
						 <td colspan="2">
						 	<select class="form-control" name="interest_mc_code" id="interestGroup2">
						 		<c:forEach var="interest" items="${imList }">
								 <option value="${interest.interest_mc_code }" ${interest.interest_mc_code==myinfo.interest_mc_code ? "selected = \"selected\"" : "" }>
								 	${interest.interest_mc }
								 </option>
							 </c:forEach>
						 	</select>
						 </td>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">지역</th>
						 <td colspan="2">
							<select class="form-control" name="loc_lc_code" id="regionGroup1">
						 		<c:forEach var="loclc" items="${llList }">
								 <option value="${loclc.loc_lc_code }" ${loclc.loc_lc_code==myinfo.loc_lc_code ? "selected = \"selected\"" : "" }>
								 	${loclc.loc_lc }
								 </option>
							 </c:forEach>
						 	</select>
						 </td>
						 <td colspan="2">
						 	<select class="form-control" name="loc_mc_code" id="regionGroup2">
						 		<c:forEach var="locmc" items="${lmList }">
								 <option value="${locmc.loc_mc_code }" ${locmc.loc_mc_code==myinfo.loc_mc_code ? "selected = \"selected\"" : "" }>
								 	${locmc.loc_mc }
								 </option>
							 </c:forEach>
						 	</select>
						 </td>
					</tr>
					<tr>
						<th colspan="8" style="height: 33px;" class="bg-light">
							<button type="submit" class="btn btn-outline-primary btn-sm btnModify">수정하기</button>
							<button type="reset" class="btn btn-outline-primary btn-sm btnModiCan" style="margin-left: 30px;">취소하기</button>
							<button type="button" class="btn btn-outline-primary btn-sm btnwithdraw" style="margin-left: 30px;">회원탈퇴</button>
						</th>
					</tr>
				</table>
			</form>
          </div><!-- end .modifyform -->
        
         
          
          
          
        </div><!-- end #myTabContent -->
        
      </div><br><br>
      
    </div><!--main-content end-->
     <br><br><br><br>
	<jsp:include page="footer.jsp" flush="false"/>
	</div>
</body>
</html>