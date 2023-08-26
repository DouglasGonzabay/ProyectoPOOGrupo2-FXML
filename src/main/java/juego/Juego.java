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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Juego{
  
  private String materia;
  private int numParalelo;
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
      this.preguntas = new ArrayList<>();
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
  public void setNivelMax(){
    nivelMax  +=1;
  }
  public void setnCom(){
    nCom +=1;
  }
  public void setTiempo(){
      tiempo = (tiempo - System.currentTimeMillis())/1000;
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
  
}