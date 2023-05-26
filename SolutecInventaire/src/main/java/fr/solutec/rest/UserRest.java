package fr.solutec.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.User;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserRest {
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("user")
	public Optional<User> postByLoginAndPassword(@RequestBody User u) {
		return userRepo.findByLoginAndPassword(u.getLogin(), u.getPassword());
	}
	
	 @Autowired
	  private PasswordEncoder passwordEncoder;

	  @Autowired
	  private UserRepository userRepository;
	  
	  
	  /**
		 * Ajoute un utilisateur dans la BDD.
		 * @param Un objet de type User.
		 * @return L'utilisateur créé un enregistré dans la BDD.
		 */
	  @PostMapping("user/registration") //API pour créer un utilisateur
	  public User Creation(@RequestBody User u){
		  u.setPassword(passwordEncoder.encode(u.getPassword()));
		  return userRepository.save(u);
	  }
}
