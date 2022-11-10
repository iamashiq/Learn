import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Student;

public class StudentAction extends Action {

	String id = "";
	Student user;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!(id = request.getParameter("id")).equals("")) {
			

			if ((user = Db.getInstance().fetchStudent(id)) != null) {

				System.out.println(id+" IDx");
				System.out.println("\n " + user.getName() + " " + user.getCgpa());
				request.setAttribute("user", user);
			}
		}

		return mapping.findForward("load");

	}

}
