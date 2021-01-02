package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import fr.training.samples.bankpilot.domain.Account;

public interface DaoAccount {

	public void createAccount(Account account);
	
	public void updateAccount(Account account);
	
	public Account findById(Long id);
	
	public List<Long> findIdByAccountNumber(String accountNumber);
	

}
