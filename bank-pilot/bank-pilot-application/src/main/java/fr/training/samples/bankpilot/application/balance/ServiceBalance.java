package fr.training.samples.bankpilot.application.balance;

import java.util.List;

import fr.training.samples.bankpilot.domain.Balance;

public interface ServiceBalance {

	/**
     * @param balance the balance to create
     * @return the result of the create
     */
	public String createBalance(Balance balance);
	
	
	/**
     * @param id the id of the balance to retrieve
     * @return the retrieved balance
     */
	public Balance findById(Long id);
	
	/**
     * @param id balance to update
     * @return the result of the update
     */
	public String updateBalance(Long id, double newAmount);

	
}
