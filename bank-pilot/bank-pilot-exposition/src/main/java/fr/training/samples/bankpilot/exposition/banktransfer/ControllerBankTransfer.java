package fr.training.samples.bankpilot.exposition.banktransfer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.training.samples.bankpilot.application.account.ServiceAccount;
import fr.training.samples.bankpilot.application.banktransfer.ServiceBankTransfer;
import fr.training.samples.bankpilot.domain.BankTransfer;

@RestController
@RequestMapping("/banktransfer")
public class ControllerBankTransfer {

	@Autowired
	ServiceAccount serviceAccount;
	
	@Autowired
	ServiceBankTransfer serviceBankTransfer;
	
	@GetMapping("/execute/{reference}/{status}/{issueraccount}/{receiveraccount}"
            + "/{transferamount}/{wordingtransfer}/{beneficiaryinform}")
    public String executeBankTransfer(@PathVariable("reference") String reference, 
                                     @PathVariable("status") int status,
                                     @PathVariable("issueraccount") String issuerAccount,
                                     @PathVariable("receiveraccount") String receiverAccount,
                                     @PathVariable("transferamount") double transferAmount,
                                     @PathVariable("wordingtransfer") String wordingTransfer,
                                     @PathVariable("beneficiaryinform") boolean beneficiaryInform) {
		
		if(transferAmount < 0) {
			return "error : transfer amount is negatif";
		}
		else {	
		       List<Long> listIdAccountIssuer = serviceAccount.findIdByAccountNumber(issuerAccount);
		       List<Long> listIdAccountReceiver = serviceAccount.findIdByAccountNumber(receiverAccount);
		       if(listIdAccountIssuer.isEmpty()||listIdAccountReceiver.isEmpty()) {
			      return "error : account issuer or account receiver not exist";
		       }
		       else {
			         return serviceBankTransfer.executeBankTransfer(reference, status, 
				   	                                                issuerAccount, listIdAccountIssuer.get(0), 
				   	                                                receiverAccount, listIdAccountReceiver.get(0), 
					                                                transferAmount, wordingTransfer, beneficiaryInform);
		       }
		}
		
	}

	
	@GetMapping("/findByReference/{reference}")
	public BankTransfer findBankTransferByReference(@PathVariable("reference") String reference) {
		List<BankTransfer> listBankTransfer = serviceBankTransfer.findBankTransferByReference(reference);
		if(listBankTransfer.isEmpty()) {
			return null;
		}
		else {
			return listBankTransfer.get(0);
		}
		
	}
	
}
