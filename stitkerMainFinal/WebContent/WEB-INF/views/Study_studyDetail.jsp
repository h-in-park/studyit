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
<title>Study_studyList.jsp</title>

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<link href="css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">	 
	<script src="https://kit.fontawesome.com/5cdf4f755d.js"></script>
	<!-- <link rel="stylesheet" href="css/Layout.css"> -->
	<link rel="stylesheet" href="css/Study_apply.css">

	<script type="text/javascript">
		$(document).ready(function() {
			
			// 마감상태이면 안내문구를 변경
			if ($("#status").html() == "마감") {
				$("#memTitle1").css("display", "none");
				$("#memTitle2").css("display", "inline");
			}
			
			// 댓글 글자수 보이기
		    $('#commentBox').on('keyup', function() {
		        $('#commentCnt').html("("+$(this).val().length+" / 300)");
		 
		        if($(this).val().length > 300) {
		            $(this).val($(this).val().substring(0, 300));
		            $('#commentCnt').html("(300 / 300)");
		        }
		    });
		    
		    // 댓글 수정 클릭하면 댓글 입력창에 보이기
		    $(".cmtUpdateBtn").click(function() {
				var comment_code = $(this).val();
				$.post("getstudycomm.action?comment_code="+comment_code
				, function(comment)
				{
					$("#commentBox").val(comment);
					$("#commBtn").html("수정");
					$("#commForm").attr({"action":"modifyStudyComm.action?comment_code="+comment_code});
					
				});
				
			});
		    
		    // 댓글 삭제
		    $(".btndelCmt").click(function() {
				if(confirm("해당 댓글을 삭제하시겠습니까?"))
				{
					var comment_code = $(this).val();
					var study_code = $("#study_code").val();
					$(location).attr('href', "deleteStudyComm.action?comment_code="+comment_code+"&study_code="+study_code);
				}
			});
		    
		    // 댓글 수정, 입력 초기화
		    $("#commCancelBtn").click(function() {
		    	$("#commBtn").html("등록");
				$("#commForm").attr({"action":"insertStudyComm.action"});
			});
		    
		    // 스터디 신청
		    $("#studyApply").click(function() {
		    	if (confirm("해당스터디를 신청하시겠습니까?"))
		    	{	
		    		var study_code = $("#study_code").val();
		    		// 자격 확인
		    		$.post("applyCheck.action?study_code="+study_code
					, function(message)
					{
		    			if (message != null && message != "" && message != " " && message.length > 2) {	
		    				alert(message);
						}
		    			else
		    				$(location).attr('href', "applyStudy.action?study_code="+ study_code);
						
					});
		    	}	
			});
		    
		});	
		
		// 추천/비추천
		function clickRec(study_code, text) {
			
			if (parseInt("<c:out value='${recomCheck}' />") > 0) {
				alert("이미 추천/비추천을 하셨습니다.");
				return;
			}
			
			if (text=='추천')
				window.location.href="insertStudyRecom.action?study_code=" + study_code;
			else
				window.location.href="insertStudyUnRecom.action?study_code=" + study_code;
		}
		
		// 스터디 신청
		function clickApply(study_code) {
			if (confirm("해당스터디를 신청하시겠습니까?"))
				window.location.href="applyStudy.action?study_code="+ study_code; 
		}
		
		// 스터디 취소
		function clickCancel(study_code) {
			if (confirm("해당 스터디 참여를 취소하시겠습니까?")) {
				window.location.href="cancelStudy.action?study_code="+study_code;
			}
		}
		
		// 확인버튼 클릭
		function clickConfirm(arg) {
			if (arg.previousElementSibling.innerHTML != "<c:out value='${sessionScope.userid}'/>") {
				alert('확인 버튼은 본인만 누를 수 있습니다.');
				return;
			}
			window.location.href="updateDate.action?study_code=" + "<c:out value='${param.study_code}' />";
		}
		
		//마감
		function clickDeadLine(study_code, text) {
			var leader = document.getElementById("leader").value;
			var fixMem = document.getElementsByClassName("fixMem");
			
			if (leader != "<c:out value='${sessionScope.code}' />") {
				alert("리더만 마감할 수 있습니다.");
				return;
			}
			
			if (text=='마감하기') {
				for (var i = 0; i < fixMem.length; i++) {
					fixMem[i].style.display="inline-block";
				}
				
				document.getElementById("fixBtn").innerHTML = "마감완료";
			}
			else 
			{	
				var cnt = 0;
				for(var i = 0; i < fixMem.length; i++)
				{
					if (fixMem[i].checked)
						cnt++;
				}	
				
				var minMem = document.getElementById("minMem").innerHTML;
				var maxMem = document.getElementById("maxMem").innerHTML;
				if (cnt < minMem || cnt > maxMem) {
					alert("정해진 인원에 맞춰서 마감해야 합니다");
					return;
				}
				
				if (confirm("선택한 인원으로 스터디 마감처리하시겠습니까?")) {
					document.getElementById("fixForm").submit();
				}
			}
		}
		
		// 스터디 수정
		function clickModify(study_code) {
			if (confirm("스터디를 수정하시겠습니까?")) {
				window.location.href="studymodifyform.action?study_code="+study_code;	
			}
			
		}
		
		function chk()
		{
			if(confirm("현재 선택한 데이터를 정말 신고하시겠습니까?"))
			{
				return true;
			}
			return false;
		}		
	
	</script>
	<style type="text/css">
	
	
	</style>
</head>
<body>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content">
   <div class="title4" style="height: 350px">
     <h3 class="titleTxt p-3 text-center">스터디 찾기</h3>
   </div>
		<div class="content">
			<br>
			<div class="tableDiv">
				<table class="table">		
					<tr>
						<th class="left" colspan="8">${detail.study_name }</th>
					</tr>
					<tr class="title">
						<th>개설자</th>
						<td>${detail.user_id }</td>
						<th>개설일</th>
						<td>${detail.write_date }</td>
						<th>조회수</th>
						<td>${detail.hitcount }</td>
						<th>추천수</th>
						<td>${detail.rec_count }</td>
					</tr>
					<tr>
						<th>종류</th>
						<td>${detail.study_type }</td>
						<th>모집 상태</th>
						<td id="status">${detail.status }</td>
						<th>최소등급</th>
						<td>${detail.min_rank }</td>
						<th>장소</th>
						<td>${detail.loc_mc }</td>
					</tr>
					<tr>
						<th>시작날짜</th>
						<td>${detail.start_date }</td>
						<th>종료날짜</th>
						<td>${detail.end_date }</td>
						<th>최소인원</th>
						<td id="minMem">${detail.min_mem }</td>
						<th>최대인원</th>
						<td id="maxMem">${detail.max_mem }</td>
					</tr>					
					<tr class="rowspan">
						<th rowspan="${weekCount}">조회수</th>
						<td rowspan="${weekCount}">${detail.hitcount }</td>
						<th rowspan="${weekCount}">횟수</th>
						<td rowspan="${weekCount }">${weekCount}</td>
						<th rowspan="${weekCount }" class="narrow">요일 및 시간</th>
						<c:forEach var="week" items="${weekDay }" varStatus="i">
							<c:if test="${!i.first }"><tr></c:if>
								<td colspan="3">${week.weekday }요일 ${week.start_time }~${week.end_time }</td>
							</tr>
						</c:forEach>
					<tr>
						<td colspan="8" class="left">
						<div class="table_content">
							<span class="text">
								${detail.study_desc }
							</span>
						</div>
						</td>
					</tr>
				</table>
				</div>
				
				<form id="fixForm" action="fixStudy.action" method="post">
					<div class="member">
					<span id="memTitle1" class="memTitle">현재 확인한 인원 <span class="badge rounded-pill bg-primary memnum">${applyCount }</span>명</span>
					<span id="memTitle2" class="memTitle" style="display:none">아래 인원으로 확정</span>
						<button type="button" class="btn btn-light conBtn btn-sm" style="background-color: #f7fbff;" onclick="window.location.href='studydetail.action?study_code=${param.study_code}'">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
							  <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
							  <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
							</svg>
						</button>
						
						<div class="mem">
						<c:forEach var="mem" items="${applyMem }">
							<div>
							    <span class="name">${mem.user_id }</span> 
								<span ${detail.status == '마감'? 'class="badge rounded-pill bg-primary"' : mem.ready=='READY'? 'class="badge rounded-pill bg-primary"' : 
								   	'class="badge rounded-pill bg-light text-dark" role="button" onclick="clickConfirm(this)"'} >
								   		${mem.position=='리더'? '리더' : detail.status == '마감'? '확정' : '확인' }
								   		${mem.ready=='READY'? '' : '' }
								</span>
								<span class="checkBox">
									<c:if test="${mem.ready=='READY' }">
									  	<input type="checkbox" id="${mem.position=='리더'? 'leader':'member'}" class="form-check-input fixMem" name="fixMem" value="${mem.user_code }" style="display:none;" checked="checked"/>
									</c:if>
								</span>
								<input type="hidden" name="study_code" value="${param.study_code }"/>
							</div>	
						</c:forEach>
						</div>
							
					</div>
					
					<div class="firstBtn">
						<div class="buttons">
							<button type="button" id="fixBtn" class="btn btn-outline-primary btn-sm" ${detail.status == '마감' || detail.status == '진행중'? 'disabled' : '' } onclick="clickDeadLine('${param.study_code}', this.innerText)">마감하기</button>
							<button type="button" id="fixBtn" class="btn btn-outline-primary btn-sm" ${(detail.status == '마감' || detail.status == '진행중') || check == "different"? "disabled='disabled'" : '' } onclick="clickModify('${param.study_code}')">수정하기</button>
							<button type="button" class="btn btn-outline-primary btn-sm" ${(detail.status == '마감' || detail.status == '진행중') || check == 'same'? "disabled='disabled'" : '' } id="studyApply">신청하기</button>
							<button type="button" class="btn btn-outline-primary btn-sm" ${applyCheck == 0? "disabled='disabled'" : "" } onclick="clickCancel('${param.study_code}')">취소하기</button>
						</div>
					</div>
				</form>
				<div class="secondBtn">
					<div class="leftBtn">
						<button type="button" class="btn btn-outline-primary btn-sm" onclick="window.location.href='studysearch.action'">목록</button>
					</div>
					<div class="buttons">
						<button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#staticBackdrop" ${check=="different" ?  "" : "style=\"display:none;\"" }
							${chkReport==0 ?  "" : "disabled = \"disabled\"" }>
		                    ${chkReport==0 ?  "신고하기" : "신고완료" }
		                </button>
		                  
		                <!-- Modal -->
		                <form action="studyreport.action" method="post" onsubmit="return chk()">
		                	<input type="hidden" name="post_code" value="${param.study_code }" />
		                  	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		                    	<div class="modal-dialog">
		                      		<div class="modal-content">
		                        		<div class="modal-header">
		                          			<h5 class="modal-title" id="staticBackdropLabel">게시글 신고</h5>
		                          			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                        		</div>
		                        		<div class="modal-body"> 
		                             		신고 카테고리<br>
		                            		<select name="post_report_ctg_code" id="post_report_ctg_code" class="form-control">
											<c:forEach var="ctg" items="${reportctg }">
												<option value="${ctg.post_report_ctg_code }">${ctg.post_report_ctg }</option>	
											</c:forEach>
											</select>
		                    
		                          			<hr>                  
		                            		신고 내용 <br>
		                            		<div>
		                              			<textarea class="form-control" rows="7" cols="20" id="report_reason" name="report_reason" placeholder="내용을 입력하세요."></textarea>
		                          			</div>
		                        		</div>
		                        
		                        		<div class="modal-footer">
		                          			<button type="button" class="btn btn-outline-primary" data-bs-dismiss="modal">취소</button>
		                          			<button type="submit" class="btn btn-primary">제출</button>
		                        		</div>
		                     		</div>
		                    	</div>
		                  	</div>
		              	</form> 
					</div>
				</div>
				<br>
				<table id="commentHeader" class="table">
				<tr>
					<td>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-right-text" viewBox="0 0 16 16">
							<path d="M2 1a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h9.586a2 2 0 0 1 1.414.586l2 2V2a1 1 0 0 0-1-1H2zm12-1a2 2 0 0 1 2 2v12.793a.5.5 0 0 1-.854.353l-2.853-2.853a1 1 0 0 0-.707-.293H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12z"/>
							<path d="M3 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 6a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 6zm0 2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
						</svg> 
						댓글 ${commentCount }
					</td>
					<td>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up" viewBox="0 0 16 16">
						 	<path d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2.144 2.144 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a9.84 9.84 0 0 0-.443.05 9.365 9.365 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111L8.864.046zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a8.908 8.908 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.224 2.224 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.866.866 0 0 1-.121.416c-.165.288-.503.56-1.066.56z"/>
						</svg> 
						추천 ${recomCount }
					</td>
					<td>
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-down" viewBox="0 0 16 16">
  							<path d="M8.864 15.674c-.956.24-1.843-.484-1.908-1.42-.072-1.05-.23-2.015-.428-2.59-.125-.36-.479-1.012-1.04-1.638-.557-.624-1.282-1.179-2.131-1.41C2.685 8.432 2 7.85 2 7V3c0-.845.682-1.464 1.448-1.546 1.07-.113 1.564-.415 2.068-.723l.048-.029c.272-.166.578-.349.97-.484C6.931.08 7.395 0 8 0h3.5c.937 0 1.599.478 1.934 1.064.164.287.254.607.254.913 0 .152-.023.312-.077.464.201.262.38.577.488.9.11.33.172.762.004 1.15.069.13.12.268.159.403.077.27.113.567.113.856 0 .289-.036.586-.113.856-.035.12-.08.244-.138.363.394.571.418 1.2.234 1.733-.206.592-.682 1.1-1.2 1.272-.847.283-1.803.276-2.516.211a9.877 9.877 0 0 1-.443-.05 9.364 9.364 0 0 1-.062 4.51c-.138.508-.55.848-1.012.964l-.261.065zM11.5 1H8c-.51 0-.863.068-1.14.163-.281.097-.506.229-.776.393l-.04.025c-.555.338-1.198.73-2.49.868-.333.035-.554.29-.554.55V7c0 .255.226.543.62.65 1.095.3 1.977.997 2.614 1.709.635.71 1.064 1.475 1.238 1.977.243.7.407 1.768.482 2.85.025.362.36.595.667.518l.262-.065c.16-.04.258-.144.288-.255a8.34 8.34 0 0 0-.145-4.726.5.5 0 0 1 .595-.643h.003l.014.004.058.013a8.912 8.912 0 0 0 1.036.157c.663.06 1.457.054 2.11-.163.175-.059.45-.301.57-.651.107-.308.087-.67-.266-1.021L12.793 7l.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315l-.353-.354.353-.354c.047-.047.109-.176.005-.488a2.224 2.224 0 0 0-.505-.804l-.353-.354.353-.354c.006-.005.041-.05.041-.17a.866.866 0 0 0-.121-.415C12.4 1.272 12.063 1 11.5 1z"/>
						</svg> 
						비추천 ${unRecomCount }</td>
					<td>
                        <button type="button" class="btn btn-outline-primary btn-sm btnRec"  onclick="clickRec('${param.study_code }', this.innerText)">추천</button> 
                        <button type="button" class="btn btn-outline-primary btn-sm btnNotRec" onclick="clickRec('${param.study_code }', this.innerText)">비추천</button>
                     </td>
				</tr>
			</table><!-- commentHeader end -->
		
			<table id="table2" class="table">
				<c:forEach var="comm" items="${comment }">
					<tr>
						<td>${comm.rnum }</td>
				 		<th>${comm.user_id }</th>
				 		<td id="comment">${comm.comments }</td>
				 		<td>
	                        <c:choose>
	                          <c:when test="${sessionScope.code!=null && sessionScope.code eq comm.user_code}">
	                             <button style="color:black;" class="btn btn-link btn-sm cmtUpdateBtn" value="${comm.comment_code }"><!-- 수정버튼 -->
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
	                                  <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
	                                </svg>
	                             </button>
	                               </c:when>
	                        </c:choose>       
	                    </td>
                        <td style="width: 6%">
	                       <c:choose>
	                          <c:when test="${sessionScope.code!=null && sessionScope.code eq comm.user_code}">
	                             <button style="color:black;" class="btn btn-link btn-sm btndelCmt" value="${comm.comment_code }"><!-- 삭제버튼 -->
	                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
	                                     <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
	                                     <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
	                                </svg>
	                             </button>
	                               </c:when>
	                       </c:choose>
	                    </td> 	
				 	</tr>
				</c:forEach>
				
			</table>
			<div id="commentFooter">
				<p>${pageIndexList }</p>
			</div><!-- #commentFooter -->
			
			<form action="insertStudyComm.action" method="post" id="commForm">	
				<table id="commentWrite" class="table">
					<tr>
				 		<td colspan="2">
				 			<textarea id="commentBox" name="comments" cols="" rows="" 
								class="form-control" placeholder="댓글을 입력하세요."></textarea>
							<input type="hidden" id="study_code" name="study_code" value="${param.study_code }"/>	
							<input type="hidden" name="user_code" value="${sessionScope.code }"/>	
				 		</td>
				 	</tr>
				 	<tr>
				 		<td>
					 		<div id="commentCnt">(0 / 300)</div>
					 	</td>
					 	<td>
			   				<button type="submit" class="btn btn-outline-primary btn-sm btn2" id="commBtn">등록</button>
			   				<button type="reset" class="btn btn-outline-primary btn-sm btn2" id="commCancelBtn">취소</button>	
		   				</td>
				 	</tr>	
				</table>
			</form>
		
	</div><!-- content -->
</div><!-- main_content -->
 <jsp:include page="footer.jsp" flush="false"/>
 </div><!-- wrapper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>