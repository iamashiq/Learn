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


public class StudentAction extends Action {
	

	Student student;
	List<StudentMark> marks;
	String studentId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		studentId =  request.getSession().getAttribute("id").toString();
		marks= new ArrayList<>();
		
		if((student = Db.getInstance().fetchStudent(studentId)) != null)
		{

			System.out.println(studentId+" ID");
			marks = Db.getInstance().fetchMarksByStudent(Integer.parseInt(studentId));

			request.setAttribute("student", student);
			request.setAttribute("marks", marks);
		}

		return mapping.findForward("load");

	}
	
	
	
	
}
