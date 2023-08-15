package modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import modelo.*;
import java.util.ArrayList;
public class NewClass {
    public static ArrayList<String> presentarMaterias (String ruta){
        ArrayList<String> materias = new ArrayList<>() ;
        try(BufferedReader bff = new BufferedReader(new FileReader(ruta))){
            String linea = bff.readLine();
            while( (linea = bff.readLine()) != null ){
                String[] info = linea.split("/");
                String materia= info[0];
                String cod = info[1];
                String par;
                if(info.length==4){
                    String[] paralelos = info[3].split(",");
                    par = "Paralelos: ";
                    for(String p : paralelos){
                        par += p;
                    }
                }
                else{
                    par = "No hay Paralelos agregados";
                }
                String general = "Materia: " + materia +"\nCódigo: " + cod + "\n" + par;
                materias.add(general);
            }        
        
        }catch(FileNotFoundException err){
            System.out.println(err);
        }catch(IOException io){
            System.out.print(io);
        }
        return materias; 
    }
    //Lectura de Materias que entrega un ArrayList para su uso
    public static ArrayList<Materia> leerMaterias(String ruta){
        ArrayList<Materia> materias = new ArrayList<>();
        try(BufferedReader buff = new BufferedReader(new FileReader(ruta))){
            String linea = buff.readLine();
            while((linea = buff.readLine()) != null){
                String[] info = linea.split("/");
                //String materia = info[0];
                String cod = info[1];
                //String par;
                String rutaPreg = ".\\archivos\\" + cod + ".dat";
                Path path = Paths.get(rutaPreg);
                Materia materiaN;
                int nivel = Integer.parseInt(info[2]);
                //File preguntas = new File()
                //Pregunta si existen paralelos
                if(info.length==4){
                    String[] paralelos = info[3].split(",");
                    ArrayList<Paralelo> lista_par = new ArrayList<>();
                    for(String p: paralelos){
                        String newRuta = ".\\archivos\\" + cod + "-" + p + ".csv";
                        File archivo = new File(newRuta);
                        String[] num = p.split("-");
                        String number = num[0].charAt(1)+""+num[0].charAt(2);
                        int numero = Integer.parseInt(number);//Extrae numero de paralelo
                        Paralelo paralelo;
                        //Comprueba si existe un archivo de lista de estudiantes
                        if(!archivo.isFile()){
                            paralelo = new Paralelo(numero,new TerminoAcademico(num[1],num[2]));
                        }
                        else{
                            ArrayList<Estudiante> estudiantes = Estudiante.listado(newRuta);
                            paralelo = new Paralelo(numero,estudiantes,new TerminoAcademico(num[1],num[2]));
                        }
                        lista_par.add(paralelo);
                    }
                    //Comprueba si existe un archivo binario de preguntas
                    if(Files.exists(path)){
                        ArrayList<Pregunta> preguntas = Pregunta.leerPreguntas(rutaPreg);
                        materiaN = new Materia(info[0],info[1],nivel,lista_par,preguntas);
                    }
                    else{
                        materiaN = new Materia(info[0],info[1],nivel,lista_par);
                    }
                }
                else{
                    if(Files.exists(path)){
                        ArrayList<Pregunta> preguntas = Pregunta.leerPreguntas(rutaPreg);
                        materiaN = new Materia(info[0],info[1],preguntas,nivel);
                    }
                    else{
                        materiaN = new Materia(info[0],info[1],nivel);
                    }
                }
                materias.add(materiaN);
            }
        }catch(FileNotFoundException err){
            System.out.println(err);
        }catch(IOException io){
            System.out.print(io);
        }
        return materias;
    }

    public static void main(String[] arr){
        //Comprobación de métodos
        ArrayList<String> materias =NewClass.presentarMaterias(".\\archivos\\materias.txt");
        for(String m: materias){
            System.out.println(m);
        }
        ArrayList<Materia> materias2 = NewClass.leerMaterias(".\\archivos\\materias.txt");
        for(Materia m: materias2){
            System.out.println(m);
            for(Paralelo p: m.getParalelos()){
                if(p.getLista()==null){
                    System.out.println("Paralelo: "+p.getNumParalelo() + " Estudiantes: No ubicados");
                }
                else{
                    System.out.println("Paralelo: "+p.getNumParalelo() + " Estudiantes: " + p.getLista().size());
                }
                
            }
        }
   
    }
}
