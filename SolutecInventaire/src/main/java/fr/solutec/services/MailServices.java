package fr.solutec.services;



import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailServices {
	
	/**
	 * Génère et envoie un mail automatique.
	 * @param L'objet du mail généré.
	 * @param La date du déclenchement de l'alerte à l'origine du mail.
	 */
	public void sendEmail(String subject, Date date) throws MessagingException,
    UnsupportedEncodingException {
	
	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy à hh:mm",Locale.FRENCH); 
		
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.office365.com");
	props.put("mail.smtp.port", "587");
	props.put("mail.smtp.ssl.trust", "smtp.office365.com");
	
	Session session = Session.getInstance(props,
			  new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication("bgauthiero@consultants-solutec.fr", "mdp");
			    }
			  });
	
	Message message = new MimeMessage(session);
	message.setFrom(new InternetAddress("bgauthiero@consultants-solutec.fr"));
	message.setRecipients(Message.RecipientType.CC,
	        InternetAddress.parse("bgauthiero@consultants-solutec.fr,ldodet@consultants-solutec.fr"));
	message.setSubject("Déclenchement de '" + subject + "'" );
	message.setText("L'alerte : '"+ subject + "' a été déclenchée le "+ dt.format(date) +". \n\nLe matériel avec un stock critique est visible sur la page d'accueil de l'application. \n \n \nMail généré automatiquement.");
	Transport.send(message);
	}
	
	
	
	
}
