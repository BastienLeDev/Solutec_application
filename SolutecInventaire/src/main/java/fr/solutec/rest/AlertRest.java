package fr.solutec.rest;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PatchMapping("modifyAlert/{idAlert}") //API pour modifier une alerte
	public Alert modifyAlert(@PathVariable Long idAlert, @RequestBody Alert alert) {
		Optional<Alert> a = alertRepo.findById(idAlert);
		a.get().setAlerte(alert.getAlerte());
		a.get().setSeuil(alert.getSeuil());
		a.get().setEmail(alert.isEmail());
		a.get().setProducts(alert.getProducts());
		a.get().setActive(alert.isActive());
		a.get().setTriggered(alert.isTriggered());
		alertRepo.save(a.get());
		return a.get();
	}
	
	@DeleteMapping("deleteAlert/{idAlert}")
	public boolean deleteAlert(@PathVariable Long idAlert){
		alertRepo.deleteById(idAlert);
		return true;
	}
	
	
	@GetMapping("getTrigger") //API pour voir les alertes déclenchées
	public Iterable<Alert> triggerHistory(){
		return alertRepo.findTrigger();
	}
}
