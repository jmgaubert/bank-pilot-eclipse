package fr.training.samples.bankpilot.exposition.holder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.training.samples.bankpilot.application.holder.ServiceHolder;
import fr.training.samples.bankpilot.domain.Holder;

@RestController
@RequestMapping("/holder")
public class ControllerHolder {
	
	@Autowired
	ServiceHolder serviceHolder;
	
	@GetMapping("/test")
	public String test() {
		return "test holder";
	}
	
	@GetMapping("/create/{nom}/{prenom}/{adressemail}")
	public String createHolder(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("adressemail") String adresseMail) {
		Holder holderS1 = new Holder();
		holderS1.setName(nom);
		holderS1.setForname(prenom);
		holderS1.setMailAddress(adresseMail);
		System.out.println(holderS1);
        String resultCreate = serviceHolder.createHolder(holderS1);	
        return resultCreate;
		
		}
	
	@GetMapping("/update/{nom}/{prenom}/{nomnew}/{prenomnew}/{adressemailnew}")
	public String updateHolder(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom, 
			                   @PathVariable("nomnew") String nomnew, @PathVariable("prenomnew") String prenomnew, @PathVariable("adressemailnew") String adressemailnew) {
		String resultUpdate = serviceHolder.updateHolder(nom, prenom, nomnew, prenomnew, adressemailnew);
		return resultUpdate;
		
	}
	
	@GetMapping("/findById/{id}")
	public Holder findHolderById(@PathVariable("id") Long id) {
		Holder holder = serviceHolder.findById(id);
		return holder;
	}
	
	@GetMapping("/findAll")
	public List<Holder> findHolderAll(){
		List<Holder> listHolder = serviceHolder.findAll();
		return listHolder;
		
	}
	
	@GetMapping("/findByNameAndForname/{name}/{forname}")
	public List<Holder> findHolderByNameAndForname(@PathVariable("name") String name, @PathVariable("forname") String forname){
		List<Holder> listHolder = serviceHolder.findByNameAndForname(name, forname);
		return listHolder;
	}
	
}
