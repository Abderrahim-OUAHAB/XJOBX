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
@Table(name = "Offres")


public class Offres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_offre")
    private Integer idOffre;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @javax.validation.constraints.Size(min = 1, max = 150)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Lob
    @javax.validation.constraints.Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Lob
    @javax.validation.constraints.Size(min = 1, max = 65535)
    @Column(name = "competences")
    private String competences;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "date_publication")
    @Temporal(TemporalType.DATE)
    private Date datePublication;
    @Basic(optional = false)
    @javax.validation.constraints.NotNull
    @Column(name = "id_recruteur")
    private int idRecruteur;
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    public Offres() {
    }

    public Offres(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public Offres(Integer idOffre, String titre, String description, String competences, Date datePublication, int idRecruteur) {
        this.idOffre = idOffre;
        this.titre = titre;
        this.description = description;
        this.competences = competences;
        this.datePublication = datePublication;
        this.idRecruteur = idRecruteur;
    }

    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int idRecruteur) {
        this.idRecruteur = idRecruteur;
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
        hash += (idOffre != null ? idOffre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Offres)) {
            return false;
        }
        Offres other = (Offres) object;
        if ((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesBeans.Offres[ idOffre=" + idOffre + " ]";
    }
}
