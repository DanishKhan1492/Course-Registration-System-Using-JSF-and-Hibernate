package app.Controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.DepartmentDao;
import app.DatabaseDaosImpl.DepartmentDaoImpl;
import app.Entities.Department;

@ManagedBean(name="showDepartmentController")
@ViewScoped
public class ShowDepartmentController {
	private DepartmentDao departmentDao=new DepartmentDaoImpl();
	private List<Department> departmentList;
	private Department department;
	
	@PostConstruct
	public void init(){
		departmentList=departmentDao.getDepartments();
	}
	
	
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String modify(Department department){
		this.department=department;
		return "";
	}
	
	public String updateDepartment(){
		
		departmentDao.updateDepartment(department);
		department=null;
		FacesContext context = FacesContext.getCurrentInstance();
		String message = "Updated SuccessFully";
	    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
	    init();
		return "";
	}
	
	public String deleteDepartment(Department department){
		departmentDao.deleteDepartment(department);
		FacesContext context = FacesContext.getCurrentInstance();
		String message = "Deleted SuccessFully";
	    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
	    init();
		return "";
	}
}
