package app.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class StudentSelectedCourse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SSC_Id")
	private Integer sSC_Id;
	private String courseName;
	
	@ManyToOne
	@JoinColumn(name="studentId")
	private Student student;

	public Integer getsSC_Id() {
		return sSC_Id;
	}

	public void setsSC_Id(Integer sSC_Id) {
		this.sSC_Id = sSC_Id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
