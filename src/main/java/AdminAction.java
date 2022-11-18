import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Admin;
import beans.Student;
import beans.Teacher;


public class AdminAction extends Action {
	

	Admin admin;
	String adminId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		adminId =  request.getSession().getAttribute("id").toString();
		
		if((admin = Db.getInstance().fetchAdmin(Integer.parseInt(adminId))) != null)
		{

			request.setAttribute("admin", admin);
		}

		return mapping.findForward("load");

	}
	
}
