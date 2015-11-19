package app.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teacherId;
	private String teacherName;
	private String userName;
	
	@OneToMany(targetEntity=TeacherAssignedCourses.class,cascade=CascadeType.ALL,mappedBy="teacher")
	private Set<TeacherAssignedCourses> teacherAssignedCourses;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<TeacherAssignedCourses> getTeacherAssignedCourses() {
		return teacherAssignedCourses;
	}
	public void setTeacherAssignedCourses(Set<TeacherAssignedCourses> teacherAssignedCourses) {
		this.teacherAssignedCourses = teacherAssignedCourses;
	}
	
	
}
