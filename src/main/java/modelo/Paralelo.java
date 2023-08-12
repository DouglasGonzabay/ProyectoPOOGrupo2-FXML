/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Daniela Basilio
 */
public class Paralelo {
  private TerminoAcademico termino;
  private int numeroParalelo;
  private ArrayList<Estudiante> listae;
  
  public Paralelo(int n, ArrayList<Estudiante> l, TerminoAcademico t){
    this(n);
    listae = l;
    termino=t;
  }
  
  public Paralelo(int n){
    numeroParalelo = n;
    listae = new ArrayList<>();
  }

  

  //public TerminoAcademico

  public int getNumParalelo(){
    return numeroParalelo;
  }
  //AGREGADO RECIENTEMENTE
  public ArrayList<Estudiante> getLista(){
    return listae;
  }
  public void agregaEstudiante(Estudiante e){
    listae.add(e);
  }
  public String listadoEstudiantes(){
    ArrayList<String> listadoe = new ArrayList<>();
    for (Estudiante e : listae){
      String datoe = e.toString();
      listadoe.add(datoe);
    }
    String lista = String.join(",",listadoe);
    return lista;
   
  }
}
