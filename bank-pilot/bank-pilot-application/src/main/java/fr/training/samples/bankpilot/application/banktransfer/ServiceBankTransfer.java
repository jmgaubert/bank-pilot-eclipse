package fr.training.samples.bankpilot.application.banktransfer;

import java.util.List;

import fr.training.samples.bankpilot.domain.BankTransfer;

public interface ServiceBankTransfer {

	
	/**
     * @param mettre les param�tres
     * @return the result of 
     */
	
	//penser � enregistrer le bordeau en base avec le statut execute
	public String executeBankTransfer(String reference, int status, 
			                          String issuerAccount, Long idIssuerAccount, 
			                          String receiverAccount, Long idReceiverAccount, 
			                          double transfertAmount, String wordingTranfer, boolean beneficiaryInform);
	
	public List<BankTransfer> findBankTransferByReference(String reference);
	
	
}
