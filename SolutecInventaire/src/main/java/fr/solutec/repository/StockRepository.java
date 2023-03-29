package fr.solutec.repository;



import org.springframework.data.repository.CrudRepository;


import fr.solutec.entities.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {

	
}
