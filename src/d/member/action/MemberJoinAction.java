package d.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import d.member.db.MemberBean;
import d.member.db.MemberDAO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		MemberDAO memberdao = new MemberDAO();
		MemberBean member = new MemberBean();
		ActionForward forward = new ActionForward();
		
		member.setM_ID(request.getParameter("M_ID"));
		member.setM_PW(request.getParameter("M_PW"));
		member.setM_NAME(request.getParameter("M_NAME"));
		member.setM_AGE(Integer.parseInt(request.getParameter("M_AGE")));
		member.setM_GENDER(request.getParameter("M_GENDER"));
		member.setM_EMAIL(request.getParameter("M_EMAIL"));
		
		boolean bool = memberdao.memberInsert(member);
		
		if (bool) {
			forward.setRedirect(true);
			forward.setPath("/MemberLoginView.me");
			return forward;
		}
		
		else {
			System.out.println("BoardJoinAction error");
			return null;
		}
	}

}
