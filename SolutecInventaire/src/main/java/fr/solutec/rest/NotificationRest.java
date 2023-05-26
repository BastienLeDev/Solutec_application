package fr.solutec.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Notification;
import fr.solutec.repository.NotificationRepository;

@RestController
@CrossOrigin("*")
public class NotificationRest {
	@Autowired
	private NotificationRepository notificationRepo;
	
	
	/**
	 * Affiche les notifications.
	 * @return Renvoie la liste des notifications enregistrées dans la BDD.
	 */
	@GetMapping("notification") //API pour voir toute les notifications
	public Iterable<Notification> getNotification(){
		return notificationRepo.findAll();
		
	}
	
	/**
	 * Supprime une notification de la BDD.
	 * @param l'ID de la notification qui doit être supprimée.
	 * @return Un booléen.
	 */
	@DeleteMapping("deleteNotification/{idNotif}") //API pour supprimer une notification
	public boolean deleteNotification(@PathVariable Long idNotif) {
		notificationRepo.deleteById(idNotif);
		return true;
	}

}
