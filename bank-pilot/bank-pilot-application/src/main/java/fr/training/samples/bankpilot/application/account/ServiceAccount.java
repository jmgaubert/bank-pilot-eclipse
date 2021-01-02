package fr.training.samples.bankpilot.application.account;

import java.time.LocalDate;
import java.util.List;

import fr.training.samples.bankpilot.domain.Account;

public interface ServiceAccount {
	
//	/**
//     * @param account the account to create
//     * @return the result of the create
//     */
//	public String createAccount(Account account);
	
	/**
     * @param mettre les paramètres
     * @return the result of the open
     */
	public String openAccount(String accountNumber, String nameHolder, String fornameHolder, String mailAddressHolder, double openMovementAmount);
		
	
	/**
     * @param account the account to update
     * @return the result of the update
     */
	public String updateAccount(Account account);
	
	/**
     * @param id the id of the account to retrieve
     * @return the retrieved holder
     */
	public Account findById(Long id);
	
	
	/**
     * @param accountNumber the account to retrieve
     * @return the list of retrieved id accountnumber
     */
	public List<Long> findIdByAccountNumber(String accountNumber);
	
	
	/**
     * @param mettre les paramètres
     * @return the result of the integrate movement
     */
	public String integrateMovementAccount(Long idAccount, LocalDate operationDate, LocalDate valueDate, double amount, String Wording);
	
	
}
