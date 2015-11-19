package app.Teacher.Controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.AuthenticationDao;
import app.DatabaseDaos.DepartmentDao;
import app.DatabaseDaos.TeacherDao;
import app.DatabaseDaosImpl.AuthenticationDaoImpl;
import app.DatabaseDaosImpl.DepartmentDaoImpl;
import app.DatabaseDaosImpl.TeacherDaoImpl;
import app.Entities.Authenticate;
import app.Entities.Department;
import app.Entities.Teacher;
import app.ManagedBeans.TeacherBean;

@ManagedBean(name="teacherRegisterController")
@RequestScoped
public class TeacherRegisterController {
	
	private TeacherDao teacherDao=new TeacherDaoImpl();

	@ManagedProperty(value = "#{teacherBean}")
	private TeacherBean teacherBean;
	private Teacher teacher;
	private Department department=new Department();
	private DepartmentDao departmentDao=new DepartmentDaoImpl();
	private List<String> departmentNames;
	private Authenticate authenticate;
	private AuthenticationDao authDao=new AuthenticationDaoImpl();
	
	public TeacherBean getTeacherBean() {
		return teacherBean;
	}

	public void setTeacherBean(TeacherBean teacherBean) {
		this.teacherBean = teacherBean;
	}

	public List<String> getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(List<String> departmentNames) {
		this.departmentNames = departmentNames;
	}

	@PostConstruct
	public void init(){
		teacher=new Teacher();
		authenticate=new Authenticate();
		departmentNames=departmentDao.getDepartmentsName();
	}
	
	public String saveTeacher(){
			teacher.setTeacherName(teacherBean.getTeacherName());
			teacher.setUserName(teacherBean.getUserName());
			department=departmentDao.getDepartment(teacherBean.getDepartmentName());
			teacher.setDepartment(department);
			
			authenticate.setUserName(teacherBean.getUserName());
			authenticate.setPassword(teacherBean.getPassword());
			authenticate.setRole("teacher");
			
			Integer status=authDao.addUser(authenticate);
			
			if(status != null && status >0){
				status=teacherDao.registerTeacher(teacher);
				if(status != null && status >0){
					String message = "Teacher Registered SuccessFully";
				    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				    return "pretty:login";
				}
			}else{
				String message = "UserName already Present";
			    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				return "";
			}
			
		return "";
	}
	
}
