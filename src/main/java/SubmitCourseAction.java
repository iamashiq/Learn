import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.*;

public class SubmitCourseAction extends Action {

	Integer classId, subjectId = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		classId = Integer.parseInt(request.getParameter("classId"));
		System.out.println(classId + " " + subjectId );

		subjectId = Integer.parseInt(request.getParameter("subjectId"));

		System.out.println(classId + " " + subjectId );
		
		Status result = Db.getInstance().insertCourse(classId, subjectId);

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
