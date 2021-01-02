package fr.training.samples.bankpilot.infrastructure;

import java.util.List;

import fr.training.samples.bankpilot.domain.BankTransfer;

public interface DaoBankTransfer {

	public void createBankTransfer(BankTransfer bankTransfer);
	
	public BankTransfer findById(Long id);
	
	public List<BankTransfer> findByReference(String reference);
	
	
}
