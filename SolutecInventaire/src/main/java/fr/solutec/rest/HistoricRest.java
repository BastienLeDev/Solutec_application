package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Historic;
import fr.solutec.repository.HistoricRepository;

@CrossOrigin("*")
@RestController
public class HistoricRest {
	@Autowired
	private HistoricRepository historicRepo;
	
	@GetMapping("historic") //API voir l'historique des actions
	public Iterable<Historic> getHistoric(){
		return historicRepo.findAll();
	}
}
