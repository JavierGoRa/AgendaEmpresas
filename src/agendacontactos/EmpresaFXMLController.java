/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class EmpresaFXMLController implements Initializable {

    
    private EntityManager entityManager;
    private Empleado empleadoSeleccionado;
    @FXML
    private TableView<Empleado> tablaEmpleado;
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
    @FXML
    private AnchorPane rootEmpresaFXML;
    //private TableColumn<Empleado, Integer> columnaIdDepartamento;
    @FXML
    private TableColumn<Empleado, String> columnaDepartamento;
    
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnaINC.setCellValueFactory(new PropertyValueFactory<>("INC"));
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        columnaNSS.setCellValueFactory(new PropertyValueFactory<>("NSS"));
        columnaFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        columnaSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));
        columnaDepartamento.setCellValueFactory(
            cellData -> {
                SimpleStringProperty property = new SimpleStringProperty();
                if (cellData.getValue().getIddepartamento()!= null) {
                    property.setValue(cellData.getValue().getIddepartamento().getNombre());
                }
                return property;
            });
        
        tablaEmpleado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    empleadoSeleccionado = newValue;
                    if (empleadoSeleccionado != null) {
                        textFieldNombre.setText(empleadoSeleccionado.getNombre());
                        textFieldApellidos.setText(empleadoSeleccionado.getApellidos());
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
        if (empleadoSeleccionado != null) {
            empleadoSeleccionado.setNombre(textFieldNombre.getText());
            empleadoSeleccionado.setApellidos(textFieldApellidos.getText());
            entityManager.getTransaction().begin();
            entityManager.merge(empleadoSeleccionado);
            entityManager.getTransaction().commit();

            int numFilaSeleccionada = tablaEmpleado.getSelectionModel().getSelectedIndex();
            tablaEmpleado.getItems().set(numFilaSeleccionada, empleadoSeleccionado);
            TablePosition pos = new TablePosition(tablaEmpleado, numFilaSeleccionada, null);
            tablaEmpleado.getFocusModel().focus(pos);
            tablaEmpleado.requestFocus();
        }
        
    }
    
    //Abrira el formulario y cuando rellene los datos los pasara a la vista como nuevo empleado
    @FXML
    private void onActionButtonNuevo(ActionEvent event) {
        try {
            //Abre el formulario
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootFormularioFXML = fxmlLoader.load();
            //Cierra la vista
            rootEmpresaFXML.setVisible(false);
            
            StackPane rootMain = (StackPane)rootEmpresaFXML.getScene().getRoot();
            rootMain.getChildren().add(rootFormularioFXML);
        
            FormularioFXMLController formularioFXMLController = (FormularioFXMLController) fxmlLoader.getController();
            formularioFXMLController.setRootEmpresaFXML(rootEmpresaFXML);
            
            formularioFXMLController.setTableViewPrevio(tablaEmpleado);
            
            empleadoSeleccionado = new Empleado();
            formularioFXMLController.setEmpleado(entityManager, empleadoSeleccionado, true);

            formularioFXMLController.mostrarDatos();

            
        } catch (IOException ex){
            Logger.getLogger(EmpresaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //Pasara los datos de un empleado al formulario para poder editarlo
    @FXML
    private void onActionButtonEditar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FormularioFXML.fxml"));
            Parent rootFormularioFXML = fxmlLoader.load();
            
            rootEmpresaFXML.setVisible(false);
            
            StackPane rootMain = (StackPane)rootEmpresaFXML.getScene().getRoot();
            rootMain.getChildren().add(rootFormularioFXML);
        
            FormularioFXMLController formularioFXMLController = (FormularioFXMLController) fxmlLoader.getController();
            formularioFXMLController.setRootEmpresaFXML(rootEmpresaFXML);
            
            formularioFXMLController.setTableViewPrevio(tablaEmpleado);
            
            formularioFXMLController.setEmpleado(entityManager, empleadoSeleccionado, false);

            formularioFXMLController.mostrarDatos();

        } catch (IOException ex){
            Logger.getLogger(EmpresaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActinoButtonSuprimir(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Desea suprimir el siguiente registro?");
        alert.setContentText(empleadoSeleccionado.getNombre() + " "
                + empleadoSeleccionado.getApellidos());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // Acciones a realizar si el usuario acepta
            entityManager.getTransaction().begin();
            entityManager.merge(empleadoSeleccionado);
            entityManager.remove(empleadoSeleccionado);
            entityManager.getTransaction().commit();
            
            tablaEmpleado.getItems().remove(empleadoSeleccionado);

            tablaEmpleado.getFocusModel().focus(null);
            tablaEmpleado.requestFocus();
        } else {
            // Acciones a realizar si el usuario cancela
            int numFilaSeleccionada = tablaEmpleado.getSelectionModel().getSelectedIndex();
            
            tablaEmpleado.getItems().set(numFilaSeleccionada, empleadoSeleccionado);
            TablePosition pos = new TablePosition(tablaEmpleado, numFilaSeleccionada, null);
            tablaEmpleado.getFocusModel().focus(pos);
            tablaEmpleado.requestFocus();
            
        }
    }
    
    
}