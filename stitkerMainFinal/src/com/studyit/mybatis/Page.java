package com.studyit.mybatis;

public class Page
{
	// 전체 페이지 수 구하기(전체 게시물 수 / 한 페이지 게시물 수)
	public int getPageCount(int numPerPage, int dataCount)
	{
		int pageCount = 0;
		
		pageCount = dataCount/numPerPage;
		
		if (dataCount%numPerPage != 0) // 나머지 있으면 1페이지 추가
			pageCount++;
		
		return pageCount;
	}
	
	// 페이징 처리
	public String getIndexList(int currentPage, int totalPage, String listUrl)
	{
		// 보여줄 페이지 리스트
		StringBuffer strList = new StringBuffer();
		
		// 한 번에 숫자 10개 보여줌
		int numPerBlock = 10;
		
		// 현재 페이지
		int currentPageSetup;
		
		int page;
		int n;
		
		// 게시물이 10개 이하면 페이징처리 안함.
		if (currentPage == 0)
			return "";
		
		// 페이지 숫자 클릭하면 해당 페이지로 이동할 수 있게끔 처리
		// 이미 요청값이 있으면 &를 붙여주고, 없으면 ?를 붙여줌.
		if (listUrl.indexOf("?") != -1)
			listUrl += "&";
		else
			listUrl += "?";
		
		// currendPageSetup = 표시할 첫 페이지 -1
		// 현재 페이지가 1~10이면 0, 11~20이면 10이 나와야 함.
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
		
		// 10, 20 등 numPerBlock의 배수인 경우 10만큼 큰 숫자가 나오므로 10(numPerBlock) 빼주기
		if (currentPage % numPerBlock == 0)
			currentPageSetup -= numPerBlock;
		
		// 1페이지에 보여줄 양보다 데이터가 많고 현재 페이지가 11 이상인 경우
		// 1페이지. 클릭하면 이동할 수 있도록 url에 추가
		if( (totalPage > numPerBlock) && (currentPageSetup > 0) )
			strList.append(" <a style='color:black; text-decoration: none;' href='" + listUrl + "pageNum=1'>1</a>");
		
		// prev(그 전 10개) 추가하기
		n = currentPage - numPerBlock;
		if( (totalPage > numPerBlock) && (currentPageSetup > 0) )
			strList.append("<a style='color:black; text-decoration: none;' href='" + listUrl + "pageNum=" + n + "'>Prev</a>");
		
		// 현재 보일 10개 (지금 -1이므로 1 더해줌)
		page = currentPageSetup + 1;
		
		// 전체 페이지 이하면서 10개 이하만큼 추가
		while ( (page <= totalPage) && (page <= currentPageSetup + numPerBlock) )
		{
			// 현재 페이지면 링크 추가 하지 않고 스타일만 적용
			if (page==currentPage)
				strList.append(" <span style='color:orange; font-weight: bold;'>" + page + "</span>");
			else
				strList.append(" <a style='color:black; text-decoration: none;' href='" + listUrl + "pageNum=" + page + "'>" + page + "</a>");
			
			page++;
		}
		
		// 현재 페이지 뒤에 페이지가 10개 넘게 남아있다면 next 추가(next 누르면 +10 페이지로 이동)
		n = currentPage + numPerBlock;
		if((totalPage - currentPageSetup) > numPerBlock)
			strList.append(" <a style='color:black; text-decoration: none;' href='" + listUrl + "pageNum=" + n + "'>Next</a>");
		
		
		// 총 페이지 수가 10이 넘으면서 현재 페이지+10보다 많을 경우 마지막 페이지 추가 
		if( (totalPage > numPerBlock) && (currentPageSetup+numPerBlock) < totalPage )
			strList.append(" <a style='color:black; text-decoration: none; 'href='" + listUrl + "pageNum=" + totalPage + "'>" + totalPage + "</a>");
			
		// 저장된 페이징을 문자열의 형태로 반환
		return strList.toString();
		
	
	}
}
