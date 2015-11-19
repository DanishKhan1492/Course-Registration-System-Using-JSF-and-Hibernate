package app.DatabaseDaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.TeacherDao;
import app.Entities.Teacher;
import app.ManagedBeans.ShowTeacherAssignCoursesBean;

public class TeacherDaoImpl implements TeacherDao{

	SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	Session session=sessionFactory.openSession();
	
	@Override
	public Integer registerTeacher(Teacher teacher) {
		session.beginTransaction();
		Integer status=(Integer) session.save(teacher);
		session.getTransaction().commit();
		
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> teacherNames() {
		List<Teacher> teachers=new ArrayList<>();
		List<String> teacherNames=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from Teacher");
		teachers=query.list();
		session.getTransaction().commit();
		
		for(Teacher teacher:teachers){
			String teacherName=teacher.getUserName();
			teacherNames.add(teacherName);
		}
		
		return teacherNames;
	}

	@Override
	public Teacher getTeacher(String teacherName) {
		Teacher teacher=new Teacher();
		session.beginTransaction();
		Query query=session.createQuery("from Teacher WHERE userName=:name");
		query.setString("name", teacherName);
		teacher=(Teacher) query.uniqueResult();
		return teacher;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShowTeacherAssignCoursesBean> getTeacherAssignedCourses(int teacherId) {
		List<ShowTeacherAssignCoursesBean> showTeacherCourses=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("select cor.creditHours,tea.teacherName, assitea.courseName, assitea.semester from Course cor,Teacher tea,TeacherAssignedCourses assitea  Where cor.courseName=assitea.courseName and assitea.teacher.teacherId=tea.teacherId and tea.teacherId=:teachId ");
		query.setInteger("teachId",teacherId );
		List<Object[]> listResult=query.list();
		session.getTransaction().commit();
		int count=1;
		for(Object[] bothObj:listResult){
			int id=count;
			String creditHours=(String)bothObj[0];
			String teacherName=(String)bothObj[1];
			String courseName=(String)bothObj[2];
			String semester=(String)bothObj[3];
			
			ShowTeacherAssignCoursesBean showTeacherAssignedCourses=new ShowTeacherAssignCoursesBean();
			showTeacherAssignedCourses.setId(id);
			showTeacherAssignedCourses.setCourseName(courseName);
			showTeacherAssignedCourses.setCreditHours(creditHours);
			showTeacherAssignedCourses.setTeacherName(teacherName);
			showTeacherAssignedCourses.setSemester(semester);
			
			showTeacherCourses.add(showTeacherAssignedCourses);
			count++;
		}
		
		return showTeacherCourses;
	}

	@Override
	public int deleteAssignedTeacherCourse(String courseName,int teacherId) {
		session.beginTransaction();
		Query query=session.createQuery("Delete from TeacherAssignedCourses Where courseName=:courseName and teacherId=:teacherId");
		query.setString("courseName",courseName);
		query.setInteger("teacherId", teacherId);
		int status=query.executeUpdate();
		session.getTransaction().commit();
		System.out.println("");
		return status;
	}
	
}
