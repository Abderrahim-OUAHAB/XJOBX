package MesBeans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author minfo
 */
@Entity
@Table(name = "Likes")

@NamedQueries({
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l"),
    @NamedQuery(name = "Likes.findByIdLike", query = "SELECT l FROM Likes l WHERE l.idLike = :idLike"),
    @NamedQuery(name = "Likes.findByIdUtilisateur", query = "SELECT l FROM Likes l WHERE l.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Likes.findByIdOffre", query = "SELECT l FROM Likes l WHERE l.idOffre = :idOffre"),
    @NamedQuery(name = "Likes.findByDateLike", query = "SELECT l FROM Likes l WHERE l.dateLike = :dateLike")})
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_like")
    private Integer idLike;
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic(optional = false)
    @Column(name = "id_offre")
    private int idOffre;
    @Column(name = "date_like")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLike;

    public Likes() {
    }

    public Likes(Integer idLike) {
        this.idLike = idLike;
    }

    public Likes(Integer idLike, int idUtilisateur, int idOffre) {
        this.idLike = idLike;
        this.idUtilisateur = idUtilisateur;
        this.idOffre = idOffre;
    }

    public Integer getIdLike() {
        return idLike;
    }

    public void setIdLike(Integer idLike) {
        this.idLike = idLike;
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

    public Date getDateLike() {
        return dateLike;
    }

    public void setDateLike(Date dateLike) {
        this.dateLike = dateLike;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLike != null ? idLike.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.idLike == null && other.idLike != null) || (this.idLike != null && !this.idLike.equals(other.idLike))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesBeans.Likes[ idLike=" + idLike + " ]";
    }
    
}
