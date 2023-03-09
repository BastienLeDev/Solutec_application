package fr.solutec.entities;

import jakarta.annotation.Nonnull;
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
public class User {
	@Id @GeneratedValue
	private Long idUser;
	private String lastname;
	private String firstname;
	@Column(unique = true)
	@Nonnull
	private String login;
	@Column(unique = true)
	@Nonnull
	private String password;

}
