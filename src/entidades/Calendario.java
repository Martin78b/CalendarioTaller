/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "CALENDARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    @NamedQuery(name = "Calendario.findById", query = "SELECT c FROM Calendario c WHERE c.id = :id"),
    @NamedQuery(name = "Calendario.findBySummary", query = "SELECT c FROM Calendario c WHERE c.summary = :summary"),
    @NamedQuery(name = "Calendario.findByDescription", query = "SELECT c FROM Calendario c WHERE c.description = :description"),
    @NamedQuery(name = "Calendario.findByLocation", query = "SELECT c FROM Calendario c WHERE c.location = :location"),
    @NamedQuery(name = "Calendario.findByTomezone", query = "SELECT c FROM Calendario c WHERE c.tomezone = :tomezone"),
    @NamedQuery(name = "Calendario.findByKind", query = "SELECT c FROM Calendario c WHERE c.kind = :kind")})
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "TOMEZONE")
    private String tomezone;
    @Column(name = "KIND")
    private String kind;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendario")
    private Collection<Evento> eventoCollection;
    @JoinColumn(name = "CUENTA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cuenta cuenta;

    public Calendario() {
    }

    public Calendario(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTomezone() {
        return tomezone;
    }

    public void setTomezone(String tomezone) {
        this.tomezone = tomezone;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Calendario[ id=" + id + " ]";
    }
    
}
