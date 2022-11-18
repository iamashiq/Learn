import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;

public class AddCourseAction extends Action {
	


	Map<Integer,String> classes;
	Map<Integer,String> subjects;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		classes = Db.getInstance().fetchClasses();
		subjects = Db.getInstance().fetchSubjects();
		request.setAttribute("classes", classes);
		request.setAttribute("subjects", subjects);

		return mapping.findForward("load");

	}
	
	
}