/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import modelo.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Window;
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
                if(numero>2 || a<2023 || numero<=0 || terminoExiste){
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
               cajita.setDisable(true);
                //v1.getChildren().clear();
               //TerminoAcademico leer = (TerminoAcademico) cajita.getValue();
               TerminoAcademico cambiar = TerminoAcademico.busqueda(terminos, (TerminoAcademico) cajita.getValue());
               int indice = terminos.indexOf(cambiar);
               ComboBox<String> cajita1 = new ComboBox<>();
               cajita1.getItems().setAll("Año","Numero de termino");
               cajita1.setPromptText("Escoja una opcion");
               cajita1.setOnAction(r -> {
                   cajita1.setDisable(true);
                   String lectura = (String) cajita1.getValue();
                   TextField newC = new TextField();
                   Button bt = new Button("Aplicar");
                   if(lectura.equals("Año")){
                       newC.setPromptText(cambiar.getAnio());
                   }else{
                       newC.setPromptText(cambiar.getNumero());
                   }
                   bt.setOnAction(k ->{
                      try{
                          String leido = newC.getText();
                          int numero = Integer.parseInt(leido);
                          switch(lectura){
                          case "Año":
                              if(numero<2023){
                                  mostrarAlerta(Alert.AlertType.INFORMATION, "Debe ingresar un numero mayor o igual al actual (2023)");
                              }else{
                              //Método para cambiar termino académico a todos los paralelos que contengan la versión anterior
                              NewClass.reemplazarEnParalelo(cambiar.getAnio(), cambiar.getNumero(), numero);
                              NewClass.reemplazarTermino(".\\archivos\\materias.txt", cambiar.getAnio(), cambiar.getNumero(), numero);
                              cambiar.setAnio(leido);
                              terminos.set(indice, cambiar);
                              TerminoAcademico.actualizarTermino(".\\archivos\\TerminosAcademicos.txt", terminos);
                
                              visualizartermino();
                              mostrarAlerta(Alert.AlertType.INFORMATION, "¡Actualización exitosa!");
                              v1.getChildren().clear();
                              }
                              break;
                          case "Numero de termino":
                              
                              if(numero<=0 || numero>2){
                                  mostrarAlerta(Alert.AlertType.INFORMATION, "Debe ingresar un numero entre 1 y 2");
                              }else{
                                NewClass.reemplazarEnParalelo(cambiar.getAnio(), cambiar.getNumero(), numero);
                                NewClass.reemplazarTermino(".\\archivos\\materias.txt", cambiar.getAnio(), cambiar.getNumero(), numero);
                                cambiar.setNumero(leido); 
                                terminos.set(indice, cambiar);
                                TerminoAcademico.actualizarTermino(".\\archivos\\TerminosAcademicos.txt", terminos);
                                
                                visualizartermino();
                                mostrarAlerta(Alert.AlertType.INFORMATION, "¡Actualización exitosa!");
                                v1.getChildren().clear();
                              }
                              break;
                      }
                      }catch(NumberFormatException nfe){
                      mostrarAlerta(Alert.AlertType.INFORMATION, "INGRESO DE DATOS ERRONEO");
                      newC.setText("");
                      }
                   
                   
                   });
                   
                   
                v1.getChildren().addAll(newC,bt);
               });
                v1.getChildren().add(cajita1);
            });
            v1.getChildren().add(cajita);

        }
        );
        
        
        
        
        Button confTermino=new Button("Configurar término");
        confTermino.setOnAction(eff->{
            v1.getChildren().clear();
            ArrayList<TerminoAcademico> terminos = TerminoAcademico.cargarTerminos(".\\archivos\\TerminosAcademicos.txt");
            ComboBox<TerminoAcademico> eleccion = new ComboBox<>();
            eleccion.getItems().addAll(terminos);
            eleccion.setOnAction(elec->{
                //Escribe en un archivo txt un unico termino configurado
                NewClass.configuracionJuego((TerminoAcademico)eleccion.getValue());
            });
            v1.getChildren().add(eleccion);
        });
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
        inMateria.setWrapText(true); 
        Button ediMateria=new Button("Editar materia");
        ediMateria.setWrapText(true); 
        Button agParalelo=new Button("Agregar paralelo");
        agParalelo.setWrapText(true); 
        Button elimParalelo=new Button("Eliminar paralelo");
        elimParalelo.setWrapText(true); 
        panelsecconf.add(inMateria, 0, 0);
        panelsecconf.add(ediMateria, 0, 1);
        panelsecconf.add(agParalelo, 0, 2);
        panelsecconf.add(elimParalelo, 0, 3);
        VBox v2=new VBox(5); 
        inMateria.setOnAction(f->{
            v2.getChildren().clear();
            Button aplicar1=new Button("Aplicar");
            TextField inCodigo=new TextField();
            inCodigo.setPromptText("Código");  
            TextField inNombre=new TextField();
            inNombre.setPromptText("Nombre");
            TextField inNiveles=new TextField();
            inNiveles.setPromptText("Cantidad de niveles");
            
            aplicar1.setOnAction(l->{
            String cod_Materia=inCodigo.getText();
            String nomb_Materia=inNombre.getText();
            String niveles_Pregunta=inNiveles.getText();
            NewClass.agregarMateria(".\\archivos\\materias.txt", nomb_Materia, cod_Materia, niveles_Pregunta);
            mostrarAlerta(Alert.AlertType.INFORMATION, "INGRESO DE DATOS CORRECTO");
            inCodigo.setText("");
            inNombre.setText("");
            inNiveles.setText("");
            visualizar();
                
            });
            //NewClass.agregarMateria(".\\archivos\\materias.txt", nomb_Materia, cod_Materia, niveles_Pregunta);
            //int niveles_preguntas=Integer.parseInt(niveles_Pregunta); no se necesiten los niveles convertido en int
            //VBox b2=new VBox(4);
            v2.getChildren().addAll(inCodigo,inNombre,inNiveles,aplicar1);
            //panelsecconf.add(v2, 2, 0);
        });
        ediMateria.setOnAction(a->{
            v2.getChildren().clear();
            ArrayList<Materia>materias2=NewClass.leerMaterias(".\\archivos\\materias.txt");
            Button boton2=new Button("Aplicar");
            ComboBox<Materia> cajita=new ComboBox<>();
            cajita.setPromptText("Escoja una materia");
            cajita.getItems().setAll(materias2);
            cajita.setOnAction(pl->{
                Materia seleccion = (Materia) cajita.getValue();
                cajita.setDisable(true);
                TextField ingresoM = new TextField();
                ingresoM.setPromptText("Nuevo Nombre");
                TextField ingresoN = new TextField();
                ingresoN.setPromptText("Nueva cantidad de niveles");
                v2.getChildren().addAll(ingresoM,ingresoN,boton2);
                boton2.setOnAction(il->{
                String lectura1 = ingresoM.getText();
                String lectura2 = ingresoN.getText();
                String codigo = seleccion.getCodigo();
                NewClass.editarMateria(".\\archivos\\materias.txt",codigo,lectura1);
                NewClass.editarMateria(".\\archivos\\materias.txt",codigo,lectura2);
                ingresoM.setText("");
                ingresoN.setText("");
                mostrarAlerta(Alert.AlertType.INFORMATION, "INGRESO DE DATOS CORRECTOS");
                v2.getChildren().clear();
                });
            });
        v2.getChildren().add(cajita);
        });
        
        
        agParalelo.setOnAction(b->{
            v2.getChildren().clear();
            ArrayList<Materia>materias1=NewClass.leerMaterias(".\\archivos\\materias.txt");
            ArrayList<TerminoAcademico> terminos1 = TerminoAcademico.cargarTerminos(".\\archivos\\TerminosAcademicos.txt");
            ComboBox<Materia> cajita3=new ComboBox<>();
            cajita3.setPromptText("Escoja una materia"); 
            cajita3.getItems().setAll(materias1);
            
            cajita3.setOnAction(jh->{
                Materia selec = (Materia) cajita3.getValue();
                String nombreM = selec.getNombre();
                cajita3.setDisable(true);
                ComboBox<TerminoAcademico> cajita4=new ComboBox<>();
                cajita4.setPromptText("Escoja un término académico");
                cajita4.getItems().setAll(terminos1);
                v2.getChildren().add(cajita4);
                cajita4.setOnAction(pl->{
                    TerminoAcademico terM = (TerminoAcademico) cajita4.getValue();
                    String anio = terM.getAnio();
                    String numT = terM.getNumero();
                    cajita4.setDisable(true);
                    Button boton3=new Button("Aplicar");  
                    TextField numParalelo = new TextField();
                    numParalelo.setPromptText("Número del paralelo");
                    v2.getChildren().addAll(numParalelo,boton3);
                    boton3.setOnAction(g->{
                        String num_Paralelo=numParalelo.getText();
                        try{
                        int numero = Integer.parseInt(num_Paralelo);
                        NewClass.agregarParalelo(".\\archivos\\materias.txt",nombreM,numero,anio,numT);
                        numParalelo.setText("");
                        }catch(NumberFormatException nxf){
                        mostrarAlerta(Alert.AlertType.INFORMATION, "INGRESO DE DATOS ERRONEO");
                        numParalelo.setText("");}
                        Label carga = new Label("Desea cargar el archivo del paralelo?");
                        HBox hbC = new HBox();
                        Button si = new Button("Si");
                        Button no = new Button("No");
                        hbC.getChildren().addAll(si,no);
                        v2.getChildren().addAll(carga,hbC);
                        FileChooser fileelegido = new FileChooser();
                        si.setOnAction(sib->{
                            Window ventanaEmergente = si.getScene().getWindow();
                            File selectedFile = fileelegido.showOpenDialog(ventanaEmergente);
                            if (selectedFile != null) {
                            try {
                            Path archivoDir = new File("archivos").toPath(); // Carpeta "archivos" en el proyecto
                            Path targetPath = archivoDir.resolve(selectedFile.getName());
                            Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Archivo cargado y guardado en la carpeta 'archivos'");
                            } catch (IOException e) {
                                e.printStackTrace();
                                }
                            }
                        v2.getChildren().clear();
                        });
                        no.setOnAction(nob->{
                           mostrarAlerta(Alert.AlertType.INFORMATION, "RECUERDE CARGAR EL ARCHIVO LUEGO EN LA SIGUIENTE\n RUTA: .\\archivos");
                           v2.getChildren().clear();
                        
                        });
                        

                    });
                
                });
                
                
                
            
            
            
            
            
            
            
            
            
            
            });
                                   
            
            v2.getChildren().add(cajita3);
        });
        elimParalelo.setOnAction(c->{
            v2.getChildren().clear();
            Button aplicar4=new Button("Aplicar");
            ComboBox<String> cajita5=new ComboBox<>();
            cajita5.setPromptText("Escoja un paralelo");
            //VBox b5=new VBox(2);
            v2.getChildren().addAll(cajita5,aplicar4);
            
        });
      panelsecconf.add(v2, 3, 0);
    }
    /*
    Codigó que iria entre las líneas 279 y 280: es para desactivar la caja de texto si se selecciona 
    el ComboBox y viceversa pero me permitía mostrar las opciones en el programa, por eso lo comenté
    no sé que este mal planteado
            cajita2.setOnAction(j->{
                if(cajita2.getSelectionModel().getSelectedItem()!= null){
                ediCodigo.setDisable(true);
                }else{
                    ediCodigo.setDisable(false);
                }
            });
    */
    @FXML
    private void adPreguntas(){
        panelsecconf.getChildren().clear();
        ArrayList<Materia>materias=NewClass.leerMaterias(".\\archivos\\materias.txt");
        ComboBox<Materia> preguntas=new ComboBox<>();
        preguntas.getItems().setAll(materias);
        preguntas.setPromptText("Escoja una Materia");
        Button agPregunta=new Button("Agregar pregunta");
        Button elimPregunta=new Button("Eliminar pregunta");
        panelsecconf.add(preguntas, 0, 0);
        panelsecconf.add(agPregunta, 0, 1);
        panelsecconf.add(elimPregunta, 0, 2);
        agPregunta.setOnAction(d->{
            Button agregar=new Button("Agregar");
            TextField enunciadoP=new TextField();
            String newEnunciado=enunciadoP.getText();
            enunciadoP.setPromptText("Enunciado de la pregunta");
            TextField nivelP=new TextField();
            String nivelDeP=nivelP.getText();
            nivelP.setPromptText("Nivel de la pregunta");
            TextField respuestaC=new TextField();
            String respuestaCorrecta=respuestaC.getText();
            respuestaC.setPromptText("Respuesta correcta");
            TextField respuestaPos1=new TextField();
            String respuestaPosible1=respuestaPos1.getText();
            respuestaPos1.setPromptText("Posible respuesta 1");
            TextField respuestaPos2=new TextField();
            String respuestaPosible2=respuestaPos2.getText();
            respuestaPos2.setPromptText("Posible respuesta 2");
            TextField respuestaPos3=new TextField();
            String respuestaPosible3=respuestaPos3.getText();
            respuestaPos3.setPromptText("Posible respuesta 3");            
            VBox b6=new VBox(7);   
            b6.getChildren().addAll(enunciadoP,nivelP,respuestaC,respuestaPos1,respuestaPos2,respuestaPos3,agregar);
            panelsecconf.add(b6,1,1);
        });
    }
    
    
    public void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
   
    
    
    
}
