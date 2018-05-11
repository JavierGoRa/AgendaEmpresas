/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import BDEmpresa.Departamento;
import BDEmpresa.Empleado;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Javier
 */
public class AgendaContactos{
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmpresaPU");
        EntityManager em = emf.createEntityManager();
    
        // Cerrar la conexión con la base de datos
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:BDEmpresa;shutdown=true"); 
        } catch (SQLException ex) { 
        }
        
        Empleado empleado1 = new Empleado(1, "John","B","Smith","123456789","1965-10-20","731 Fondren, Houston, TX",30000);
        Empleado empleado2 = new Empleado(2, "Franklin","T","Wong","333445555", "1955-12-20", "638 Voss, Houston, TX ",40000);
        Empleado empleado3 = new Empleado(3, "Alicia","J","Zelaya","999887777","1968-07-19","3321 Castle, Spring, TX",25000);
        Empleado empleado4 = new Empleado(4, "Jennifer","S", "Wallace","987654321","1941-12-25","291 Berry, Bellaire, TX",43000);
        Empleado empleado5 = new Empleado(5, "Rames","K","Narayan","666884444","1972-5-25","975 Fire Oak, Humble, TX" ,38000);
        Empleado empleado6 = new Empleado(6, "Joyce","A","English","453453453","1972-07-31","5631 Rice, Houston, TX", 25000);
        Empleado empleado7 = new Empleado(7, "Ahmad","V","Jabbar","987987987","1969-08-06","980 Dallas, Houston, TX",25000);
        Empleado empleado8 = new Empleado(8, "James","E","Borg","888775555","1937-01-02","450 Stone, Houston, TX",55000);
        
        
        Departamento departamento1 = new Departamento(1, "Investigación",1,"1988-6-22");
        Departamento departamento2 = new Departamento(2, "Administración",2,"1995-01-1");
        Departamento departamento3 = new Departamento(3, "Dirección",3,"1981-6-18");
        
            
        em.getTransaction().begin();
        em.persist(departamento1);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
        Empleado empleadoCharllie = new Empleado() ;
        em.getTransaction().commit();
    }
    
}
