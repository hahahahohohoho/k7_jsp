package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		MVCBoardDAO dao = new MVCBoardDAO();

		Map<String, Object> map = new HashMap<>(); // 뷰에 전달할 매개변수 저장용 맵

		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");

		if(searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map); // 게시물 개수

		/*페이지 처리 start*/
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

		//현재페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); //요청 받은 페이지로 수정

		int start = (pageNum-1) * pageSize; //MYSQL은 0부터 시작
		map.put("start", start);
		map.put("pageSize", pageSize);
		/* 페이지 처리 end */

		List<MVCBoardDTO> boardList = dao.selectListPage(map);
		dao.close();

		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		req.setAttribute("boardList", boardList);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
	}
}
