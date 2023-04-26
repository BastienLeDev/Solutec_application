package fr.solutec.repository;

import java.sql.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.typeProduct.nameProduct = ?1")
	public Iterable<Product> findByNameProduct(String nameProduct);
	
	public Iterable<Product> findByRefProduct(String refProduct);
	
	@Query("SELECT p FROM Product p WHERE owner LIKE %?1%")
	public Iterable<Product> findWithOwner(String name);
	
	
	
	public Iterable<Product> findByEntryDate(Date entryDate);
	
	public Iterable<Product> findByExitDate(Date exitDate);
	
	
	
	
	@Query("SELECT p FROM Product p WHERE owner = null")
	public Set<Product> findStock();
	
	@Query("SELECT p FROM Product p WHERE owner <> null")
	public Set<Product> findNotStock();
	
	@Query("SELECT COUNT(p) FROM Product p WHERE p.owner = null AND p.typeProduct.nameProduct = ?1")
	public long findStockPC(String nameProduct);
	
	@Query("SELECT COUNT(p) FROM Product p WHERE p.typeProduct.nameProduct = ?1")
	public long findTotalPC(String nameProduct);
	
	@Query("SELECT p FROM Product p WHERE reservation = true")
	public Iterable<Product> findReservation();

}
