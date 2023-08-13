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
        try(BufferedReader bff = new BufferedReader(new FileReader(ruta))){
            String linea = bff.readLine();
            ArrayList<String> materias = new ArrayList<>() ;
            while( (linea = bff.readLine()) != null ){
                String[] info = linea.split("/");
                String materia= info[0];
                String cod = info[1];
                String[] paralelos = info[2].split(",");
                for(String p : paralelos){
                
                
                
                }
                
                
            
            
            
            
            
            }        
        
        }catch(FileNotFoundException err){
            err.printStackTrace();
        }catch(IOException io){
            System.out.print(io);
        }
        return null; 
    }

    public static void main(String[] arr){
        
        
        
        
        
        
    }
}
