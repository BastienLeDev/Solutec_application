package fr.solutec.entities;


import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class Inventory {
	@Id @GeneratedValue
	private Long idInventory;
	private int numberInventory;
	@OneToMany(mappedBy = "inventory")
	private Set<ProductInventory> product;
	private Date dateInventory;

}
