package app.ManagedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class StudentSelectedCourseManagedBean {

	private Integer sSC_Id;
	private String courseName;


	public Integer getsSC_Id() {
		return sSC_Id;
	}

	public void setsSC_Id(Integer sSC_Id) {
		this.sSC_Id = sSC_Id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
