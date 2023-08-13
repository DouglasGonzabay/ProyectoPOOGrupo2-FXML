/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modelo.NewClass;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ConfController implements Initializable {


    @FXML
    private BorderPane panelConf;
    @FXML
    private VBox vboxconf;
    @FXML
    private Button btadminter;
    @FXML
    private Button btadminmat;
    @FXML
    private Button btadminpreg;
    @FXML
    private Button btsalir1;
    @FXML
    private GridPane panelsecconf;
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
    @FXML
    private void visualizar(){
        ArrayList<String> materias =NewClass.presentarMaterias(".\\archivos\\materias.txt");
        VBox b1=new VBox();
        for (String materia: materias){            
            Text texto= new Text(materia);
            b1.getChildren().add(texto);            
        }
        panelsecconf.add(new Text("Materias"), 0, 0);
        panelsecconf.add(b1, 1, 0);
        
    
    }
    
    
}
