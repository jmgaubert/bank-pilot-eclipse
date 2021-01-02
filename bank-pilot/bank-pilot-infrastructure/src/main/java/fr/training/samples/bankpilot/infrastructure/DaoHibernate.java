package fr.training.samples.bankpilot.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DaoHibernate {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory==null) {
			sessionFactory=new Configuration()
					.configure("hibernate.cfg.xml")
					.buildSessionFactory();
		}
		return sessionFactory;
	}
	
}
