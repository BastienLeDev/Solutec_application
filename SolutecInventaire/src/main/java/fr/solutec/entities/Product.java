package fr.solutec.entities;

import java.sql.Date;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
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
public class Product {
	@Id @GeneratedValue
	private Long idProduct;
	private String nameProduct;
	@Column(unique=true)
	@Nullable
	private String refProduct;
	private String owner; //Responsable du matériel
	private Date entryDate; //Entrée en stock
	private Date exitDate; //Sortie de stock


}	