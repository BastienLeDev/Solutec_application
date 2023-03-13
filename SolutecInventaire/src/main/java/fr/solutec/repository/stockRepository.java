package fr.solutec.repository;


import org.hibernate.mapping.Set;
import org.springframework.data.repository.CrudRepository;


import fr.solutec.entities.Stock;

public interface stockRepository extends CrudRepository<Stock, Long> {

	
}
