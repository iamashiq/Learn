import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Admin;
import beans.Student;
import beans.Teacher;


public class AdminAction extends Action {
	

	Admin admin;
	String adminId;
	
	Map<Integer,String> teachers,students,admins;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		adminId =  request.getSession().getAttribute("id").toString();
		
		if((admin = Db.getInstance().fetchAdmin(Integer.parseInt(adminId))) != null)
		{
			request.setAttribute("admin", admin);
		}

		students = Db.getInstance().fetchStudents();
		teachers = Db.getInstance().fetchTeachers();
		admins = Db.getInstance().fetchAdmins();
		request.setAttribute("students", students);
		request.setAttribute("teachers", teachers);
		request.setAttribute("admins", admins);
		

		return mapping.findForward("load");

	}
	
}
