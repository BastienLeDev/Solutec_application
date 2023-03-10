package fr.solutec.rest;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.Product;
import fr.solutec.repository.ProductRepository;

@RestController
@CrossOrigin("*")
public class ProductRest {
	@Autowired
	private ProductRepository productRepo;
	
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
	
	@DeleteMapping("delete/{idProduct}") //API Supprimer un article
	public Boolean deleteProduct(@PathVariable Long idProduct){
		Optional<Product> p = productRepo.findById(idProduct);
		if(p.get() != null) {
			productRepo.deleteById(idProduct);
			return true;
		}else {
			return false;
		}
	}
	
}
