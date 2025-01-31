package MesBeans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Reponses")

public class Reponses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reponse")
    private Integer idReponse;

    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    @Basic(optional = false)
    @Column(name = "id_commentaire")
    private int idCommentaire;

    @Basic(optional = false)
    @Lob
    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date_reponse")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReponse;

    public Reponses() {
    }

    public Reponses(Integer idReponse, int idUtilisateur, int idCommentaire, String contenu) {
        this.idReponse = idReponse;
        this.idUtilisateur = idUtilisateur;
        this.idCommentaire = idCommentaire;
        this.contenu = contenu;
    }

    public Integer getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Integer idReponse) {
        this.idReponse = idReponse;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(Date dateReponse) {
        this.dateReponse = dateReponse;
    }

    @Override
    public int hashCode() {
        return (idReponse != null ? idReponse.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Reponses)) {
            return false;
        }
        Reponses other = (Reponses) object;
        return (this.idReponse != null || other.idReponse == null) && (this.idReponse == null || this.idReponse.equals(other.idReponse));
    }

    @Override
    public String toString() {
        return "MesBeans.Reponses[ idReponse=" + idReponse + " ]";
    }
}
