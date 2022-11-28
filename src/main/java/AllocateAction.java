import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Allocation;
import beans.TeacherAllocation;

import javax.servlet.http.HttpSession;

public class AllocateAction extends Action {
	


	Map<String,String> teachers;
	List<Allocation> allocations;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		teachers = Db.getInstance().fetchTeachersWithDepartmentId();
		allocations = Db.getInstance().fetachAllAllocations();
		request.setAttribute("teachers", teachers);
		request.setAttribute("allocations", allocations);

		return mapping.findForward("load");

	}
	
	
}
