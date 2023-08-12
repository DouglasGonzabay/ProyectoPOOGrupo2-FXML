/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.io.*;
public class Estudiante{
  private String nombre;
  private int matricula;
  public Estudiante(String n, int m){
    nombre = n;
    matricula = m;
  }
  public String getNombre(){
    return nombre;
  }
  public int getMatricula(){
    return matricula;
  }
  public void setNombre(String nombre){
    this.nombre = nombre;
  }
  public void setMatricula(int matricula){
    this.matricula = matricula;
  }
  public String toString(){
    return nombre+"-"+matricula;
  }
  public static ArrayList<Estudiante> listado(String ruta){
    ArrayList<Estudiante> lista = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
      String lectura;
      while((lectura=br.readLine())!=null){
        String[] estudiante = lectura.split(",");
        Estudiante e = new Estudiante(estudiante[0],(Integer.parseInt(estudiante[1])));
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    return lista;
  }
  //Metodo para Crear listados y para añadir estudiantes
  public static void escribirListado(String ruta, ArrayList<Estudiante> estudiantes){
    try(BufferedWriter write = new BufferedWriter(new FileWriter(ruta))){
      for(Estudiante e: estudiantes){
        String alumno = e.nombre + ","+e.matricula;
        write.write(alumno);
        write.write("\n");
      }
    }
    catch(IOException e){
        e.printStackTrace();
    }
  }
  //Crear un archivo de Lista vacío
  public static void crearListado(String ruta){
    try(BufferedWriter write = new BufferedWriter(new FileWriter(ruta))){
      write.write("");
    }
    catch(IOException e){
        e.printStackTrace();
    }
  }
}
