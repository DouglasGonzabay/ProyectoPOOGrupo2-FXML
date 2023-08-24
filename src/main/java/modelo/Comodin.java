/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Daniela Basilio
 */
public class Comodin {
    private Scanner sc;
  private int comodinCincuenta, comodinCompanero, comodinSalon;
  public Comodin(){
    comodinCincuenta = 0;
    comodinCompanero = 0;
    comodinSalon = 0;
  }
  public int getComodinCincuenta(){
    return comodinCincuenta;
  }
  public int getComodinCompanero(){
    return comodinCompanero;
  }
  public int getComodinSalon(){
    return comodinSalon;
  }
  //Elimina dos de las preguntas y suma 1 al valor de comodinCincuenta
  public void cincuentaCincuenta(Pregunta p){
    ArrayList<String> respuestas = new ArrayList<>();
    respuestas.add(p.getR2());
    respuestas.add(p.getR3());
    respuestas.add(p.getR4());
    String respuestaRandom="";
    for (int i=0;i<respuestas.size();i++){
      int index=(int)(Math.random()*respuestas.size());
      respuestaRandom = respuestas.get(index);
    }
    p.setR2(respuestaRandom);
    p.setR3("-");
    p.setR4("-");
    //Pregunta pregunta1 = new Pregunta(p.getEnunciado(),p.getNivel(),p.getRespuestaCorrecta(),respuestaRandom,"-","-");
    //String muestraP = pregunta1.mostrarPregunta();
    //comodinCincuenta += 1;
    //return muestraP;
  }
  public void consultaCompanero(Estudiante e){
    sc = new Scanner(System.in);
    System.out.print("Seleccione la respuesta: ");
    String letter = sc.nextLine();
    System.out.println(e.getNombre() + "dice que la respuesta es" + letter);
    comodinCompanero += 1;
  }
  //El metodo consultaSalon establecerá un censo a los estudiantes sobre cual es la repuesta correcta, pero solo uno elegido por el monton será el que tenga la capacidad de indicar
  public void consultaSalon(Pregunta p){
    //sc = new Scanner(System.in);
    //System.out.print("Seleccione la respuesta: ");
    //String letter = sc.nextLine();
    System.out.println("El curso dice que la respuesta del salon es " + p.getRespuestaCorrecta());
    comodinSalon += 1;
  }
    
}
