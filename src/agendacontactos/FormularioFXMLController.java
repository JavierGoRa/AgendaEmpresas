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
import javafx.scene.control.TextField;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionButtonGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    }
    
}
