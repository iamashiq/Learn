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
		
		if(username == null || password == null)
		{
			return mapping.findForward("failed");	
		}
		
		password = Db.getInstance().getMD5Hash(password);
		
		
		Login login;
		
		if( (login = Db.getInstance().fetchLogin(username, password)) != null)
		{
			System.out.println("Logged in");

			HttpSession session = request.getSession();
			session.setAttribute("id", login.id());
			session.setAttribute("role", login.roleId());
			
			System.out.println("Role is "+session.getAttribute("role"));
			
			if((Integer)session.getAttribute("role") == 1)
			{
				return mapping.findForward("student");
			}
			else if((Integer)session.getAttribute("role") == 2)
			{
				return mapping.findForward("teacher");
			}
			else if((Integer)session.getAttribute("role") == 3)
			{
				return mapping.findForward("admin");
			}
			else
			{
				System.out.println("Login failed! Role error");
				return mapping.findForward("failed");
				
			}
		}
		else
		{
			System.out.println("Login failed! No such user");
			return mapping.findForward("failed");
		}

	}
	
	
}
