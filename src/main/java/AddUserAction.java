import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.TeacherAllocation;
import beans.StudentMark;
import beans.Student;
import beans.Teacher;


public class AddUserAction extends Action {
	

	Map<Integer,String> classes;
	Map<Integer,String> departments;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

		classes = Db.getInstance().fetchClasses();
		departments = Db.getInstance().fetchDepartments();
		request.setAttribute("classes", classes);
		request.setAttribute("departments", departments);
		

		for (Map.Entry<Integer, String> d : departments.entrySet()) {
			System.out.println(d.getKey()+" "+d.getValue());
		}
		
		
		return mapping.findForward("load");

	}
	
	
	
	
}
