package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.training.samples.bankpilot.domain.Balance;

@Repository
public class DaoBalanceImpl implements DaoBalance {

	public void createBalance(Balance balance) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(balance);
	    session.getTransaction().commit();
	    session.close();
		
	}

	public Balance findById(Long id) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Balance result = session.find(Balance.class, id);
	    session.getTransaction().commit();
	    session.close();
		return result;
	}

	public void updateBalance(Balance balance) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.saveOrUpdate(balance);
	    session.getTransaction().commit();
	    session.close();
	}


}
