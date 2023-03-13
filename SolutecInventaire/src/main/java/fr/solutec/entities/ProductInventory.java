package fr.solutec.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class ProductInventory {
	@EmbeddedId
	private ProductInventoryConstraint id;
	
	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@MapsId("inventory_id")
	@JoinColumn(name = "inventory_id")
	private Inventory inventory;

}
