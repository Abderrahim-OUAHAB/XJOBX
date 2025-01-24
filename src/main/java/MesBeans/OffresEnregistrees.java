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
@Table(name = "Offres_Enregistrees")
@javax.xml.bind.annotation.XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffresEnregistrees.findAll", query = "SELECT o FROM OffresEnregistrees o"),
    @NamedQuery(name = "OffresEnregistrees.findByIdEnregistrement", query = "SELECT o FROM OffresEnregistrees o WHERE o.idEnregistrement = :idEnregistrement"),
    @NamedQuery(name = "OffresEnregistrees.findByIdUtilisateur", query = "SELECT o FROM OffresEnregistrees o WHERE o.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "OffresEnregistrees.findByIdOffre", query = "SELECT o FROM OffresEnregistrees o WHERE o.idOffre = :idOffre"),
    @NamedQuery(name = "OffresEnregistrees.findByDateEnregistrement", query = "SELECT o FROM OffresEnregistrees o WHERE o.dateEnregistrement = :dateEnregistrement")})
public class OffresEnregistrees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enregistrement")
    private Integer idEnregistrement;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "id_utilisateur")
    private int idUtilisateur;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "id_offre")
    private int idOffre;
    @Column(name = "date_enregistrement")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnregistrement;

    public OffresEnregistrees() {
    }

    public OffresEnregistrees(Integer idEnregistrement) {
        this.idEnregistrement = idEnregistrement;
    }

    public OffresEnregistrees(Integer idEnregistrement, int idUtilisateur, int idOffre) {
        this.idEnregistrement = idEnregistrement;
        this.idUtilisateur = idUtilisateur;
        this.idOffre = idOffre;
    }

    public Integer getIdEnregistrement() {
        return idEnregistrement;
    }

    public void setIdEnregistrement(Integer idEnregistrement) {
        this.idEnregistrement = idEnregistrement;
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

    public Date getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnregistrement != null ? idEnregistrement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OffresEnregistrees)) {
            return false;
        }
        OffresEnregistrees other = (OffresEnregistrees) object;
        if ((this.idEnregistrement == null && other.idEnregistrement != null) || (this.idEnregistrement != null && !this.idEnregistrement.equals(other.idEnregistrement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesBeans.OffresEnregistrees[ idEnregistrement=" + idEnregistrement + " ]";
    }
}
