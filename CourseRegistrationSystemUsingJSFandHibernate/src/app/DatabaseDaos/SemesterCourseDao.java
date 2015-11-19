package app.DatabaseDaos;

import java.util.List;

import app.Entities.SemesterCourse;

public interface SemesterCourseDao {
	public Integer addSemesterCourse(SemesterCourse semCourse);
	public void updateCourse(SemesterCourse semCourse);
	public List<SemesterCourse> getSemesterCourses();
	public void deleteCourse(SemesterCourse semesterCourse);
	public List<String> getSpecificSemesterCourses(String semesterNumber);
}
