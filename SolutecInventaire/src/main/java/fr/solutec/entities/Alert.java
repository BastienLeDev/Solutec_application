package fr.solutec.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Alert {
	@Id @GeneratedValue
	private Long idAlert;
	private String Alerte;
	@OneToMany
	@JoinTable(name = "typeProduct_alert",
	joinColumns = @JoinColumn(name = "idAlert"),
	inverseJoinColumns = @JoinColumn(name = "idTypeProduct"))
	private List<TypeProduct> products = new ArrayList<>();
	private int Seuil;
	private boolean Active;
	private boolean Triggered ;
	private boolean Email;
}
