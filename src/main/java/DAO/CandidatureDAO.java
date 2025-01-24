/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Candidatures;
import Metier.ICandidatureMetier;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minfo
 */
public class CandidatureDAO implements ICandidatureMetier{

    @Override
    public String addCandidature(Candidatures candidature) {
                     try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Save the Personne object
        se.save(candidature);
        tr.commit();
        return "OK";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();  // Print the stack trace to the console or log it
        return "Une erreur a été survenue: " + e.getMessage();
    }
    }

    @Override
    public Candidatures getCandidatureById(int id) {
              //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
            Candidatures candidature = (Candidatures) se.get(Candidatures.class, id);
            tr.commit();
            if (candidature != null) {
         
                    return candidature;
                } else {
                    return null;
                }  
    }

    @Override
    public List<Candidatures> getCandidaturesByUtilisateurId(int utilisateurId) {
        //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
            List<Candidatures> candidatures = (List<Candidatures>) se.get(Candidatures.class, utilisateurId);
            tr.commit();
            if (candidatures != null) {
         
                    return candidatures;
                } else {
                    return null;
                }
    }

    @Override
    public String updateCandidature(Candidatures candidature) {
 try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the existing user
        Candidatures existingCandidature = se.get(Candidatures.class, candidature.getIdCandidature());
        if (existingCandidature != null) {
            // Update user properties
            existingCandidature.setIdUtilisateur(candidature.getIdUtilisateur());
            existingCandidature.setIdOffre(candidature.getIdOffre());
            existingCandidature.setDatePostulation(candidature.getDatePostulation());
            existingCandidature.setStatut(candidature.getStatut());
    
            // Ajoutez d'autres champs nécessaires ici

            se.update(existingCandidature);
            tr.commit();
            return "OK";
        } else {
            return "KO";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return "Error occurred: " + e.getMessage();
    }
    }

    @Override
    public String deleteCandidature(int id) {
try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the user
        Candidatures candidature = se.get(Candidatures.class, id);
        if (candidature != null) {
            // Delete the user
            se.delete(candidature);
            tr.commit();
            return "OK";
        } else {
            return "KO";
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log the exception
        return "KO";
    }
    }
    
}
