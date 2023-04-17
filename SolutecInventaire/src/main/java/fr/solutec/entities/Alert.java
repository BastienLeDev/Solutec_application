package fr.solutec.entities;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private List<String> products = new ArrayList<>();
	private int Seuil;
	private boolean Active;
	private boolean Triggered ;
	private boolean Email;
}
