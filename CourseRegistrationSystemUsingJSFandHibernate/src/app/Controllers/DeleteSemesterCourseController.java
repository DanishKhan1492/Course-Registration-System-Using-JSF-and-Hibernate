package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import app.DatabaseDaos.SemesterCourseDao;
import app.DatabaseDaosImpl.SemesterCourseDaoImpl;
import app.Entities.SemesterCourse;

@ManagedBean(name="deleteSemesterCourseController")
@RequestScoped
public class DeleteSemesterCourseController {
	private SemesterCourse semesterCourse;
	private SemesterCourseDao semesterCourseDao=new SemesterCourseDaoImpl();
	
	public SemesterCourse getSemesterCourse() {
		return semesterCourse;
	}
	public void setSemesterCourse(SemesterCourse semesterCourse) {
		this.semesterCourse = semesterCourse;
	}
	
	
	public String deleteCourse(SemesterCourse semcourse){
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			semesterCourseDao.deleteCourse(semcourse);
			String message = "Delete SuccessFully";
		    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
			return "pretty:showSemesterCourse";
		}else{
			return "pretty:login";
		}
		
		
	} 
	
}
