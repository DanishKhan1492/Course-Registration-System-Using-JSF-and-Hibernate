package app.Controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.DatabaseDaos.CourseDao;
import app.DatabaseDaosImpl.CourseDaoImpl;
import app.Entities.Course;

@ManagedBean(name="showCourseController")
@ViewScoped
public class ShowCourseController {
	
	private List<Course> courses;
	private CourseDao courseDao=new CourseDaoImpl();
	private Course course;

	
	@PostConstruct
	public void init(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			courses=courseDao.getCourse();
		}else{
			courses=null;
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	public String modifyCourse(Course course){
		this.course=course;
		return null;
	}
	
	public String updateCourse(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			courseDao.updateCourse(course);
			course=null;
			FacesContext context = FacesContext.getCurrentInstance();
			String message = "Updated SuccessFully";
		    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
		    init();
		    return null;
		}else{
			return "pretty:login";
		}
	}
}
