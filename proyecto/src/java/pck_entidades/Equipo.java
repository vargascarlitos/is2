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
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByIdequipo", query = "SELECT e FROM Equipo e WHERE e.idequipo = :idequipo")
    , @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idequipo")
    private Integer idequipo;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto")
    @ManyToOne
    private Proyecto idproyecto;
    @OneToMany(mappedBy = "idequipo")
    private Collection<Usuario> usuarioCollection;

    public Equipo() {
    }

    public Equipo(Integer idequipo) {
        this.idequipo = idequipo;
    }

    public Integer getIdequipo() {
        return idequipo;
    }

    public void setIdequipo(Integer idequipo) {
        this.idequipo = idequipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Proyecto getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Proyecto idproyecto) {
        this.idproyecto = idproyecto;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipo != null ? idequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idequipo == null && other.idequipo != null) || (this.idequipo != null && !this.idequipo.equals(other.idequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_entidades.Equipo[ idequipo=" + idequipo + " ]";
    }
    
}
