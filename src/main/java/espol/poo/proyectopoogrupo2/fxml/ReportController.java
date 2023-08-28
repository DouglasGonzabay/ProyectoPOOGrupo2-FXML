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

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView<Juego> tablareport;
    @FXML
    private TableColumn<Juego, String> fechacol;
    @FXML
    private TableColumn<Juego, Estudiante> participantcol;
    @FXML
    private TableColumn<Juego, Integer> levelcol;
    @FXML
    private TableColumn<Juego, Long> timecol;
    @FXML
    private TableColumn<Juego, Integer> pregcol;
    @FXML
    private TableColumn<Juego,Integer > comdcol;
    @FXML
    private TableColumn<Juego, String> premiocol;
    @FXML
    private TableColumn<Juego, String> detallescol;
    
    static ObservableList<Juego> reporte;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Juego>juego=Juego.leerReportes();
        ArrayList<Juego>j1=new ArrayList();
        for(Juego j:juego){
            String fecha=j.getFecha();
            Estudiante participante1=j.getParticipante();
            int pCon=j.getPcon();
            int numLevel=j.getNivelMax();
            long timeGame=j.getTiempo();
            int numComod=j.getPcon();
            String premio=j.getPremio();
            String detail="";
            Juego jg=new Juego(fecha,participante1,numLevel,timeGame,pCon,numComod,premio,detail);
            j1.add(jg);
        }
        reporte=FXCollections.observableArrayList();
        /*
        fechacol.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        participantcol.setCellValueFactory(new PropertyValueFactory<>("Participante"));
        levelcol.setCellValueFactory(new PropertyValueFactory<>("Nivel Máximo"));
        timecol.setCellValueFactory(new PropertyValueFactory<>("Tiempo"));
        pregcol.setCellValueFactory(new PropertyValueFactory<>("Preguntas Contestadas"));
        comdcol.setCellValueFactory(new PropertyValueFactory<>("Comodines usados"));
        premiocol.setCellValueFactory(new PropertyValueFactory<>("Premio"));
        detallescol.setCellValueFactory(new PropertyValueFactory<>("Detalles"));
        */
        reporte.addAll(j1);
        tablareport.setItems(reporte);
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
      
    
    
}
