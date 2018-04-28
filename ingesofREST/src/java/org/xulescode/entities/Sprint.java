/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xulescode.entities;

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
    , @NamedQuery(name = "Sprint.findByIdSprint", query = "SELECT s FROM Sprint s WHERE s.idSprint = :idSprint")
    , @NamedQuery(name = "Sprint.findByNombre", query = "SELECT s FROM Sprint s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Sprint.findByFechaIni", query = "SELECT s FROM Sprint s WHERE s.fechaIni = :fechaIni")
    , @NamedQuery(name = "Sprint.findByFechaFin", query = "SELECT s FROM Sprint s WHERE s.fechaFin = :fechaFin")
    , @NamedQuery(name = "Sprint.findByEstado", query = "SELECT s FROM Sprint s WHERE s.estado = :estado")})
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_ini")
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 100)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idSprint")
    private Collection<Tarea> tareaCollection;
    @JoinColumn(name = "id_us", referencedColumnName = "id_us")
    @ManyToOne
    private Userhistory idUs;
    @OneToMany(mappedBy = "idSprint")
    private Collection<Proyecto> proyectoCollection;

    public Sprint() {
    }

    public Sprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public Userhistory getIdUs() {
        return idUs;
    }

    public void setIdUs(Userhistory idUs) {
        this.idUs = idUs;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprint != null ? idSprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.xulescode.entities.Sprint[ idSprint=" + idSprint + " ]";
    }
    
}
