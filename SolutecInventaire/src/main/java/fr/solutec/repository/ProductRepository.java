package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	public Iterable<Product> findByNameProduct(String nameProduct);

}
