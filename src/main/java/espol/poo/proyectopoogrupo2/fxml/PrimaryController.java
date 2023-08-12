package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button btconf;
    @FXML
    private Button btnew;
    @FXML
    private Button btreport;
    @FXML
    private Button btsalir;

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

}
