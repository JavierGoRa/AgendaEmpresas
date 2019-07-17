/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene; 
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Javier
 */
public class AgendaContactos extends Application {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        StackPane rootMain = new StackPane();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmpresaFXML.fxml"));
        Pane rootEmpresaFXML = fxmlLoader.load();
        rootMain.getChildren().add(rootEmpresaFXML);
        
        emf = Persistence.createEntityManagerFactory("EmpresaPU");
        em = emf.createEntityManager();
        
        EmpresaFXMLController empresaFXMLController = (EmpresaFXMLController) fxmlLoader.getController();  
        empresaFXMLController.setEntityManager(em);
        empresaFXMLController.cargarTodosEmpleados();
        
        Scene scene = new Scene(rootMain, 656, 400);
        
        primaryStage.setTitle("Agenda mpresa");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
    
    public void stop() throws Exception {
        em.close(); 
        emf.close(); 
        try { 
            DriverManager.getConnection("jdbc:derby:BDEmpresa;shutdown=true"); 
        } catch (SQLException ex) { 
        }  
    }
    
    public static void main(String[] args) {
        launch(args);
        
      
        
//        Empleado empleado1 = new Empleado();
//        empleado1.setIdempleado(1);
//        empleado1.setNombre("John");
//        empleado1.setInc('B');
//        empleado1.setApellidos("Smith");
//        empleado1.setNss("333445555");
//        empleado1.setFechanac(new Date());
//        empleado1.setDireccion("731 Fondren, Houston, TX");
//        empleado1.setSalario(new BigDecimal(30000));
//        
//        Empleado empleado2 = new Empleado();
//        empleado2.setIdempleado(2);
//        empleado2.setNombre("Franklin");
//        empleado2.setInc('T');
//        empleado2.setApellidos("Wong");
//        empleado2.setNss("333445555");
//        empleado2.setFechanac(new Date());
//        empleado2.setDireccion("638 Voss, Houston, TX");
//        empleado2.setSalario(new BigDecimal(40000));
//        
//        Empleado empleado3 = new Empleado();
//        empleado3.setIdempleado(3);
//        empleado3.setNombre("Alicia");
//        empleado3.setInc('J');
//        empleado3.setApellidos("Zelaya");
//        empleado3.setNss("999887777");
//        empleado3.setFechanac(new Date());
//        empleado3.setDireccion("3321 Castle, spring, TX");
//        empleado3.setSalario(new BigDecimal(25000));
//        
//        Empleado empleado4 = new Empleado();
//        empleado4.setIdempleado(4);
//        empleado4.setNombre("Jennifer");
//        empleado4.setInc('S');
//        empleado4.setApellidos("Wallace");
//        empleado4.setNss("987654321");
//        empleado4.setFechanac(new Date());
//        empleado4.setDireccion("291 Berry, Bellaire, TX");
//        empleado4.setSalario(new BigDecimal(43000));
//        
//        Empleado empleado5 = new Empleado();
//        empleado5.setIdempleado(5);
//        empleado5.setNombre("Ramesh");
//        empleado5.setInc('K');
//        empleado5.setApellidos("Narayan");
//        empleado5.setNss("666884444");
//        empleado5.setFechanac(new Date());
//        empleado5.setDireccion("975 Fire Oak, Humble, TX");
//        empleado5.setSalario(new BigDecimal(38000));
//        
//        Empleado empleado6 = new Empleado();
//        empleado6.setIdempleado(6);
//        empleado6.setNombre("Joyce");
//        empleado6.setInc('A');
//        empleado6.setApellidos("English");
//        empleado6.setNss("453453453");
//        empleado6.setFechanac(new Date());
//        empleado6.setDireccion("5631 Rice, Houston, TX");
//        empleado6.setSalario(new BigDecimal(25000));
//        
//        Empleado empleado7 = new Empleado();
//        empleado7.setIdempleado(7);
//        empleado7.setNombre("Ahmad");
//        empleado7.setInc('V');
//        empleado7.setApellidos("Jabbar");
//        empleado7.setNss("987987987");
//        empleado7.setFechanac(new Date());
//        empleado7.setDireccion("980 Dallas, Houston, TX");
//        empleado7.setSalario(new BigDecimal(25000));
//        
//        Empleado empleado8= new Empleado();
//        empleado8.setIdempleado(8);
//        empleado8.setNombre("JAmes");
//        empleado8.setInc('E');
//        empleado8.setApellidos("Borg");
//        empleado8.setNss("888775555");
//        empleado8.setFechanac(new Date());
//        empleado8.setDireccion("450 Stone, Houston, TX");
//        empleado8.setSalario(new BigDecimal(43000));
//        
//        Departamento departamento1 = new Departamento();
//        departamento1.setIddepartamento(1);
//        departamento1.setNombre("Investigacion");
//        departamento1.setNumdepart(1);
//        departamento1.setFechajefe(new Date());
//            
//        Departamento departamento2 = new Departamento();
//        departamento2.setIddepartamento(2);
//        departamento2.setNombre("Administracion");
//        departamento2.setNumdepart(2);
//        departamento2.setFechajefe(new Date());
//        
//        Departamento departamento3 = new Departamento();
//        departamento3.setIddepartamento(3);
//        departamento3.setNombre("Direccion");
//        departamento3.setNumdepart(3);
//        departamento3.setFechajefe(new Date());
//        
//        em.getTransaction().begin();
//        em.persist(empleado1);
//        em.persist(empleado2);
//        em.persist(empleado3);
//        em.persist(empleado4);
//        em.persist(empleado5);
//        em.persist(empleado6);
//        em.persist(empleado7);
//        em.persist(empleado8);
//        em.persist(departamento1);
//        em.persist(departamento2);
//        em.persist(departamento3);
//        em.getTransaction().commit();
    // Cerrar la conexión con la base de datos
          
    }
    
    
    
}