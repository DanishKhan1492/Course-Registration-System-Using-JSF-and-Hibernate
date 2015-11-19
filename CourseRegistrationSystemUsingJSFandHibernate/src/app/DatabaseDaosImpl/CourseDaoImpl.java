package app.DatabaseDaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.CourseDao;
import app.Entities.Course;

public class CourseDaoImpl implements CourseDao{

	private SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	private Session session=sessionFactory.openSession();
	
	@Override
	public Integer add(Course course) {
		
		session.beginTransaction();
		Integer status = (Integer) session.save(course);
		session.getTransaction().commit();
		
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCourse() {
		List<Course> getCourses=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from Course");
		getCourses=query.list();
		return getCourses;
	}

	@Override
	public void deleteCourse(Course course) {
		session.beginTransaction();
		course=(Course) session.load(Course.class, course.getCourseId());
		session.delete(course);
		session.getTransaction().commit();
	}

	@Override
	public void updateCourse(Course course) {
		session.beginTransaction();
		session.update(course);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCoursesName() {
		List<Course> getCourses=new ArrayList<>();
		List<String> coursesNames=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from Course");
		getCourses=query.list();
		
		for(Course course:getCourses){
			String courseName=course.getCourseName();
			coursesNames.add(courseName);
		}
		
		return coursesNames;
	}

}
