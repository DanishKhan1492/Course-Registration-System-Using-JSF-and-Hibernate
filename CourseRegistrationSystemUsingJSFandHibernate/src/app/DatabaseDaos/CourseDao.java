package app.DatabaseDaos;

import java.util.List;

import app.Entities.Course;

public interface CourseDao {
	public Integer add(Course course);
	public List<Course> getCourse();
	public void deleteCourse(Course course);
	public void updateCourse(Course course);
	public List<String> getCoursesName();
}
