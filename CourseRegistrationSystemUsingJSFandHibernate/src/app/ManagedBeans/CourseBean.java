package app.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="courseBean")
@ViewScoped
public class CourseBean {
	private int courseId;
	private String courseName;
	private String courseCode;
	private String creditHours;
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}
}
