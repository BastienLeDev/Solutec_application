package fr.solutec.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@OneToOne
	private User user;
	@OneToOne
	private Product productBefore;
	@OneToOne
	private Product productAfter;
	private String typeModif;
}
