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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] arr){
        ArrayList<String> materias =NewClass.presentarMaterias(".\\archivos\\materias.txt");
        for(String m: materias){
            System.out.println(m);
        }
   
    }
}
