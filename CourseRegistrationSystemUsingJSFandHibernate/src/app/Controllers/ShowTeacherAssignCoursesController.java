package app.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.TeacherDao;
import app.DatabaseDaosImpl.TeacherDaoImpl;
import app.Entities.Teacher;
import app.ManagedBeans.ShowTeacherAssignCoursesBean;

@ManagedBean(name="showTeacherAssignCoursesController")
@ViewScoped
public class ShowTeacherAssignCoursesController {
	private TeacherDao teacherDao=new TeacherDaoImpl();
	List<ShowTeacherAssignCoursesBean> teacherCoursesList;
	private Teacher teacher=new Teacher();
	private String teacherUserName;
	private List<String> userNamesOfTeachers;
	
	public List<ShowTeacherAssignCoursesBean> getTeacherCoursesList() {
		return teacherCoursesList;
	}
	public void setTeacherCoursesList(
			List<ShowTeacherAssignCoursesBean> teacherCoursesList) {
		this.teacherCoursesList = teacherCoursesList;
	}

	public String getTeacherUserName() {
		return teacherUserName;
	}
	public void setTeacherUserName(String teacherUserName) {
		this.teacherUserName = teacherUserName;
	}

	public List<String> getUserNamesOfTeachers() {
		return userNamesOfTeachers;
	}
	public void setUserNamesOfTeachers(List<String> userNamesOfTeachers) {
		this.userNamesOfTeachers = userNamesOfTeachers;
	}
	
	@PostConstruct
	public void init(){
		userNamesOfTeachers=teacherDao.teacherNames();
		userNamesOfTeachers.add(0, "Select Teacher");
	}
	
	public String showTeacherCourses(){
		teacherCoursesList=new ArrayList<>();
		teacher=teacherDao.getTeacher(teacherUserName);
		teacherCoursesList=teacherDao.getTeacherAssignedCourses(teacher.getTeacherId());
		return null;
	}
	
	public String deleteTeacherCourse(ShowTeacherAssignCoursesBean courseName){
		int status=teacherDao.deleteAssignedTeacherCourse(courseName.getCourseName(),teacher.getTeacherId());
		if(status > 0){
			String message = "Teacher Deleted SuccessFully";
		    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		    return "pretty:showAssignCoursesToTeacher";
		}else{
			return "";
		}
		
	}
	
	
}
