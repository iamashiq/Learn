import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Allocation;

import javax.servlet.http.HttpSession;

public class AllocateAction extends Action {
	


	Map<Integer,String> teachers;
	Map<Integer,String> courses;
	List<Allocation> allocations;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		teachers = Db.getInstance().fetchTeachers();
		courses = Db.getInstance().fetchCourses();
		allocations = Db.getInstance().fetachAllocations(-1);
		request.setAttribute("courses", courses);
		request.setAttribute("teachers", teachers);
		request.setAttribute("allocations", allocations);
		
		for(Allocation a : allocations)
		{
			System.out.println(a.className()+" "+a.subjectName());
		}

		return mapping.findForward("load");

	}
	
	
}
