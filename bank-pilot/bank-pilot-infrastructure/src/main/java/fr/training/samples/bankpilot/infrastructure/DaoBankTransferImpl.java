package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import fr.training.samples.bankpilot.domain.Balance;
import fr.training.samples.bankpilot.domain.BankTransfer;
import fr.training.samples.bankpilot.domain.Holder;

@Repository
public class DaoBankTransferImpl implements DaoBankTransfer {

	public void createBankTransfer(BankTransfer bankTransfer) {
	    SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(bankTransfer);
	    session.getTransaction().commit();
	    session.close();
		
	}

	public BankTransfer findById(Long id) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    BankTransfer result = session.find(BankTransfer.class, id);
	    session.getTransaction().commit();
	    session.close();
		return result;
	}

	public List<BankTransfer> findByReference(String reference) {
		SessionFactory sessionFactory = DaoHibernate.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    Query query=session.createQuery("select bt from BankTransfer bt where bt.reference=?1");
		query.setParameter(1, reference);
		List<BankTransfer> listBankTransfer=query.getResultList();
		session.getTransaction().commit();
		session.close();
		return listBankTransfer;
	}

}
