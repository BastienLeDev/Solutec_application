package fr.solutec.repository;

import java.sql.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public Iterable<Product> findByNameProduct(String nameProduct);
	
	public Iterable<Product> findByInventory(long inventory);
	
	@Query("SELECT p FROM Product p WHERE owner LIKE %?1%")
	public Iterable<Product> findWithOwner(String name);
	
	public Iterable<Product> findByEntryDate(Date entryDate);
	
	public Iterable<Product> findByExitDate(Date exitDate);
	
	@Query("SELECT p FROM Product p WHERE owner = null")
	public Set<Product> findStock();

}
