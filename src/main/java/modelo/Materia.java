/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

/**
 *
 * @author Daniela Basilio
 */
public class Materia {
    private String nombre;
  private String codigo;
  private int niveles;
  // ArrayList<TerminoAcademico> terminos;
  //Corrección1: Se añade el arrayList de las preguntas
  private ArrayList<Pregunta> preguntas;
  private ArrayList<Paralelo> paralelos;
  ////////////////////////////////////////
  public Materia(String n, String cod, int nv){
    nombre = n;
    codigo = cod;
    niveles = nv;
    paralelos = null;
    preguntas = null;
  }
  public Materia(String n, String cod, int nv, ArrayList<Paralelo> p){
    nombre = n;
    codigo = cod;
    niveles = nv;
    paralelos = p;
    preguntas = null;      
  }
  public Materia(String n, String cod, ArrayList<Pregunta> preguntas, int nv){
    nombre = n;
    codigo = cod;
    niveles = nv;
    paralelos = null;
    this.preguntas = preguntas;
    Collections.sort(preguntas);      
  }
  
  public Materia(String n, String cod, int nv, ArrayList<Paralelo> p, ArrayList<Pregunta> preguntas){
    nombre = n;
    codigo = cod;
    niveles = nv;
    paralelos = p;
    this.preguntas = preguntas;
    Collections.sort(preguntas);
  }
  public String getNombre(){
    return nombre;
  }
  public String getCodigo(){
    return codigo;
  }
  public int getNiveles(){
    return niveles;
  }
  public ArrayList<Paralelo> getParalelos(){
    return paralelos;
  }
  public ArrayList<Pregunta> getPreguntas(){
    //Añadir info si se requiere
    return preguntas;
  }
  /*
  public void agregarTermino(TerminoAcademico ter){
    terminos.add(ter);
  }
*/
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  public void setCodigo(String codigo){
    this.codigo = codigo;
  }
  public void setNiveles(int niveles){
    this.niveles = niveles;
  }
  /*
  public void setTerminos(ArrayList<TerminoAcademico> terminos){
    this.terminos = terminos;
  }
  */
  public String toString(){
    return "Materia: "+nombre+"- Codigo: "+codigo;
  }
  //métodos nuevos
  //Agrega una nueva pregunta y la escribe en el respectivo archivo de preguntas
  public void agregarPregunta(Pregunta p, String ruta){
    preguntas.add(p);
    Collections.sort(preguntas);
    Pregunta.escribirPreguntas(ruta,preguntas);
  }
  
  public void visualizarPreguntas(){
    for(Pregunta p: preguntas){
      System.out.println(p);
    }
  }

//Crea una nueva clase de paralelo que se añade automaticamente al grupo de archivos (Cuando se especifica la ruta a guardar)
  public void agregarParalelo(int numero, TerminoAcademico termino, String ruta){
    paralelos.add(new Paralelo(numero,Estudiante.listado(ruta),termino));
  }
  //Crea una nueva clase de paralelo pero creando un archivo vacío de lista de estudiantes
  public void agregarParalelo(int numero, TerminoAcademico termino){
    //CREAR CONTENIDO
  }

  public void aregarMateria(String ruta){
    //Agregar contenido de guardado de materia
  }
  
  /*
  public static void main(String[] args){

  }
  */    
}
