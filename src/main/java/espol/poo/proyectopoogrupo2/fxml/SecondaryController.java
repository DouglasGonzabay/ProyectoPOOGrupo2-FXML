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
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modelo.Estudiante;
import modelo.Materia;
import modelo.NewClass;
import modelo.Paralelo;
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
    private ComboBox<Materia> slmateria;
    @FXML
    private ComboBox<Paralelo> slparalelo;
    @FXML
    private TextField ingnivel;
    @FXML
    private VBox vbparticipante;
    @FXML
    private VBox vbcompanero;
    @FXML
    private Button btazar;
    @FXML
    private AnchorPane scrollEstudiante;
    @FXML
    private AnchorPane scrollCompanero;
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
        ArrayList<Materia> materias2 = NewClass.leerMaterias(".\\archivos\\materias.txt");
        slmateria.getItems().setAll(materias2);
        slparalelo.setDisable(true);
        scrollEstudiante.setPrefSize(100, 200);
        scrollEstudiante.setStyle("-fx-background-color:WHITE");
        scrollCompanero.setPrefSize(100, 200);
        scrollCompanero.setStyle("-fx-background-color:WHITE");
        // TODO
    }  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void modParalelo(ActionEvent event){
        slparalelo.setDisable(false);
        ArrayList<Paralelo> par = (ArrayList<Paralelo>)slmateria.getValue().getParalelos();
        slparalelo.getItems().setAll(par);
    }
    @FXML
    private void cargarEstudiantes(ActionEvent event){
        scrollEstudiante.getChildren().clear();
        scrollCompanero.getChildren().clear();
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>)slparalelo.getValue().getLista();
        if(estudiantes == null){
            scrollEstudiante.getChildren().add(new Label("No hay Estudiantes"));
            scrollCompanero.getChildren().add(new Label("No hay Estudiantes"));
            scrollEstudiante.setPrefSize(100, 200);
            scrollCompanero.setPrefSize(100, 200);
        }
        else{
            VBox estudiante = new VBox();
            VBox companero = new VBox();
            int n=0;
            for(Estudiante e: estudiantes){
                Button est = new Button(e.toString());
                est.setStyle("-fx-background-color:WHITE");
                Button com = new Button(e.toString());
                com.setStyle("-fx-background-color:WHITE");
                estudiante.getChildren().add(est);
                companero.getChildren().add(com);
                n+=25;
            }
            scrollEstudiante.getChildren().add(estudiante);
            scrollCompanero.getChildren().add(companero);
            //Regula el tamaño acorde al tamaño predeterminado de un botón
            scrollEstudiante.setPrefSize(350,n);
            scrollCompanero.setPrefSize(350,n);
        }
    }

}