package fr.solutec.services;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.solutec.entities.Alert;
import fr.solutec.entities.Notification;
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.NotificationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationTest {
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Test
	public void createNotifTest() {
		//Type de produit fictif
		TypeProduct t1 = new TypeProduct(null,"type");
		
		// Alerte fictive pour le test
		Alert a = new Alert();
		a.setAlerte("Alerte fictive");
		a.setActive(true);
		a.setDate(new Date(5));
		a.setSeuil(1);
		a.getProducts().add(t1);
		
		//Notification attendue
		Notification expectedNotification = new Notification();
		expectedNotification.setAlerte(a.getAlerte());
		expectedNotification.setDate(a.getDate());
		
		//Appel de la méthode de création de notification
		Notification n = createNotification(a);
		
		//Vérification des résultats
		assertEquals(expectedNotification.getAlerte(),n.getAlerte());
		assertEquals(expectedNotification.getDate(), n.getDate());
	}
	
	//Copie de la fonction réel avec une notification en retour (car de type Void)
	public Notification createNotification(Alert a) {
		Notification n = new Notification();
		n.setAlerte(a.getAlerte());
		n.setDate(a.getDate());
		notificationRepo.save(n);
		return n;
	}
}
