package fr.solutec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.solutec.entities.User;
import fr.solutec.security.JwtUtils;
import fr.solutec.services.UserDetailsServiceImpl;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class authenticationRest {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@PostMapping("/authenticate")
    public Rep authenticate(@RequestBody User request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getLogin());
        if (userDetails != null) {
        	Rep rep = new Rep();
            rep.setContent(jwtUtils.generateToken(userDetails).toString());
            return rep;
            
        }
        Rep rep = new Rep();
        rep.setContent(ResponseEntity.status(400).body("Some error has occurred").toString());
        return rep;
    }
	
	
	

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Rep {
	private String content;
}