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
<title>스터디 찾기</title>
<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="<%=cp %>/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/studylist.css">
	<link rel="stylesheet" type="text/css" href="<%=cp %>/css/bootstrap-reboot.css">
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<style type="text/css">
a {color:#000000;
text-decoration: none;}
</style>
<script type="text/javascript">

 	function itemChange()
	{
		//alert($('#loc_lc').val()); 테스트
		var LL1 = [ "전체","은평구/서대문구/마포구" , "종로구/중구/용산구" , "도봉구/강북구/성북구/노원구" 
					,"동대문구/중랑구/성동구/광진구" , "강서구/양천구" , "영등포구/구로구/금천구"
					, "동작구/관악구" , "서초구/강남구" , "송파구/강동구"];
		var ll1 = ['LM0','LM1','LM2','LM3','LM4','LM5','LM6','LM7','LM8','LM9'];
		
		var LL2 = ["전체","수원시" ,"고양시" ,"용인시" ,"성남시" ,"부천시" , "화성시" ,"남양주시" , "안산시" ,"안양시" ,"기타"];
		var ll2 = ['LM0','LM10','LM11','LM12','LM13','LM14','LM15','LM16','LM17','LM18','LM19'];
		
		var LL3 = ["전체","충청도","경상도","전라도","강원도","인천","대전","광주","대구","울산" ,"부산"];
		var ll3 = ['LM0','LM20','LM21','LM23','LM24','LM25','LM26','LM27','LM28','LM29'];
		
		var LL4 = ["온라인"];
		var ll4 = ['LM19'];
		
		var selectItem = $("#loc_lc").val();

		var changeItem;
		var changeitem;
		 

		if(selectItem == "LL1"){
		  changeItem = LL1;
		  changeitem = ll1;
		}
		else if(selectItem == "LL2"){
		  changeItem = LL2;
		  changeitem = ll2;
		}
		else if(selectItem == "LL3"){
		  changeItem =  LL3;
		  changeitem = ll3;
		}
		else if(selectItem == "LL4"){
		  changeItem =  LL4;
		  changeitem = ll4;
		}
		 
		$('#loc_mc').empty();
		 
		for(var count = 0; count < changeItem.length; count++){                
		                var option = "<option value='"+changeitem[count]+ "'>"+changeItem[count]+"</option>";
		                $('#loc_mc').append(option);
		            }

	}
 	
 	

</script>
<style type="text/css">
.searchLeft > table > tbody > tr > td
{
	width:170px;
}
span {
color:#000;}
table > tbody > tr 
{
	height: 50px;
}
</style>
</head>
<div class="wrapper">
 <jsp:include page="header.jsp" flush="false"/>
 <div class="main-content3">
   <div class="title4" style="height: 350px">
     <h3 class="titleTxt text-center"><a href="studysearch.action"  style="text-decoration:none; color:white" >스터디 찾기</a></h3>
   </div>
   <div class="container"><br><br><br>
	<div class="search">
	<form action="studysearch.action" name="myForm">
	<div class="searchRight">
		<select class="form-select" aria-label="Default select example" name="searchKey" id="searchKey" style="width: 130px; display: inline;">
		<!--     <option value="all" selected="selected">전체</option>  -->
			<option id="study_name" value="study_name" ${searchKey =='study_name' ? 'selected="selected"' : '' }>스터디명</option> 
			<option id="leader" value="leader" ${searchKey =="leader" ? 'selected="selected"' : '' }>스터디 리더</option>
			<option id="study_code" value="study_code" ${searchKey =="study_code" ? 'selected="selected"' : '' }>스터디 번호</option>
		</select>
		<input type="text" name="searchValue" id="searchValue" class="form-control" placeholder="검색어를 입력하세요." 
		        style="width: 200px; display: inline;" value="${searchValue}">
						<%-- <select name="searchCategory" id="searchCategory" class="form-control">
							<c:forEach var="interview" items="${interestlist }">
								<option value="${interview.interest_mc_code }">${interview.interest_mc }</option>	
							</c:forEach>
						</select> --%>
	<button type="submit"  id="searchBtn"  class="btn btn-outline-primary btn-md mb-1">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
  <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
</svg>
	</button>
		<br><br>
	</div>
	</form>
	<form action="studysearch.action" name="myForm2">
	<div class="searchLeft">
	<table class="table">
	<tr>
	<td><span>지역으로 검색</span></td>
	<td>
	<select class="form-select" style="width:160px;" onchange="itemChange(this)" aria-label="Default select example" name="loc_lc" id="loc_lc" style="width: 130px; display: inline;">
	    <option selected="selected">지역대분류</option>
	    <c:forEach var="lc" items="${loc_lc }"> 
	   		 <option value="${lc.loc_lc_code }" ${lc.loc_lc_code ==loc_Lc? 'selected="selected"' : '' }>${lc.loc_lc }</option>
	    </c:forEach>  
	</select>
	</td>
	<td><select class="form-select"  aria-label="Default select example" name="loc_mc" id="loc_mc" style="width: 250px; display: inline;">
	    <option selected="selected">지역중분류</option> 
	</select></td>
	</tr>
	<tr>
	<td><span>스터디 유형으로 검색</span></td>
	<td><select style="width:170px;margin-right:10px" class="form-select" aria-label="Default select example" name="study_type" id="study_type" style="width: 130px; display: inline;">
	    <option value="all">스터디 분류(전체)</option>
	    <c:forEach var="type" items="${type }">
	    	<option value="${type.study_type }">${type.study_type }</option>
	     </c:forEach>
	</select></td>
	<td><input type="submit" class="btn btn-outline-primary" value="검색하기"></td>
	</tr>
	</table>
	</div>
	</form>
	</div>
	<div class="mt-5 mb-5">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col" class="bg-light">스터디 번호</th>
		      <th scope="col" class="bg-light">스터디명</th>
		      <th scope="col" class="bg-light">기간</th>
		      <th scope="col" class="bg-light">스터디 리더</th>
		      <th scope="col" class="bg-light">지역</th>
		      <th scope="col" class="bg-light">최소등급</th>
		      <th scope="col" class="bg-light">스터디 분류</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="list" items="${list }">
		    <tr>
		      <td>${list.study_code2 }</td>
		      <td><a href="studydetail.action?study_code=${list.study_code }">${list.study_name }</a></td>
		      <td>${list.period } </td>
		      <td>${list.leader } </td>
		      <td>${list.loc_mc }</td>
		      <td>${list.min_rank}</td>
		      <td>${list.study_type }</td>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>

	<div class="page">
	${pageIndexList }
	</div>
	   
   </div>
  </div>
 <jsp:include page="footer.jsp" flush="false"/>
 </div>
</body>
</html>