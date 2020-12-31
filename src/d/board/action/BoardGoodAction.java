package d.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.board.db.BoardDAO;

public class BoardGoodAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("굿액션 클래스 넘어옴");
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		
		System.out.println("num  = " + request.getParameter("num"));
		
		
		int num = Integer.parseInt(request.getParameter("num"));
		int good = 0;
		
		good = boarddao.getGoodCount(num);
		
		
		request.setAttribute("good", good);
		forward.setRedirect(false);
		forward.setPath("/BoardDetailAction.bo");
		
		return forward;
		
	}

}
