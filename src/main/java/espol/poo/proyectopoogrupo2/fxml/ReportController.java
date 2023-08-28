/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import juego.Juego;
import modelo.Estudiante;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ReportController implements Initializable {


    @FXML
    private Button btsalir2;
    
    //static ObservableList<Juego> reporte;
    @FXML
    private VBox visParticipantes;
    @FXML
    private VBox visualizarInfo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Juego> reportes = Juego.leerReportes();
        for(Juego j: reportes){
            Label l = new Label(j.getParticipante().toString());
            l.setOnMouseClicked(eh->{
            
                visualizarInfo.getChildren().clear();
                Label fecha = new Label("Fecha: " + j.getFecha());
                Label nombre = new Label("Participante: "+j.getParticipante().getNombre());
                Label companero = new Label("Compañero: " + j.getCompanero().getNombre());
                Label nivelM = new Label("Nivel Máximo Alcanzado: "+j.getNivelMax());
                Label tiempo = new Label("Tiempo: " + j.getTiempo());
                Label contestadas = new Label("Preguntas Contestadas: " + j.getPcon());
                Label comodines = new Label("Comodines usados: " + j.getNcont());
                Label premio = new Label("Premio entregado: " + j.getPremio());
                VBox preguntas = new VBox(5);
                for(String s: j.getPreguntasContestadas()){
                    VBox h = new VBox(1);
                    Label n1 = new Label("- "+s);
                    n1.setWrapText(true);
                    Label n2 = new Label("Copmodin usado: "+j.getComodinesUsados().get(j.getPreguntasContestadas().indexOf(s)));
                    n2.setWrapText(true);
                    h.getChildren().addAll(n1,n2);
                    preguntas.getChildren().add(h);
                }
                preguntas.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                //preguntas.setMaxWidth(1500);
                visualizarInfo.getChildren().addAll(fecha,nombre,companero,nivelM,tiempo,contestadas,comodines,premio,new Label("Pregunta Contestada y su comodin: "),preguntas);
            });
            visParticipantes.getChildren().add(l);
        }
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
      
    
    
}
