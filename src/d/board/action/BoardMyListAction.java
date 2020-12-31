package d.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import d.board.db.BoardDAO;

public class BoardMyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardDAO boarddao = new BoardDAO();
	      ActionForward forward = new ActionForward();
	      
	      HttpSession session = request.getSession();
	      String id = (String)session.getAttribute("id");
	      
	      List list = new ArrayList();
	      
	      int page = 1;
	      int limit = 10;
	      
	      if (request.getParameter("page") != null) {
	         page = Integer.parseInt(request.getParameter("page"));
	         
	      }
	      
	      int listcount = boarddao.getListCount();
	      list = boarddao.getMyList(page, limit, id);
	      
	   
	      int maxpage = (int)((double) listcount/limit + 0.95);
	      
	      int startpage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
	      
	      int endpage = maxpage;
	      
	      if (endpage > startpage + 10 - 1) {
	         endpage = startpage + 10 - 1;
	      }
	      
	      request.setAttribute("page", page);
	      request.setAttribute("maxpage", maxpage);
	      request.setAttribute("startpage", startpage);
	      request.setAttribute("endpage", endpage);
	      request.setAttribute("boardlist", list);

	      forward.setRedirect(false);
	      forward.setPath("./board/myList.jsp");
	      return forward;
		
	}

}
