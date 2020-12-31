package d.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		BoardDAO boarddao = new BoardDAO();
	      BoardBean board = new BoardBean();
	      ActionForward forward = new ActionForward();
	      
	      int num = Integer.parseInt(request.getParameter("num"));
	      boarddao.setReadCount(num); // 메소드 이름 변경
	      board = boarddao.getDetailList(num); // 메소드 이름 변경
	      
	      if (board == null) {
	         System.out.println("상세보기 실패");
	         return null;
	      } else {
	         System.out.println("상세보기 성공");
	         
	         request.setAttribute("board", board);
	         forward.setRedirect(false);
	         forward.setPath("./board/view.jsp");
	         return forward;
	      }
	      
	      
	}

}
