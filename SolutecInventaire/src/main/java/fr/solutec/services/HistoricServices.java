package fr.solutec.services;

import java.util.GregorianCalendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.solutec.entities.Historic;
import fr.solutec.entities.Product;
import fr.solutec.repository.HistoricRepository;

@Service
public class HistoricServices {
	@Autowired
	private HistoricRepository historicRepo;
	
	public Historic add(Product product) {
		Historic h = new Historic();
		GregorianCalendar calendar = new GregorianCalendar();
		h.setDateHistoric(calendar.getTime());
		h.setTypeModif("Ajout d'un produit");
		h.setProductAfter(product);
		return historicRepo.save(h);
	}
	
	public Historic delete(Optional<Product> product) {
		Historic h = new Historic();
		GregorianCalendar calendar = new GregorianCalendar();
		h.setDateHistoric(calendar.getTime());
		h.setTypeModif("Suppression de produit");
		h.setProductBefore(product.get());
		return historicRepo.save(h);
		
	}
	
	public Historic modif(Product productB, Product productA) {
		Historic h = new Historic();
		GregorianCalendar calendar = new GregorianCalendar();
		h.setDateHistoric(calendar.getTime());
		h.setProductAfter(productA);
		h.setProductBefore(productB);
		h.setTypeModif("Modification de produit");
		return historicRepo.save(h);
	}
	
}
