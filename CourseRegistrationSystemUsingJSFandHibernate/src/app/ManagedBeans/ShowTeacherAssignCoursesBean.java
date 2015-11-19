package app.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="ShowTeacherAssignCoursesBean")
@ViewScoped
public class ShowTeacherAssignCoursesBean {
	
	private int id;
	private String teacherName;
	private String courseName;
	private String creditHours;
	private String semester;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
}
