package app.Student.Controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.StudentDao;
import app.DatabaseDaosImpl.StudentDaoImpl;
import app.Entities.Student;
import app.ManagedBeans.ShowStudentCourses;


@ManagedBean(name="showSelectedCourseController")
@ViewScoped
public class ShowSelectedCourseController {
	
	private StudentDao studentDao=new StudentDaoImpl();
	private List<ShowStudentCourses> stdSelectedCourseList;
	private Student student =new Student();
	
	public List<ShowStudentCourses> getStdSelectedCourseList() {
		return stdSelectedCourseList;
	}

	public void setStdSelectedCourseList(
			List<ShowStudentCourses> stdSelectedCourseList) {
		this.stdSelectedCourseList = stdSelectedCourseList;
	}

	@PostConstruct
	public void init(){
		String userName=(String)(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"));
		int stdId=0;
		student=studentDao.getStudent(userName);
		stdId=student.getStudentId();
		stdSelectedCourseList=studentDao.reteriveSelectedCourse(stdId);	
	}	
}
