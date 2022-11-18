import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Login;
import beans.Student;


public class InsertAction extends Action {
	


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

		String fullname = request.getParameter("fullname");
		Integer dep_id = Integer.parseInt(request.getParameter("dep_id"));
		String phone = request.getParameter("phone");
		String dob = request.getParameter("dob");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		
		
			
		System.out.println("Insert in action");
		System.out.println(username+" ,"+password+" ,"+fullname+" ,"+dep_id+" ,"+email+" ,"+phone+" ,"+dob+","+gender);

//		boolean success = Db.getInstance().insertRecord(
//				new Login(-1,username,password,"admin")
//				, new Student(-1,dep_id,fullname,email,phone,dob,gender));
		
		if(false)
		{
			System.out.println("Insert success");
			return mapping.findForward("complete");
		}
		else
		{
			System.out.println("Insert fail");
		}
		
		return mapping.findForward("load");

	}
	
	
	
	
}
