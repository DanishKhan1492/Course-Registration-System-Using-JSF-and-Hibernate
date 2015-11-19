package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.SemesterCourseDao;
import app.DatabaseDaosImpl.SemesterCourseDaoImpl;
import app.Entities.SemesterCourse;
import app.ManagedBeans.SemesterCourseBean;

@ManagedBean(name="addSemesterCourseController")
@RequestScoped
public class AddSemesterCourseController {
	private SemesterCourseDao semCourseDao=new SemesterCourseDaoImpl();
	
	@ManagedProperty(value = "#{semesterCourseBean}")
	private SemesterCourseBean semCourseBean;
	
	private SemesterCourse semCourse=new SemesterCourse();
	
	public void setSemCourseBean(SemesterCourseBean semCourseBean) {
		this.semCourseBean = semCourseBean;
	}



	public String AddSemesterCourse(){
			semCourse.setSemCourseName(semCourseBean.getSemCourseName());
			semCourse.setSemesterNumber(semCourseBean.getSemesterNumber());
			Integer status=semCourseDao.addSemesterCourse(semCourse);
			
			if(status != null && status>0){
				String message = "Semester Course Inserted SuccessFully ";
			    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
			    return "";
			}else{
				String message = "Semester Course is Already Present";
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, message,message));
			    return "";
			}
	}
}
