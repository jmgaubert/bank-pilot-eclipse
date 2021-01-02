package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.training.samples.bankpilot.domain.Holder;

@Repository
public class DaoHolderImpl implements DaoHolder {

	public void createHolder(Holder holder) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(holder);
	    session.getTransaction().commit();
	    session.close();
	}
	
	public void updateHolder(Holder holder) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.saveOrUpdate(holder);
	    session.getTransaction().commit();
	    session.close();
		
	}

	public Holder findById(Long id) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Holder result = session.find(Holder.class, id);
	    session.getTransaction().commit();
	    session.close();
		return result;
	}
	
	public List<Holder> findAll() {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("select h from Holder h");
	    List<Holder> listHolder=query.getResultList();
	    session.getTransaction().commit();
	    session.close();
		return listHolder;
	}

	public List<Holder> findByNameAndForname(String name, String forname) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("select h from Holder h where h.name=?1 and h.forname=?2");
		query.setParameter(1, name);
		query.setParameter(2, forname);
		List<Holder> listHolder=query.getResultList();
		session.getTransaction().commit();
		session.close();
		return listHolder;		
	}

}
