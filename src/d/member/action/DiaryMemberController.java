package d.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.board.action.BoardMainAction;



public class DiaryMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String RequestURI = request.getRequestURI();
		ActionForward forward = null;
		Action action = null;
		System.out.println("RequestURI = " + RequestURI);
		
		if(RequestURI.equals("/MemberLoginView.me")) {
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/login.jsp");
			
		}
		else if (RequestURI.equals("/MemberLoginAction.me")) {
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				System.out.println("Controller error - MemberLoginAction");
			}
		}
		
		else if(RequestURI.equals("/MemberJoinView.me")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/join.jsp");
			
		}
		else if(RequestURI.equals("/MemberJoinAction.me")) {
			
			action = new MemberJoinAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {   
				e.printStackTrace();
			}
		}
		else if(RequestURI.equals("/MemberLogoutAction.me")) {
			
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {   
				e.printStackTrace();
			}
		}
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
		
		
		
		
	}

	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
