package d.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DiaryBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String RequestURI = request.getRequestURI();
			Action action = null;
			ActionForward forward = null;
			
			if (RequestURI.equals("/BoardMainAction.bo")) {
				
				action = new BoardMainAction();

				try {
					forward = action.execute(request, response);
				} catch (Exception e) {   
					System.out.println("Controller error- BoardMainAction : " + e);
				}
				
			}
			
			else if (RequestURI.equals("/BoardWriteView.bo")) {
		         forward = new ActionForward();
		         forward.setRedirect(false);
		         forward.setPath("./board/write.jsp");
		         
		      }
			
			else if (RequestURI.equals("/BoardWriteAction.bo")) {
		         action = new BoardWriteAction();
		         
		         try {
		            forward = action.execute(request, response);
		         } catch (Exception e) {
		        	 System.out.println("Controller error- BoardWriteAction : " + e);		         }
		         
		      }
			
			//나
			else if(RequestURI.equals("/BoardAllListAction.bo")) {
			
				action = new BoardAllListAction();
				
				try {
					forward = action.execute(request, response);
					
				}catch(Exception e) {
					System.out.println("Controller error- BoardAllListAction : " + e);
				}
				
			}
			
			else if (RequestURI.equals("/BoardDetailAction.bo")) {
		         
		         action = new BoardDetailAction();

		         try {
		            forward = action.execute(request, response);
		         } catch (Exception e) {
		        	 System.out.println("Controller error- BoardDetailAction : " + e);		         }
		         
		      }
		
			
			//여기부터 나
			else if(RequestURI.equals("/BoardModifyView.bo")) {
				action = new BoardModifyView();
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					System.out.println("Controller error- BoardModifyView : " + e);
				}
				
			}
			
			
			else if(RequestURI.equals("/BoardModifyAction.bo")) {
				action = new BoardModifyAction();
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					System.out.println("Controller error - BoardModifyAction");
				}
				
			}
			
			else if (RequestURI.equals("/BoardDeleteAction.bo")) {
				action = new BoardDeleteAction();
				
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					System.out.println("Controller error - BoardDeleteAction");
				}
			}
			else if (RequestURI.equals("/BoardGoodAction.bo")) {
				
				System.out.println("굿액션 컨트롤러 넘어옴");
				action = new BoardGoodAction();
				
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					System.out.println("Controller error - BoardGoodAction");
				}
			}
			
			
			else if (RequestURI.equals("/BoardMyListAction.bo")) {
	            action = new BoardMyListAction();
	            
	            try {
	               forward = action.execute(request, response);
	               
	            } catch (Exception e) {
	               System.out.println("Controller error - BoardMyListAction");

	            }
	            
	         }
			
			
			
			if (forward!=null) {
				
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());				
				}
				else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				}
			}
}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

}
