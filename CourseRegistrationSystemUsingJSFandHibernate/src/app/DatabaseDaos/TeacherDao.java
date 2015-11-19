package app.DatabaseDaos;

import java.util.List;

import app.Entities.Teacher;
import app.ManagedBeans.ShowTeacherAssignCoursesBean;

public interface TeacherDao {
	public Integer registerTeacher(Teacher teacher);
	public List<String> teacherNames();
	public Teacher getTeacher(String teacherName);
	public List<ShowTeacherAssignCoursesBean> getTeacherAssignedCourses(int teacherId);
	public int deleteAssignedTeacherCourse(String courseName, int teacherId);
}
