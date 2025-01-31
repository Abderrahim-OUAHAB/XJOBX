package Metier;

import MesBeans.Commentaires;
import java.util.List;

public interface ICommentaireMetier {
    String addCommentaire(Commentaires commentaire);
    Commentaires getCommentaireById(int id);
    List<Commentaires> getCommentairesByOffreId(int idOffre);
    String updateCommentaire(Commentaires commentaire);
    String deleteCommentaire(int id);
}
