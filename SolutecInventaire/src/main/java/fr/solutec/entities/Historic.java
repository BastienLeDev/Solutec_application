package fr.solutec.entities;


import java.util.Date;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Historic {
	@Id @GeneratedValue
	private Long idHistoric;
	private Date dateHistoric;
	private String user;
	@ManyToOne
	@JoinTable(name = "typeProduct_historic",
	joinColumns = @JoinColumn(name = "idHistoric"),
	inverseJoinColumns = @JoinColumn(name = "idTypeProduct"))
	private TypeProduct typeProduct;
	@Nullable
	private String refProductB;
	private String ownerB; // B=> Before
	private Date entryDateB; 
	private Date exitDateB; 
	private boolean reservationB;
	@Nullable
	private String refProductA;
	private String ownerA; // A=> After
	private Date entryDateA; 
	private Date exitDateA; 
	private boolean reservationA;
	private String typeModif;
}
