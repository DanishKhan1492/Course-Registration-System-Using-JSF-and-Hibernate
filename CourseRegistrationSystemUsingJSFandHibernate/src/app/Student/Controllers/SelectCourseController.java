package app.Student.Controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import app.DatabaseDaos.SemesterCourseDao;
import app.DatabaseDaos.StudentDao;
import app.DatabaseDaosImpl.SemesterCourseDaoImpl;
import app.DatabaseDaosImpl.StudentDaoImpl;
import app.Entities.Student;
import app.Entities.StudentSelectedCourse;

@ManagedBean(name="selectCourseController")
@ViewScoped
public class SelectCourseController {

	private SemesterCourseDao semCourseDao=new SemesterCourseDaoImpl();
	private StudentDao studentDao=new StudentDaoImpl();
	private Student student;
	private List<String> semester;
	private String semesterSelect;	
	private List<String> selectedCourses;
	private List<String> courseList;
	
	
	
	
	public List<String> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}
	public List<String> getSemester() {
		return semester;
	}
	public void setSemester(List<String> semester) {
		this.semester = semester;
	}
	public String getSemesterSelect() {
		return semesterSelect;
	}
	public void setSemesterSelect(String semesterSelect) {
		this.semesterSelect = semesterSelect;
	}
	public List<String> getSelectedCourses() {
		return selectedCourses;
	}
	public void setSelectedCourses(List<String> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}
	@PostConstruct
	public void init(){
		semester=new ArrayList<>();
		semester.add("Select Semester");
		semester.add("1st");
		semester.add("2nd");
		semester.add("3rd");
		semester.add("4th");
		semester.add("5th");
		semester.add("6th");
		semester.add("7th");
		semester.add("8th");
		
	}
	
	public void populateCourses(){
		courseList=semCourseDao.getSpecificSemesterCourses(semesterSelect);
	}
	
	public String addSelectedCourses(){
		Integer status=null;
			String userName=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
			student=studentDao.getStudent(userName);	
			
			Set<StudentSelectedCourse> studentCourseSet=new HashSet<>();
			
			for(String courseName:selectedCourses){
				StudentSelectedCourse studentCourse=new StudentSelectedCourse();
				studentCourse.setCourseName(courseName);
				studentCourse.setStudent(student);
				
				studentCourseSet.add(studentCourse);
			}
			student.setStudentSelectedCourse(studentCourseSet);
			status=studentDao.addStudent(student);
			if(status > 0 && status != null){
				student.setRegistered(true);
				studentDao.updateStudent(student);
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("registered", "Registered");
				String message = "Course Registration SuccessFully Done";
			    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				return "pretty:selectCourses";
			}else{
				return "";
			}
	}
}
