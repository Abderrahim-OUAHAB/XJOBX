/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Metier;

import MesBeans.OffresEnregistrees;

public interface IOffreEnregistreeMetier {
    String addOffreEnregistree(OffresEnregistrees offreEnregistree);
    String deleteOffreEnregistree(int id);
}
