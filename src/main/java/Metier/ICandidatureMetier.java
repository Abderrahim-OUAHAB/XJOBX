/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.Candidatures;
import MesBeans.Likes;
import java.util.List;

public interface ICandidatureMetier {
    String addCandidature(Candidatures candidature);
    Candidatures getCandidatureById(int id);
    List<Candidatures> getCandidaturesByUtilisateurId(int utilisateurId);
    String updateCandidature(Candidatures candidature);
    String deleteCandidature(int id);
      Candidatures getCandidatureByUserAndOffer(int userId, int offreId) ;
      List<Object[]> getCandidaturesByRecruteurId(int recruteurId);
}
