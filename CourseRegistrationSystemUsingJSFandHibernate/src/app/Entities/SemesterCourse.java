package app.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semestercourses")
public class SemesterCourse implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int semCourseId;
	@Column(unique=true)
	private String semCourseName;
	private String semesterNumber;
	
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
