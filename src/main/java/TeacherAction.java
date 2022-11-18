import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.StudentMark;
import beans.Student;
import beans.Teacher;


public class TeacherAction extends Action {
	

	Teacher teacher;
	String teacherId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		teacherId =  request.getSession().getAttribute("id").toString();
		
		if((teacher = Db.getInstance().fetchTeacher(Integer.parseInt(teacherId))) != null)
		{

			request.setAttribute("teacher", teacher);
		}

		return mapping.findForward("load");

	}
	
	
	
	
}
