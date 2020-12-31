package d.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		BoardBean board = new BoardBean();
		BoardDAO boarddao = new BoardDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		board = boarddao.getDetailList(num);
		
		if (board==null) {
			System.out.println("BoardModifyView error");
			return null;
		}
		
		request.setAttribute("board", board);
		forward.setRedirect(false);
		forward.setPath("./board/modify.jsp");
		return forward;
	}

}
