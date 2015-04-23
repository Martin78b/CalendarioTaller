/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findById", query = "SELECT e FROM Evento e WHERE e.id = :id"),
    @NamedQuery(name = "Evento.findByStatus", query = "SELECT e FROM Evento e WHERE e.status = :status"),
    @NamedQuery(name = "Evento.findByCreated", query = "SELECT e FROM Evento e WHERE e.created = :created"),
    @NamedQuery(name = "Evento.findByUpdated", query = "SELECT e FROM Evento e WHERE e.updated = :updated"),
    @NamedQuery(name = "Evento.findBySummary", query = "SELECT e FROM Evento e WHERE e.summary = :summary"),
    @NamedQuery(name = "Evento.findByDescription", query = "SELECT e FROM Evento e WHERE e.description = :description"),
    @NamedQuery(name = "Evento.findByLocation", query = "SELECT e FROM Evento e WHERE e.location = :location"),
    @NamedQuery(name = "Evento.findByColorid", query = "SELECT e FROM Evento e WHERE e.colorid = :colorid"),
    @NamedQuery(name = "Evento.findByStartdate", query = "SELECT e FROM Evento e WHERE e.startdate = :startdate"),
    @NamedQuery(name = "Evento.findByStartdatetime", query = "SELECT e FROM Evento e WHERE e.startdatetime = :startdatetime"),
    @NamedQuery(name = "Evento.findByStarttimezone", query = "SELECT e FROM Evento e WHERE e.starttimezone = :starttimezone"),
    @NamedQuery(name = "Evento.findByEnddate", query = "SELECT e FROM Evento e WHERE e.enddate = :enddate"),
    @NamedQuery(name = "Evento.findByEnddatetime", query = "SELECT e FROM Evento e WHERE e.enddatetime = :enddatetime"),
    @NamedQuery(name = "Evento.findByEndtimezone", query = "SELECT e FROM Evento e WHERE e.endtimezone = :endtimezone"),
    @NamedQuery(name = "Evento.findByRecurrence", query = "SELECT e FROM Evento e WHERE e.recurrence = :recurrence"),
    @NamedQuery(name = "Evento.findByRecurrenceeventid", query = "SELECT e FROM Evento e WHERE e.recurrenceeventid = :recurrenceeventid"),
    @NamedQuery(name = "Evento.findByOriginalstartdate", query = "SELECT e FROM Evento e WHERE e.originalstartdate = :originalstartdate"),
    @NamedQuery(name = "Evento.findByOriginalstartdatetime", query = "SELECT e FROM Evento e WHERE e.originalstartdatetime = :originalstartdatetime"),
    @NamedQuery(name = "Evento.findByOriginaltimezone", query = "SELECT e FROM Evento e WHERE e.originaltimezone = :originaltimezone"),
    @NamedQuery(name = "Evento.findByTransparency", query = "SELECT e FROM Evento e WHERE e.transparency = :transparency"),
    @NamedQuery(name = "Evento.findByVisibility", query = "SELECT e FROM Evento e WHERE e.visibility = :visibility"),
    @NamedQuery(name = "Evento.findByIcaluid", query = "SELECT e FROM Evento e WHERE e.icaluid = :icaluid"),
    @NamedQuery(name = "Evento.findBySequencia", query = "SELECT e FROM Evento e WHERE e.sequencia = :sequencia"),
    @NamedQuery(name = "Evento.findByRemindersdefault", query = "SELECT e FROM Evento e WHERE e.remindersdefault = :remindersdefault"),
    @NamedQuery(name = "Evento.findByRemindermethod", query = "SELECT e FROM Evento e WHERE e.remindermethod = :remindermethod"),
    @NamedQuery(name = "Evento.findByReminderminutes", query = "SELECT e FROM Evento e WHERE e.reminderminutes = :reminderminutes")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "CREATED")
    @Temporal(TemporalType.DATE)
    private Date created;
    @Column(name = "UPDATED")
    @Temporal(TemporalType.DATE)
    private Date updated;
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "COLORID")
    private String colorid;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "STARTDATETIME")
    @Temporal(TemporalType.TIME)
    private Date startdatetime;
    @Column(name = "STARTTIMEZONE")
    private String starttimezone;
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "ENDDATETIME")
    @Temporal(TemporalType.TIME)
    private Date enddatetime;
    @Column(name = "ENDTIMEZONE")
    private String endtimezone;
    @Column(name = "RECURRENCE")
    private String recurrence;
    @Column(name = "RECURRENCEEVENTID")
    private String recurrenceeventid;
    @Column(name = "ORIGINALSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date originalstartdate;
    @Column(name = "ORIGINALSTARTDATETIME")
    @Temporal(TemporalType.TIME)
    private Date originalstartdatetime;
    @Column(name = "ORIGINALTIMEZONE")
    private String originaltimezone;
    @Column(name = "TRANSPARENCY")
    private String transparency;
    @Column(name = "VISIBILITY")
    private String visibility;
    @Column(name = "ICALUID")
    private String icaluid;
    @Column(name = "SEQUENCIA")
    private String sequencia;
    @Column(name = "REMINDERSDEFAULT")
    private Boolean remindersdefault;
    @Column(name = "REMINDERMETHOD")
    private String remindermethod;
    @Column(name = "REMINDERMINUTES")
    private Integer reminderminutes;
    @JoinColumn(name = "CALENDARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Calendario calendario;

    public Evento() {
    }

    public Evento(String id) {
        this.id = id;
    }

    public Evento(String id, Date created, Date startdate, Date startdatetime) {
        this.id = id;
        this.created = created;
        this.startdate = startdate;
        this.startdatetime = startdatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(Date startdatetime) {
        this.startdatetime = startdatetime;
    }

    public String getStarttimezone() {
        return starttimezone;
    }

    public void setStarttimezone(String starttimezone) {
        this.starttimezone = starttimezone;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(Date enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getEndtimezone() {
        return endtimezone;
    }

    public void setEndtimezone(String endtimezone) {
        this.endtimezone = endtimezone;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public String getRecurrenceeventid() {
        return recurrenceeventid;
    }

    public void setRecurrenceeventid(String recurrenceeventid) {
        this.recurrenceeventid = recurrenceeventid;
    }

    public Date getOriginalstartdate() {
        return originalstartdate;
    }

    public void setOriginalstartdate(Date originalstartdate) {
        this.originalstartdate = originalstartdate;
    }

    public Date getOriginalstartdatetime() {
        return originalstartdatetime;
    }

    public void setOriginalstartdatetime(Date originalstartdatetime) {
        this.originalstartdatetime = originalstartdatetime;
    }

    public String getOriginaltimezone() {
        return originaltimezone;
    }

    public void setOriginaltimezone(String originaltimezone) {
        this.originaltimezone = originaltimezone;
    }

    public String getTransparency() {
        return transparency;
    }

    public void setTransparency(String transparency) {
        this.transparency = transparency;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getIcaluid() {
        return icaluid;
    }

    public void setIcaluid(String icaluid) {
        this.icaluid = icaluid;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public Boolean getRemindersdefault() {
        return remindersdefault;
    }

    public void setRemindersdefault(Boolean remindersdefault) {
        this.remindersdefault = remindersdefault;
    }

    public String getRemindermethod() {
        return remindermethod;
    }

    public void setRemindermethod(String remindermethod) {
        this.remindermethod = remindermethod;
    }

    public Integer getReminderminutes() {
        return reminderminutes;
    }

    public void setReminderminutes(Integer reminderminutes) {
        this.reminderminutes = reminderminutes;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Evento[ id=" + id + " ]";
    }
    
}
