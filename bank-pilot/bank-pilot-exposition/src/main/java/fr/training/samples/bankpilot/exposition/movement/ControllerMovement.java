package fr.training.samples.bankpilot.exposition.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.training.samples.bankpilot.application.account.ServiceAccount;

@RestController
@RequestMapping("/movement")
public class ControllerMovement {

	@Autowired
	ServiceAccount serviceAccount;
	
	@GetMapping("/test")
	public String test() {
		return "test movement";
	}
	

	
}
