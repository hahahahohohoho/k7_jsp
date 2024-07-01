package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr="";

		int totalPages = (int)(Math.ceil(((double) totalCount / pageSize)));

		// 이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum-1)/blockPage) * blockPage)+1;
		if(pageTemp != 1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp-1) + "'>[이전 블록]</a>";
		}

		// 각페이지 번호 출력
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				pagingStr = "&nbsp;" + pageTemp + "&nbsp;";
			} else {
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		//다음페이지 블록 바로가기 출력
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "pageNum=" + pageTemp + "'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "pageNum=" + totalPages + "'>[마지막 블록]</a>";
		}

		return pagingStr;
	}
}
