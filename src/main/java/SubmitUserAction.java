import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.*;

public class SubmitUserAction extends Action {

	String username, password, fullname, email, phone,dob,gender,joindate,qualification = "";
	Integer roleId,loginId,classId,departmentId = 0;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		roleId = Integer.parseInt(request.getParameter("role"));

		username = request.getParameter("username");
		password = request.getParameter("password");
		fullname = request.getParameter("fullname");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		

		gender = request.getParameter("gender");

		classId = Integer.parseInt(request.getParameter("classId"));
		dob = request.getParameter("dob");
		
		departmentId = Integer.parseInt(request.getParameter("departmentId"));
		joindate = request.getParameter("joindate");
		qualification = request.getParameter("qualification");
		

		System.out.println(roleId+" "+username+" "+password+" "+fullname+" "+email+" "+phone);
		
		
		//Add Login
		
		loginId = Db.getInstance().insertLogin(new Login(-12,username,password,roleId+""));
		if(loginId>-1)
		{
			//Add Admin
			if(roleId == 3)
			{
				System.out.println("User added Id = "+loginId);
				if(Db.getInstance().insertAdmin(new Admin(loginId,fullname,email,phone)))
				{
					System.out.println("Success Admin Print");
					return mapping.findForward("success");
				}
			}
			//Add Student
			else if(roleId == 1)
			{
				System.out.println("User added for student Id = "+loginId);
				if(Db.getInstance().insertStudent(new Student(loginId,classId,fullname,email,phone,dob,gender,"","")))
				{
					System.out.println("Success Student Print");
					return mapping.findForward("success");
				}
				
			}
			//Add Teacher
			else if(roleId == 2)
			{
				System.out.println("User added for teahcer Id = "+loginId);
				if(Db.getInstance().insertTeacher(new Teacher(loginId,departmentId,fullname,email,phone,joindate,gender,qualification,"")))
				{
					System.out.println("Success Teacher Print");
					return mapping.findForward("success");
				}
				
			}
			
		}
		
	

		return mapping.findForward("failed");

	}

}
