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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FormularioFXMLController implements Initializable {

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldINC;
    @FXML
    private TextField textFieldApellidos;
    @FXML
    private TextField textFieldNSS;
    @FXML
    private TextField textFieldFechaNac;
    @FXML
    private TextField textFieldSalario;
    @FXML
    private TextField textFieldDireccion;

    /**
     * Initializes the controller class.
     */
    
    private Pane rootEmpresaFXML;
    @FXML
    private AnchorPane rootFormularioFXML;
    private TableView tableViewPrevio;
    private Empleado empleado;
    private EntityManager entityManager;
    private boolean nuevoEmpleado;
    
    
    
    @FXML
    private ComboBox<Departamento> comboBoxDepartamento;
    
    
    
    public void setRootEmpresaFXML(Pane rootEmpresaFXML) {
        this.rootEmpresaFXML = rootEmpresaFXML;
    }
    
    public void setEmpleado(EntityManager entityManager, Empleado empleado, boolean nuevoEmpleado){
        this.entityManager = entityManager;
        entityManager.getTransaction().begin();
        if(!nuevoEmpleado){
            this.empleado = entityManager.find(Empleado.class, empleado.getIdempleado());
        } else {
            this.empleado = empleado;
        }
        this.nuevoEmpleado = nuevoEmpleado;
    }
    
    public void setTableViewPrevio(TableView tableViewPrevio){
        this.tableViewPrevio = tableViewPrevio;
    }
    
    public void mostrarDatos() {
        textFieldNombre.setText(empleado.getNombre());
//        textFieldINC.setText(empleado.getInc());
        textFieldApellidos.setText(empleado.getApellidos());
        textFieldNSS.setText(empleado.getNss());       
        textFieldDireccion.setText(empleado.getDireccion());
        Query queryDepartamentoFindAll = entityManager.createNamedQuery("Departamento.findAll");
        List listaDepartamento = queryDepartamentoFindAll.getResultList();
        comboBoxDepartamento.setItems(FXCollections.observableList(listaDepartamento));
        comboBoxDepartamento.setCellFactory((ListView<Departamento> l) -> new ListCell<Departamento>() {
            @Override
            protected void updateItem(Departamento departamento, boolean empty) {
                super.updateItem(departamento, empty);
                if (departamento == null || empty) {
                    setText("");
                } else {
                    setText(departamento.getNombre());
                }
            }
        });        
        comboBoxDepartamento.setConverter(new StringConverter<Departamento>() {
            @Override
            public String toString(Departamento departamento) {
                if (departamento == null) {
                    return null;
                } else {
                    return departamento.getNombre();
                }
            }
            @Override
            public Departamento fromString(String userId) {
                return null;
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        StackPane rootMain = (StackPane)rootFormularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(rootFormularioFXML);
    
        rootEmpresaFXML.setVisible(true);
        
        empleado.setNombre(textFieldNombre.getText());
//        empleado.setInc(textFieldINC.getText());
        empleado.setApellidos(textFieldApellidos.getText());
        empleado.setNss(textFieldNSS.getText());
        empleado.setDireccion(textFieldDireccion.getText());
        if(empleado.getSalario() != null) {
            textFieldSalario.setText(empleado.getSalario().toString());
        }        
        
        if(nuevoEmpleado){
            entityManager.persist(empleado);
        } else {
            entityManager.merge(empleado);
        }
        entityManager.getTransaction().commit();
        
        int numFilaSeleccionada;
        if(nuevoEmpleado){
            tableViewPrevio.getItems().add(empleado);
            numFilaSeleccionada = tableViewPrevio.getItems().size() - 1;
            tableViewPrevio.getSelectionModel().select(numFilaSeleccionada);
            tableViewPrevio.scrollTo(numFilaSeleccionada);
        } else {
            numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
            tableViewPrevio.getItems().set(numFilaSeleccionada, empleado);
        }
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
    }
    
    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        entityManager.getTransaction().rollback();
        
        int numFilaSeleccionada = tableViewPrevio.getSelectionModel().getSelectedIndex();
        TablePosition pos = new TablePosition(tableViewPrevio, numFilaSeleccionada, null);
        tableViewPrevio.getFocusModel().focus(pos);
        tableViewPrevio.requestFocus();
    }
}