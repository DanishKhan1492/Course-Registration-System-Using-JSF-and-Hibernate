package app.DatabaseDaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.SemesterCourseDao;
import app.Entities.SemesterCourse;

public class SemesterCourseDaoImpl implements SemesterCourseDao{

	private SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	private Session session=sessionFactory.openSession();
	
	@Override
	public Integer addSemesterCourse(SemesterCourse semCourse) {
		
		session.beginTransaction();
		Integer status=(Integer)session.save(semCourse);
		session.getTransaction().commit();
		return status;
	}

	@Override
	public void updateCourse(SemesterCourse semCourse) {
		session.beginTransaction();
		session.update(semCourse);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SemesterCourse> getSemesterCourses() {
		List<SemesterCourse> semCoursesList=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from SemesterCourse");
		semCoursesList=query.list();
		return semCoursesList;
	}

	@Override
	public void deleteCourse(SemesterCourse semesterCourse) {
		session.beginTransaction();
		session.delete(semesterCourse);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSpecificSemesterCourses(String semesterNumber) {
		List<SemesterCourse> semesterCourses=new ArrayList<>();
		List<String> semesterCourse=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from SemesterCourse WHERE semesterNumber=:semNumber");
		query.setString("semNumber", semesterNumber);
		semesterCourses = query.list();
		session.getTransaction().commit();
		
		for(SemesterCourse semCourse:semesterCourses){
			semesterCourse.add(semCourse.getSemCourseName());
		}
		
			
		return semesterCourse;	
	}

}
