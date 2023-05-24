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
		h.setEntryDateA(product.getEntryDate());
		h.setExitDateA(product.getExitDate());
		h.setOwnerA(product.getOwner());
		h.setRefProductA(product.getRefProduct());
		h.setReservationA(product.isReservation());
		h.setTypeProduct(product.getTypeProduct());
		return historicRepo.save(h);
	}
	
	public Historic delete(Optional<Product> product) {
		Historic h = new Historic();
		GregorianCalendar calendar = new GregorianCalendar();
		h.setDateHistoric(calendar.getTime());
		h.setTypeModif("Suppression de produit");
		h.setEntryDateB(product.get().getEntryDate());
		h.setExitDateB(product.get().getExitDate());
		h.setOwnerB(product.get().getOwner());
		h.setRefProductB(product.get().getRefProduct());
		h.setReservationB(product.get().isReservation());
		h.setTypeProduct(product.get().getTypeProduct());
		return historicRepo.save(h);
		
	}
	
	public Historic modif(Product productB, Product productA) {
		Historic h = new Historic();
		GregorianCalendar calendar = new GregorianCalendar();
		h.setDateHistoric(calendar.getTime());
		h.setTypeModif("Modification de produit");
		h.setTypeProduct(productA.getTypeProduct());
		// Produit avant la modification
		h.setEntryDateA(productA.getEntryDate());
		h.setExitDateA(productA.getExitDate());
		h.setOwnerA(productA.getOwner());
		h.setRefProductA(productA.getRefProduct());
		h.setReservationA(productA.isReservation());
		// Produit après la modification
		h.setEntryDateB(productB.getEntryDate());
		h.setExitDateB(productB.getExitDate());
		h.setOwnerB(productB.getOwner());
		h.setRefProductB(productB.getRefProduct());
		h.setReservationB(productB.isReservation());
		return historicRepo.save(h);
	}
	
}
