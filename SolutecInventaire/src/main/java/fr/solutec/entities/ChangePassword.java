package fr.solutec.entities;

import lombok.Data;

@Data
public class ChangePassword { //Classe POJO pour le changement de mot de passe
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
