package fr.solutec.rest;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import fr.solutec.services.NotificationServices;

@CrossOrigin("*")
@RestController
public class AlertRest {
	
	@Autowired
	private AlertRepository alertRepo;
	@Autowired
	private TypeProductRepository typeProductRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private NotificationServices notificationServ;

	
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
		a.get().setAction(alert.getAction());
		return alertRepo.save(a.get());
	}
	
	@PatchMapping("refreshAlert") //API pour voir rafraichir l'état des alertes
	public ArrayList<String> refreshAlert(){
		ArrayList<String> product = new ArrayList<String>();
		GregorianCalendar calendar = new GregorianCalendar();
		Iterable<Alert> listAlert = alertRepo.findAll();
		for(Alert a : listAlert) {
			if(a.isActive()) {
				if(!a.getProducts().isEmpty()) {
					for(TypeProduct t : a.getProducts()) {
						long stock = productRepo.findStockPC(t.getNameProduct());
						if(stock <= a.getSeuil()) {
							product.add(t.getNameProduct());
							if(!a.isTriggered()) {
							a.setTriggered(true);
							a.setDate(calendar.getTime());
							notificationServ.createNotification(a);
							}
						}else {
							a.setTriggered(false);
						}
					}
				}else {
					a.setTriggered(false);
				}	
			alertRepo.save(a);	
	}
		}
		return product;
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
