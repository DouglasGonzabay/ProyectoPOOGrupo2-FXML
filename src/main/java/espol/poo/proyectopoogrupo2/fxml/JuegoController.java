/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectopoogrupo2.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
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
    private Label enunciado;
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
    private Button opcionA;
    @FXML
    private Label enunciadoA;
    @FXML
    private Button opcionB;
    @FXML
    private Label EnunciadoB;
    @FXML
    private Button OpcionC;
    @FXML
    private Label EnunciadoC;
    @FXML
    private Button OpcionD;
    @FXML
    private Label EnunciadoD;

    static boolean pregTerm;
    static boolean esCorrecta;
    int n;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Pregunta pregunta1 = new Pregunta("¿Cuál es el atributo de campo que permite acceder a cualquier atributo o metodo sin impedimento alguno?", 1, "Public","Private","Protected","Defect");
  Pregunta pregunta2 = new Pregunta("Si tenemos que escribir por consola una variable de tipo char ¿Cúal de estas opciones es la correcta para inicializar una variable char por Scanner (Scanner sc = new Scanner(System.in)): ", 1, "sc.next().charAt(0)","sc.nextChar()","sc.nexInt()","sc.nextLine()");
  Pregunta pregunta3 = new Pregunta("¿Cual debe ser la salida de la siguiente linea?: String mes = String(12)", 2, "Error","12","String 12","Ninguna de las anteriores");
    ArrayList<Pregunta> preguntas = new ArrayList<>();
    preguntas.add(pregunta1);
    preguntas.add(pregunta2);
    preguntas.add(pregunta3);
    esCorrecta = true;
    n = 0;
    for(int i=0; i<preguntas.size();i++){

        if(esCorrecta){
            mostrarPregunta(preguntas.get(i));
            i = n;
            System.out.println(i);
        }else{
            enunciado.setText("");
        enunciadoA.setText("");
        EnunciadoB.setText("");
        EnunciadoC.setText("");
        EnunciadoD.setText("");
            break;
        }
        
    }
    }    
    public void mostrarPregunta(Pregunta p){
        //int i = 0;
        String[] respuestas = new String[]{p.getRespuestaCorrecta(),p.getR2(),p.getR3(), p.getR4()};
    List<String> respuestas_l = Arrays.asList(respuestas);
    Collections.shuffle(respuestas_l);
    enunciado.setText(p.getEnunciado());
    enunciado.setFont(new Font("Tahoma", 14));
    enunciadoA.setText(respuestas_l.get(0));
    enunciadoA.setFont(new Font("Tahoma", 14));
    EnunciadoB.setText(respuestas_l.get(1));
    EnunciadoB.setFont(new Font("Tahoma", 14));
    EnunciadoC.setText(respuestas_l.get(2));
    EnunciadoC.setFont(new Font("Tahoma", 14));
    EnunciadoD.setText(respuestas_l.get(3));
    EnunciadoD.setFont(new Font("Tahoma", 14));
    }
    
    

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
