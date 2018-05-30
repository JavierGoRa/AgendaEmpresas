/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendacontactos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FormularioFXMLController implements Initializable {

    @FXML
    private TextField textFieldIdEmpleado;
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
    private Empleado Empleado;
    private EntityManager entityManager;
    private boolean nuevoEmpleado;
    
    public void setRootEmpresaFXML(Pane rootEmpresaFXML) {
        this.rootEmpresaFXML = rootEmpresaFXML;
    }
    
    public void setTableViewPrevio(TableView tableViewPrevio){
        this.tableViewPrevio = tableViewPrevio;
    }
    
//    public void setEmpleado(EntityManager entityManager, Empleado empleado, boolean nuevoEmpleado){
//        this.entityManager = entityManager;
//        entityManager.getTransaction().begin();
//        if(!nuevoEmpleado){
//            this.Empleado = entityManager.find(Empleado.class, empleado.getIdempleado());
//        } else {
//            this.empleado = empleado;
//        }
//        this.nuevoEmpleado = nuevoEmpleado;
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
        StackPane rootMain = (StackPane)rootFormularioFXML.getScene().getRoot();
        rootMain.getChildren().remove(rootFormularioFXML);
    
        rootEmpresaFXML.setVisible(true);
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    }
    
    
    
}
