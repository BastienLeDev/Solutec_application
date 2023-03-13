package fr.solutec.rest;

import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Product;
import fr.solutec.entities.Stock;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.stockRepository;

@RestController
@CrossOrigin("*")
public class StockRest {
	@Autowired
	private stockRepository stockRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("getStock/{idStock}")
	public Stock getStock(@PathVariable Long idStock){
		Optional<Stock> s = stockRepo.findById(idStock);
		Iterable<Product> stock =  productRepo.findStock();
		s.get().setProduct((java.util.Set<Product>) stock);
		return stockRepo.save(s.get());
	}
}
