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



public class FilteredSubjects extends Action {
	

	Map<Integer,String> subjects;
	String classId;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				
		classId  = request.getParameter("classId");
		String jsonString="";
		
		if( (subjects = Db.getInstance().fetchFilteredSubjects(Integer.parseInt(classId))).size() > 0 )
		{
			jsonString = new Gson().toJson(subjects);
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
