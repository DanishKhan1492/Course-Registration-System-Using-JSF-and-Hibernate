package app.Student.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.AuthenticationDao;
import app.DatabaseDaos.StudentDao;
import app.DatabaseDaosImpl.AuthenticationDaoImpl;
import app.DatabaseDaosImpl.StudentDaoImpl;
import app.Entities.Authenticate;
import app.Entities.Student;
import app.ManagedBeans.StudentBean;


@ManagedBean(name="studentRegestrationController")
@RequestScoped
public class StudentRegestrationController {
	
	@ManagedProperty(value = "#{studentBean}")
	private StudentBean studentBean;
	
	private Student student;
	private Authenticate auth;
	private StudentDao studentDao=new StudentDaoImpl();
	private AuthenticationDao authDao=new AuthenticationDaoImpl();
	private List<String> semesters;

	@PostConstruct
	public void init(){
		student=new Student();
		auth=new Authenticate();
		semesters=new ArrayList<>();
		semesters.add("1st");
		semesters.add("2nd");
		semesters.add("3rd");
		semesters.add("4th");
		semesters.add("5th");
		semesters.add("6th");
		semesters.add("7th");
		semesters.add("8th");
		
	}

	
	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}



	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}
	
	public String addStudent(){
		student.setStudentName(studentBean.getStudentName());
		student.setSemester(studentBean.getSemester());
		student.setAddress(studentBean.getAddress());
		student.setBatch(studentBean.getBatch());
		student.setUserName(studentBean.getUserName());
		
		
		auth.setUserName(studentBean.getUserName());
		auth.setPassword(studentBean.getPassword());
		auth.setRole("student");
		
		Integer status=authDao.addUser(auth);
		
		if(status != null && status>0){
			status=studentDao.addStudent(student);
			String message = "Student Added SuccessFully";
		    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
		    return "pretty:login";
		}else{
			String message = "User Name Already Present";
		    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
		    return null;
		}
	}
}
