package fr.solutec.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Alert;

public interface AlertRepository extends CrudRepository<Alert, Long> {
	
	@Query("SELECT a FROM Alert a WHERE Triggered = true AND Active = true")
	public Iterable<Alert> findTrigger();

}
