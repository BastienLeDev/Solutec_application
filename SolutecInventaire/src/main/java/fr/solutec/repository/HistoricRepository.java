package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Historic;

public interface HistoricRepository extends CrudRepository<Historic, Long> {

}
