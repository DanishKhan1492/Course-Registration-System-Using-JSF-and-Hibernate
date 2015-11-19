package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.DatabaseDaos.CourseDao;
import app.DatabaseDaosImpl.CourseDaoImpl;
import app.Entities.Course;
import app.ManagedBeans.CourseBean;

@ManagedBean(name="courseController")
@RequestScoped
public class CourseController {
	
	private CourseDao courseDao=new CourseDaoImpl();
	private Course course=new Course();
	private FacesContext context = FacesContext.getCurrentInstance();
	
	@ManagedProperty(value="#{courseBean}")
	private CourseBean courseBean;

	public void setCourseBean(CourseBean courseBean) {
		this.courseBean = courseBean;
	}
	
	public String addCourse(){
		 HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		 HttpSession session=request.getSession();
		 
		 if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			 course.setCourseName(courseBean.getCourseName());
				course.setCourseCode(courseBean.getCourseCode());
				course.setCreditHours(courseBean.getCreditHours());
				Integer status=courseDao.add(course);
				if(status != null && status>0){
					String message = "Course Inserted SuccessFully ";
				    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				    return "";
				}else{
					String message = "Course is Already Present";
				    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				    return "";
				}
		 }else{
			 return "pretty:login";
		 }

	}
}
