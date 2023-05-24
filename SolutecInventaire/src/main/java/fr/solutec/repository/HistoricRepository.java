package fr.solutec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Historic;

public interface HistoricRepository extends CrudRepository<Historic, Long> {
	
	@Query("SELECT h FROM Historic h WHERE DATEDIFF(NOW(), h.dateHistoric) > 60")
	public Iterable<Historic> getOldHistoric();
}
