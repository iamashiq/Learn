import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Student;


public class AdminAction extends Action {
	

	List<Student> allStudents;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		if((allStudents = Db.getInstance().fetchStudents(true,null)) != null)
		{
			for(Student u : allStudents)
			{
				System.out.println("\n "+u.getName()+" "+u.getCgpa());
			}
			request.setAttribute("allStudents", allStudents);
		}

		return mapping.findForward("load");

	}
	
	
	
	
}
