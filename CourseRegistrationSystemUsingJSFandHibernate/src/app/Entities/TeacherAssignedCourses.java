package app.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeacherAssignedCourses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int assigndCourseId;
	private String courseName;
	private String semester;
	
	@ManyToOne
	@JoinColumn(name="teacherId")
	private Teacher teacher;

	
	public int getAssigndCourseId() {
		return assigndCourseId;
	}

	public void setAssigndCourseId(int assigndCourseId) {
		this.assigndCourseId = assigndCourseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	
	
	
	
}
