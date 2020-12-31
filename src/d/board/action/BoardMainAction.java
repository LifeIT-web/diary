package d.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();
		List goodlist = new ArrayList();
		
		boardlist = boarddao.getNewList();
		goodlist = boarddao.getGoodList();

		request.setAttribute("boardlist", boardlist);
		request.setAttribute("goodlist", goodlist);
		
		forward.setRedirect(false);
		forward.setPath("./board/main.jsp");
		return forward;

	}

}
