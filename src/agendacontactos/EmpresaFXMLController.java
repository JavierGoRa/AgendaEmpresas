/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class EmpresaFXMLController implements Initializable {

    
    private EntityManager entityManager;
    @FXML
    private TableView<Empleado> tablaEmpleado;
    @FXML
    private TableColumn<Empleado, String> columnaIdEmpleado;
    @FXML
    private TableColumn<Empleado, String> columnaNombre;
    @FXML
    private TableColumn<Empleado, Integer> columnaINC;
    @FXML
    private TableColumn<Empleado, String> columnaApellidos;
    @FXML
    private TableColumn<Empleado, Integer> columnaNSS;
    @FXML
    private TableColumn<Empleado, Integer> columnaFechaNac;
    @FXML
    private TableColumn<Empleado, String> columnaDireccion;
    @FXML
    private TableColumn<Empleado, Integer> columnaSalario;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        columnaIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("IdEmpleado"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaINC.setCellValueFactory(new PropertyValueFactory<>("INC"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        columnaNSS.setCellValueFactory(new PropertyValueFactory<>("NSS"));
        columnaFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));
    }    
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void cargarTodosEmpleados(){
        Query queryEmpleadoFindAll = entityManager.createNamedQuery("Empleado.findAll");
        List<Empleado> listEmpleado = queryEmpleadoFindAll.getResultList();
        tablaEmpleado.setItems(FXCollections.observableArrayList(listEmpleado));
    }
}