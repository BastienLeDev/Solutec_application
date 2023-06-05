package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Alert;
import fr.solutec.entities.Product;
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.AlertRepository;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;
import fr.solutec.services.HistoricServices;

@RestController
@CrossOrigin("*")
public class TypeProductRest {

	@Autowired
	private TypeProductRepository typeProductRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private AlertRepository alertRepo;
	
	@Autowired
	private HistoricServices historicServ;
	
	@GetMapping("typeProduct/liste")
	public Iterable<TypeProduct> getAll() {
		return typeProductRepo.findAllOrderedByNameProduct();
	}
	
	@PostMapping("typeProduct/add/{login}")
	public TypeProduct addTypeProduct(@RequestBody TypeProduct typeProduct, @PathVariable String login) {
		typeProductRepo.save(typeProduct);
		historicServ.addType(typeProduct, login);
		return typeProduct;
	}
	
	@DeleteMapping("typeProduct/delete/{idTypeProduct}/{login}")
	public void deleteTypeProduct(@PathVariable Long idTypeProduct, @PathVariable String login ) {
		Iterable<Product> allProducts = productRepo.findAll();
		Iterable<Alert> allAlerts = alertRepo.findAll();
		for (Product product : allProducts) {
			if(product.getTypeProduct() == typeProductRepo.findById(idTypeProduct).get()) {
				Optional<Product> p = productRepo.findById(product.getIdProduct());
				historicServ.delete(p, login);
				productRepo.deleteById(product.getIdProduct());
			}
		}
		for (Alert alert: allAlerts) {
			for(TypeProduct t: alert.getProducts()) {
				if(t == typeProductRepo.findById(idTypeProduct).get()) {
					alertRepo.deleteById(alert.getIdAlert());
				}
			}
		}
		historicServ.deleteTypeProduct(typeProductRepo.findById(idTypeProduct).get(), login);
		typeProductRepo.deleteById(idTypeProduct);
		
	}
	
	
}
