/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Offres;
import Metier.IOffreMetier;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minfo
 */
public class OffreDAO implements IOffreMetier {

    @Override
    public String addOffre(Offres offre) {
             try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Save the Personne object
        se.save(offre);
        tr.commit();
        return "OK";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();  // Print the stack trace to the console or log it
        return "Une erreur a été survenue: " + e.getMessage();
    }    }

    @Override
    public Offres getOffreById(int id) {
  //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
            Offres offre = (Offres) se.get(Offres.class, id);
            tr.commit();
            if (offre != null) {
         
                    return offre;
                } else {
                    return null;
                }      }

    @Override
    public List<Offres> getAllOffres() {
          try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve all users
        List<Offres> offres = se.createQuery("from Offres", Offres.class).list();
        tr.commit();
        return offres;
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace(); // Print the stack trace to the console or log it
        return null; // Or handle the error appropriately
    }
    }

    @Override
    public String updateOffre(Offres offre) {
    try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the existing user
        Offres existingOffre = se.get(Offres.class, offre.getIdOffre());
        if (existingOffre != null) {
            // Update user properties
            existingOffre.setTitre(offre.getTitre());
            existingOffre.setDescription(offre.getDescription());
            existingOffre.setCompetences(offre.getCompetences());
            existingOffre.setDatePublication(offre.getDatePublication());
            existingOffre.setIdRecruteur(offre.getIdRecruteur());
            // Ajoutez d'autres champs nécessaires ici

            se.update(existingOffre);
            tr.commit();
            return "OK";
        } else {
            return "KO";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return "Error occurred: " + e.getMessage();
    }    }

    @Override
    public String deleteOffre(int id) {
  try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the user
        Offres offre = se.get(Offres.class, id);
        if (offre != null) {
            // Delete the user
            se.delete(offre);
            tr.commit();
            return "OK";
        } else {
            return "KO";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return "KO";
    }    }
    
}
