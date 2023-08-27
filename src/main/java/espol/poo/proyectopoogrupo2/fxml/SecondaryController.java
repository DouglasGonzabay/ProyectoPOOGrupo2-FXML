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
import java.util.Collections;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import juego.Juego;
import modelo.Estudiante;
import modelo.Materia;
import modelo.NewClass;
import modelo.Paralelo;
import modelo.Pregunta;
import modelo.TerminoAcademico;
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
    private AnchorPane scrollEstudiante;
    
    static Boolean disposicion = false;
    //static ArrayList<Estudiante> estudiantesCOp = new ArrayList<>();
    @FXML
    private VBox jugadores;
    @FXML
    private Button volver;
    /*
    @FXML
    private ScrollPane scrollEstudiante;
    @FXML
    private ScrollPane scrollCompanero;
    */
    /**    private ScrollPane scrollCompanero;

     * Initializes the controller class.
     * 
     */
    static Estudiante participante;
    static Estudiante companero;
    static ArrayList<Pregunta> preguntas;
    static ArrayList<Pregunta> preguntasFiltradas;
    static Materia materiaElecta;
    @FXML
    private Button establecerPreg;
    @FXML
    private VBox visNivel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Materia> materias2 = NewClass.leerMaterias(".\\archivos\\materias.txt");
        slmateria.getItems().setAll(materias2);
        slparalelo.setDisable(true);
        scrollEstudiante.setPrefSize(100, 200);
        scrollEstudiante.setStyle("-fx-background-color:WHITE");
        btempezar.setDisable(true);
        //Desactiva la elección de niveles para preguntas
        ingnivel.setDisable(true);
        establecerPreg.setDisable(true);
        //scrollCompanero.setPrefSize(100, 200);
        //scrollCompanero.setStyle("-fx-background-color:WHITE");
        // TODO
    }  
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    //Escribe el reporte antes de iniciar el juego
    @FXML
    private void switchToJuego()throws IOException {
        Juego nuevoJuego = new Juego(participante,companero,preguntasFiltradas);
        ArrayList<Juego> lectura = Juego.leerReportes();
        lectura.add(nuevoJuego);
        Juego.escribirReportes(lectura);
        System.out.println(nuevoJuego.toString());
        App.setRoot("juego");
    }
    @FXML
    private void modParalelo(ActionEvent event){
        
        visNivel.getChildren().clear();
        ingnivel.setDisable(false);
        establecerPreg.setDisable(false);
        slparalelo.setDisable(false);
        btempezar.setDisable(true);
        //Establecer atributo de materia para validaciones
        materiaElecta =(Materia)slmateria.getValue();
        ArrayList<Paralelo> par = (ArrayList<Paralelo>)slmateria.getValue().getParalelos();
        String codigo = (String)slmateria.getValue().getCodigo();
        //CDomprueba que existan preguntas el arrayList
        try{
            preguntas = Pregunta.leerPreguntas(".\\archivos\\"+codigo+".dat");
        ///Visualizador de preguntas
        ArrayList<Integer> nivelesUnicos = new ArrayList<>();
        ArrayList<Integer> cantidad = new ArrayList<>();
        int NivelMax = materiaElecta.getNiveles();
        for(int i =1; i<=NivelMax;i++){
            nivelesUnicos.add(i);
            cantidad.add(0);
        }
        for(Pregunta p: preguntas){
            int level = p.getNivel();
            for(Integer h: nivelesUnicos){
                if(h==level){
                    int n = cantidad.get(nivelesUnicos.indexOf(h))+1;
                    cantidad.set(nivelesUnicos.indexOf(h),n);
                }
            }
            //nivelesDisponibles.add(level);
            //indicesPreg.add(preguntas.indexOf(p));
        }
        visNivel.getChildren().add(new Label("Cantidad de preguntas"));
        for(Integer h: nivelesUnicos){
            Label etiqueta = new Label("Nivel " + h + ": "+cantidad.get(nivelesUnicos.indexOf(h)));
            visNivel.getChildren().add(etiqueta);
        }
        
        ///
        //Comprobar
        for(Pregunta p: preguntas){
            System.out.println(p);
        }
        //
        }catch(NullPointerException f){
            visNivel.getChildren().add(new Label("No Existen preguntas"));
            establecerPreg.setDisable(true);
        }
        TerminoAcademico terJuego = NewClass.terminoConfigurado();
        System.out.println(par.size());
        ArrayList<Paralelo> filtrado = new ArrayList<>();
        
        for(Paralelo p: par){
            if(terJuego.toString().equals(p.getTermino().toString())){
                filtrado.add(p);
            }
        }
            if(!filtrado.isEmpty()){
                slparalelo.getItems().setAll(filtrado);
            }else{
                slparalelo.setPromptText("No existen paralelos");
               slparalelo.setDisable(true);
            }
        //scrollCompanero.getChildren().clear();
        
        //Borra la lista de estudiantes para evitar que se realice una selección erronea
        scrollEstudiante.getChildren().clear();
    }
    @FXML
    private void cargarEstudiantes(ActionEvent event){
        scrollEstudiante.getChildren().clear();
        jugadores.getChildren().clear();
        btempezar.setDisable(true);
        //scrollCompanero.getChildren().clear();
        ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>)slparalelo.getValue().getLista();
        if(estudiantes == null){
            scrollEstudiante.getChildren().add(new Label("No hay Estudiantes"));
            //scrollCompanero.getChildren().add(new Label("No hay Estudiantes"));
            scrollEstudiante.setPrefSize(100, 200);
            //scrollCompanero.setPrefSize(100, 200);
        }
        else{
            VBox estudiante = new VBox();
            //VBox companero = new VBox();
            int n=0;
            for(Estudiante e: estudiantes){
                Button est = new Button(e.toString());
                String nombre = e.getNombre();
                est.setOnAction(eh -> {
                    if(!disposicion){
                        disposicion = true;
                        est.setDisable(disposicion);
                        jugadores.getChildren().add(new Label("Participante: " + nombre));
                        participante = e;
                    }else{
                       disposicion = false;
                       jugadores.getChildren().add(new Label("Compañero: " + nombre));
                       est.setDisable(!disposicion);
                       scrollEstudiante.setDisable(true);
                       btempezar.setDisable(false);
                       companero = e;
                    }
                });
                volver.setOnAction(he->{
                    scrollEstudiante.setDisable(false);
                    disposicion = false;
                    jugadores.getChildren().clear();
                    btempezar.setDisable(true);
                    estudiante.getChildren().forEach(node->{
                        if (node instanceof Button){
                            Button b = (Button) node;
                            b.setDisable(false);
                        }
                    });                
                });
                
                est.setStyle("-fx-background-color:WHITE");
                Button com = new Button(e.toString());
                //com.setStyle("-fx-background-color:WHITE");
                estudiante.getChildren().add(est);
                //companero.getChildren().add(com);
                n+=25;
            }
            scrollEstudiante.getChildren().add(estudiante);
            //scrollCompanero.getChildren().add(companero);
            //Regula el tamaño acorde al tamaño predeterminado de un botón
            scrollEstudiante.setPrefSize(350,n);
            //scrollCompanero.setPrefSize(350,n);
        }
    }
    @FXML
    private void cargarPreguntas(){
        preguntasFiltradas = new ArrayList<>();
        ArrayList<Integer> nivelesUnicos = new ArrayList<>();
        ArrayList<Integer> cantidad = new ArrayList<>();
        int NivelMax = materiaElecta.getNiveles();
        for(int i =1; i<=NivelMax;i++){
            nivelesUnicos.add(i);
            cantidad.add(0);
        }
        //Lista que obtiene los niveles de cada pregunta
        ArrayList<Integer> nivelesDisponibles = new ArrayList<>();
        //Lista que obtiene los indices de la preguntas
        ArrayList<Integer> indicesPreg = new ArrayList<>();
        Collections.sort(nivelesUnicos);
        for(Pregunta p: preguntas){
            int level = p.getNivel();
            for(Integer h: nivelesUnicos){
                if(h==level){
                    int n = cantidad.get(nivelesUnicos.indexOf(h))+1;
                    cantidad.set(nivelesUnicos.indexOf(h),n);
                }
            }
            nivelesDisponibles.add(level);
            indicesPreg.add(preguntas.indexOf(p));
        }
        /*
        visNivel.getChildren().add(new Label("Cantidad de preguntas"));
        for(Integer h: nivelesUnicos){
            Label etiqueta = new Label("Nivel " + h + ": "+cantidad.get(nivelesUnicos.indexOf(h)));
            visNivel.getChildren().add(etiqueta);
        }*/
        
        
        String cantNiveles = ingnivel.getText();
        try{
            int cant = Integer.parseInt(cantNiveles);
            //Si no existe preguntas del nivel máximo de la materia, este nivel se descarta para el juego
            if(cantidad.contains(0)){
                int indice = cantidad.indexOf(0);
                nivelesUnicos.remove(0);
                cantidad.remove(indice);
            }
            //
            if(cant>Collections.min(cantidad)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Preguntas insuficientes");
                alert.setHeaderText("Notificacion");
                alert.setContentText("Número supera la cantidad mínima de preguntas existentes"); 
                alert.showAndWait();
                ingnivel.setText("");
            }else if(cant<=0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error al establecer preguntas");
                alert.setHeaderText("Notificacion");
                alert.setContentText("Valor inválido"); 
                alert.showAndWait();
                ingnivel.setText("");
            }else{
                //Filtrar Preguntas
                
                for(int m = 0; m<nivelesUnicos.size();m++){
                    int contador = 1;
                for(int n = 0; n<nivelesDisponibles.size();n++){
                    
                        if((nivelesDisponibles.get(n) == nivelesUnicos.get(m))&&contador<=cant){
                            preguntasFiltradas.add(preguntas.get(n));
                            contador+=1;
                        }else{
                            contador=0;
                            //n = nivelesDisponibles.size()+1;
                        }
                    }
                }
            }
            
        }catch(NumberFormatException nb){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error de conversión");
            alert.setHeaderText("Notificacion");
            alert.setContentText("Solo puede agregar números"); 
            alert.showAndWait();
            ingnivel.setText("");
        }
        
    }
}