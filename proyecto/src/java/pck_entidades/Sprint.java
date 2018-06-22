/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "sprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s")
    , @NamedQuery(name = "Sprint.findByIdsprint", query = "SELECT s FROM Sprint s WHERE s.idsprint = :idsprint")
    , @NamedQuery(name = "Sprint.findByNombre", query = "SELECT s FROM Sprint s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sprint.findByFechaini", query = "SELECT s FROM Sprint s WHERE s.fechaini = :fechaini")
    , @NamedQuery(name = "Sprint.findByFechafin", query = "SELECT s FROM Sprint s WHERE s.fechafin = :fechafin")
    , @NamedQuery(name = "Sprint.findByEstado", query = "SELECT s FROM Sprint s WHERE s.estado = :estado")})
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsprint")
    private Integer idsprint;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechaini")
    @Temporal(TemporalType.DATE)
    private Date fechaini;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Size(max = 30)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idsprint")
    private Collection<Tarea> tareaCollection;
    @JoinColumn(name = "idus", referencedColumnName = "idus")
    @ManyToOne
    private Userhistories idus;

    public Sprint() {
    }

    public Sprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public Integer getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Integer idsprint) {
        this.idsprint = idsprint;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Tarea> getTareaCollection() {
        return tareaCollection;
    }

    public void setTareaCollection(Collection<Tarea> tareaCollection) {
        this.tareaCollection = tareaCollection;
    }

    public Userhistories getIdus() {
        return idus;
    }

    public void setIdus(Userhistories idus) {
        this.idus = idus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsprint != null ? idsprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idsprint == null && other.idsprint != null) || (this.idsprint != null && !this.idsprint.equals(other.idsprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_entidades.Sprint[ idsprint=" + idsprint + " ]";
    }
    
}
