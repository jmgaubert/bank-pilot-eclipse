package fr.training.samples.bankpilot.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date integrationDate;
	private LocalDate operationDate;
	private LocalDate valueDate;
	private double amount;
	private String wording;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getIntegrationDate() {
		return integrationDate;
	}
	public void setIntegrationDate(Date integrationDate) {
		this.integrationDate = integrationDate;
	}
	public LocalDate getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(LocalDate operationDate) {
		this.operationDate = operationDate;
	}
	public LocalDate getValueDate() {
		return valueDate;
	}
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}

	
}
