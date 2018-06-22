/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
    , @NamedQuery(name = "Tarea.findByIdtarea", query = "SELECT t FROM Tarea t WHERE t.idtarea = :idtarea")
    , @NamedQuery(name = "Tarea.findByNombre", query = "SELECT t FROM Tarea t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tarea.findByEstado", query = "SELECT t FROM Tarea t WHERE t.estado = :estado")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtarea")
    private Integer idtarea;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idsprint", referencedColumnName = "idsprint")
    @ManyToOne
    private Sprint idsprint;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

    public Tarea() {
    }

    public Tarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public Integer getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(Integer idtarea) {
        this.idtarea = idtarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Sprint getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Sprint idsprint) {
        this.idsprint = idsprint;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarea != null ? idtarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.idtarea == null && other.idtarea != null) || (this.idtarea != null && !this.idtarea.equals(other.idtarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pck_entidades.Tarea[ idtarea=" + idtarea + " ]";
    }
    
}
