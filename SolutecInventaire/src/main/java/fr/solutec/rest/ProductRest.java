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
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;


@RestController
@CrossOrigin("*")
public class ProductRest {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private TypeProductRepository typeProductRepo;
	
	
	@GetMapping("liste") // API pour avoir la liste de tout le matériel
	public Iterable<Product> getAll(){
		return productRepo.findAll();
	}
	
	@GetMapping("filter1/{nameProduct}") //API pour filtrer par type de matériel
	public Iterable<Product> getByProduct(@PathVariable String nameProduct){
		return productRepo.findByNameProduct(nameProduct);
	}
	
	@GetMapping("filter2/{refProduct}") //API pour filtrer par la référence du produit
	public Iterable<Product> getByReference(@PathVariable String refProduct){
		return productRepo.findByRefProduct(refProduct);
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
	
	@PostMapping("add/database") //API Ajouter un article (dans la BDD/stock)
	public Boolean addProduct(@RequestBody Product product ){
	
			productRepo.save(product);
		
		return true;
		}
	
	@PatchMapping("patch/product")
	public Boolean patchProduct(@RequestBody Product product ) {
		Product p = productRepo.findById(product.getIdProduct()).get();
		p.setTypeProduct(typeProductRepo.findByNameProduct(product.getTypeProduct().getNameProduct()).get()) ;
		p.setRefProduct(product.getRefProduct());
		p.setOwner(product.getOwner());
		p.setEntryDate(product.getEntryDate());
		p.setExitDate(product.getExitDate());
		p.setReservation(product.isReservation());
		productRepo.save(p);
		return true;
	}
	
	@GetMapping("getReservation") //API pour voir le nombre de PC en stock
	public Iterable<Product> getReservation(){
		return productRepo.findReservation() ;
	}
	
	@GetMapping("products/getStock") //API pour avoir les types de produits
	public List<List<String>> getStock(){
			List<List<String>> listStock = new ArrayList<>();
			Iterable<TypeProduct> listTypeProduct = typeProductRepo.findAllOrderedByNameProduct();
			List<String> listAutres = new ArrayList<>();
			for (TypeProduct typeProduct : listTypeProduct) {
				List<String> listByTypeProduct = new ArrayList<>();
				String nameProduct = typeProduct.getNameProduct();
				String autres = "Autres";
				if(nameProduct.equals(autres)) {
					long dispoAutres = productRepo.findStock(nameProduct);
					long totalAutres = productRepo.findTotal(nameProduct);
					String stockByProduct = dispoAutres + " / " + totalAutres ;
					listAutres.add(nameProduct);
					listAutres.add(stockByProduct);
				}
				else {
				long dispo = productRepo.findStock(nameProduct);
				long total = productRepo.findTotal(nameProduct);
				String stockByProduct = dispo + " / " + total ;
				listByTypeProduct.add(nameProduct);
				listByTypeProduct.add(stockByProduct);
				listStock.add(listByTypeProduct);	
				}
				
			}
			if(listAutres.isEmpty() != true) {
				listStock.add(listAutres);
			}
			return listStock;
		}
	
	
	@GetMapping("removeReservation/{idProduct}") //API pour voir le nombre de PC en stock
	public Product removeReservation(@PathVariable Long idProduct){
		Optional<Product> p = productRepo.findById(idProduct);
		p.get().setReservation(false);
		return productRepo.save(p.get()) ;
	}
}
