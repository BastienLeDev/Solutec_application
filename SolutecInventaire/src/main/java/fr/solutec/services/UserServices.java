package fr.solutec.services;

import fr.solutec.entities.User;
import fr.solutec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public boolean checkPassword(User user, String rawPassword) { //Vérification du mdp brut et mdp de la base de données chiffré
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void changePassword(User user, String newPassword) { //Changement du mot de passe
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }
}
