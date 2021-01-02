package fr.training.samples.bankpilot.exposition.holder;

import java.io.Serializable;

public class DtoHolder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String forname;
	private String mailAddress;
	
	/**
     * No-arg constructor for JavaBean tools
     */
	public DtoHolder() {
	}
	
	
	 /**
     * @param id
     * @param name
     * @param forname
     * @param mailAddress
     */
	public DtoHolder(Long id, String name, String forname, String mailAddress) {
		this.id = id;
		this.name = name;
		this.forname = forname;
		this.mailAddress = mailAddress;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getForname() {
		return forname;
	}


	public void setForname(String forname) {
		this.forname = forname;
	}


	public String getMailAddress() {
		return mailAddress;
	}


	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	

}
