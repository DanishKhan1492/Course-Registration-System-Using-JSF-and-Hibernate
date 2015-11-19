package app.DatabaseDaosImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import app.DatabaseDaos.AuthenticationDao;
import app.Entities.Authenticate;
import app.ManagedBeans.AuthenticateBean;

public class AuthenticationDaoImpl implements AuthenticationDao {

	private SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	private Session session=sessionFactory.openSession();
	
	@Override
	public AuthenticateBean authenticateUser(Authenticate authenticate) {
		AuthenticateBean auth=new AuthenticateBean();
		session.beginTransaction();
		Query query=session.createQuery("from Authenticate WHERE UserName=:name");
		query.setString("name", authenticate.getUserName());
		Object queryResult = query.uniqueResult();
		authenticate=(Authenticate)queryResult;
		session.getTransaction().commit();
		
		auth.setAuthId(authenticate.getAuth_Id());
		auth.setUserName(authenticate.getUserName());
		auth.setPassword(authenticate.getPassword());
		auth.setRole(authenticate.getRole());
		
		return auth;
	}

	@Override
	public Integer addUser(Authenticate authenticate) {
		session.beginTransaction();
		Integer status=(Integer) session.save(authenticate);
		session.getTransaction().commit();
		return status;
	}

}
