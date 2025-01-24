/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Profils;
import Metier.IProfilMetier;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minfo
 */
public class ProfilDAO implements IProfilMetier {

    @Override
    public String addProfil(Profils profil) {
 try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Save the Personne object
        se.save(profil);
        tr.commit();
        return "OK";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();  // Print the stack trace to the console or log it
        return "Une erreur a été survenue: " + e.getMessage();
    }    }

    @Override
    public Profils getProfilById(int id) {
 //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
            Profils profil = (Profils) se.get(Profils.class, id);
            tr.commit();
            if (profil != null) {
         
                    return profil;
                } else {
                    return null;
                }      }

    @Override
    public String updateProfil(Profils profil) {
   try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the existing user
        Profils existingProfil = se.get(Profils.class, profil.getIdProfil());
        if (existingProfil != null) {
            // Update user properties
            existingProfil.setIdUtilisateur(profil.getIdUtilisateur());
            existingProfil.setCompetences(profil.getCompetences());
            existingProfil.setExperience(profil.getExperience());
            existingProfil.setDateCreation(new Date());
            // Ajoutez d'autres champs nécessaires ici

            se.update(existingProfil);
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
    public String deleteProfil(int id) {
 try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the user
        Profils profil = se.get(Profils.class, id);
        if (profil != null) {
            // Delete the user
            se.delete(profil);
            tr.commit();
            return "OK";
        } else {
            return "KO";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return "KO";
    }    }

    @Override
    public Profils getProfilByIdUser(int id) {
              //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
        Profils user = (Profils) se.createQuery("SELECT p FROM Profils p WHERE p.idUtilisateur = :idUtilisateur")
                                          .setParameter("idUtilisateur", id)
                                          .uniqueResult();
            tr.commit();
            if (user != null) {
         
                    return user;
                } else {
                    return null;
                } 
    }
    
}
