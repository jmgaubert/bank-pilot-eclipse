package fr.training.samples.bankpilot.application.balance;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.training.samples.bankpilot.domain.Balance;
import fr.training.samples.bankpilot.infrastructure.DaoBalance;

@Service
public class ServiceBalanceImpl implements ServiceBalance {

//	@Autowired
//	private DaoAccount daoAccount;
	
	@Autowired
	private DaoBalance daoBalance;
	
	
	@Transactional
	public String createBalance(Balance balance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Balance findById(Long id) {
		Balance balance = daoBalance.findById(id);
		return balance;
	}

	@Transactional
	public String updateBalance(Long id, double newAmount) {
		Balance balance = daoBalance.findById(id);
		balance.setAmount(newAmount);
		daoBalance.updateBalance(balance);
		return "OK : balance updated";
	}

}
