/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Daniela Basilio
 */
public class TerminoAcademico implements Comparable<TerminoAcademico>{
  private String anio;
  private String numero;

  //Constructor
  public TerminoAcademico(String a, String num){
    anio = a;
    numero = num;
  }

  //metodos
    @Override
  public String toString(){
    return "PAO-"+numero+"-"+anio;
  }

  //Getters
  public String getAnio(){
    return anio;
  }
  public String getNumero(){
    return numero;
  }

  //Setters
  public void setAnio(String anio){
    this.anio = anio;
  }
  public void setNumero(String numero){
    this.numero = numero;
  }
  
  @Override
  public int compareTo(TerminoAcademico t){
      int a = Integer.parseInt(this.anio);
      int n = Integer.parseInt(this.numero);
      int r;
      if(a<Integer.parseInt(t.anio)){
          r = -1;
      }
      else if(a>Integer.parseInt(t.anio)){
          r = 1;
      }
      else{
          if(n<Integer.parseInt(t.numero)){
          r = -1;
      }
      else if(n>Integer.parseInt(t.numero)){
          r = 1;
      }
      else{
          r=0;
      }
      }
    return r;  
  }
  //Lectura y uso de ArrayList de terminos de consulta y configuración de término
  public static ArrayList<TerminoAcademico> cargarTerminos(String ruta){
    ArrayList<TerminoAcademico> terminos = new ArrayList<>();
    try(BufferedReader buff = new BufferedReader(new FileReader(ruta))){
      String leer;
      buff.readLine();
      while((leer=buff.readLine())!=null){
        String[] lectura = leer.split("-");
        TerminoAcademico ter = new TerminoAcademico(lectura[0],lectura[1]);
        terminos.add(ter);
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    Collections.sort(terminos);
    return terminos;
  }
  //Para validar clases
  public static ArrayList<String> cargarTerminosString(String ruta){
    ArrayList<String> terminos = new ArrayList<>();
    try(BufferedReader buff = new BufferedReader(new FileReader(ruta))){
      String leer;
      buff.readLine();
      while((leer=buff.readLine())!=null){
        String[] lectura = leer.split("-");
        TerminoAcademico ter = new TerminoAcademico(lectura[0],lectura[1]);
        terminos.add(ter.toString());
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    return terminos;
  }
  //Añade un nuevo termino académico al archivo de terminos academicos establecidos
  //NOTA: clase basica para añadior termino, ANTES debe validar que el termino no se encuentre repetido y que el año no sea menor al actual
  public static void anadirTermino(String ruta, TerminoAcademico term){
    try(BufferedWriter write = new BufferedWriter(new FileWriter(ruta,true))){
      write.write(term.anio + "-" + term.numero);
      write.write("\n");
    }
    catch(IOException e){
        e.printStackTrace();
    }
  }
  //Reescribe un nuevo archivo eliminando el contenido actual y utilizando uno nuevo (SOLO PARA ESCRITURA DE ARCHIVO; NO MODIFICACION DE CLASES)
  //NOTA: utilizar cuando se actualice la lista de terminos académicos con alguna modificación
  public static void actualizarTermino(String ruta, ArrayList<TerminoAcademico> t){
    //FileWriter no recibe un true por lo que elimina el contenido y lo vuelve a agregar
    ArrayList<String> terminosString = new ArrayList<>();
    Collections.sort(t);
    for(TerminoAcademico ter: t){
        terminosString.add(ter.toString());
    }
    Set<String> hasSet = new HashSet<>(terminosString);
    terminosString.clear();
    terminosString.addAll(hasSet);
    try(BufferedWriter write = new BufferedWriter(new FileWriter(ruta))){
        write.write("ANIO-NUMERO");
        write.write("\n");
        for(String termino: terminosString){
        String[] term = termino.split("-");
        write.write(term[2] + "-" + term[1]);
        write.write("\n");
      }

    }
    catch(IOException e){
        e.printStackTrace();
    }
    
}
 public static TerminoAcademico busqueda(ArrayList<TerminoAcademico> t,TerminoAcademico ter){
     for(int i =0; i<t.size();i++){
         String tr = t.get(i).toString();
         String tr1 = ter.toString();
         if(tr.equals(tr1)){
          TerminoAcademico terR = t.get(i);
          i = t.size()+1;
         return terR;
         }        
     }
     return null;
 }
 
}
