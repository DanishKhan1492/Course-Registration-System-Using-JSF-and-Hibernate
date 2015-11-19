package app.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	private String studentName;
	@Column(unique=true)
	private String userName;
	private String address;
	private String semester;
	private String batch;
	private boolean registered;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	@OneToMany(targetEntity=StudentSelectedCourse.class,cascade=CascadeType.ALL,mappedBy="student")
	private Set<StudentSelectedCourse> studentSelectedCourse;
	
	public Set<StudentSelectedCourse> getStudentSelectedCourse() {
		return studentSelectedCourse;
	}
	public void setStudentSelectedCourse(
			Set<StudentSelectedCourse> studentSelectedCourse) {
		this.studentSelectedCourse = studentSelectedCourse;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public boolean isRegistered() {
		return registered;
	}
	public void setRegistered(boolean registered) {
		this.registered = registered;
	}
	
	
}
