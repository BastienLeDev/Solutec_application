package fr.solutec.rest;

import java.util.Optional;

import fr.solutec.entities.Roles;
import fr.solutec.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import fr.solutec.entities.User;
import fr.solutec.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserRest {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RolesRepository rolesRepo;
	
	@PostMapping("user")
	public Optional<User> postByLoginAndPassword(@RequestBody User u) {
		return userRepo.findByLoginAndPassword(u.getLogin(), u.getPassword());
	}
	
	 @Autowired
	  private PasswordEncoder passwordEncoder;
	  
	  /**
		 * Ajoute un utilisateur dans la BDD.
		 * @param Un objet de type User.
		 * @return L'utilisateur créé et enregistré dans la BDD.
		 */
	  @PostMapping("user/registration/{roleName}") //API pour créer un utilisateur avec un rôle
	  public User Creation(@RequestBody User user, @PathVariable String roleName){
		  User u = new User();
		  Roles r = rolesRepo.findByRoleName(roleName);
		  u.setLogin(user.getLogin());
		  u.getRoles().add(r);
		  u.setPassword(passwordEncoder.encode(user.getPassword()));
		  return userRepo.save(u);
	  }
}
