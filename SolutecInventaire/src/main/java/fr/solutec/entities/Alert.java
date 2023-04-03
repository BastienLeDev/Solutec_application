package fr.solutec.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Alert {
	@Id @GeneratedValue
	private Long idProduct;
	private String nameAlert;
	@OneToMany
	private Set<Product> productAlert;
	private int limit;
	private boolean active;
	private boolean trigger = false;
	private boolean notifEmail = false;
}
