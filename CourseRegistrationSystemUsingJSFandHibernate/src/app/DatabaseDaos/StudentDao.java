package app.DatabaseDaos;

import java.util.List;

import app.Entities.Student;
import app.ManagedBeans.ShowStudentCourses;

public interface StudentDao {
	public Integer addStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
	public Student getStudent(String userName);
	public List<ShowStudentCourses> reteriveSelectedCourse(int id);
}
