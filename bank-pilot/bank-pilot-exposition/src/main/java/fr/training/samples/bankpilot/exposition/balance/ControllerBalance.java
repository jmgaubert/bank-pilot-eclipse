package fr.training.samples.bankpilot.exposition.balance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.training.samples.bankpilot.application.account.ServiceAccount;
import fr.training.samples.bankpilot.application.balance.ServiceBalance;
import fr.training.samples.bankpilot.domain.Account;
import fr.training.samples.bankpilot.domain.Balance;

@RestController
@RequestMapping("/balance")
public class ControllerBalance {

	@Autowired
	ServiceAccount serviceAccount;
	
//	@Autowired
//	ServiceBalance serviceBalance;
	
	@GetMapping("/test")
	public String test() {
		return "test balance";
	}
	
	@GetMapping("/findByAccountNumber/{accountnumber}")
	public Balance findBalanceByAccountNumber(@PathVariable("accountnumber") String accountNumber){
		List<Long> listIdAccount = serviceAccount.findIdByAccountNumber(accountNumber);
		if(listIdAccount.isEmpty()){
			return null;
		}
		else {
			Account account = serviceAccount.findById(listIdAccount.get(0));
			return account.getBalance();
		}
		
	}
	
	@GetMapping("/update/{accountnumber}/{newamount}")
	public String updateBalance(@PathVariable("accountnumber") String accountNumber, @PathVariable("newamount") double newAmont) {
		List<Long> listIdAccount = serviceAccount.findIdByAccountNumber(accountNumber);
		if(listIdAccount.isEmpty()){
			return "error : account not exist";
		}
		else {
			Account accountUpdate = serviceAccount.findById(listIdAccount.get(0));
			accountUpdate.getBalance().setAmount(newAmont);
			accountUpdate.getBalance().setUpdatedDate(new Date());
            serviceAccount.updateAccount(accountUpdate);
	    	return "OK : balance account updated";
		}
		
	}
	
}
