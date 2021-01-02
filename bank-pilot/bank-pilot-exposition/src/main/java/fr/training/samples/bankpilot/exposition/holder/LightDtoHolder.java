package fr.training.samples.bankpilot.exposition.holder;

import java.io.Serializable;

public class LightDtoHolder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String forname;
	private String mailAddress;
	
	 /**
     * No-arg constructor for JavaBean tools
     */
	public LightDtoHolder() {
	
	}

	/**
     * @param name
     * @param forname
     * @param mailAddress
     */
	public LightDtoHolder(String name, String forname, String mailAddress) {
		this.name = name;
		this.forname = forname;
		this.mailAddress = mailAddress;
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
