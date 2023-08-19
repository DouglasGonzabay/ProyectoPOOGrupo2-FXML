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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modelo.NewClass;
import modelo.TerminoAcademico;
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
    @FXML
    private FlowPane flow;
    @FXML
    private FlowPane panelVisual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //TextFlow defaultText = new TextFlow();
        panelVisual.setStyle("-fx-background-color:WHITE");
        vboxconf.setStyle("-fx-background-color:AQUA");
        panelVisual.getChildren().addAll(new Text("No existe información"),new Text(" para mostrar"));
        //panelVisual.getChildren().add(defaultText);
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }    
    @FXML
    private void visualizar(){
        panelVisual.getChildren().clear();
        panelVisual.getChildren().add(new Text("Materias"));
        ArrayList<String> materias =NewClass.presentarMaterias(".\\archivos\\materias.txt");
        //VBox b1=new VBox();
        for (String materia: materias){            
            Text texto= new Text(materia);
            //b1.getChildren().add(texto);
            panelVisual.getChildren().add(texto);
        }
    }
    @FXML
    private void visualizartermino(){
        panelVisual.getChildren().clear();
        panelVisual.getChildren().add(new Text("Términos Académicos"));
        ArrayList<TerminoAcademico> terminos =TerminoAcademico.cargarTerminos(".\\archivos\\TerminosAcademicos.txt");
        //VBox b1=new VBox();
        for (TerminoAcademico termino: terminos){            
            Text texto= new Text(termino.toString());
            //b1.getChildren().add(texto);
            panelVisual.getChildren().add(texto);            
        }
    }
    @FXML
    private void aTermino(){
        panelsecconf.getChildren().clear();
        visualizartermino();
        Button inTermino=new Button("Ingresar término");
        Button ediTermino=new Button("Editar término");
        Button confTermino=new Button("Configurar término");
        inTermino.setWrapText(true);
        ediTermino.setWrapText(true);
        confTermino.setWrapText(true);
        panelsecconf.add(inTermino, 0, 0);
        panelsecconf.add(ediTermino, 0, 1);
        panelsecconf.add(confTermino, 0, 2);
    }
    @FXML
    private void ediMateriaParalelo(){
        panelsecconf.getChildren().clear();
        visualizar();
        Button inMateria=new Button("Ingresar materia");
        Button ediMateria=new Button("Editar materia");
        Button agParalelo=new Button("Agregar paralelo");
        Button elimParalelo=new Button("Eliminar paralelo");
        panelsecconf.add(inMateria, 0, 0);
        panelsecconf.add(ediMateria, 0, 1);
        panelsecconf.add(agParalelo, 0, 2);
        panelsecconf.add(elimParalelo, 0, 3);
    }
    @FXML
    private void adPreguntas(){
        panelsecconf.getChildren().clear();
        Button agPregunta=new Button("Agregar pregunta");
        Button elimPregunta=new Button("Eliminar pregunta");
        panelsecconf.add(agPregunta, 0, 0);
        panelsecconf.add(elimPregunta, 0, 1);
    }
    
    
}
