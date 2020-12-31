package d.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardAllListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = boarddao.getListCount(); 
		boardlist = boarddao.getBoardList(page,limit); 
		
		//총 페이지 수
		int maxpage = (int)((double)listcount/limit+0.95);
		
	
		int startpage = (((int)((double)page/ 10 + 0.9)) -1) *10 + 1;
		
		
		int endpage = maxpage;
		
		if(endpage>startpage+10-1) endpage = startpage + 10-1;
		
		request.setAttribute("page", page);
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardlist", boardlist);
		
		forward.setRedirect(false);
		forward.setPath("./board/allList.jsp");
		return forward;
		
		
	}

}
