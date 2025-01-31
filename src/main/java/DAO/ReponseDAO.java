package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Reponses;
import Metier.IReponseMetier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ReponseDAO implements IReponseMetier {

    @Override
    public String addReponse(Reponses reponse) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(reponse);
            transaction.commit();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue: " + e.getMessage();
        }
    }

    @Override
    public Reponses getReponseById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Reponses.class, id);
        }
    }

    @Override
    public List<Reponses> getReponsesByCommentaireId(int idCommentaire) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Reponses r WHERE r.idCommentaire = :idCommentaire")
                    .setParameter("idCommentaire", idCommentaire)
                    .list().reversed();
        }
    }

    @Override
    public String updateReponse(Reponses reponse) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(reponse);
            transaction.commit();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur: " + e.getMessage();
        }
    }

    @Override
    public String deleteReponse(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Reponses reponse = session.get(Reponses.class, id);
            if (reponse != null) {
                session.delete(reponse);
                transaction.commit();
                return "OK";
            }
            return "KO";
        } catch (Exception e) {
            e.printStackTrace();
            return "KO";
        }
    }
}
