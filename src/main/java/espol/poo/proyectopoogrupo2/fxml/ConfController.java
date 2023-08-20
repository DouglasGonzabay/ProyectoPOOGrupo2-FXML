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
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modelo.Materia;
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
        //Boton para ingresar término
        Button inTermino=new Button("Ingresar término");
        VBox v1=new VBox(5); 
        inTermino.setOnAction(e->{
            v1.getChildren().clear();
            //VBox v2=new VBox(5); 
            TextField t1=new TextField();
            t1.setPromptText("Año");
            TextField t2=new TextField();
            t2.setPromptText("Número");
            Button aplicar=new Button("Aplicar");
            aplicar.setOnAction(h->{
                try{
                String anio, n;
                anio=t1.getText();
                n=t2.getText();
                int numero=Integer.parseInt(n);
                int a=Integer.parseInt(anio);
                boolean terminoExiste = TerminoAcademico.cargarTerminosString(".\\archivos\\TerminosAcademicos.txt").contains((new TerminoAcademico(anio,n)).toString());
                if(numero>2 || a<2023 || numero==0 || terminoExiste){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resultado de operacion");
                    alert.setHeaderText("Notificacion");
                    if(terminoExiste){
                       alert.setContentText("El término académico agregado ya existe"); 
                    }
                    else{
                        alert.setContentText("El año debe ser mayor al actual (2023) y el termino no puede \nsuperar el 2 ni ser 0 ");
                    }
                    
                    alert.showAndWait();
                    t1.setText("");
                    t2.setText("");
                }
                else{
                    TerminoAcademico.anadirTermino(".\\archivos\\TerminosAcademicos.txt",new TerminoAcademico(anio,n));
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Ingreso de datos exitoso");
                    //Actualiza visor de terminos
                    visualizartermino();
                    t1.setText("");
                    t2.setText("");
                }
                }catch(NumberFormatException nb){
                   mostrarAlerta(Alert.AlertType.INFORMATION, "Ingreso de datos incorrectos");
                    t1.setText("");
                    t2.setText("");
                    
                }
            });
            //v2.getChildren().addAll(t1,t2,aplicar);
            v1.getChildren().addAll(t1,t2,aplicar);
            //panelsecconf.add(v1,3,0);
        });
        
        //Creación de boton para editar término
        Button ediTermino=new Button("Editar término");
        ediTermino.setOnAction(i ->{
            v1.getChildren().clear();
            //VBox v2 = new VBox(5);
            ArrayList<TerminoAcademico> terminos = TerminoAcademico.cargarTerminos(".\\archivos\\TerminosAcademicos.txt");
            ComboBox<TerminoAcademico> cajita = new ComboBox<>();
            cajita.getItems().addAll(terminos);
            cajita.setPromptText("Escoja un Termino");
            cajita.setOnAction(j->{
                //v1.getChildren().clear();
               TerminoAcademico leer = (TerminoAcademico) cajita.getValue();
               TerminoAcademico cambiar = TerminoAcademico.busqueda(terminos, leer);
               int indice = terminos.indexOf(cambiar);
               ComboBox<String> cajita1 = new ComboBox<>();
               cajita1.getItems().setAll("Año","Numero de termino");
               cajita1.setPromptText("Escoja una opcion");
               cajita1.setOnAction(r -> {
                   //v1.getChildren().clear();
                   String lectura = (String) cajita1.getValue();
                   TextField newC = new TextField();
                   Button bt = new Button("Aplicar");
                   if(lectura.equals("Año")){
                       newC.setPromptText(cambiar.getAnio());
                   }else{
                       newC.setPromptText(cambiar.getNumero());
                   }
                   
                v1.getChildren().addAll(newC,bt);
               });
                v1.getChildren().add(cajita1);
            });
            v1.getChildren().add(cajita);
            
         //panelsecconf.add(v1,3,1);
        }
        );
        
        
        
        
        Button confTermino=new Button("Configurar término");
        inTermino.setWrapText(true);
        ediTermino.setWrapText(true);
        confTermino.setWrapText(true);
        panelsecconf.add(inTermino, 0, 0);
        panelsecconf.add(ediTermino, 0, 1);
        panelsecconf.add(confTermino, 0, 2);
        panelsecconf.add(v1,3,0);
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
        ArrayList<Materia>materias=NewClass.leerMaterias(".\\archivos\\materias.txt");;
        ComboBox<Materia> preguntas=new ComboBox<>();
        preguntas.getItems().setAll(materias);
        preguntas.setPromptText("Escoja una Materia");
        Button agPregunta=new Button("Agregar pregunta");
        Button elimPregunta=new Button("Eliminar pregunta");
        panelsecconf.add(preguntas, 0, 0);
        panelsecconf.add(agPregunta, 0, 1);
        panelsecconf.add(elimPregunta, 0, 2);
    }
    
    
    public void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    
}
