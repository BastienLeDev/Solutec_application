package fr.solutec.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.TypeProduct;

public interface TypeProductRepository extends CrudRepository<TypeProduct, Long>{

	public Optional<TypeProduct> findByNameProduct(String nameProduct);
}
