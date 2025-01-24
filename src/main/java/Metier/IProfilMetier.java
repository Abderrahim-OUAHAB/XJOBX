/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.Profils;

public interface IProfilMetier {
    String addProfil(Profils profil);
    Profils getProfilById(int id);
      Profils getProfilByIdUser(int id);
    String updateProfil(Profils profil);
    String deleteProfil(int id);
}
