/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "userhistories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userhistories.findAll", query = "SELECT u FROM Userhistories u")
    , @NamedQuery(name = "Userhistories.findByIdus", query = "SELECT u FROM Userhistories u WHERE u.idus = :idus")
    , @NamedQuery(name = "Userhistories.findByDescripcion", query = "SELECT u FROM Userhistories u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "Userhistories.findByPrioridad", query = "SELECT u FROM Userhistories u WHERE u.prioridad = :prioridad")})
public class Userhistories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idus")
    private Integer idus;
    @Size(max = 30)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 30)
    @Column(name = "prioridad")
    private String prioridad;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
    @ManyToOne
    private Proyecto idproyecto;
    @OneToMany(mappedBy = "idus")
    private Collection<Sprint> sprintCollection;

    public Userhistories() {
    }

    public Userhistories(Integer idus) {
        this.idus = idus;
    }

    public Integer getIdus() {
        return idus;
    }

    public void setIdus(Integer idus) {
        this.idus = idus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Proyecto getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Proyecto idproyecto) {
        this.idproyecto = idproyecto;
    }

    @XmlTransient
    public Collection<Sprint> getSprintCollection() {
        return sprintCollection;
    }

    public void setSprintCollection(Collection<Sprint> sprintCollection) {
        this.sprintCollection = sprintCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idus != null ? idus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userhistories)) {
            return false;
        }
        Userhistories other = (Userhistories) object;
        if ((this.idus == null && other.idus != null) || (this.idus != null && !this.idus.equals(other.idus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_entidades.Userhistories[ idus=" + idus + " ]";
    }
    
}
