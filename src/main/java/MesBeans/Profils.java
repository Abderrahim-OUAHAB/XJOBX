package MesBeans;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "Profils")
@javax.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profils.findAll", query = "SELECT p FROM Profils p"),
    @NamedQuery(name = "Profils.findByIdProfil", query = "SELECT p FROM Profils p WHERE p.idProfil = :idProfil"),
    @NamedQuery(name = "Profils.findByIdUtilisateur", query = "SELECT p FROM Profils p WHERE p.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Profils.findByExperience", query = "SELECT p FROM Profils p WHERE p.experience = :experience"),
    @NamedQuery(name = "Profils.findByDateCreation", query = "SELECT p FROM Profils p WHERE p.dateCreation = :dateCreation")})
public class Profils implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profil")
    private Integer idProfil;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Lob
    @javax.validation.constraints.Size(min = 1, max = 65535)
    @Column(name = "competences")
    private String competences;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "experience")
    private int experience;
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    public Profils() {
    }

    public Profils(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public Profils(Integer idProfil, int idUtilisateur, String competences, int experience) {
        this.idProfil = idProfil;
        this.idUtilisateur = idUtilisateur;
        this.competences = competences;
        this.experience = experience;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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
        hash += (idProfil != null ? idProfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Profils)) {
            return false;
        }
        Profils other = (Profils) object;
        if ((this.idProfil == null && other.idProfil != null) || (this.idProfil != null && !this.idProfil.equals(other.idProfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesBeans.Profils[ idProfil=" + idProfil + " ]";
    }
}
