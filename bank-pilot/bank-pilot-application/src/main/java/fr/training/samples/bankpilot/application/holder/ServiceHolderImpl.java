package fr.training.samples.bankpilot.application.holder;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.training.samples.bankpilot.domain.Holder;
import fr.training.samples.bankpilot.infrastructure.DaoHolder;

@Service
public class ServiceHolderImpl implements ServiceHolder {
	
	@Autowired
	private DaoHolder daoHolder;
	
	@Transactional
	public String createHolder(Holder holder) {
		
		List<Holder> listHolder = daoHolder.findByNameAndForname(holder.getName(),holder.getForname());
		if(listHolder.isEmpty()) {
			daoHolder.createHolder(holder);
			return "OK : holder created";
		}
		else {
			return "error : holder exist with same name/forname";
			
		}
		
	}
	
	@Transactional
	public String updateHolder(String name, String forname, String nameNew, String fornameNew, String mailAddressNew) {
// a affiner: ne pas permettre de modifier nom/prenom si nouvelle valeur est dejà existante
		List<Holder> listHolder = daoHolder.findByNameAndForname(name, forname);
		if(listHolder.size()==1) {
			Holder holderNew = new Holder();
			holderNew.setId(listHolder.get(0).getId());
			holderNew.setName(nameNew);
			holderNew.setForname(fornameNew);
			holderNew.setMailAddress(mailAddressNew);
			daoHolder.updateHolder(holderNew);
			return "Ok : holder modify";
		}
		else {
			return "error : holder does not exist or not single";
		}
	
	}
	
	@Transactional
	public Holder findById(Long id) {
		Holder holder = daoHolder.findById(id);
		return holder;
	}

	@Transactional
	public List<Holder> findAll() {
		List<Holder> listHolder = daoHolder.findAll();
		return listHolder;
	}

	@Transactional
	public List<Holder> findByNameAndForname(String name, String forname) {
		List<Holder> listHolder = daoHolder.findByNameAndForname(name, forname);
		return listHolder;
	}

}
