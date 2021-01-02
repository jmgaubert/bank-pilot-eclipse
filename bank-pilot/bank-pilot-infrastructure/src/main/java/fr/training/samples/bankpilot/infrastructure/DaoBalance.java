package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import fr.training.samples.bankpilot.domain.Balance;

public interface DaoBalance {

	public void createBalance(Balance balance);
	
	public Balance findById(Long id);
	
	public void updateBalance(Balance balance);
	
//	public List<Balance> findAll();
	
}
