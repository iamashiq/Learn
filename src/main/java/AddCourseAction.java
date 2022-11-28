import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpSession;

public class AddCourseAction extends Action {
	


	Map<Integer,String> classes,subjects,courses;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		classes = Db.getInstance().fetchClasseswithDepartments();
		courses = Db.getInstance().fetchCourses();
		
		request.setAttribute("classes", classes);
		request.setAttribute("courses", courses);

		return mapping.findForward("load");

	}
	
	
}
