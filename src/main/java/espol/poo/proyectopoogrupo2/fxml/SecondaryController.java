/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.*;
import javafx.scene.control.ScrollPane;
/**
 * FXML Controller class
 *
 * @author Daniela Basilio
 */
public class SecondaryController implements Initializable {


    @FXML
    private Button btatras;
    @FXML
    private Button btempezar;
    @FXML
    private ComboBox<?> slmateria;
    @FXML
    private ComboBox<?> slparalelo;
    @FXML
    private TextField ingnivel;
    @FXML
    private VBox vbparticipante;
    @FXML
    private VBox vbcompanero;
    @FXML
    private Button btazar;
    /*
    @FXML
    private ScrollPane scrollEstudiante;
    @FXML
    private ScrollPane scrollCompanero;
    */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

}