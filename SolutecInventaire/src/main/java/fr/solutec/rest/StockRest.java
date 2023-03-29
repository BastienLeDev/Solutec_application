package fr.solutec.rest;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Product;
import fr.solutec.entities.Stock;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.StockRepository;


@RestController
@CrossOrigin("*")
public class StockRest {
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("stock/{idStock}")//API voir le matériel en stock (idstock = 1)
	public Iterable<Product> getStock(@PathVariable Long idStock){
		Optional<Stock> s = stockRepo.findById(idStock);
		return s.get().getProduct();
	}
	
	@GetMapping("notStock") //API voir le matériel pas en stock
	public Iterable<Product> getNotStock(){
		return productRepo.findNotStock();
	}
	
	@GetMapping("saveStock/{idStock}")//API MaJ/Sauvegarde du stock en fonction du matérielle qui n'a plus de respo (idstock = 1)
	public Stock SaveStock(@PathVariable Long idStock){
		Optional<Stock> s = stockRepo.findById(idStock);
		Iterable<Product> stock =  productRepo.findStock();
		s.get().setProduct((java.util.Set<Product>) stock);
		return stockRepo.save(s.get());
	}
}
