package fr.training.samples.bankpilot.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String reference;
	private int status;
	private String issuerAccount;
	private String receiverAccount;
	private double amount;
	private LocalDate executionDate;
	private String wording;
	private boolean isBeneficiaryInform;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIssuerAccount() {
		return issuerAccount;
	}
	public void setIssuerAccount(String issuerAccount) {
		this.issuerAccount = issuerAccount;
	}
	public String getReceiverAccount() {
		return receiverAccount;
	}
	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(LocalDate executionDate) {
		this.executionDate = executionDate;
	}
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}
	public boolean isBeneficiaryInform() {
		return isBeneficiaryInform;
	}
	public void setBeneficiaryInform(boolean isBeneficiaryInform) {
		this.isBeneficiaryInform = isBeneficiaryInform;
	}
	
	
	
	
}
