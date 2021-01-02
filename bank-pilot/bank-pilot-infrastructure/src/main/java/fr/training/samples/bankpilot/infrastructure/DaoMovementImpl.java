package fr.training.samples.bankpilot.infrastructure;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.training.samples.bankpilot.domain.Movement;

@Repository
public class DaoMovementImpl implements DaoMovement{

	public void createMovement(Movement movement) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(movement);
	    session.getTransaction().commit();
	    session.close();
		
	}

	public Movement findById(Long id) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Movement result = session.find(Movement.class, id);
	    session.getTransaction().commit();
	    session.close();
		return result;
	}

}
