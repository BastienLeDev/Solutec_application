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
	  
	  @PostMapping("user/registration") //API pour créer un utilisateur
	  public User Creation(@RequestBody User u){
		  u.setPassword(passwordEncoder.encode(u.getPassword()));
		  return userRepository.save(u);
	  }
	  
	  
	  @GetMapping("user/liste") //API nécéssitant une authentification
	  public Iterable<User> VoirListe(){
		  return userRepository.findAll();
		  }
	  

	  @GetMapping("user/coucou") //API accessible pour tout le monde
	    public ResponseEntity<String> sayGoodBye() {
	        return ResponseEntity.ok("Good by and see you later");
	    }

}
