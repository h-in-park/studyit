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
<title>스터디 개설</title>
<!-- 스터디 개설 페이지 css -->
<link rel="stylesheet" type="text/css" href="css/studywrite.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="./jquery-ui-1.12.1/datepicker-ko.js"></script>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/bootstrap.min.css">
<script type="text/javascript" src="<%=cp %>/js/util.js"></script>
<script src="https://kit.fontawesome.com/5cdf4f755d.js" crossorigin="anonymous"></script>

<script type="text/javascript">
	
    $(document).ready(function()
    {
    
    	// 목적 및 목표 글자수
		$('#purpose').on('keyup', function() {
	        $('#purposeCnt').html("("+$(this).val().length+" / 300)");
	 
	        if($(this).val().length > 300) {
	            $(this).val($(this).val().substring(0, 300));
	            $('#purposeCnt').html("(300 / 300)");
	        }
	    });
    	
		//datepicker 한국어로 사용하기 위한 언어설정
        $.datepicker.setDefaults($.datepicker.regional['ko']); 
		
      	//시작일.
        $('#startDate').datepicker({
            dateFormat: "yy-mm-dd"           // 날짜의 형식
            , changeYear : true				 // 년을 이동하기 위한 선택상자 표시여부
            , changeMonth: true              // 월을 이동하기 위한 선택상자 표시여부
            , minDate: 0                     // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
            , maxDate: '+14d'
            , onClose: function( selectedDate ) {    
                // 시작일(fromDate) datepicker가 닫힐때
                // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                $("#endDate").datepicker( "option", "minDate", selectedDate );
            }                
        });
      	
      	//종료일
        $('#endDate').datepicker({
            dateFormat: "yy-mm-dd"
            , changeYear : true
            , changeMonth: true
            , minDate: '+10d'                      // 선택할수있는 최소날짜, ( 0 : 오늘 이전 날짜 선택 불가)
            , maxDate: '+1y 14d'
            , onClose: function( selectedDate ) {
                // 종료일(toDate) datepicker가 닫힐때
                // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
                $("#startDate").datepicker( "option", "maxDate", selectedDate );
            }                
        });
      


 		// 등록 버튼 눌렀을 때 
		$("#sendBtn").click(function()
		{
			// 1. 데이터 검사
			//-- 공란(입력항목 누락)이 있는지에 대한 여부 확인
			if ( $("#study_name").val()=="" || $("#purpose").val()=="" || $("#studyCategory").val()==""
					|| $("#studyType").val()=="" || $("#startDate").val()==""
					|| $("#endDate").val()=="" || $("#weekday").val()=="" || $("#startTime").val()==""
					|| $("#endTime").val()=="" || $("#regionGroup1").val()=="" || $("#regionGroup2").val()=="")
				
			{
				$("#err").html("* 입력 항목이 누락되었습니다.");
				$("#err").css("display", "inline");
				return;		//-- submit 액션 처리 중단
			}

			// submit 액션 처리 수행
			$("#studyForm").submit();
		});
 		
		// 지역대분류 선택시 중분류 나오도록.
		$("#regionGroup1").change(function(){
			var selectedLc = "loc_lc_code="+this.value;
			
			$.ajax(
			{
				type : "POST"
				, url : "studyregionajax.action"
				, data : selectedLc
				, dataType : "json"
				, success : function(jsonArr)
				{
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
					$("#regionGroup2").html(out); 
				}
				, error:function(request,status,error){
		        	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			   	}
			});
		});
		

    });
    
 

	// 요일 선택 시 시간 입력
	function checkDay(arg)
	{
	    if(arg.checked){
	        $("#"+arg.value+'Time').show();
	    }else{
	        $("#"+arg.value+'Time').children(".form-control").val("");
	        $("#"+arg.value+'Time').children(".form-control").val("");
	        $("#"+arg.value+'Time').hide();
	    }
	}



</script>
   
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class=" main main-content text-center"><br>
   
    <div id="StudyOpen">
	<div id="StudyOpen_title">
		스터디 개설 신청
	</div><!-- #StudyOpen_title -->
	
	<div class="main">
	<form action="studymodify.action" method="post" name="studyForm" >
	
	<p class="header_left">스터디 개설 정보 입력</p>
	
	<table id="table1" class="table table-striped table-bordered">
		<tr>
			<th>스터디 이름</th>
			<td>
				<input type="text" maxlength="60" name="study_name" class="form-control" id="study_name" value="${study.study_name }" required>
				<input type="hidden" name="study_code" value="${param.study_code }"/>
			</td>
		</tr>
		<tr>
			<th>스터디의 목적 및 목표</th>
			<td>
				<textarea rows="2" cols="40" name="study_desc" id="purpose" class="form-control" required>${study.study_desc }</textarea>
				<div style="font-size: 7pt;" id="purposeCnt">(0 / 300)</div>
			</td>
		</tr>
		<tr>
			<th>스터디 분야</th>
			<td>
				<select name="interest_mc_code" class="form-control" id="studyCategory" required>
					<option value="">선택</option>
					 <c:forEach var="interest" items="${imList }">
						 <option value="${interest.interest_mc_code }" ${interest.interest_mc_code == study.interest_mc_code? "selected='selected'" : ""}>
						 	${interest.interest_mc }
						 </option>
					 </c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>스터디 유형</th>
			<td>
				<select name="study_type_code" class="form-control" id="studyType" required>
					<option value="">선택</option>
					 <c:forEach var="studytype" items="${stList }">
						 <option value="${studytype.study_type_code }" ${studytype.study_type_code == study.study_type_code? "selected='selected'" : ""}>
						 	${studytype.study_type }
						 </option>
					 </c:forEach>
				</select>
			</tr>
		<tr>
			<th>시작 날짜</th>
			<td>
				<input type="text" id="startDate" name="start_date" class="form-control" required placeholder="스터디 시작일" value="${study.start_date }" disabled='disabled'>
			</td>
		</tr>
		<tr>
			<th>종료 날짜</th>
			<td>
				<input type="text" id="endDate" name="end_date" class="form-control" placeholder="스터디 종료일" value="${study.end_date }" required>
				<span class="errMsg" style="font-size: 7pt;">*종료 날짜는 시작 날짜로부터 1년까지 선택할 수 있습니다.</span>
			</td>
		</tr>
		<tr>
			<th>스터디 진행 요일 및 시간</th>
			<td>
				<div id="studyDay" class="form-control" >
					<c:forEach var="weekday" items="${wdList }">
						<c:set var="flag" value="false" />
						<c:forEach var="meetday" items="${meetdayList }" varStatus="status">
							<c:if test="${not flag }">
								<c:if test="${meetday.weekday_code eq weekday.weekday_code}">
									<label>
										<input type="checkbox" name="day" value="${weekday.weekday_code }" onchange="checkDay(this)" checked='checked'>${weekday.weekday }요일
									</label>
									<div id="${weekday.weekday_code }Time" class="form group" style="display:block">
										시작 시간 <input name="selectWeek[]" type="time" class="form-control" style="display:inline-block" value='${meetday.start_time}'>
										종료 시간 <input name="selectWeek[]" type="time" class="form-control" style="display:inline-block" value='${meetday.end_time}'>
									</div>
									<br>
									<c:set var="flag" value="true" />
								</c:if>
								
								<c:if test="${status.last && not flag }">
									<label>
										<input type="checkbox" name="day" value="${weekday.weekday_code }" onchange="checkDay(this)">${weekday.weekday }요일
									</label>
									<div id="${weekday.weekday_code }Time" class="form group" style="display:none">
										시작 시간 <input name="selectWeek[]" type="time" class="form-control" style="display:inline-block">
										종료 시간 <input name="selectWeek[]" type="time" class="form-control" style="display:inline-block">
									</div>
									<br>
								</c:if>
							</c:if>
						</c:forEach>	
					</c:forEach>
				</div>
			</td>
		</tr>
		<tr>
			<th>지역</th>
			<td>
				<select name="loc_lc_code" id="regionGroup1" class="form-control"  required>
					<option value="">선택</option>
					 <c:forEach var="loclc" items="${llList }">
						 <option value="${loclc.loc_lc_code }" ${loclc.loc_lc_code == study.loc_lc_code? "selected='selected'" : "" }>
						 	${loclc.loc_lc }
						 </option>
					 </c:forEach>
				</select>
				<select name="loc_mc_code" id="regionGroup2" class="form-control"  required>
					<option value="">선택</option>
					 <c:forEach var="locmc" items="${lmList }">
						 <option value="${locmc.loc_mc_code }" ${locmc.loc_mc_code == study.loc_mc_code? "selected='selected'" : "" }>
						 	${locmc.loc_mc }
						 </option>
					 </c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>최소인원수</th>
			<td>
				<select name="min_mem_code" id="minMem" class="form-control" disabled='disabled' required>
					<option value="">선택</option>
					 <c:forEach var="memnum" items="${mnList }">
						 <option value="${memnum.memnum_code }" ${memnum.memnum_code == study.min_mem_code? "selected='selected'" : "" }>
						 	${memnum.memnum}
						 </option>
					 </c:forEach>
				</select>
				<span style="font-size: 7pt;">
				*스터디 개설이 가능한 인원수는 3명 이상, 8명 이하입니다.</span>
			</td>
		</tr>
		<tr>
			<th>최대인원수</th>
			<td>
				<select name="max_mem_code" id="maxMem" class="form-control"  required>
					<option value="">선택</option>
					 <c:forEach var="memnum" items="${mnList }">
						 <option value="${memnum.memnum_code }" ${memnum.memnum_code == study.max_mem_code? "selected='selected'" : "" }>
						 	${memnum.memnum}
						 </option>
					 </c:forEach>
				</select>
				<span style="font-size: 7pt;">
				*스터디 개설이 가능한 인원수는 3명 이상, 8명 이하입니다.</span>
			</td>
		</tr>
		<tr>
			<th>참여최소등급</th>
			<td>
				<select name="min_rank" id="grade" class="form-control" disabled='disabled' required>
					<option value="">선택</option>
					 <c:forEach var="rank" items="${urList }">
						 <option value="${rank.rank_code }" ${rank.rank_code == study.min_rank? "selected='selected'" : "" }>
						 	${rank.rank}
						 </option>
					 </c:forEach>
				</select>
				<span style="font-size: 7pt;">
				*참여최소등급은 개설자의 등급보다 높을 수 없습니다.</span>
			</td>
		</tr>
	</table>
	<span id="err" style="color: red; font-size: 8pt;" ></span>
	
	<div id="footer">
		<button type="submit" id="sendBtn" class="btn btn-outline-primary">수정</button>
		<button type="reset" class="btn btn-outline-primary">재입력</button>
		<button type="button" class="btn btn-outline-primary" onclick="window.location.href='studydetail.action?study_code=${param.study_code}'">취소</button>
	</div><!-- #footer -->
	
	</form>
	</div>
	
</div><!-- #StudyOpen -->
   
  </div>
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>