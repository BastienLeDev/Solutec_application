package fr.solutec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.solutec.entities.Alert;
import fr.solutec.entities.Notification;
import fr.solutec.repository.NotificationRepository;

@Service
public class NotificationServices {
	@Autowired
	private NotificationRepository notificationRepo;
	
	/**
	 * Enregistre une notification dans la BDD.
	 * @param Un objet de type Alert.
	 */
	public void createNotification(Alert a) {
		Notification n = new Notification();
		n.setAlerte(a.getAlerte());
		n.setDate(a.getDate());
		notificationRepo.save(n);
	}

}
