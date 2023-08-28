/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

/**
 *
 * @author User
 */
import modelo.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Juego implements Serializable{
  
  //private String materia;
  //private int numParalelo;
  private Estudiante participante;
  private Estudiante companero;
  private ArrayList<Pregunta> preguntas;
  private int puntaje = 0; 
  private int pCont = 0;
  private int nCom = 0;
  private int nivelMax = 0;
  private ArrayList<String> comodinesUsados;
  private String premio;
  private String fecha;
  private long tiempo;
  private ArrayList<String> preguntasContestadas;
  private static final long serialVersionUID = 1;
  private String detalle;
  
// que quieres hacer aquí para ayudarte : mira crea el constructor con esos atributos y crea uno solo con un atributo arrayListString mejor ok ( 
  public Juego(Estudiante e, Estudiante c, ArrayList<Pregunta> p){
      this.fecha = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a").format(LocalDateTime.now());
      this.participante = e;
      this.companero = c;
      this.tiempo = System.currentTimeMillis();
      this.nivelMax = 0;
      this.puntaje = 0;
      this.comodinesUsados = new ArrayList<>();
      this.pCont = 0;
      this.nCom = 0;
      this.premio = "";
      this.preguntasContestadas = new ArrayList<>();
      this.preguntas = p;
  }
  public Juego (String fecha,Estudiante participante,int nivel,long tiempo,int pCont,int numComodines,String premio,String detalles){
      this.fecha=fecha;
      this.participante=participante;
      this.nivelMax=nivel;
      this.tiempo=tiempo;
      this.pCont=pCont;
      this.nCom=numComodines;
      this.premio=premio;
      this.detalle=detalles;
    }
  
  /*public Juego(String m, int num, Estudiante participante, Estudiante companero, ArrayList<Pregunta> preguntas){
    this.materia=m;
    this.numParalelo=num;
    this.participante=participante;
    this.companero=companero;
    this.preguntas=preguntas; 
  }*/
  public void escribirReporte(String ruta) throws IOException{
    try(BufferedWriter bff = new BufferedWriter(new FileWriter(ruta,true))){
      String linea;
      bff.write(generarReporte());
      bff.write("\n");
    }catch(FileNotFoundException exp){
      exp.printStackTrace();
    }

  }
  
  public Estudiante getParticipante(){
    return participante;
  }
  public Estudiante getCompanero(){
    return companero;
  }
  /*
  public String getNombreP(){
    return companero.getNombre();
  }
  */
  public ArrayList<Pregunta> getPreguntas(){
    return preguntas;
  }
  
  public int getPuntaje(){
    return puntaje;
  }
  public String getFecha(){
      return fecha;
  }
  public int getPcon(){
      return pCont;
  }
  public int getNcont(){
      return nCom;
  }
  public int getNivelMax(){
      return nivelMax;
  }
  public long getTiempo(){
      return tiempo;
  }
  public ArrayList<String> getPreguntasContestadas(){
      return preguntasContestadas;
  }
  public ArrayList<String> getComodinesUsados(){
      return comodinesUsados;
  }
  public void setPuntaje(int puntaje){
    this.puntaje += puntaje;
  }
  public void setPcon(){
    pCont+=1;
  }
  public void setNivelMax(int n){
    nivelMax  = n;
  }
  public void setnCom(){
    nCom +=1;
  }
  public void setTiempo(){
      tiempo = (System.currentTimeMillis()- tiempo)/1000;
  }
  public void agregarComodin(String com){
      comodinesUsados.add(com);
  }
  public void agregarPregunta(String enun){
      preguntasContestadas.add(enun);
  }
  public void setPremio(String p){
      premio = p;
  }
  public String getPremio(){
      return premio;
  }
  
  
  //Escribir archivo binario de datos del juego
  public static void escribirReportes(ArrayList<Juego> juegos){
    try{
      ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(".\\archivos\\Reporte.dat"));
      escribir.writeObject(juegos);
      escribir.flush();
      escribir.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
  //Leer un archivo binario de reportes de juego
  public static ArrayList<Juego> leerReportes(){
    ArrayList<Juego> reportes = new ArrayList<>();
    try{
      ObjectInputStream p = new ObjectInputStream(new FileInputStream(".\\archivos\\Reporte.dat"));
      reportes = (ArrayList<Juego>)p.readObject();
      p.close();
    }
    catch(ClassNotFoundException e){
      System.out.println("Archivo vacío");
      reportes = new ArrayList<>();
    }
    catch(IOException ej){
      ej.printStackTrace();
      reportes = new ArrayList<>();
    }
    catch(Exception ex){
      ex.printStackTrace();
      reportes = new ArrayList<>();
    }
    return reportes;
  }
  
  public String toString(){
      return participante + "; " + companero + "; " + preguntas.size();
  }
  
  //Para detalle de reporte
  public String generarReporte(){
    //Scanner sc=Scanner(System.in);
    //System.out.println("Ingrese el premio: ");
    //String premio=sc.NextLine();
    //return "Participante: "+ this.getNombreP()+ "/Compañero: "+ companero+"/Nivel Maximo alcanzado: "+nivelMax+"/Preguntas contestadas: "+pCont+ "/Comodines usados: "+nCom+"/PREMIO: "+premio;
    //sc.close();
    return "";
  }
/*
  public ArrayList<String> generarDetallePregunta(Pregunta p){
    
  }
*/
  public static void main(String[] arr){
      ArrayList<Juego> reportes = Juego.leerReportes();
      for(Juego j: reportes){
          System.out.println(j.toString()+" Nivel Max: "+j.getNivelMax() + " P contestadas: " + j.getPcon() + " Puntaje: "+j.getPuntaje() + " Tiempo: " + j.getTiempo() + " Fecha: " + j.getFecha());
      }
  }
  
}