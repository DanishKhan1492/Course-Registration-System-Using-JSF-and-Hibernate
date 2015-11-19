package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.DatabaseDaos.CourseDao;
import app.DatabaseDaosImpl.CourseDaoImpl;
import app.Entities.Course;

@ManagedBean(name="deleteController")
@RequestScoped
public class DeleteController {
	private Course course;
	private CourseDao courseDao=new CourseDaoImpl();
	private FacesContext context = FacesContext.getCurrentInstance();
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String deleteCourse(Course course){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			courseDao.deleteCourse(course);
			String message = "Delete SuccessFully";
		    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
			return "pretty:showCourses";
		}else{
			return "pretty:login";
		}
		
		
	} 
	
}
