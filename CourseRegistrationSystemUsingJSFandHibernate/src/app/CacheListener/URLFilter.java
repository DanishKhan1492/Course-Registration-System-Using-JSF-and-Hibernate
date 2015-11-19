package app.CacheListener;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class URLFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpSession session=req.getSession(false);
		
		if(session.getAttribute("user") != null && session.getAttribute("role").equals("admin")){
			filterChain.doFilter(request, response);
		}else if(session.getAttribute("user") != null && session.getAttribute("role").equals("student")){
			filterChain.doFilter(request, response);
		}else if(session.getAttribute("user") != null && session.getAttribute("role").equals("teacher")){
			filterChain.doFilter(request, response);
		}else{
			resp.sendRedirect("pretty:login");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
