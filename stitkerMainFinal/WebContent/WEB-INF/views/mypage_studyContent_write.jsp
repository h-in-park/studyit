<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String cp = request.getContextPath(); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 컨텐츠 작성</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="<%=cp %>/css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery.min.js"></script>

<!-- Summernote-lite 가져오기 -->
<script src="js/summernote-lite.js"></script>
<script src="js/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="<%=cp %>/css/summernote-lite.css">
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
  
	});
	
</script>
<script>
	
	function goModify(frm) {
	var content = frm.content.value;
	
	if (content.trim() == ''){
		
		alert("내용을 입력해주세요");
		return false;
	}
	
	frm.submit();
}
</script>
<style type="text/css">

.table .textareaTd {padding-right: 10%;}

</style>
</head>
<body>
<div class="wrapper">
	<jsp:include page="header.jsp" flush="false"/>
	<div class="main-content">
		<div class="title">
        <h3 class="titleTxt p-3 text-center">컨텐츠 작성</h3>
      </div>
		<div class="container">		
			<form action="contentinsert.action?studycode=${param.studycode }" method="post">
			<div class="tableDiv">
			<br><br><br><br>
				<table class="table table-borderless">
					<tr>
						<th>제목</th>
						<td>
							<input required="required"  type="text" class="form-control write" name="title" id="title" placeholder="제목을 입력하세요">
						</td> 
					</tr>
					<tr>
						<th>내용</th>
						<td class="textareaTd">
							<textarea required="required" name="content" id="summernote"></textarea>
							<br><br>
							<div class="buttons col-4" style="margin:0 auto">
								<button type="button" class="btn btn-outline-primary mb-2" onclick="history.back()">이전 페이지로 이동</button>
								<button type="submit" class="btn btn-outline-primary mb-2">등록하기</button>
							</div>
							<br><br>
						</td>
					</tr> 
				</table>
				<input type="hidden" name="parti_code" id="parti_code" value="${parti_code }">
			</div>
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp" flush="false"/>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>	
</div>
	
</body>
</html>