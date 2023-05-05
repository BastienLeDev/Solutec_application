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
	
	public void createNotification(Alert a) {
		Notification n = new Notification();
		n.setAlerte(a);
		n.setDate(a.getDate());
		notificationRepo.save(n);
	}

}
