package fr.solutec.rest;



import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Optional;

import javax.mail.MessagingException;

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
import fr.solutec.services.MailServices;
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
	@Autowired
	private MailServices mailServ;

	/**
	 * Créé un enregistre une alerte dans la BDD.
	 * @param a l'objet de type Alert
	 * @return L'alerte enregistrée dans la BDD.
	 */
	@PostMapping("createAlert") //API créer une alerte
	public Alert createAlert(@RequestBody Alert a) {
		return  alertRepo.save(a);
	}
	
	/**
	 * Affiche toute les objets de type Alert enregistrés dans la BDD.
	 * @return La liste des alertes enregistrés dans la BDD.
	 */
	@GetMapping("getAlert") //API pour voir toute les alertes
	public Iterable<Alert> alertHistory(){
		return alertRepo.findAll();
	}
	
	/**
	 * Modifie un alerte déjà existante.
	 * @param L'ID de l'alerte qui doit être modifiée.
	 * @param Un objet de type Alert qui va remplacé l'alerte qui sera modifiée.
	 * @return L'alerte modifiée et enregistrée dans la BDD.
	 */
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
	
	//Vérifie si une alerte a été déclenchée suite à un modification.
	@PatchMapping("refreshAlert") //API pour voir rafraichir l'état des alertes
	public ArrayList<String> refreshAlert() throws UnsupportedEncodingException, MessagingException{
		ArrayList<String> product = new ArrayList<String>();
		GregorianCalendar calendar = new GregorianCalendar();
		Iterable<Alert> listAlert = alertRepo.findAll();
		for(Alert a : listAlert) {
			//Vérifie que l'alerte est active
			if(a.isActive()) {
				if(!a.getProducts().isEmpty()) {
					for(TypeProduct t : a.getProducts()) {
						long stock = productRepo.findStock(t.getNameProduct());
						//Vérifie que le stock de chaque équipement assoscié à l'alerte est au dessus du seuil de l'alerte.
						if(stock <= a.getSeuil()) {
							product.add(t.getNameProduct());
							// Si l'alerte est déclenchée, une notification est créée
							if(!a.isTriggered()) {
							a.setTriggered(true);
							a.setDate(calendar.getTime());
							notificationServ.createNotification(a);
							// Si l'alerte est déclenchée et que la notification par mail est activée alors un mail automatique est généré
							if(a.isEmail()) {
							mailServ.sendEmail(a.getAlerte(),a.getDate());
							}
							}
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
	
	/**
	 * Supprime un équipement d'une alerte.
	 * @param Le nom du produit qui doit être supprimé.
	 * @param L'ID de l'alerte dans laquelle le produit doit être supprimé.
	 * @return L'alerte modifiée et enregistrée dans la BDD.
	 */
	@PatchMapping("deleteTypeProduct/{nameProduct}/{idAlert}") //API pour supprimer un produit d'une alerte
	public Alert deleteTypeProduct(@PathVariable String nameProduct, @PathVariable Long idAlert) {
		Optional<TypeProduct> t = typeProductRepo.findByNameProduct(nameProduct);
		Optional<Alert> a = alertRepo.findById(idAlert);
		a.get().getProducts().remove(t.get());
		return alertRepo.save(a.get());
	}
	
	/**
	 * Supprime une alerte.
	 * @param L'ID de l'alerte qui doit être supprimée.
	 * @return Un booléen.
	 */
	@DeleteMapping("deleteAlert/{idAlert}")
	public boolean deleteAlert(@PathVariable Long idAlert){
		alertRepo.deleteById(idAlert);
		return true;
	}
	
	/**
	 * Affiche la liste des alertes déclenchées.
	 * @return Les alertes enregistrées dans la BDD avec trigerred=true.
	 */
	@GetMapping("getTrigger") //API pour voir les alertes déclenchées
	public Iterable<Alert> triggerHistory(){
		return alertRepo.findTrigger();
	}
}
