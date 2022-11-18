import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;

public class AddEntityAction extends Action {
	

	Map<Integer,String> departments;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		departments = Db.getInstance().fetchDepartments();
		request.setAttribute("departments", departments);
		
		return mapping.findForward("load");

	}
	
	
}
