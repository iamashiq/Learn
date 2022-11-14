import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Login;

import javax.servlet.http.HttpSession;

public class AuthAction extends Action {
	


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Login login;
		
		if( (login = Db.getInstance().fetchLogin(username, password)) != null)
		{
			System.out.println("Logged in");

			HttpSession session = request.getSession();
			session.setAttribute("id", login.getId());
			session.setAttribute("role", login.getRole());
			return mapping.findForward(login.getRole());
		}
		else
		{
			System.out.println("Login failed");
			return mapping.findForward("failed");
		}

	}
	
	
}
