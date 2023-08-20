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
  private ArrayList<String> ComodinesUsados;
  private String premio;
  
// que quieres hacer aquí para ayudarte : mira crea el constructor con esos atributos y crea uno solo con un atributo arrayListString mejor ok ( 
  public Juego(String m, int num, Estudiante participante, Estudiante companero, ArrayList<Pregunta> preguntas){
    this.materia=m;
    this.numParalelo=num;
    this.participante=participante;
    this.companero=companero;
    this.preguntas=preguntas; 
  }
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
  public String getNombreP(){
    return companero.getNombre();
  }
  public ArrayList<Pregunta> getPreguntas(){
    return preguntas;
  }
  
  public int getPuntaje(){
    return puntaje;
  }
  public void setPuntaje(int puntaje){
    this.puntaje += puntaje;
  }
  public void setPcon(){
    pCont+=1;
  }
  public void setNivelMax(int nivel){
    nivelMax = nivel;
  }
  public void setnCom(){
    nCom +=1;
  }
  //Para detalle de reporte
  public String generarReporte(){
    //Scanner sc=Scanner(System.in);
    //System.out.println("Ingrese el premio: ");
    //String premio=sc.NextLine();
    return "Participante: "+ this.getNombreP()+ "/Compañero: "+ companero+"/Nivel Maximo alcanzado: "+nivelMax+"/Preguntas contestadas: "+pCont+ "/Comodines usados: "+nCom+"/PREMIO: "+premio;
    //sc.close();   
  }
/*
  public ArrayList<String> generarDetallePregunta(Pregunta p){
    
  }
*/
  
}