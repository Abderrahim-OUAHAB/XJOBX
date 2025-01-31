package DAO;

import Hibernate.HibernateUtil;
import MesBeans.Commentaires;
import Metier.ICommentaireMetier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CommentaireDAO implements ICommentaireMetier {

    @Override
    public String addCommentaire(Commentaires commentaire) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(commentaire);
            transaction.commit();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur est survenue: " + e.getMessage();
        }
    }

    @Override
    public Commentaires getCommentaireById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Commentaires.class, id);
        }
    }

    @Override
    public List<Commentaires> getCommentairesByOffreId(int idOffre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Commentaires c WHERE c.idOffre = :idOffre")
                    .setParameter("idOffre", idOffre)
                    .list();
        }
    }

    @Override
    public String updateCommentaire(Commentaires commentaire) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(commentaire);
            transaction.commit();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur: " + e.getMessage();
        }
    }

    @Override
    public String deleteCommentaire(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Commentaires commentaire = session.get(Commentaires.class, id);
            if (commentaire != null) {
                session.delete(commentaire);
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
