package fr.solutec.repository;

import org.springframework.data.repository.CrudRepository;

import fr.solutec.entities.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}
