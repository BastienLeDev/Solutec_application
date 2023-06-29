package fr.solutec.rest;

import fr.solutec.entities.ChangePassword;
import fr.solutec.repository.UserRepository;
import fr.solutec.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import fr.solutec.entities.User;
import fr.solutec.security.JwtResponse;
import fr.solutec.security.JwtUtils;
import fr.solutec.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class authenticationRest {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserServices userServ;
	
	/**
	 * Authentifie l'utilisateur.
	 * @param Un objet de type User.
	 * @return Un token généré si l'authentification est correcte.
	 */
	@PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getLogin());
        if (userDetails != null) {
        	final String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

	@PatchMapping("/changePassword/{loginUser}")
	public ResponseEntity<String> changePassword(@RequestBody ChangePassword request,@PathVariable String loginUser) {
		// Récupérer l'utilisateur actuellement authentifié
		User currentUser = userRepo.findByLogin(loginUser);

		// Vérifier la validité du mot de passe actuel
		if (!userServ.checkPassword(currentUser, request.getOldPassword())) {
			return ResponseEntity.badRequest().body("Le mot de passe actuel est incorrect.");
		}

		// Vérifier si les nouveaux mots de passe correspondent
		if (!request.getNewPassword().equals(request.getConfirmPassword())) {
			return ResponseEntity.badRequest().body("Les nouveaux mots de passe ne correspondent pas.");
		}

		// Mettre à jour le mot de passe de l'utilisateur
		userServ.changePassword(currentUser, request.getNewPassword());

		return ResponseEntity.ok("Le mot de passe a été changé avec succès.");
	}
}