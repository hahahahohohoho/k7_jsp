package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
)

public class EditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selctView(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/14MVCBoard/Edit.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//1.파일 업로드 처리
		String sDirectory = req.getServletContext().getRealPath("/Uploads");
		String oFileName ="";

		try {
			oFileName = FileUtil.uploadFile(req, sDirectory);
		} catch (Exception e) {
			// TODO: handle exception
			JSFunction.alertBack(resp, "파일 업로드 오류입니다.");
		}

		//2.파일 업로드 외 처리
		//수정 내용을 매개변수에 얻어옴
		String idx = req.getParameter("idx");
		idx = idx.replace("/","");
		String prevOfile = req.getParameter("prevOfile");
		String prevSfile = req.getParameter("prevSfile");

		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		//비밀번호는 session에 담아옴
		HttpSession session = req.getSession();
		String pass =(String)session.getAttribute("pass");

		//DTO에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		// 원본 파일명과 저장된 파일 이름 설정
		if (oFileName != "") { // 첨부파일 있으면 기존파일 삭제 후 새 파일 등록
			String sFileName = FileUtil.renameFile(sDirectory, oFileName);

			dto.setOfile(oFileName);
			dto.setSfile(sFileName);

			FileUtil.deleteFile(req, "/Uploads", prevSfile);
		}
		else { // 첨부파일 없으면 기존 이름 유지
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}

		//DB에 반영
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();

		if(result ==1) { //수정 성공
			session.removeAttribute("pass");
			resp.sendRedirect("../mvcboard/view.do?idx="+idx);
		}
		else {
			System.out.println("../mvcboard/view.do?idx=" + idx);
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요",
									 "../mvcboard/view.do?idx=" + idx);
		}
	}
}
