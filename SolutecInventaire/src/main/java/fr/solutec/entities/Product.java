package fr.solutec.entities;





import java.sql.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class Product {
	@Id @GeneratedValue
	private Long idProduct;
	private String nameProduct;
	@Column(unique=true)
	@Nullable
	private String refProduct;
	private long inventory;
	private String owner;
	private Date entryDate;
	private Date exitDate;
	private int inStock;
	private int outOfStock;

}
