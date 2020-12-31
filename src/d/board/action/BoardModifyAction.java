package d.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		BoardBean board = new BoardBean();
		
		int B_NUM = Integer.parseInt(request.getParameter("B_NUM"));
		String B_ID = (String)session.getAttribute("id");
		String B_TITLE = request.getParameter("B_TITLE");
		String B_CONTENT = request.getParameter("B_CONTENT");
		
		
		board.setB_ID(B_ID);
		board.setB_NUM(B_NUM);
		board.setB_TITLE(B_TITLE);
		board.setB_CONTENT(B_CONTENT);
		
		
		
		boolean bool = false;
		
		bool = boarddao.boardUserCheck(board);
		
		if (!bool) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('수정 하실 권한이 없습니다.');");
			out.print("location.href='/BoardDetailAction.bo';");
			out.print("</script>");
		}
		
		bool = boarddao.boardModify(board);
		
		if (bool) {
			forward.setRedirect(false);
			forward.setPath("/BoardDetailAction.bo?num="+board.getB_NUM());
			return forward;
		}else {
			System.out.println("BoardModifyAction - boardModify() error");
}
		
		return null;
		
		
		
	}

}
