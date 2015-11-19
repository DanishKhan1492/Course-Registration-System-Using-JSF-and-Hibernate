package app.Controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="adminRequestForwardController")
@RequestScoped
public class AdminRequestForwardController {
	
	public String forwardAdminFormRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:adminForm";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardAddCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:courses";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardShowCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:showCourses";
		}else{
			return "pretty:login";
		}
		
	}
	
	
	public String forwardAddSemesterCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:addSemesterCourse";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardShowSemesterCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:showSemesterCourse";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardAddDepartmentCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:addDepartment";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardShowDepartmentsCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:showDepartment";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardAssignTeacherCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:assignCoursesToTeacher";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String ShowAssignTeacherCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("admin")){
			return "pretty:showAssignCoursesToTeacher";
		}else{
			return "pretty:login";
		}
		
	}

	
	/*Student Link Navigations*/

	
	
	public String forwardStudentFormRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("student")){
			return "pretty:studentMenu";
		}else{
			return "pretty:login";
		}
		
	}
	
	
	public String forwardSelectCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("student")){
			return "pretty:selectCourses";
		}else{
			return "pretty:login";
		}
		
	}
	
	public String forwardShowSelectedCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("student")){
			return "pretty:showStudentCourses";
		}else{
			return "pretty:login";
		}
		
	}
	
	/*Teacher Link Navigations*/
	
	public String forwardTeacherAssignedCourseRequest(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session=request.getSession();
		
		if(session.getAttribute("role") != null && session.getAttribute("role").equals("teacher")){
			return "pretty:showTeacherCourses";
		}else{
			return "pretty:login";
		}
		
	}
	
	
}
