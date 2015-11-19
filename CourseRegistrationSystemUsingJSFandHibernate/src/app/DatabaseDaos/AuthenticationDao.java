package app.DatabaseDaos;

import app.Entities.Authenticate;
import app.ManagedBeans.AuthenticateBean;

public interface AuthenticationDao {
	public AuthenticateBean authenticateUser(Authenticate authenticate);
	public Integer addUser(Authenticate authenticate);
}
