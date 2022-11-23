

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class AdminAuthFilter extends HttpFilter implements Filter {
       
    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		System.out.println("Admin Filter working");
		
		if(request.getSession().getAttribute("role") != null &&  (Integer) request.getSession().getAttribute("role") == 3)
		{
			System.out.println("Filter : pass");
			chain.doFilter(request, response);
		}
		else
		{
			System.out.println("Filter : fail");
			response.sendRedirect(request.getContextPath()+"/logout.do");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
