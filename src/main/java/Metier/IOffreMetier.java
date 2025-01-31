/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.Offres;
import java.util.List;

public interface IOffreMetier {
    String addOffre(Offres offre);
    Offres getOffreById(int id);
    Offres getOffreByUserIdAndOffre(int useriId,int offerId);
    List<Offres> getAllOffres();
    Offres getAllOffresByCompetence(String compt);
    String updateOffre(Offres offre);
    String deleteOffre(int id);
    List<Offres> rechercherOffres(String titre, String competences, String recruteur);
}
