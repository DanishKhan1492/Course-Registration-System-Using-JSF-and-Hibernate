package app.Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import app.DatabaseDaos.AuthenticationDao;
import app.DatabaseDaos.StudentDao;
import app.DatabaseDaosImpl.AuthenticationDaoImpl;
import app.DatabaseDaosImpl.StudentDaoImpl;
import app.Entities.Authenticate;
import app.Entities.Student;
import app.ManagedBeans.AuthenticateBean;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController {
	private AuthenticationDao authUser = new AuthenticationDaoImpl();
	private Authenticate authenticate = new Authenticate();
	private StudentDao studentDao = new StudentDaoImpl();
	private Student student = new Student();

	@ManagedProperty(value = "#{authenticateBean}")
	private AuthenticateBean authenticateBean;

	public void setAuthenticateBean(AuthenticateBean authenticateBean) {
		this.authenticateBean = authenticateBean;
	}

	public String authenticateUser() {
		FacesContext context = FacesContext.getCurrentInstance();
		String userName = authenticateBean.getUserName(), password = authenticateBean.getPassword(), role = authenticateBean.getRole();
		authenticate.setUserName(userName);
		authenticate.setPassword(password);
		authenticate.setRole(role);

		authenticateBean = authUser.authenticateUser(authenticate);

		if (authenticateBean != null) {
			context.getExternalContext().getSessionMap().put("user", authenticateBean.getUserName());
			context.getExternalContext().getSessionMap().put("role", authenticateBean.getRole().toLowerCase());
			if (authenticate.getRole().toLowerCase().equals("admin")) {
				return "pretty:adminForm";
				
			} else if (authenticate.getRole().toLowerCase().equals("student")) {
				student = studentDao.getStudent(authenticate.getUserName());
				Boolean registered = student.isRegistered();
				if (registered) {
					context.getExternalContext().getSessionMap().put("registered", "Registered");
				}
				return "pretty:selectCourses";
				
			} else if (authenticate.getRole().toLowerCase().equals("teacher")) {
				return "pretty:showTeacherMenu";
				
			} else {
				return "";
			}

		} else {
			String message = "User Name or Password Is Wrong ";
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, message, message));
			return "";
		}
	}

	public String invalidateUser() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.getSession().invalidate();
		return "pretty:login";
	}
}
