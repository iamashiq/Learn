import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.*;

public class SubmitEntityAction extends Action {

	String entity,name;
	Integer departmentId, total = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		entity = request.getParameter("entity");
		name = request.getParameter("name");
		
		if(entity.equals("Class"))
		{
			departmentId = Integer.parseInt(request.getParameter("departmentId"));
			System.out.println( "Class gpt  " + entity );
		}
		else if(entity.equals("Subject"))
		{
			total = Integer.parseInt(request.getParameter("total"));
		}

		System.out.println( "Recieved " + entity );

		
		Status result = Db.getInstance().insertEntity(entity, name,departmentId,total);

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
