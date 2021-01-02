package fr.training.samples.bankpilot.exposition.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.training.samples.bankpilot.application.account.ServiceAccount;
import fr.training.samples.bankpilot.application.holder.ServiceHolder;
import fr.training.samples.bankpilot.domain.Account;

@RestController
@RequestMapping("/account")
public class ControllerAccount {

	@Autowired
	ServiceAccount serviceAccount;
	
	@Autowired
	ServiceHolder serviceHolder;
	
	@GetMapping("/test")
	public String test() {
		return "test account";
	}
	
	@GetMapping("/findByAccountNumber/{accountnumber}")
	public Account findAccountByAccountNumber(@PathVariable("accountnumber") String accountNumber){
		List<Long> listIdAccount = serviceAccount.findIdByAccountNumber(accountNumber);
		return serviceAccount.findById(listIdAccount.get(0));
		
	}
	
	@GetMapping("/findById/{id}")
	public Account findAccountById(@PathVariable("id") Long id) {
		Account account = serviceAccount.findById(id);
		return account;
	}
	
	
	@GetMapping("/open/{accountnumber}/{name}/{forname}/{mailaddress}/{movementinit}")
	public String openAccount(@PathVariable("accountnumber") String accountNumber, 
			                  @PathVariable("name") String nameHolder, 
			                  @PathVariable("forname") String fornameHolder, 
			                  @PathVariable("mailaddress") String mailAddressHolder,
			                  @PathVariable("movementinit") double movementInit) {
		
		List<Long> listIdAccount = serviceAccount.findIdByAccountNumber(accountNumber);
		if(listIdAccount.isEmpty()) {
			return serviceAccount.openAccount(accountNumber, nameHolder, fornameHolder, mailAddressHolder, movementInit);
		}
		else {
			return "error : account is already exist";
			
		}
		
	}
	
	@GetMapping("/integratemovement/{accountnumber}/{operationdateAAAA}/{operationdateMM}/{operationdateJJ}"
            + "/{valuedateAAAA}/{valuedateMM}/{valuedateJJ}/{amount}/{wording}")
    public String integrateMovement(@PathVariable("accountnumber") String accountNumber, 
                                    @PathVariable("operationdateAAAA") int operationDateAAAA,
                                    @PathVariable("operationdateMM") int operationDateMM,
                                    @PathVariable("operationdateJJ") int operationDateJJ,
                                    @PathVariable("valuedateAAAA") int valueDateAAAA,
                                    @PathVariable("valuedateMM") int valueDateMM,
                                    @PathVariable("valuedateJJ") int valueDateJJ,
                                    @PathVariable("amount") double amount,
                                    @PathVariable("wording") String wording) {
		
		LocalDate operationDate = LocalDate.of(operationDateAAAA,operationDateMM,operationDateJJ);
		LocalDate valueDate = LocalDate.of(valueDateAAAA, valueDateMM, valueDateJJ);
		
		List<Long> listIdAccount = serviceAccount.findIdByAccountNumber(accountNumber);
		if(listIdAccount.isEmpty()){
			return "error : account not exist, movement not integred";
		}
		else {
			return serviceAccount.integrateMovementAccount(listIdAccount.get(0), operationDate, valueDate, amount, wording);
		}
		
	}
	
		
}
