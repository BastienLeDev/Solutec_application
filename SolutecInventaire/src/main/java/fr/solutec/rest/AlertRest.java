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
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.AlertRepository;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;

@CrossOrigin("*")
@RestController
public class AlertRest {
	
	@Autowired
	private AlertRepository alertRepo;
	@Autowired
	private TypeProductRepository typeProductRepo;
	@Autowired
	private ProductRepository productRepo;

	
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
		return alertRepo.save(a.get());
	}
	
	@GetMapping("refreshAlert") //API pour voir rafraichir l'état des alertes
	public void refreshAlert(){
		Iterable<Alert> listAlert = alertRepo.findAll();
		for(Alert a : listAlert) {
			if(a.isActive()) {
				for(TypeProduct t : a.getProducts()) {
					long stock = productRepo.findStockPC(t.getNameProduct());
					if(stock <= a.getSeuil()) {
						a.setTriggered(true);
					}else {
						a.setTriggered(false);
					}
				}
			alertRepo.save(a);	
	}
		}
	}
	
	
	@PatchMapping("deleteTypeProduct/{nameProduct}/{idAlert}") //API pour supprimer un produit d'une alerte
	public Alert deleteTypeProduct(@PathVariable String nameProduct, @PathVariable Long idAlert) {
		Optional<TypeProduct> t = typeProductRepo.findByNameProduct(nameProduct);
		Optional<Alert> a = alertRepo.findById(idAlert);
		a.get().getProducts().remove(t.get());
		return alertRepo.save(a.get());
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
