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
		String phone = request.getParameter("phone");
		Integer age = Integer.parseInt( request.getParameter("age"));
		//Integer gender =Integer.parseInt( request.getParameter("gender"));
		Integer gender = 0;
		if(request.getParameter("gender").equals("female"))
		{
			gender = 1;
		}
		else if(request.getParameter("gender").equals("male"))
		{
			gender = 2;
		}
		else
		{
			gender = 3;
			
		}
		Float cgpa = Float.parseFloat(request.getParameter("cgpa"));

		System.out.println("Insert in action");
		System.out.println(username+" ,"+password+" ,"+fullname+" ,"+phone+" ,"+age+" ,"+gender+" ,"+cgpa+" ,");

		boolean success = Db.getInstance().insertRecord(
				new Login(-1,username,password,"admin")
				, new Student(-1,fullname,phone,age,gender,cgpa));
		
		if(success)
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
