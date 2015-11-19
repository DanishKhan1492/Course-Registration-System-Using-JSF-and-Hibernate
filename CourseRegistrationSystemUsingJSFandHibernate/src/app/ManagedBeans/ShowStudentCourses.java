package app.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="showStudentCourses")
@ViewScoped
public class ShowStudentCourses {
	
	int sSc_Id;
	String courseName;
	String courseCode;
	String creditHours;
	
	public int getsSc_Id() {
		return sSc_Id;
	}
	public void setsSc_Id(int sSc_Id) {
		this.sSc_Id = sSc_Id;
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
