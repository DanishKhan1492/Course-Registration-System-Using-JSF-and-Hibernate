package app.Controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.CourseDao;
import app.DatabaseDaos.DepartmentDao;
import app.DatabaseDaos.TeacherDao;
import app.DatabaseDaosImpl.CourseDaoImpl;
import app.DatabaseDaosImpl.DepartmentDaoImpl;
import app.DatabaseDaosImpl.TeacherDaoImpl;
import app.Entities.Teacher;
import app.Entities.TeacherAssignedCourses;

@ManagedBean(name="assignTeacherCourses")
@ViewScoped
public class AssignTeacherCourses {
	
	private TeacherDao teacherDao=new TeacherDaoImpl();
	private DepartmentDao departmentDao=new DepartmentDaoImpl();
	private CourseDao courseDao=new CourseDaoImpl();
	
	private List<String> departmentNames;
	private List<String> teacherNames;
	private List<String> courseList;
	private String departmentName;
	private String teacherName;
	private List<String> assignedCourses;
	private Teacher teacher=new Teacher();
	private String semester;
	private List<String> semesterList;
	
	
	public List<String> getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(List<String> departmentNames) {
		this.departmentNames = departmentNames;
	}

	public List<String> getTeacherNames() {
		return teacherNames;
	}

	public void setTeacherNames(List<String> teacherNames) {
		this.teacherNames = teacherNames;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public List<String> getAssignedCourses() {
		return assignedCourses;
	}

	public void setAssignedCourses(List<String> assignedCourses) {
		this.assignedCourses = assignedCourses;
	}
	
	public TeacherDao getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public CourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public List<String> getSemesterList() {
		return semesterList;
	}

	public void setSemesterList(List<String> semesterList) {
		this.semesterList = semesterList;
	}

	@PostConstruct
	public void init(){
		departmentNames=departmentDao.getDepartmentsName();
		departmentNames.add(0, "Please Select Department");
		
		semesterList=new ArrayList<>();
		semesterList.add("1st");
		semesterList.add("2nd");
		semesterList.add("3rd");
		semesterList.add("4th");
		semesterList.add("5th");
		semesterList.add("6th");
		semesterList.add("7th");
		semesterList.add("8th");
	}
	
	public void populateTeachers(){
		teacherNames=teacherDao.teacherNames();
		teacherNames.add(0, "Select Teacher");
	}
	
	public void populateCourses(){
		courseList=courseDao.getCoursesName();
	}
	
	public String assignCoursesToTeacher(){
		Integer status=null;
		teacher=teacherDao.getTeacher(teacherName);
		Set<TeacherAssignedCourses> teacherAssignedCoursesSet=new HashSet<>();
		
		for(String courseName:assignedCourses){
			TeacherAssignedCourses teacherAssignedCoures=new TeacherAssignedCourses();
			teacherAssignedCoures.setCourseName(courseName);
			teacherAssignedCoures.setSemester(semester);
			teacherAssignedCoures.setTeacher(teacher);
			teacherAssignedCoursesSet.add(teacherAssignedCoures);
		}
		
		teacher.setTeacherAssignedCourses(teacherAssignedCoursesSet);
		status=teacherDao.registerTeacher(teacher);
		
		if(status>0 && status != null){
			String message = "Courses are Assigned to Teacher SuccessFully";
		    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "pretty:assignCoursesToTeacher";
		}
		
		return "";
	}
}
