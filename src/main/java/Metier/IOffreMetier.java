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
    List<Offres> getAllOffres();
    String updateOffre(Offres offre);
    String deleteOffre(int id);
}
