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



public class FilteredStudents extends Action {
	

	Map<Integer,String> students;
	String classId,courseId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		classId  = request.getParameter("classId");
		courseId  = request.getParameter("courseId");
		String jsonString="";
		
		if((students = Db.getInstance().fetchFilteredStudents(Integer.parseInt(classId),Integer.parseInt(courseId))).size() > 0)
		{
//			for(Map.Entry<Integer, String> entry : students.entrySet())
//			{
//				System.out.println(entry.getKey()+" - "+entry.getValue());
//			}

			jsonString = new Gson().toJson(students);
			System.out.println("JSON in filteredStudent : "+ jsonString);
		}
		
		
		
	
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonString);
		out.flush();
		
		return null;

	}
	
	
	
	
}
