package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import app.DatabaseDaos.DepartmentDao;
import app.DatabaseDaosImpl.DepartmentDaoImpl;
import app.Entities.Department;
import app.ManagedBeans.DepartmentBean;

@ManagedBean(name="departmentController")
@ViewScoped
public class DepartmentController {
	private DepartmentDao departmentDao=new DepartmentDaoImpl();
	@ManagedProperty(value="#{departmentBean}")
	private DepartmentBean departmentBean;
	private Department department;
	
	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}
	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}
	
	
	public String addDepartment(){
			department=new Department();
			department.setDepartmentName(departmentBean.getDepartmentName());
			Integer status=departmentDao.addDepartment(department);
			if(status>0 && status != null){
				String message = "Added SuccessFully";
			    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, message,message));
				return "";
			}else{
				return "";
			}
		
	}
	
}
