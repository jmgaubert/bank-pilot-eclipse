package fr.training.samples.bankpilot.application.banktransfer;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.training.samples.bankpilot.domain.Account;
import fr.training.samples.bankpilot.domain.BankTransfer;
import fr.training.samples.bankpilot.domain.Movement;
import fr.training.samples.bankpilot.infrastructure.DaoAccount;
import fr.training.samples.bankpilot.infrastructure.DaoBankTransfer;

@Service
public class ServiceBankTransferImpl implements ServiceBankTransfer{

	@Autowired
	private DaoAccount daoAccount;
	
	@Autowired
	private DaoBankTransfer daoBankTransfer;
	
	@Transactional
	public String executeBankTransfer(String reference, int status, String issuerAccountNumber, Long idIssuerAccount, String receiverAccountNumber, Long idReceiverAccount,
			double transfertAmount, String wordingTranfer, boolean beneficiaryInform) {
		
		Date todayDate = new Date();
//résoudre cette histoire d'heure
		LocalDate localTodayDate = Instant.ofEpochMilli(todayDate.getTime())
		        		                     .atZone(ZoneId.of("GMT+1"))
		         		                     .toLocalDate();
		
		Account issuerAccount = daoAccount.findById(idIssuerAccount);
		Account receiverAccount = daoAccount.findById(idReceiverAccount);
		
		if(issuerAccount.getBalance().getAmount() < transfertAmount) {
		   return "error : balance of issuer is less then transfer amount";
		}
		else {
		Movement issuerMovement = new Movement();
		issuerMovement.setIntegrationDate(todayDate);
		issuerMovement.setOperationDate(localTodayDate);
		issuerMovement.setValueDate(localTodayDate.minusDays(1));
//implémenter un test quelque part sur montant transfert doit être > 0 et < solde issuer (controler ou ici ?) 		
		issuerMovement.setAmount(transfertAmount * -1); 
		issuerMovement.setWording(wordingTranfer +" à destination de "+receiverAccount.getHolder().getForname()+" "+receiverAccount.getHolder().getName());
		
		issuerAccount.getListMovement().add(issuerMovement);
		
		double soldeIssuerBefore = issuerAccount.getBalance().getAmount();
		BigDecimal soldeIssuerAfterBigDecimal = new BigDecimal(soldeIssuerBefore - transfertAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		issuerAccount.getBalance().setAmount(soldeIssuerAfterBigDecimal.doubleValue());
		issuerAccount.getBalance().setUpdatedDate(todayDate);
		daoAccount.updateAccount(issuerAccount);
		
		
		Movement receiverMovement = new Movement();
		receiverMovement.setIntegrationDate(todayDate);
		receiverMovement.setOperationDate(localTodayDate);
		receiverMovement.setValueDate(localTodayDate.plusDays(1));
		receiverMovement.setAmount(transfertAmount); 
		receiverMovement.setWording(wordingTranfer +" de la part de "+issuerAccount.getHolder().getForname()+" "+issuerAccount.getHolder().getName());
		
		receiverAccount.getListMovement().add(receiverMovement);
		
		double soldeReceiverBefore = receiverAccount.getBalance().getAmount();
		BigDecimal soldeReceiverAfterBigDecimal = new BigDecimal(soldeReceiverBefore + transfertAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		receiverAccount.getBalance().setAmount(soldeReceiverAfterBigDecimal.doubleValue());
		receiverAccount.getBalance().setUpdatedDate(todayDate);
		daoAccount.updateAccount(receiverAccount);
		
		BankTransfer bankTransfer = new BankTransfer();
		bankTransfer.setReference(reference);
		bankTransfer.setIssuerAccount(issuerAccountNumber);
		bankTransfer.setReceiverAccount(receiverAccountNumber);
		bankTransfer.setAmount(transfertAmount);
		bankTransfer.setStatus(9000);
		bankTransfer.setBeneficiaryInform(beneficiaryInform);
		bankTransfer.setWording(wordingTranfer);
		bankTransfer.setExecutionDate(localTodayDate);
		daoBankTransfer.createBankTransfer(bankTransfer);
		
		return "OK: Bank Transfer executed";
		}
	}

	
	public List<BankTransfer> findBankTransferByReference(String reference) {
		List<BankTransfer> listBankTransfer = daoBankTransfer.findByReference(reference);
		return listBankTransfer;
	}

}
