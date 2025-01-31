package Metier;

import MesBeans.Reponses;
import java.util.List;

public interface IReponseMetier {
    String addReponse(Reponses reponse);
    Reponses getReponseById(int id);
    List<Reponses> getReponsesByCommentaireId(int idCommentaire);
    String updateReponse(Reponses reponse);
    String deleteReponse(int id);
}
