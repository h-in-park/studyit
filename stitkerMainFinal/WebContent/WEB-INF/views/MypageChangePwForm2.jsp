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
		
		/* 비밀번호 변경하기 버튼 클릭시 */
		$(".btnModifyPw").click(function()
		{
			alert("확인");
		});
		
		/* 취소하기 버튼 클릭시 */
		$(".btnModiCan").click(function()
		{
			$(location).attr("href", "mypage.action");
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
          
          <!-- 비밀번호 수정 폼 -->
          <div class="modifyform" id="Modifyinfo" >
          	<form action="changepassword.action" method="post">
	            <table id="table1" class="table tablemi" style="width: 70%; margin: auto;">
					<tr>
						<th colspan="8" class="bg-light">비밀번호 변경</th>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">새 비밀번호</th>
						 <td colspan="4">
						 	<input type="password" class="form-control" name="newPassword" placeholder="새로운 비밀번호를 입력해주세요."/>
						 </td>
					</tr>
					<!-- <tr>
						 <th colspan="4" class="bg-light">새 비밀번호 확인</th>
						 <td colspan="4">
						 	<input type="password" class="form-control" name="email"/>
						 </td>
					</tr> -->
					
					<tr>
						<th colspan="8" style="height: 33px;" class="bg-light">
							<button type="submit" class="btn btn-outline-primary btn-sm btnModify">변  경</button>
							<button type="reset" class="btn btn-outline-primary btn-sm btnModiCan" style="margin-left: 30px;">취  소</button>
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