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
		$("#wdl_reason").css("display", "none");
		//$(".btnWithdraw").attr("disabled", "disabled");
		$(".btnWithdraw").css("display", "none");
		
		/* 취소 버튼 클릭시 */
		$(".btnModiCan").click(function()
		{
			$(location).attr("href", "mypage.action");
		});

		
		$(".btnConfirm").click(function()
		{
			$.ajax({
				url: "withdrawajax.action",
				type: "GET",
				data:
				{
					"ssn":$("#ssn").val()
				},
				success: function(errMsg)
				{
					var msg = $.trim (errMsg);
					if (msg == "본인 확인 완료")
					{
						//$(".btnWithdraw").attr("disabled", true);
						$(".btnWithdraw").css("display", "inline");
					}			
					$("#errMsg").html(errMsg);
				},
				error: function()
				{
					alert("본인 확인 오류");
				}
			});
			
		});
		
		$("#wdl_ctg_code").change(function()
		{
			var ctg_code = this.value;
			$("#wdl_reason").css("display", "none");
			
			if (ctg_code == "WC3")
			{
				$("#wdl_reason").css("display", "inline");
			}
		});
		
		
		$(".btnWithdraw").click(function()
		{
			this.submit();
		});
	});
</script>	
<script type="text/javascript">
	function submit(obj)
	{
		obj.submit();
	}
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
          
          <!-- 탈퇴 확인 폼 -->
          <div class="modifyform" id="Modifyinfo" >
          	<form action="mypage_withdraw.action" method="post">
	            <table id="table1" class="table tablemi" style="width: 70%; margin: auto;">
					<tr>
						<th colspan="8" class="bg-light">본인 확인</th>
					</tr>
					<tr>
						<th colspan="4" class="bg-light">주민등록번호</th>
						<td colspan="3">
						 	<input type="text" class="form-control" name="ssn" id="ssn" 
						 		placeholder="주민등록번호를 입력해주세요( '-' 제외 )" required="required" style="float: left; width: 85%;"/>
							<button type="button" class="btn btn-outline-primary btn-sm btnConfirm" 
								style="float: left; margin-left: 15px; margin-top: 3px;">확  인</button>
						</td>
					 	<td colspan="1" style="text-align: center;">
					 		<span style="font-size: 9pt; color: red;" id="errMsg"></span>
					 	</td>
					</tr>
					<tr>
						 <th colspan="4" class="bg-light">탈퇴 사유 선택</th>
						 <td colspan="4">
						 	<select class="form-control" name="wdl_ctg_code" id="wdl_ctg_code" required="required">
						 		<option value="">선택</option>
						 		<c:forEach var="category" items="${categoryList }">
								 <option value="${category.wdl_ctg_code }" >
								 	${category.wdl_ctg }
								 </option>
							 </c:forEach>
						 	</select>
						 	<textarea class="form-control" placeholder="기타 사유를 입력해주세요." name="wdl_reason" id="wdl_reason" 
						 		style="display: none;" required="required"></textarea>
						 </td>
					</tr>
						 	
					<tr>
						<th colspan="8" style="height: 33px;" class="bg-light">
							<button type="button" class="btn btn-outline-primary btn-sm btnWithdraw" onclick="submit(this.form)">탈 퇴</button>
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