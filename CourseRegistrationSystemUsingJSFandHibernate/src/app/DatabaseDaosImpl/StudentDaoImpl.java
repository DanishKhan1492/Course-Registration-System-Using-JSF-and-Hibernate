package app.DatabaseDaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.StudentDao;
import app.Entities.Student;
import app.ManagedBeans.ShowStudentCourses;

public class StudentDaoImpl implements StudentDao{

	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private Session session=sessionFactory.openSession();
	
	@Override
	public Integer addStudent(Student student) {
		session.beginTransaction();
		Integer status=(Integer)session.save(student);
		session.getTransaction().commit();
		return status;
	}

	@Override
	public void updateStudent(Student student) {
		session.beginTransaction();
		session.update(student);
		session.getTransaction().commit();
		
	}

	@Override
	public void deleteStudent(Student student) {
		session.beginTransaction();
		session.delete(student);
		session.getTransaction().commit();
		
	}

	@Override
	public Student getStudent(String userName) {
		Student student=new Student();
		session.beginTransaction();
		Query query=session.createQuery("from Student WHERE userName=:name");
		query.setString("name", userName);
		student=(Student) query.uniqueResult();
		return student;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShowStudentCourses> reteriveSelectedCourse(int studentId) {
		List<ShowStudentCourses> stdCourseList=new ArrayList<>();
		
		session.beginTransaction();
		Query query=session.createQuery("select ssc.sSC_Id,ssc.courseName,cou.courseCode,cou.creditHours from StudentSelectedCourse ssc, Course cou Where cou.courseName=ssc.courseName and studentId=:stdId");
		query.setInteger("stdId", studentId);
		List<Object[]> listResult=query.list();
		session.getTransaction().commit();
		
		for(Object[] bothObj:listResult){
			
			int id=(Integer)bothObj[0];
			String courseName=(String)bothObj[1];
			String courseCode=(String)bothObj[2];
			String creditHours=(String)bothObj[3];
			
			ShowStudentCourses showStdCourse=new ShowStudentCourses();			
			showStdCourse.setsSc_Id(id);
			showStdCourse.setCourseName(courseName);
			showStdCourse.setCourseCode(courseCode);
			showStdCourse.setCreditHours(creditHours);
			
			stdCourseList.add(showStdCourse);
		}
		
		return stdCourseList;
	}
	
}
