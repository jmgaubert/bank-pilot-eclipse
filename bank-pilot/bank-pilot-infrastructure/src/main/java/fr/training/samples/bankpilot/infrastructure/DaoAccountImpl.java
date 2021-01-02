package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.training.samples.bankpilot.domain.Account;

@Repository
public class DaoAccountImpl implements DaoAccount {

	public void createAccount(Account account) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(account);
	    session.getTransaction().commit();
	    session.close();
		
	}

	public void updateAccount(Account account) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.saveOrUpdate(account);
	    session.getTransaction().commit();
	    session.close();
		
	}
	
	
	public Account findById(Long id) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Account result = session.find(Account.class, id);
	    Hibernate.initialize(result.getListMovement());
	    session.getTransaction().commit();
	    session.close();
		return result;
	}

	public List<Long> findIdByAccountNumber(String accountNumber) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("select a.id from Account a where a.accountNumber=?1");
		query.setParameter(1, accountNumber);
		List<Long> listId=query.getResultList();
		session.getTransaction().commit();
		session.close();
		return listId;
	}

}
