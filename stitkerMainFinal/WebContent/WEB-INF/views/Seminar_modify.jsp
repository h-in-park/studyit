<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("UTF-8");
   String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세미나 및 공모전 글 수정</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/jquery-ui.css">
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="<%=cp %>/js/jquery-ui.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/Write.css">
<link rel="stylesheet" href="css/Layout.css">

<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>	
<link rel="stylesheet" href="css/summernote-lite.css">

<script type="text/javascript">

	window.onload = function() {
		document.getElementById("title").focus();
	}
</script>

<script type="text/javascript">
	
		$(document).ready(function() {
			$('#summernote').summernote({
				height: 400,
				minHeight: null,
				maxHeight: null,
				focus: true,
				lang: "ko-KR",
				placeholder: '내용을 입력하세요.'
	  		});
			
			$("#cancel").click(function()
			{
				var result = confirm("취소 하시겠습니까?");
				
				if(result)
				{
					location.replace("WEB-INF/views/Seminar_list.jsp");
				}
			});
			
			//datepicker 한국어로 사용하기 위한 언어설정
	        $.datepicker.setDefaults($.datepicker.regional['ko']); 
			
	      	//시작일.
	        $('#start_date').datepicker({
	            dateFormat: "yy-mm-dd"           // 날짜의 형식
	            , changeYear : true				 // 년을 이동하기 위한 선택상자 표시여부
	            , changeMonth: true              // 월을 이동하기 위한 선택상자 표시여부
	            , minDate: 0                     // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	            , onClose: function( selectedDate ) {    
	                // 시작일(fromDate) datepicker가 닫힐때
	                // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
	                $("#endDate").datepicker( "option", "minDate", selectedDate );
	            }                
	        });
	      	
	      	//종료일
	        $('#end_date').datepicker({
	            dateFormat: "yy-mm-dd"
	            , changeYear : true
	            , changeMonth: true
	            , minDate: 0                      // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
	            , onClose: function( selectedDate ) {
	                // 종료일(toDate) datepicker가 닫힐때
	                // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
	                $("#startDate").datepicker( "option", "maxDate", selectedDate );
	            }                
	        });
			
			 // 에러(span 엘리먼트) 안내 초기화
            $("#err").css("display", "none");


            
             // 수정하기 버튼이 클릭되었을 때 수행할 코드 처리
             $("#submitBtn").click(function()
             {
             // 1. 데이터 검사
             //-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
             if($("#searchCategory").val()=="" || $("#title").val()==""|| $("#summernote").val()=="")
             {
                $("#err").html("입력 항목이 누락되었습니다.");
                $("#err").css("display", "inline");
                return;      //--submit 액션 처리 중단
             }

             if($("#pw").val() != $("#pw2").val() )
				{
					$("#err").html("비밀번호가 일치하지 않습니다");
					$("#err").css("display","inline");
					return;
				}

             
             // submit 액션 처리 수행 
             $("#updateForm").submit();
                  
             });

   			
   			$("#pw").on("keyup", function()
   			{
   				$("#err").css("display","none");
   				if($("#pw").val() != $("#pw2").val() )
   				{
   					$("#err").html("비밀번호가 일치하지 않습니다");
   					$("#err").css("display","inline");
   				}
   				else
   				{
   					$("#err").html("비밀번호가 일치합니다");
   					$("#err").css("display","inline");
   					$("#err").css("color","green");
   					$('#submitBtn').attr('disabled', false);
   				}
   			});
	  
	  
		});
		
		
	
</script>

</head>
<body>

<div class="whole">
	<jsp:include page="header.jsp" flush="false"/>
	
	<!-- 메뉴 구성 영역 -->
 	<div class="menu">
	<br> 
		<h1 class="text-center">정보공유</h1>
		<br><br><br>
		<nav>
			<ul id="menu">
				<li><a href="informlist.action">IT기술정보공유</a></li>
				<li><a href="seminarlist.action" class="selected">세미나 및 공모전</a></li>
				<li><a href="interviewlist.action">면접/코딩테스트 후기</a></li>
				<li><a href="freelist.action">자유게시판</a></li>
				<li><a href="questionlist.action">Q&A</a></li>
			</ul>
		</nav>
	</div>
	
	<div class="content">
		<p class="category">세미나/공모전 수정하기</p>
		<br>
		<form action="seminarupdate.action" method="get" name="myForm" role="form" class="form-inline" id="updateForm">
		<table class="table table-borderless" id="table">
		<input type="hidden" name="code" value="${sessionScope.code}">
		<input type="hidden" name="post_code" value="${post.post_code}">
		<input type="hidden" name="userpw" value="${sessionScope.userpw}" id="pw2">
			<tr>
				<th>
					말머리
				</th>
				<td>
					<select name="seminar_category" class="form-control" required="required">
						<c:forEach var="seminarCtg" items="${scList }">
						 	 <option value="${seminarCtg.seminar_category_code }"
						 	 ${seminarCtg.seminar_category_code==post.seminar_category_code ? "selected=\"selected\"" : "" }>
						 	 ${seminarCtg.seminar_category }</option>
							 </option>
						 </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					제목
				</th>
				<td>
					<input type="text" class="form-control" placeholder="제목을 입력하세요" required="required" id="title"
					name="title" value="${post.title }">
				</td>
			</tr>
			<tr>
				<th>
					모집 시작일
				</th>
				<td>
					<input type="text" class="form-control" placeholder="모집 시작일" required="required" 
					name="start_date" id="start_date" value="${post.start_date }">
				</td>
			</tr>
			<tr>
				<th>
					모집 종료일
				</th>
				<td>
					<input type="text" class="form-control" placeholder="모집 종료일" required="required" 
					name="end_date" id="end_date" value="${post.end_date }">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="form-control" placeholder="내용을 입력하세요"  style="height: 170px;" 
					required="required" name="content" id="summernote"
					>${post.content }</textarea>
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<div class="form group">
					<input type="password" class="form-control" placeholder="패스워드를 입력하세요" required="required"
					id="pw" name="inputPw" style="display:inline-block;">	
					<span id="err" style="color: red; font-weight: bold; display:none;"></span>	
					</div>
				</td>
			</tr>
			
			
		</table>
		<div id="btnSet">
			<input type="submit" value="수정하기" class="btn btn-outline-primary" id="submitBtn" disabled="disabled">
			<input type="button" value="취소하기" class="btn btn-outline-primary" id="cancel" >	
		</div>
		</form>
	
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
</div>

</body>	
</html>