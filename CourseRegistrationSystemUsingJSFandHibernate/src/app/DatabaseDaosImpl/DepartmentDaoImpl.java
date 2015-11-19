package app.DatabaseDaosImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.DepartmentDao;
import app.Entities.Department;

public class DepartmentDaoImpl implements DepartmentDao {

	private SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	Session session=sessionFactory.openSession();
	
	@Override
	public Integer addDepartment(Department department) {
		
		session.beginTransaction();
		Integer status=(Integer)session.save(department);
		session.getTransaction().commit();
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getDepartments() {
		List<Department> departmentList=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from Department");
		departmentList=query.list();
		session.getTransaction().commit();
		return departmentList;
	}

	@Override
	public void deleteDepartment(Department department) {
		session.beginTransaction();
		session.delete(department);
		session.getTransaction().commit();
	}

	@Override
	public void updateDepartment(Department department) {
		session.beginTransaction();
		session.update(department);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDepartmentsName() {
		List<Department> departmentList=new ArrayList<>();
		List<String> departmentNames=new ArrayList<>();
		session.beginTransaction();
		Query query=session.createQuery("from Department");
		departmentList=query.list();
		session.getTransaction().commit();
		
		for(Department department:departmentList){
			String departmentName=department.getDepartmentName();
			departmentNames.add(departmentName);
		}
		
		return departmentNames;
	}

	@Override
	public Department getDepartment(String departName) {
		Department department=new Department();
		session.beginTransaction();
		Query query=session.createQuery("from Department Where departmentName=:depName");
		query.setString("depName", departName);
		department=(Department) query.uniqueResult();		
		return department;
	}

}
