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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Empleado personaSeleccionada;
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
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellidos;
    
    
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
    
        tablaEmpleado.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    personaSeleccionada = newValue;
                    if (personaSeleccionada != null) {
                        textFieldNombre.setText(personaSeleccionada.getNombre());
                        textFieldApellidos.setText(personaSeleccionada.getApellidos());
                    } else {
                        textFieldNombre.setText("");
                        textFieldApellidos.setText("");
                    }
                });
    }    
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void cargarTodosEmpleados(){
        Query queryEmpleadoFindAll = entityManager.createNamedQuery("Empleado.findAll");
        List<Empleado> listEmpleado = queryEmpleadoFindAll.getResultList();
        tablaEmpleado.setItems(FXCollections.observableArrayList(listEmpleado));
    }

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        if (personaSeleccionada != null) {
            personaSeleccionada.setNombre(textFieldNombre.getText());
            personaSeleccionada.setApellidos(textFieldApellidos.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(personaSeleccionada);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tablaEmpleado.getSelectionModel().getSelectedIndex();
            tablaEmpleado.getItems().set(numFilaSeleccionada, personaSeleccionada);
            TablePosition pos = new TablePosition(tablaEmpleado, numFilaSeleccionada, null);
            tablaEmpleado.getFocusModel().focus(pos);
            tablaEmpleado.requestFocus();
        }
    }
}