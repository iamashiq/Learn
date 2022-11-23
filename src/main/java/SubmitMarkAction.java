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
import beans.Status;
import beans.Student;
import beans.Teacher;
import beans.TeacherMark;


public class SubmitMarkAction extends Action {
	
	
	Integer courseId,studentId,score;
	

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		studentId = Integer.parseInt( request.getParameter("studentId") );
		courseId = Integer.parseInt( request.getParameter("courseId") );
		score = Integer.parseInt( request.getParameter("score") );
		
		System.out.println(courseId + " "+studentId + " "+score);
		
		Status validationStatus;
		if(! ( validationStatus = ServerValidation.getInstance().vaildateInt(score+"")).status())
		{
			request.setAttribute("error_message", validationStatus.message());
			return mapping.findForward("failed");
			
		}
		
		
		Status result = Db.getInstance().insertMark(new TeacherMark(studentId, courseId, score));

		if (result.status()) {
			System.out.println("Success Print");
			return mapping.findForward("success");
		}
		else
		{
			System.out.println("Error Print "+result.message());
			request.setAttribute("error_message", result.message());
			return mapping.findForward("failed");
			
		}


	}
	
	
	
	
}
