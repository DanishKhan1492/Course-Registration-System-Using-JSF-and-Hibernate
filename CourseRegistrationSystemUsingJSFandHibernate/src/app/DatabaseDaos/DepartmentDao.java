package app.DatabaseDaos;

import java.util.List;
import app.Entities.Department;

public interface DepartmentDao {
	public Integer addDepartment(Department department);
	public List<Department> getDepartments();
	public void deleteDepartment(Department department);
	public void updateDepartment(Department department);
	public List<String> getDepartmentsName();
	public Department getDepartment(String departName);
}
