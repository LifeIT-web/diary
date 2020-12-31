package d.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import d.board.db.BoardBean;
import d.board.db.BoardDAO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("boardWriteAction 들어옴");
		HttpSession  session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		
			BoardBean board = new BoardBean();
	      BoardDAO boarddao = new BoardDAO();
	      ActionForward forward = new ActionForward();
	      
	      String realFolder = "";
	      String saveFolder = "/boardupload";
	      
	      int fileSize = 5 * 1024 * 1024;
	      
	      realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
	      boolean bool = false;
	            
	      
	      try {
	         
	         MultipartRequest multi = null;
	         
	         multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
	         
	         board.setB_ID(id);
	         board.setB_TITLE(multi.getParameter("B_TITLE"));
	         board.setB_CONTENT(multi.getParameter("B_CONTENT"));
	         board.setB_FILE(multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	         
	         bool = boarddao.boardInsert(board);
	         
	         if (bool) {
	            System.out.println("게시판 등록 성공");
	            
	            forward.setRedirect(false);
	            forward.setPath("/BoardMainAction.bo");
	            return forward;
	         }
	      } catch (Exception e) {
	         System.out.println("boardWriteAction error : " + e);
	      }
	      return null;
	}

}
