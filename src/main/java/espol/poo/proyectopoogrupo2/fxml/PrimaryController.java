package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.NewClass;
import modelo.TerminoAcademico;

public class PrimaryController implements Initializable {

    @FXML
    private Button btconf;
    @FXML
    private Button btnew;
    @FXML
    private Button btreport;
    @FXML
    private Button btsalir;
    @FXML
    private BorderPane border;
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TerminoAcademico terJuego = NewClass.terminoConfigurado();
       VBox v = new VBox(5);
       if(terJuego.getAnio().equals("")||terJuego.getNumero().equals("")){
           btnew.setDisable(true);
           Label l = new Label("NOTA: Configure el término académico antes de jugar");
           v.getChildren().addAll(l,new Label(terJuego.toString()+" Termino No Configurado"));
           border.setBottom(v);
       }else{
           btnew.setDisable(false);
           v.getChildren().clear();
           v.getChildren().addAll(new Label(terJuego.toString()+" Termino Configurado"));
           border.setBottom(v);
       }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void switchToconf() throws IOException {
        App.setRoot("conf");
    }
    @FXML
    private void switchToreport() throws IOException {
        App.setRoot("report");
    }
    @FXML
    private void cerrarVentana(ActionEvent e) throws IOException{
        Platform.exit();
    }

}
