package app.Teacher.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.TeacherDao;
import app.DatabaseDaosImpl.TeacherDaoImpl;
import app.Entities.Teacher;
import app.ManagedBeans.ShowTeacherAssignCoursesBean;

@ManagedBean(name="showTeacherAssignedCoursesController")
@RequestScoped
public class ShowTeacherAssignedCoursesController {
	private TeacherDao teacherDao=new TeacherDaoImpl();
	List<ShowTeacherAssignCoursesBean> teacherCoursesList;
	private Teacher teacher=new Teacher();
	
	public List<ShowTeacherAssignCoursesBean> getTeacherCoursesList() {
		return teacherCoursesList;
	}
	public void setTeacherCoursesList(
			List<ShowTeacherAssignCoursesBean> teacherCoursesList) {
		this.teacherCoursesList = teacherCoursesList;
	}
	
	@PostConstruct
	public void init(){
		teacherCoursesList=new ArrayList<>();
		String userName=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		teacher=teacherDao.getTeacher(userName);
		teacherCoursesList=teacherDao.getTeacherAssignedCourses(teacher.getTeacherId());
	}
	
}
