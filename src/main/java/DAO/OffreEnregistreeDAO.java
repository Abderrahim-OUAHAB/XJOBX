/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Hibernate.HibernateUtil;
import MesBeans.OffresEnregistrees;
import Metier.IOffreEnregistreeMetier;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minfo
 */
public class OffreEnregistreeDAO implements IOffreEnregistreeMetier{

    @Override
    public String addOffreEnregistree(OffresEnregistrees offreEnregistree) {
           try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Save the Personne object
        se.save(offreEnregistree);
        tr.commit();
        return "OK";
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();  // Print the stack trace to the console or log it
        return "Une erreur a été survenue: " + e.getMessage();
    }
    }

    @Override
    public String deleteOffreEnregistree(int id) {
         try {
        // Create connection to the database
        Session se = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = se.beginTransaction();

        // Retrieve the user
        OffresEnregistrees offresEnregistrees = se.get(OffresEnregistrees.class, id);
        if (offresEnregistrees != null) {
            // Delete the offresEnregistrees
            se.delete(offresEnregistrees);
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
