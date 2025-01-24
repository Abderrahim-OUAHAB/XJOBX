/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.Utilisateurs;

public interface IUtilisateurMetier {
    String addUtilisateur(Utilisateurs utilisateur);
    Utilisateurs getUtilisateurById(int id);
    Utilisateurs getUtilisateurByEmail(String email);
    String updateUtilisateur(Utilisateurs utilisateur);
    String deleteUtilisateur(int id);
    String authentification(Utilisateurs utilisateur);
}
