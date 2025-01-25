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
@Table(name = "Candidatures")

public class Candidatures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_candidature")
    private Integer idCandidature;
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic(optional = false)
    @Column(name = "id_offre")
    private int idOffre;
    @Basic(optional = false)
    @Column(name = "date_postulation")
    @Temporal(TemporalType.DATE)
    private Date datePostulation;
    @Basic(optional = false)
    @Column(name = "statut")
    private String statut;
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    public Candidatures() {
    }

    public Candidatures(Integer idCandidature) {
        this.idCandidature = idCandidature;
    }

    public Candidatures(Integer idCandidature, int idUtilisateur, int idOffre, Date datePostulation, String statut) {
        this.idCandidature = idCandidature;
        this.idUtilisateur = idUtilisateur;
        this.idOffre = idOffre;
        this.datePostulation = datePostulation;
        this.statut = statut;
    }

    public Integer getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(Integer idCandidature) {
        this.idCandidature = idCandidature;
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

    public Date getDatePostulation() {
        return datePostulation;
    }

    public void setDatePostulation(Date datePostulation) {
        this.datePostulation = datePostulation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidature != null ? idCandidature.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Candidatures)) {
            return false;
        }
        Candidatures other = (Candidatures) object;
        if ((this.idCandidature == null && other.idCandidature != null) || (this.idCandidature != null && !this.idCandidature.equals(other.idCandidature))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesBeans.Candidatures[ idCandidature=" + idCandidature + " ]";
    }
    
}
