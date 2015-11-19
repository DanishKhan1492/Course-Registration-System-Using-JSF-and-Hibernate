package app.Controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import app.DatabaseDaos.SemesterCourseDao;
import app.DatabaseDaosImpl.SemesterCourseDaoImpl;
import app.Entities.SemesterCourse;

@ManagedBean(name="showSemesterCourseController")
@ViewScoped
public class ShowSemesterCourseController {
	private List<SemesterCourse> semCourseList;
	private List<String> semCourseName;
	private SemesterCourseDao semCourseDao=new SemesterCourseDaoImpl();
	private SemesterCourse semCourse;
	
	/*Pre Work*/
	
	@PostConstruct
	public void init(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		semCourseName=new ArrayList<>();
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			semCourseList=semCourseDao.getSemesterCourses();
			for(SemesterCourse semesterCourse:semCourseList){
				semCourseName.add(semesterCourse.getSemCourseName());
			}
			
		}else{
			semCourseList=null;
		}
	}
	
	/*Getters And Setters*/
	
	public List<SemesterCourse> getSemCourseList() {
		return semCourseList;
	}
	public List<String> getSemCourseName() {
		return semCourseName;
	}

	public void setSemCourseList(List<SemesterCourse> semCourseList) {
		this.semCourseList = semCourseList;
	}
	public SemesterCourse getSemCourse() {
		return semCourse;
	}
	public void setSemCourse(SemesterCourse semCourse) {
		this.semCourse = semCourse;
	}
	
	public String modify(SemesterCourse semCourse){
		this.semCourse=semCourse;
		return null;
	}
	
	public String updateSemesterCourse(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			semCourseDao.updateCourse(semCourse);
			semCourse=null;
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
