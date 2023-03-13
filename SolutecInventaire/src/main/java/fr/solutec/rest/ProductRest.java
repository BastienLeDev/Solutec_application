package fr.solutec.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Product;
import fr.solutec.entities.Stock;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.stockRepository;

@RestController
@CrossOrigin("*")
public class ProductRest {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private stockRepository stockRepo;
	
	@GetMapping("liste") // API pour avoir la liste de tout le matériel
	public Iterable<Product> getAll(){
		return productRepo.findAll();
	}
	
	@GetMapping("filter1/{nameProduct}") //API pour filtrer par type de matériel
	public Iterable<Product> getByProduct(@PathVariable String nameProduct){
		return productRepo.findByNameProduct(nameProduct);
	}
	
	@GetMapping("filter2/{inventory}")//API pour filtrer par n° d'inventaire
	public Iterable<Product> getByInventory(@PathVariable long inventory){
		return productRepo.findByInventory(inventory);
	}
	
	@GetMapping("filter3/{owner}")// API pour filtrer par responsable
	public Iterable<Product> getByOwner(@PathVariable String owner){
		return productRepo.findWithOwner(owner);
	}
	
	@GetMapping("filter4/{entryDate}") //API Filtrer par date d'entrée
	public Iterable<Product> getByEntryDate(@PathVariable Date entryDate){
		return productRepo.findByEntryDate(entryDate);
	}
	
	@GetMapping("filter5/{exitDate}") //API Filtrer par date d'entrée
	public Iterable<Product> getByExitDate(@PathVariable Date exitDate){
		return productRepo.findByExitDate(exitDate);
	}
	
	@DeleteMapping("delete/{idProduct}") //API Supprimer un article (Suppression de la BDD)
	public Boolean deleteProduct(@PathVariable Long idProduct){
		Optional<Product> p = productRepo.findById(idProduct);
		if(p.get() != null) {
			productRepo.deleteById(idProduct);
			return true;
		}else {
			return false;
		}
	}
	
	@PostMapping("add/database/{idStock}") //API Ajouter un article (dans la BDD/stock) (idStock = 1)
	public Boolean addProduct(@PathVariable Long idStock, @RequestBody List<Product> products ){
		Optional<Stock> s = stockRepo.findById(idStock);
		for(Product p : products) {
			productRepo.save(p);
			s.get().getProduct().add(p);
			stockRepo.save(s.get());
		}
		return true;
		}
	
	@PutMapping("backToStock") //API Enlever responsable à des articles pour retour au stock 
	public Iterable<Product> backToStock(@RequestBody List<Product> products){
		List<Product> prod = new ArrayList<Product>();
		Optional <Product> pro;
		for(Product p : products) {
			pro = productRepo.findById(p.getIdProduct());
			pro.get().setEntryDate(p.getEntryDate());
			pro.get().setOwner(p.getOwner());
			pro.get().setExitDate(null);
			prod.add(pro.get());
		}
		return productRepo.saveAll(prod);
	}
	
	@PutMapping("removeFromStock") //API pour assigner un article à un responsable avec date de sortie 
	public Iterable<Product> removeFromStock(@RequestBody List<Product> products){
		List<Product> prod = new ArrayList<Product>();
		Optional <Product> pro;
		for(Product p : products) {
			pro = productRepo.findById(p.getIdProduct());
			pro.get().setExitDate(p.getExitDate());
			pro.get().setOwner(p.getOwner());
			prod.add(pro.get());
		}
		return productRepo.saveAll(prod);
	}
	
	
}
