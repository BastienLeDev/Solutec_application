package fr.solutec.entities;

import java.sql.Date;


import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
@Entity
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduct;
	@ManyToOne
	@JoinTable(name = "typeProduct_product",
	joinColumns = @JoinColumn(name = "idProduct"),
	inverseJoinColumns = @JoinColumn(name = "idTypeProduct"))

	private TypeProduct typeProduct;
	@Column(unique=true)
	@Nullable
	private String refProduct;
	private String owner; //Responsable du matériel
	private Date entryDate; //Entrée en stock
	private Date exitDate; //Sortie de stock
	private boolean reservation;


}	