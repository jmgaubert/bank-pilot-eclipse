import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.training.samples.bankpilot.domain.Account;
import fr.training.samples.bankpilot.domain.Balance;
import fr.training.samples.bankpilot.domain.BankTransfer;
import fr.training.samples.bankpilot.domain.Holder;
import fr.training.samples.bankpilot.domain.Movement;
import fr.training.samples.bankpilot.infrastructure.DaoAccount;
import fr.training.samples.bankpilot.infrastructure.DaoAccountImpl;
import fr.training.samples.bankpilot.infrastructure.DaoBalance;
import fr.training.samples.bankpilot.infrastructure.DaoBalanceImpl;
import fr.training.samples.bankpilot.infrastructure.DaoBankTransfer;
import fr.training.samples.bankpilot.infrastructure.DaoBankTransferImpl;
import fr.training.samples.bankpilot.infrastructure.DaoHolder;
import fr.training.samples.bankpilot.infrastructure.DaoHolderImpl;
import fr.training.samples.bankpilot.infrastructure.DaoMovement;
import fr.training.samples.bankpilot.infrastructure.DaoMovementImpl;

public class RunDao {

	public static void main(String[] args) {
		
		//déclaration des interfaces DAO --> on est on main donc pas de AutoWired, on est obligé de faire des new sur Impl
		DaoHolder daoHolder = new DaoHolderImpl();
		DaoBalance daoBalance = new DaoBalanceImpl();
		DaoMovement daoMovement = new DaoMovementImpl();
		DaoAccount daoAccount = new DaoAccountImpl();
		DaoBankTransfer daoBankTransfer = new DaoBankTransferImpl();
		
		
		System.out.println("------------------------TEST SUR HOLDER INFRASTRUCTURE-------------------------------------------------------");
		
		Holder holder1 = new Holder();
		holder1.setName("GAUBERT");
		holder1.setForname("Jean-Michel");
		holder1.setMailAddress("jean-michel.gaubert@wanadoo.fr");
		daoHolder.createHolder(holder1);		
		
		Holder holder2 = new Holder();
		holder2.setName("LABELLE");
		holder2.setForname("Valérie");
		holder2.setMailAddress("valérie.labelle@gmail.com");
		daoHolder.createHolder(holder2);	
		
		Holder holderResult = new Holder();
		
		System.out.println("------------------------holder recherche par id--------------------------------------------------------------");
		holderResult = daoHolder.findById(1L);
		System.out.println("recherche holder 1 : "+holderResult.getName()+" "+holderResult.getForname()+" "+holderResult.getMailAddress());
		
		holderResult = daoHolder.findById(2L);
		System.out.println("recherche holder 2 : "+holderResult.getName()+" "+holderResult.getForname()+" "+holderResult.getMailAddress());

		System.out.println("------------------------holder recherche All-----------------------------------------------------------------");
		List<Holder> listHolderAll=daoHolder.findAll();
		for(Holder holderL:listHolderAll) {
			System.out.println("liste holder id : "+holderL.getId()+" "+holderL.getName()+" "+holderL.getForname()+" "+holderL.getMailAddress());
		}	
		
		
		System.out.println("------------------------holder update------------------------------------------------------------------------");	
		
		holderResult.setName("test update");
		daoHolder.updateHolder(holderResult);
		
		
		System.out.println("------------------------holder recherche par name/forname----------------------------------------------------");
		String nameR1 = "GAUBERT";
		String fornameR1 = "Jean-Michel";
		List<Holder> listHolderFindByNameAndForname=daoHolder.findByNameAndForname(nameR1, fornameR1);
		for(Holder holderLb:listHolderFindByNameAndForname) {
			System.out.println("liste holder id : "+holderLb.getId()+" "
		                                           +holderLb.getName()+" "
					                               +holderLb.getForname()+" "
		                                           +holderLb.getMailAddress());
		}
		
		System.out.println("------------------------holder recherche par name/forname non trouve-----------------------------------------");
		String nameRv = "GAUBERT";
		String fornameRv = "jacques";
		List<Holder> listHolderFindByNameAndFornameVide=daoHolder.findByNameAndForname(nameRv, fornameRv);
		if(listHolderFindByNameAndFornameVide.isEmpty()) {
			System.out.println("recherche holder pour nom : "+nameRv+" prenom : "+fornameRv+" --> non trouve");
			}
		for(Holder holderLv:listHolderFindByNameAndFornameVide) {
			System.out.println("liste holder id : "+holderLv.getId()+" "
		                                           +holderLv.getName()+" "
					                               +holderLv.getForname()+" "
		                                           +holderLv.getMailAddress());
		}
		
		
		System.out.println("------------------------TEST SUR BALANCE INFRASTRUCTURE-------------------------------------------------------");

		Balance balance1 = new Balance();
		balance1.setUpdatedDate(new Date());
		balance1.setAmount(1550.67);
		daoBalance.createBalance(balance1);
		
		Balance balanceResult = new Balance();
		System.out.println("------------------------balance recherche par id--------------------------------------------------------------");
		balanceResult = daoBalance.findById(1L);
		System.out.println("recherche balance 1 : "+balanceResult.getId()+" "+balanceResult.getUpdatedDate()+" "+balanceResult.getAmount());
		
		System.out.println("------------------------balance update------------------------------------------------------------------------");
		balance1.setUpdatedDate(new Date());
		balance1.setAmount(16615.45);
		daoBalance.updateBalance(balance1);
		System.out.println("update balance 1 : "+balance1.getId()+" "+balance1.getUpdatedDate()+" "+balance1.getAmount());
		
		System.out.println("------------------------TEST SUR MOVEMENT INFRASTRUCTURE-------------------------------------------------------");

		Movement movement1 = new Movement();
		movement1.setIntegrationDate(new Date());
		LocalDate operationDate = LocalDate.of(2020,12, 21);
		LocalDate valueDate = LocalDate.of(2020,12, 22);
		movement1.setOperationDate(operationDate);
		movement1.setValueDate(valueDate);
		movement1.setAmount(15416.45);
		movement1.setWording("test sur DAO Movement");
		daoMovement.createMovement(movement1);
		
		Movement movementResult = daoMovement.findById(1L);
		System.out.println("recherche movement 1 : "+movementResult.getId()+" "
		                                            +movementResult.getIntegrationDate()+" "
		                                            +movementResult.getOperationDate()+" "
		                                            +movementResult.getValueDate()+" "
		                                            +movementResult.getAmount()+" "
		                                            +movementResult.getWording());
		
		
		System.out.println("------------------------TEST SUR BANKTRANSFER INFRASTRUCTURE--------------------------------------------------");
		BankTransfer bankTransfer = new BankTransfer();
		bankTransfer.setIssuerAccount("BNPAFRPP123456");
		bankTransfer.setReceiverAccount("BNPAFRPP883478");
		bankTransfer.setReference("bordereau transfert 000001");
		bankTransfer.setStatus(4000);
		bankTransfer.setBeneficiaryInform(true);
		bankTransfer.setAmount(46565.85);
		bankTransfer.setWording("anniversaire de chloe");
		bankTransfer.setExecutionDate(operationDate);
		daoBankTransfer.createBankTransfer(bankTransfer);
		
		BankTransfer bankTransferResult = daoBankTransfer.findById(1L);
		System.out.println("recherche banktransfer 1 : "+bankTransfer.getId()+" "
				                                        +bankTransfer.getReference()+" "
				                                        +bankTransfer.getIssuerAccount()+" "
				                                        +bankTransfer.getReceiverAccount()+" "
				                                        +bankTransfer.getExecutionDate()+" "
				                                        +bankTransfer.getAmount()+" "
				                                        +bankTransfer.getWording()+" "
				                                        +bankTransfer.isBeneficiaryInform());
		
		
		System.out.println("------------------------TEST SUR ACCOUNT INFRASTRUCTURE-------------------------------------------------------");
		Account account1 = new Account();
		Balance balanceN = new Balance();
		Movement movementInit = new Movement();
		List<Movement> listMovement = new ArrayList<Movement>();
		
		balanceN.setUpdatedDate(new Date());
		balanceN.setAmount(300.0);
		account1.setOpeningDate(operationDate);
		account1.setAccountNumber("BNPAFRPP123456");
		account1.setHolder(listHolderFindByNameAndForname.get(0));
		account1.setBalance(balanceN);
		
		movementInit.setIntegrationDate(new Date());
		movementInit.setOperationDate(operationDate);
		movementInit.setValueDate(operationDate);
		movementInit.setAmount(balanceN.getAmount());
		movementInit.setWording("movement init opening account");
		listMovement.add(movementInit);
		
		account1.setListMovement(listMovement);
		

		daoAccount.createAccount(account1);
		
		List<Long> listIdAccount =daoAccount.findIdByAccountNumber("BNPAFRPP123456");
		
		Account accountFindById = daoAccount.findById(listIdAccount.get(0));
		System.out.println("recherche account 1 : "+accountFindById.getId()+" "
				                                   +accountFindById.getAccountNumber()+" "
				                                   +accountFindById.getOpeningDate());
		System.out.println("holder              : "+accountFindById.getHolder().getId()+" "
				                                   +accountFindById.getHolder().getName()+" "
				                                   +accountFindById.getHolder().getForname()+" "
				                                   +accountFindById.getHolder().getMailAddress());
		
		System.out.println("balance             : "+accountFindById.getBalance().getId()+" "
			                                       +accountFindById.getBalance().getUpdatedDate()+" "
			                                       +accountFindById.getBalance().getAmount());
		
		System.out.println("nb movement         : "+accountFindById.getListMovement().size());
		
		for(Movement movementL:accountFindById.getListMovement()) {
        System.out.println("movement            : "+movementL.getId()+" "
					                               +movementL.getIntegrationDate()+" "
					                               +movementL.getOperationDate()+" "
					                               +movementL.getValueDate()+" "
					                               +movementL.getAmount()+" "
					                               +movementL.getWording());

		}
		
		//quand on intègre un MVT il faut que le compte existe et que cela mette à jour le solde
		
			

		
	}

}
