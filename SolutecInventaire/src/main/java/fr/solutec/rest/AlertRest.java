package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Alert;
import fr.solutec.repository.AlertRepository;

@CrossOrigin("*")
@RestController
public class AlertRest {
	
	@Autowired
	private AlertRepository alertRepo;
	
	@PostMapping("createAlert") //API créer une alerte
	public Alert createAlert(@RequestBody Alert a) {
		return  alertRepo.save(a);
	}
	
	@GetMapping("getAlert") //API pour voir toute les alertes
	public Iterable<Alert> alertHistory(){
		return alertRepo.findAll();
	}
	
	@GetMapping("getTrigger") //API pour voir les alertes déclenchées
	public Iterable<Alert> triggerHistory(){
		return alertRepo.findTrigger();
	}
}
