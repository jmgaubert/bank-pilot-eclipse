package fr.training.samples.bankpilot.application.holder;

import java.util.List;

import fr.training.samples.bankpilot.domain.Holder;

public interface ServiceHolder {

	/**
     * @param holder the holder to create
     * @return the result of the create
     */
	
	public String createHolder(Holder holder);
	
	/**
     * @param holder the holder to update
     * @return the result of the create
     */
	public String updateHolder(String name, String forname, String nameNew, String fornameNew, String mailAddressNew);
	
	/**
     * @param id the id of the holder to retrieve
     * @return the retrieved holder
     */
	public Holder findById(Long id);
	
	/**
     * @param null
     * @return the list of retrieved holder
     */
	public List<Holder> findAll();
	
	
	/**
     * @param name and forname the holder to retrieve
     * @return the list of retrieved holder
     */
	public List<Holder> findByNameAndForname(String name, String forname);
	
	
}
