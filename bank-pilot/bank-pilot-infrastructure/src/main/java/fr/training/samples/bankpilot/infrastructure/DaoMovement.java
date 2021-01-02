package fr.training.samples.bankpilot.infrastructure;

import fr.training.samples.bankpilot.domain.Movement;

public interface DaoMovement {

	public void createMovement(Movement movement);
	
	public Movement findById(Long id);
	
//	public List<Movement> findByPeriod();
	
}
