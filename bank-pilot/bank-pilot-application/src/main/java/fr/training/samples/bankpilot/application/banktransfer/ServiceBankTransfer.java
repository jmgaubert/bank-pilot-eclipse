package fr.training.samples.bankpilot.application.banktransfer;

import java.util.List;

import fr.training.samples.bankpilot.domain.BankTransfer;

public interface ServiceBankTransfer {

	
	/**
     * @param mettre les paramètres
     * @return the result of 
     */
	
	//penser à enregistrer le bordeau en base avec le statut execute
	public String executeBankTransfer(String reference, int status, 
			                          String issuerAccount, Long idIssuerAccount, 
			                          String receiverAccount, Long idReceiverAccount, 
			                          double transfertAmount, String wordingTranfer, boolean beneficiaryInform);
	
	public List<BankTransfer> findBankTransferByReference(String reference);
	
	
}
