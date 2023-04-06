package fr.solutec.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Alert {
	@Id @GeneratedValue
	private Long idAlert;
	private String nameAlert;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Product> products = new ArrayList<>();
	private int level;
	private boolean state;
	private boolean triggered ;
	private boolean notifEmail;
}
