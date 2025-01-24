/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Utilisateurs;
import Metier.IUtilisateurMetier;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minfo
 */
public class UtilisateurDAO implements IUtilisateurMetier{

    @Override
    public String addUtilisateur(Utilisateurs utilisateur) {
              try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Save the Personne object
        se.save(utilisateur);
        tr.commit();
        return "OK";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();  // Print the stack trace to the console or log it
        return "Une erreur a été survenue: " + e.getMessage();
    }
    }

    @Override
    public Utilisateurs getUtilisateurById(int id) {
         
           //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
            Utilisateurs user = (Utilisateurs) se.get(Utilisateurs.class, id);
            tr.commit();
            if (user != null) {
         
                    return user;
                } else {
                    return null;
                }  
    }

    @Override
    public Utilisateurs getUtilisateurByEmail(String email) {
         //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
        Utilisateurs user = (Utilisateurs) se.createQuery("FROM Utilisateurs u WHERE u.email = :email")
                                          .setParameter("email", email)
                                          .uniqueResult();
            tr.commit();
            if (user != null) {
         
                    return user;
                } else {
                    return null;
                }  
    }

    @Override
    public String updateUtilisateur(Utilisateurs updatedUser) {
     try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the existing user
        Utilisateurs existingUser = se.get(Utilisateurs.class, updatedUser.getIdUtilisateur());
        if (existingUser != null) {
            // Update user properties
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setNom(updatedUser.getNom());
            existingUser.setPhotoProfil(updatedUser.getPhotoProfil());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setMotDePasse(updatedUser.getMotDePasse());
            existingUser.setDateCreation(new Date());
            // Ajoutez d'autres champs nécessaires ici

            se.update(existingUser);
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
    public String deleteUtilisateur(int id) {
  try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the user
        Utilisateurs user = se.get(Utilisateurs.class, id);
        if (user != null) {
            // Delete the user
            se.delete(user);
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

    @Override
    public String authentification(Utilisateurs utilisateur) {
        //Conserver les valeurs saisies par l’utilisateur dans des variables temporaires
         
            String email = utilisateur.getEmail();
            String passWord = utilisateur.getMotDePasse();
           //Création des paramètres de connexion à la BD
            Session se = HibernateUtil.getSessionFactory().openSession();
            Transaction tr = se.beginTransaction();
            //Obtention d’une occurrence à partir de son identifiant
              // Recherche de l'utilisateur par email en utilisant une requête HQL
    Utilisateurs user = (Utilisateurs) se.createQuery("FROM Utilisateurs u WHERE u.email = :email")
                                          .setParameter("email", email)
                                          .uniqueResult();
            tr.commit();
            if (user != null) {
                if (user.getEmail().equals(email) &&
       (user.getMotDePasse().equals(passWord))) {
                    return "OK";
                } else {
                    return "KO";
                }  } else {
                return "KO";
} 
    }
    
}
