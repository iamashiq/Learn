import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.StudentMark;
import beans.Student;

import com.google.gson.Gson;



public class CoursesByDepartmentId extends Action {
	

	Map<Integer,String> courses;
	String departmentId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		departmentId  = request.getParameter("departmentId");
		String jsonString="";
		
		if( (courses = Db.getInstance().fetchCoursesByDepartmentId(Integer.parseInt(departmentId))).size() > 0 )
		{
//			for(Map.Entry<Integer, String> entry : students.entrySet())
//			{
//				System.out.println(entry.getKey()+" - "+entry.getValue());
//			}

			jsonString = new Gson().toJson(courses);
			System.out.println("JSON : "+ jsonString);
		}
		
		
		
	
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonString);
		out.flush();
		
		return null;

	}
	
	
	
	
}
