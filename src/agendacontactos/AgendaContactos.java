/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

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
    
        //PARA REALIZAR UNA SECUENCIA HABITUAL
            //PARA INIICIAR UNA TRANSACCION
            //em.getTransaction().begin();

            //PARA REALIZAR UN VOLCADO 
            //em.getTransaction().commit();
        //
        
        em.getTransaction().begin();
        Empleado empleadoCharllie = new Empleado() ;
        em.getTransaction().commit();
    }
    
}
