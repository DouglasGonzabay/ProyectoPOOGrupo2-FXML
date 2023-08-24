/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import modelo.Comodin;
import modelo.Pregunta;

/**
 * FXML Controller class
 *
 * @author User
 */
public class JuegoController implements Initializable {

    @FXML
    private BorderPane escena;
    @FXML
    private Label tiempo;
    @FXML
    private ImageView imgTiempo;
    @FXML
    private Button cincuenta;
    @FXML
    private Button pComp;
    @FXML
    private Button pSalon;
    @FXML
    private Label participante;
    @FXML
    private Label compañero;

    @FXML
    private VBox panelPreguntas;
    
    //static int m;
    @FXML
    private Label lbpregunta;
    @FXML
    private Button btSalir;
   
    /**
     * Initializes the controller class.
     */
    //static Boolean value;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //value = true;
        btSalir.setDisable(true);
        Pregunta pregunta1 = new Pregunta("¿Cuál es el atributo de campo que permite acceder a cualquier atributo o metodo sin impedimento alguno?", 1, "Public","Private","Protected","Defect");
  Pregunta pregunta2 = new Pregunta("Si tenemos que escribir por consola una variable de tipo char ¿Cúal de estas opciones es la correcta para inicializar una variable char por Scanner (Scanner sc = new Scanner(System.in)): ", 1, "sc.next().charAt(0)","sc.nextChar()","sc.nexInt()","sc.nextLine()");
  Pregunta pregunta3 = new Pregunta("¿Cual debe ser la salida de la siguiente linea?: String mes = String(12)", 2, "Error","12","String 12","Ninguna de las anteriores");
  Pregunta pregunta4 = new Pregunta("Es la habilidad de definir comportamiento especializado de un subclase: ",3,"Sobrecarga de métodos","Hilos","Sobreescritura de métodos","Ninguna");
  Pregunta pregunta5 = new Pregunta("Es un contrato de lo que una clase puede hacer sin decirle como hacerlo:  ",3,"Interfaces","Métodos","Sobreescritura de métodos","Herencia");
  Pregunta pregunta6 = new Pregunta("¿Cuál palabra clave sirve para heredar los metodos y atributos de una clase Padre a una clase Hija?", 2, "extends ","extend","implements","inherit");
    ArrayList<Pregunta> preguntas = new ArrayList<>();
    preguntas.add(pregunta1);
    preguntas.add(pregunta2);
    preguntas.add(pregunta3);
    preguntas.add(pregunta4);
    preguntas.add(pregunta5);
    preguntas.add(pregunta6);
    Collections.sort(preguntas);
    /*
    ArrayList<List<String>> azar = new ArrayList<>();
    for(Pregunta preg: preguntas){
        String[] respuestas = new String[]{preg.getRespuestaCorrecta(),preg.getR2(),preg.getR3(), preg.getR4()};
        List<String> respuestas_l = Arrays.asList(respuestas);
        Collections.shuffle(respuestas_l);
        azar.add(respuestas_l);
    }*/
    int n = 0;
    //int tamaño = preguntas.size();
    //m =0;
    mostrarPregunta(preguntas,n);
    //Secuencia s = new Secuencia(value,preguntas,n,azar);
    //s.start();
    //s.setSec(value);
    }
    
    public void mostrarPregunta(ArrayList<Pregunta> preguntas, int n){
        if(n<preguntas.size()&&n!=-10){
        
        lbpregunta.setText("");
        panelPreguntas.getChildren().clear();
        lbpregunta.setText("Pregunta Nº"+(n+1)+"  Nivel: "+preguntas.get(n).getNivel());
        lbpregunta.setStyle("-fx-font-weight: bold;-fx-font-size: 20;");
        
    String[] respuestas = new String[]{preguntas.get(n).getRespuestaCorrecta(),preguntas.get(n).getR2(),preguntas.get(n).getR3(), preguntas.get(n).getR4()};
    List<String> respuestas_l = Arrays.asList(respuestas);
    Collections.shuffle(respuestas_l);
        
    Label enun = new Label(preguntas.get(n).getEnunciado());
    enun.setStyle("-fx-font-weight: bold;-fx-font-size: 20;");
    enun.setWrapText(true);
    panelPreguntas.getChildren().add(enun);
    for(int i = 0; i<respuestas_l.size();i++){
        HBox h = new HBox(5);
        Label letra =new Label("");
            switch (i) {
                case 0:
                    letra.setText("a.");
                    break;
                case 1:
                    letra.setText("b.");
                    break;
                case 2:
                    letra.setText("c.");
                    break;
                default:
                    letra.setText("d.");
                    break;
            }
        letra.setStyle("-fx-font-weight: bold;-fx-font-size: 20;");
        String respondido = respuestas_l.get(i);
        Label res = new Label(respondido);
        res.setStyle("-fx-font-weight: bold;-fx-font-size: 15;");
        res.setWrapText(true);
        h.getChildren().addAll(letra,res);
        Pregunta p = preguntas.get(n);
        cincuenta.setOnMouseClicked(eh->{
            
            //Secuencia s = new Secuencia(true,preguntas,n,azar);
            //s.start();
            ComodinPregunta(p);
            mostrarPregunta(preguntas,n);
            
            cincuenta.setDisable(true);
        });
        
        h.setOnMouseClicked(e->{
            int m = 0;
            if(evaluarPregunta(p,respondido)){
                m = n+1;
                
                
            }else{
                m = -10;
                
            }
            //Secuencia s = new Secuencia(evaluarPregunta(p,respondido),preguntas,m,azar);
            //s.start();
            mostrarPregunta(preguntas,m);
            
        });
        panelPreguntas.getChildren().add(h);
        
        
    }}else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText("JUEGO TERMINADO");
        alert.showAndWait();
        panelPreguntas.getChildren().clear();
        btSalir.setDisable(false);
        
        }
    }
    
    
   public boolean evaluarPregunta(Pregunta p, String respuesta){
       if(respuesta.equals(p.getRespuestaCorrecta())){
           return true; 
           
        }else{
           return false;
            
        }
   }
   public static void ComodinPregunta(Pregunta p){
       Comodin c = new Comodin();
       c.cincuentaCincuenta(p);
    
   }
   
   private class Secuencia extends Thread{
      Boolean is ;
      int secuencia;
      public Secuencia(Boolean is){
          this.is = is;
          this.secuencia = 60;
      }
      public void run(){
          //int r = 60;
          while(is&&secuencia!=0){
                String time = secuencia+"";
                Platform.runLater(()->{
                    tiempo.setText(time);
                    
                });
                secuencia = secuencia-1;
          }
      }
     public void setSecuencia(){
         secuencia = 0;
     }
   }
   
   @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }    
   /*
   private class Secuencia extends Thread{
       Boolean sec;
       ArrayList<Pregunta> preguntas;
       int tamaño;
       ArrayList<List<String>> azar;
       public Secuencia(Boolean sec, ArrayList<Pregunta> preguntas,int tamaño, ArrayList<List<String>> azar){
           this.sec = sec;
           this.preguntas = preguntas;
           this.tamaño = tamaño;
           this.azar = azar;
       }
       public void setSec(Boolean sec){
           this.sec = sec;
       }
        public void run(){
            int r = 60;
            //Platform.runLater(()->{mostrarPregunta(preguntas,tamaño,azar);});
            while(sec&&r!=0&&(tamaño<preguntas.size())){
                
                
                String time = r+"";
                Platform.runLater(()->{
                    panelPreguntas.getChildren().clear();
                    mostrarPregunta(preguntas,tamaño,azar);
                    tiempo.setText(time);
                    });
               System.out.println("1");
               
               
                try {
                    Thread.sleep(1000);
                    r = r-1;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        public void detener(){
                sec = false;
            }
    }
    */
    /*
    

    }    
    public void comprobarPregunta(Pregunta p, String respuesta){
        //int i = 0;
        if(respuesta.equals(p.getRespuestaCorrecta())){
           //return true; 
           m =1;
        }else{
            //return false;
            m = 0;
        }
    }*/
    
    

    /*
    private class Secuencia extends Thread{
        boolean pregTerm;
        boolean esCorrecta;
        Pregunta p;
        public Secuencia(Pregunta p){
            this.pregTerm = false;
            this.p = p;
        }
        public void run(){
            Platform.runLater(() ->{
            enunciado.setText("");
            enunciadoA.setText("");
            EnunciadoB.setText("");
            EnunciadoC.setText("");
            EnunciadoD.setText("");
            mostrarPregunta(p);
            
            opcionA.setOnAction(a->{
                if(enunciadoA.getText().equals(p.getRespuestaCorrecta())){
                    pregTerm = true;
                    esCorrecta = true;
                }else{
                    pregTerm = true;
                    esCorrecta = false;
                }
            });
            opcionB.setOnAction(a->{
                if(EnunciadoB.getText().equals(p.getRespuestaCorrecta())){
                    pregTerm = true;
                    esCorrecta = true;
                }else{
                    pregTerm = true;
                    esCorrecta = false;
                }
            });
            OpcionC.setOnAction(a->{
                if(EnunciadoC.getText().equals(p.getRespuestaCorrecta())){
                    pregTerm = true;
                    esCorrecta = true;
                }else{
                    pregTerm = true;
                    esCorrecta = false;
                }
            });
            OpcionD.setOnAction(a->{
                if(EnunciadoD.getText().equals(p.getRespuestaCorrecta())){
                    pregTerm = true;
                    esCorrecta = true;
                }else{
                    pregTerm = true;
                    esCorrecta = false;
                }
            });
            esperar(pregTerm);
            });
        }*/
        /*
        public void esperar(boolean corr) {
            int n = 60;
            while(!corr||n!=0){
                try{
                  Thread.sleep(1000);
                  //tiempo.setText("");
                  //tiempo.setText(String.valueOf(n));
                  n = n - 1;
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                
            }
        }*/
    
}
