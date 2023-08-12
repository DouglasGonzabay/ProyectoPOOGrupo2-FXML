/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ReportController implements Initializable {


    @FXML
    private Button btsalir2;
    @FXML
    private TableView<?> tablareport;
    @FXML
    private TableColumn<?, ?> fechacol;
    @FXML
    private TableColumn<?, ?> participantcol;
    @FXML
    private TableColumn<?, ?> levelcol;
    @FXML
    private TableColumn<?, ?> timecol;
    @FXML
    private TableColumn<?, ?> pregcol;
    @FXML
    private TableColumn<?, ?> comdcol;
    @FXML
    private TableColumn<?, ?> premiocol;
    @FXML
    private TableColumn<?, ?> detallescol;
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
