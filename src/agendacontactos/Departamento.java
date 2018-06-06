/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "DEPARTAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByIddepartamento", query = "SELECT d FROM Departamento d WHERE d.iddepartamento = :iddepartamento")
    , @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Departamento.findByNumdepart", query = "SELECT d FROM Departamento d WHERE d.numdepart = :numdepart")
    , @NamedQuery(name = "Departamento.findByFechajefe", query = "SELECT d FROM Departamento d WHERE d.fechajefe = :fechajefe")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEPARTAMENTO")
    private Integer iddepartamento;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "NUMDEPART")
    private int numdepart;
    @Column(name = "FECHAJEFE")
    @Temporal(TemporalType.DATE)
    private Date fechajefe;
    @OneToMany(mappedBy = "iddepartamento")
    private Collection<Empleado> empleadoCollection;

    public Departamento() {
    }

    public Departamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Departamento(Integer iddepartamento, String nombre, int numdepart) {
        this.iddepartamento = iddepartamento;
        this.nombre = nombre;
        this.numdepart = numdepart;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumdepart() {
        return numdepart;
    }

    public void setNumdepart(int numdepart) {
        this.numdepart = numdepart;
    }

    public Date getFechajefe() {
        return fechajefe;
    }

    public void setFechajefe(Date fechajefe) {
        this.fechajefe = fechajefe;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepartamento != null ? iddepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.iddepartamento == null && other.iddepartamento != null) || (this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agendacontactos.Departamento[ iddepartamento=" + iddepartamento + " ]";
    }
    
}
