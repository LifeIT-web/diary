package d.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import d.member.db.MemberBean;
import d.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		MemberDAO memberdao = new MemberDAO();

		int result = 0;
		String id = request.getParameter("M_ID");
		String pw = request.getParameter("M_PW");
		
		result = memberdao.memberUserCheck(id, pw);
		
		if (result == -1) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('아이디와 비밀번호가 일치하지 않습니다.');");
			out.print("location.href='/MemberLoginView.me';");
			out.print("</script>");
			out.close();
			
			return null;
		}
		else if (result == 0) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('아이디가 존재하지 않습니다.');");
			out.print("location.href='/MemberLoginView.me';");	// jsp 파일에 confirm 창으로 회원가입 하시겠습니까? 구현
			out.print("</script>");
			out.close();
			return null;
		}

		session.setAttribute("id", id);
		forward.setRedirect(true);
		forward.setPath("/BoardMainAction.bo");
		return forward;
		
		
	}

}
