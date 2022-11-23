import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.*;

public class SubmitAllocationAction extends Action {

	Integer teacherId, courseId = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		teacherId = Integer.parseInt(request.getParameter("teacherId"));

		courseId = Integer.parseInt(request.getParameter("courseId"));

		System.out.println(teacherId + " " + courseId );

		Status result = Db.getInstance().insertAllocation(teacherId, courseId);

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
