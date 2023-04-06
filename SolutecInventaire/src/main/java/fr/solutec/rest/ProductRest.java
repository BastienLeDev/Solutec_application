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
		p.setNameProduct(product.getNameProduct());
		p.setRefProduct(product.getRefProduct());
		p.setOwner(product.getOwner());
		p.setEntryDate(product.getEntryDate());
		p.setExitDate(product.getExitDate());
		productRepo.save(p);
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
			pro.get().setEntryDate(null);
			prod.add(pro.get());
		}
		return productRepo.saveAll(prod);
	}
	
	@GetMapping("getStockPc") //API pour voir le nombre de PC en stock
	public String getStockPC(String nameProduct){
		nameProduct = "PC intercontrat";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockSouris") //API pour voir le nombre de PC en stock
	public String getStockSouris(String nameProduct){
		nameProduct = "Souris";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockBadgeStructure") //API pour voir le nombre de PC en stock
	public String getStockBadgeStructure(String nameProduct){
		nameProduct = "Badge structure";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockBadgeIntercontrat") //API pour voir le nombre de PC en stock
	public String getStockBadgeIntercontrat(String nameProduct){
		nameProduct = "Badge intercontrat";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockAlim") //API pour voir le nombre de PC en stock
	public String getStockAlim(String nameProduct){
		nameProduct = "Alimentation";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockPosteMission") //API pour voir le nombre de PC en stock
	public String getStockPosteMission(String nameProduct){
		nameProduct = "Poste mission";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockPosteStructure") //API pour voir le nombre de PC en stock
	public String getStockPosteStructure(String nameProduct){
		nameProduct = "Poste structure";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockBase") //API pour voir le nombre de PC en stock
	public String getStockBase(String nameProduct){
		nameProduct = "Base";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockTelephoneMission") //API pour voir le nombre de PC en stock
	public String getStockTelephoneMission(String nameProduct){
		nameProduct = "Téléphone mission";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockTelephoneStructure") //API pour voir le nombre de PC en stock
	public String getStockTelephoneStructure(String nameProduct){
		nameProduct = "Téléphone structure";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockCasqueMission") //API pour voir le nombre de PC en stock
	public String getStockCasqueMission(String nameProduct){
		nameProduct = "Casque mission";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockCasqueStructure") //API pour voir le nombre de PC en stock
	public String getStockCasqueStructure(String nameProduct){
		nameProduct = "Casque structure";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockEcranTeletravail") //API pour voir le nombre de PC en stock
	public String getStockEcranTeletravail(String nameProduct){
		nameProduct = "Ecran télétravail";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockEcranStructure") //API pour voir le nombre de PC en stock
	public String getStockEcranStructure(String nameProduct){
		nameProduct = "Ecran structure";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockPCINT") //API pour voir le nombre de PC en stock
	public String getStockPCINT(String nameProduct){
		nameProduct = "PC INT";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockSupportPC") //API pour voir le nombre de PC en stock
	public String getStockSupportPC(String nameProduct){
		nameProduct = "Support PC";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockClavier") //API pour voir le nombre de PC en stock
	public String getStockClavier(String nameProduct){
		nameProduct = "Clavier";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockCablesHDMI") //API pour voir le nombre de PC en stock
	public String getStockCablesHDMI(String nameProduct){
		nameProduct = "Cables HDMI";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockCablesEthernet") //API pour voir le nombre de PC en stock
	public String getStockCablesEthernet(String nameProduct){
		nameProduct = "Cables ethernet";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
	@GetMapping("getStockCoqueTelephone") //API pour voir le nombre de PC en stock
	public String getStockCoqueTelephone(String nameProduct){
		nameProduct = "Coque téléphone";
		long dispo = productRepo.findStockPC(nameProduct);
		long total = productRepo.findTotalPC(nameProduct);
		return dispo + " / " + total ;
	}
	
}
