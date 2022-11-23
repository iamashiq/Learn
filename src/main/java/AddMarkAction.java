import java.util.ArrayList;
import java.util.List;

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


public class AddMarkAction extends Action {
	

	List<TeacherAllocation> allocations;
	String teacherId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		teacherId =  request.getSession().getAttribute("id").toString();
		
		if((allocations = Db.getInstance().fetachAllocations(Integer.parseInt(teacherId))) != null)
		{

			request.setAttribute("allocations", allocations);
			
		}

		return mapping.findForward("load");

	}
	
	
	
	
}
