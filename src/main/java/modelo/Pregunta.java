/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;
public class Pregunta implements Comparable<Pregunta>, Serializable{
  private String enunciado;
  private int nivelPregunta;
  private String respuesta1, respuesta2, respuesta3, respuesta4;
  private static final long serialVersionUID = 1;
  public Pregunta(String enun, int niv, String r1, String r2, String r3, String r4){
    enunciado = enun;
    //Comprueba si el nivel de la pregunta es menor o igual al del nivel maximo de la materia, si el nivel de la pregunta supera el maximo de la materia entonces procede a agregar el valor por defecto(maximo) de la materia a la pregunta
    nivelPregunta = niv;
    //r1 debe ser la respuesta correcta
    respuesta1 = r1;
    respuesta2 = r2;
    respuesta3 = r3;
    respuesta4 = r4;
  }
  //Muestra la pregunta y las respuestas de forma aleatoria
  public String mostrarPregunta(){
    String a = enunciado;
    //ArrayList<String> respuestasR = new ArrayList<>();
    //respuestasR.add(enunciado);
    String[] letters = new String[]{"\na. ","\nb. ","\nc. ","\nd. "};
    String[] respuestas = new String[]{respuesta1,respuesta2,respuesta3, respuesta4};
    List<String> respuestas_l = Arrays.asList(respuestas);
    Collections.shuffle(respuestas_l);
    for(String r: respuestas_l){
      a = a + letters[respuestas_l.indexOf(r)] + r;
      //respuestasR.add(letters[respuestas_l.indexOf(r)] + r);
    }
    //for(String r: respuestasR){
      //System.out.println(r);
    //}
    return a;
  }
  //Recibe el ArrayList de mostrarPregunta y un caracter equivalente a la seleccion de la respuesta
  public boolean esCorrecta(String mostrarpreg ,char seleccion){
    String[] pregun = mostrarpreg.split("\n");
    String[] resp = new String[]{pregun[1],pregun[2],pregun[3],pregun[4]};
    for(String resSelec: resp){
      char letra = resSelec.charAt(0);
      //String l = letra + "";
      if(letra==seleccion){
        if(resSelec.split(". ")[1].equals(getRespuestaCorrecta())){
          return true;
        }
      }
    }
    return false;
  }
  
  public String getRespuestaCorrecta(){
    return respuesta1;
  }
  public int getNivel(){
    return nivelPregunta;
  }
  public void setNivel(int nivelPregunta){
    this.nivelPregunta = nivelPregunta;
  }
  public String getR2(){
    return respuesta2;
  }
  public String getR3(){
    return respuesta3;
  }
  public String getR4(){
    return respuesta4;
  }
  public String getEnunciado(){
    return enunciado;
  }
  public void setR2(String r2){
    this.respuesta2=r2;
  }
  public void setR3(String r3){
    this.respuesta3=r3;
  }
  public void setR4(String r4){
    this.respuesta4=r4;
  }
  //Ordenamiento por nivel
  public int compareTo(Pregunta pregunta){
    if(this.nivelPregunta<pregunta.nivelPregunta){
      return -1;
    }
    else if(this.nivelPregunta>pregunta.nivelPregunta){
      return 1;
    }
    else{
      return 0;
    }
  }
  //Genera un ArrayList de las preguntas guardadas
  
  public static ArrayList<Pregunta> leerPreguntas(String ruta){
    ArrayList<Pregunta> preg = new ArrayList<>();
    try{
      ObjectInputStream p = new ObjectInputStream(new FileInputStream(ruta));
      preg = (ArrayList<Pregunta>)p.readObject();
      p.close();
    }
    catch(ClassNotFoundException e){
      System.out.println("Archivo vacío");
    }
    catch(IOException ej){
      ej.printStackTrace();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    return preg;
  }
  
  
  //Escribir y crear archivo binario de preguntas
  public static void escribirPreguntas(String ruta, ArrayList<Pregunta> preguntas){
    try{
      ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(ruta));
      escribir.writeObject(preguntas);
      escribir.flush();
      escribir.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  public static void main(String[] args){
    /*
    Pregunta pregunta1 = new Pregunta("¿Cuál es el atributo de campo que permite acceder a cualquier atributo o metodo sin impedimento alguno?", 1, "Public","Private","Protected","Defect");
  Pregunta pregunta2 = new Pregunta("Si tenemos que escribir por consola una variable de tipo char ¿Cúal de estas opciones es la correcta para inicializar una variable char por Scanner (Scanner sc = new Scanner(System.in)): ", 1, "sc.next().charAt(0)","sc.nextChar()","sc.nexInt()","sc.nextLine()");
  Pregunta pregunta3 = new Pregunta("¿Cual debe ser la salida de la siguiente linea?: String mes = String(12)", 2, "Error","12","String 12","Ninguna de las anteriores");
    ArrayList<Pregunta> preguntas = new ArrayList<>();
    preguntas.add(pregunta1);
    preguntas.add(pregunta2);
    preguntas.add(pregunta3);
    Pregunta.escribirPreguntas("archivo/preguntas.dat",preguntas);
    */
    
    //Pregunta pregunta4 = new Pregunta("¿Cuál palabra cable sirve para heredar los metodos y atributos de una clase Padre a una clase Hija?", 2, "extends ","extend","implements","inherit");
    ArrayList<Pregunta> preguntas = Pregunta.leerPreguntas("archivo/preguntas.dat");
    
    //preguntas.add(pregunta4);
    //Pregunta.escribirPreguntas("archivo/preguntas.dat",preguntas);
    
    for(Pregunta p: preguntas){
      System.out.println(p.mostrarPregunta());
    }
    
  }
  
  
}
