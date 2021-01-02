package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import fr.training.samples.bankpilot.domain.Holder;

public interface DaoHolder {
	
	public void createHolder(Holder holder);
	
	public void updateHolder(Holder holder);
	
	public Holder findById(Long id);
	
	public List<Holder> findAll();
	
	public List<Holder> findByNameAndForname(String name, String forname);
	
}
