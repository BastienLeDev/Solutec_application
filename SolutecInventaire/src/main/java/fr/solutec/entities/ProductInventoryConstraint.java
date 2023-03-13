package fr.solutec.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProductInventoryConstraint implements Serializable {
	@Column(name="product_id")
	private Long productId;
	@Column(name="inventory_id")
	private Long inventoryId;

}
