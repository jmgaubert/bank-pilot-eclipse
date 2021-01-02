package fr.training.samples.bankpilot.application.account;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.training.samples.bankpilot.domain.Account;
import fr.training.samples.bankpilot.domain.Balance;
import fr.training.samples.bankpilot.domain.Holder;
import fr.training.samples.bankpilot.domain.Movement;
import fr.training.samples.bankpilot.infrastructure.DaoAccount;
import fr.training.samples.bankpilot.infrastructure.DaoHolder;

@Service
public class ServiceAccountImpl implements ServiceAccount {

	@Autowired
	private DaoAccount daoAccount;
	
	@Autowired
	private DaoHolder daoHolder;
	
	
	@Transactional
	public String openAccount(String accountNumber, String nameHolder, String fornameHolder, String mailAddressHolder,
			double openMovementAmount) {

		Date todayDate = new Date();
//résoudre cette histoire d'heure
		LocalDate localTodayDate = Instant.ofEpochMilli(todayDate.getTime())
		        		                     .atZone(ZoneId.of("GMT+1"))
		         		                     .toLocalDate();
		 	   
 		Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setOpeningDate(localTodayDate);
		         
        List<Holder> listHolderExist = daoHolder.findByNameAndForname(nameHolder, fornameHolder);
        if(listHolderExist.isEmpty()) {
         	Holder initHolder = new Holder();
         	initHolder.setName(nameHolder);
         	initHolder.setForname(fornameHolder);
         	initHolder.setMailAddress(mailAddressHolder);
         	account.setHolder(initHolder);
         }
         else {
         	account.setHolder(listHolderExist.get(0));
         }
		         
         Balance initBalance = new Balance();
         initBalance.setAmount(openMovementAmount);
         initBalance.setUpdatedDate(todayDate);
         account.setBalance(initBalance);
		         
         Movement initMovement = new Movement();
         initMovement.setIntegrationDate(todayDate);
         initMovement.setOperationDate(localTodayDate);
         initMovement.setValueDate(localTodayDate);
         initMovement.setAmount(openMovementAmount);
         initMovement.setWording("movement init opening account");
		         
         List<Movement> listMovement = new ArrayList<Movement>();
         listMovement.add(initMovement);
         account.setListMovement(listMovement);
         
         daoAccount.createAccount(account);
		 return "OK : account open";
	}


	@Transactional
	public Account findById(Long id) {
		Account account = daoAccount.findById(id);
		return account;
	}

	@Transactional
	public List<Long> findIdByAccountNumber(String accountNumber) {
		List<Long> listIdAccount = daoAccount.findIdByAccountNumber(accountNumber);
		return listIdAccount;
	}

	@Transactional
	public String integrateMovementAccount(Long idAccount, LocalDate operationDate, LocalDate valueDate, double amount,
			String wording) {
		Movement newMovement = new Movement();
		newMovement.setIntegrationDate(new Date());
		newMovement.setOperationDate(operationDate);
		newMovement.setValueDate(valueDate);
		newMovement.setAmount(amount);
		newMovement.setWording(wording);
		
		Account accountUpdate = daoAccount.findById(idAccount);
		accountUpdate.getListMovement().add(newMovement);
		double soldeBefore = accountUpdate.getBalance().getAmount();
		BigDecimal soldeAfterBigDecimal = new BigDecimal(soldeBefore + amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		accountUpdate.getBalance().setAmount(soldeAfterBigDecimal.doubleValue());
		accountUpdate.getBalance().setUpdatedDate(new Date());
		daoAccount.updateAccount(accountUpdate);
		return "OK : movement integrate";
	}
	
	
//a voir si encore utilise	
	@Transactional
	public String updateAccount(Account account) {
		daoAccount.updateAccount(account);
		return "OK : update account";
	}
	
	
}
