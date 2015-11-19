package app.ManagedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import app.DatabaseDaos.CourseDao;
import app.DatabaseDaosImpl.CourseDaoImpl;
import app.Entities.Course;

@ManagedBean(name="semesterCourseBean")
@ViewScoped
public class SemesterCourseBean {
	
	private CourseDao courseDao=new CourseDaoImpl();
	
	private int semCourseId;
	private String semCourseName;
	private String semesterNumber;
	private List<String> allCourses;
	
	@PostConstruct
	public void init(){
		allCourses=new ArrayList<>();
		List<Course> getCourses=courseDao.getCourse();
		for(Course course:getCourses){
			String courseName=course.getCourseName();
			allCourses.add(courseName);
		}
	}

	public List<String> getAllCourses() {
		return allCourses;
	}


	public int getSemCourseId() {
		return semCourseId;
	}
	public void setSemCourseId(int semCourseId) {
		this.semCourseId = semCourseId;
	}
	public String getSemCourseName() {
		return semCourseName;
	}
	public void setSemCourseName(String semCourseName) {
		this.semCourseName = semCourseName;
	}
	public String getSemesterNumber() {
		return semesterNumber;
	}
	public void setSemesterNumber(String semesterNumber) {
		this.semesterNumber = semesterNumber;
	}
	
	
}
