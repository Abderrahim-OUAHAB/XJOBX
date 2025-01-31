package MesBeans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Commentaires")

public class Commentaires implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commentaire")
    private Integer idCommentaire;

    @NotNull
    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    @NotNull
    @Column(name = "id_offre")
    private int idOffre;

    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "contenu")
    private String contenu;

    @Column(name = "date_commentaire")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommentaire;

    public Commentaires() {
    }

    public Commentaires(Integer idCommentaire, int idUtilisateur, int idOffre, String contenu) {
        this.idCommentaire = idCommentaire;
        this.idUtilisateur = idUtilisateur;
        this.idOffre = idOffre;
        this.contenu = contenu;
    }

    public Integer getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(Integer idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    @Override
    public int hashCode() {
        return (idCommentaire != null ? idCommentaire.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Commentaires)) {
            return false;
        }
        Commentaires other = (Commentaires) object;
        return (this.idCommentaire != null || other.idCommentaire == null) && (this.idCommentaire == null || this.idCommentaire.equals(other.idCommentaire));
    }

    @Override
    public String toString() {
        return "MesBeans.Commentaires[ idCommentaire=" + idCommentaire + " ]";
    }
}
