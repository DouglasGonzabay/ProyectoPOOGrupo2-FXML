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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import modelo.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    //Método creado para reemplazar termino académico del archivo materias.txt
    public static void reemplazarTermino(String ruta, String año, String numero, int cambio){
        ArrayList<String> reescritura = new ArrayList<>();
        try(BufferedReader d = new BufferedReader(new FileReader(ruta))){
            String linea = d.readLine();
            reescritura.add(linea);
            while((linea = d.readLine())!=null){
                String[] es = linea.split("/");
                if(es.length==3){
                    reescritura.add(linea);
                }
                else{
                    String[] par = es[3].split(",");
                    String newesc = es[0]+"/"+es[1]+"/"+es[2]+"/";
                    for(String p: par){
                        String[] var = p.split("-");
                        if((año.equals(var[1]))&&(numero.equals(var[2]))){
                            if(Arrays.asList(par).indexOf(p)<(par.length -1 )){
                                if(cambio==1 || cambio==2){
                                    newesc = newesc + var[0]+"-"+var[1]+"-"+cambio+",";
                                }
                                else{
                                    newesc = newesc + var[0]+"-"+cambio+"-"+var[2]+",";
                                }
                            }
                            else{
                                if(cambio==1 || cambio==2){
                                    newesc = newesc + var[0]+"-"+var[1]+"-"+cambio;
                                }
                                else{
                                    newesc = newesc + var[0]+"-"+cambio+"-"+var[2];
                                }
                            }
                        }
                        else{
                            if(Arrays.asList(par).indexOf(p)<(par.length -1 )){
                                newesc = newesc + var[0]+"-"+var[1]+"-"+var[2]+",";
                            }
                            else{
                                newesc = newesc + var[0]+"-"+var[1]+"-"+var[2];
                            }
                        } 
                    }
                    reescritura.add(newesc);
                }
            }
        }catch(IOException e){
            System.out.print(e);
        }
        try(BufferedWriter w = new BufferedWriter(new FileWriter(ruta))){
            for(String r: reescritura){
                w.write(r);
                w.write("\n");
            }
        }catch(IOException io){
            System.out.println(io);
        }
        
    }
    //Método para reemplaza el término en el nombre del paralelo que lo contenga
    public static void reemplazarEnParalelo(String año, String numero, int cambio){
        File carpeta = new File(".\\archivos");
        if(carpeta.isDirectory()){
            for(String e: carpeta.list()){
                if(e.contains(".csv")){
                String[] s = e.split("-");
                if(s.length == 4){
                    if(año.equals(s[2])&&(numero+".csv").equals(s[3])){
                        File oldname = new File(".\\archivos\\"+e);
                        File newName;
                        if(cambio==1 || cambio==2){
                            newName = new File(".\\archivos\\"+s[0]+"-"+s[1]+"-"+s[2]+"-"+cambio+".csv");
                            oldname.renameTo(newName);
                        }else{
                            newName = new File(".\\archivos\\"+s[0]+"-"+s[1]+"-"+cambio+"-"+s[3]);
                            oldname.renameTo(newName);
                        }
                    }
                }
                else{
                    System.out.println("No existen archivos para actualizar");
                }
                }else{
                    System.out.println("No existen archivos para actualizar");
                }
            }
            
        }
    }
    //Guarda en un documento un único término configurado
    public static void configuracionJuego(TerminoAcademico t){
        try(BufferedWriter j = new BufferedWriter(new FileWriter(".\\archivos\\configuracionTermino.txt"))){
            j.write(t.getAnio()+"-"+t.getNumero());
        }catch(IOException io){
            System.out.println(io);
        }
    }
    public static TerminoAcademico terminoConfigurado(){
        TerminoAcademico t = new TerminoAcademico("","");
        try(BufferedReader br = new BufferedReader(new FileReader(".\\archivos\\configuracionTermino.txt"))){
            String read;
            while((read=br.readLine())!=null){
                String[] ter = read.split("-");
                t = new TerminoAcademico(ter[0],ter[1]);
                //t.setAnio(ter[0]);
                //t.setAnio(ter[1]);
            }
        }catch(FileNotFoundException exo){
            t = new TerminoAcademico("","");
        }catch(IOException ioe){
            t = new TerminoAcademico("","");
        }
        return t;
    }
    //Clase para editar cantidad de niveles o nombre de la materia
    public static void editarMateria(String ruta,String codigo, String nuevo){
        ArrayList<String> materiasRegistradas = new ArrayList<>();
        try(BufferedReader bbb = new BufferedReader(new FileReader(ruta))){
            String read = bbb.readLine();
            materiasRegistradas.add(read);
            while((read=bbb.readLine())!=null){
                String[] cc = read.split("/");
                if(cc[1].equals(codigo)){
                    String agregarMod;
                    //El valor nuevo es un numero y la materia contiene paralelos
                   if(isNumeric(nuevo)&&(cc.length==4)){
                        agregarMod = cc[0]+"/"+cc[1]+"/"+nuevo+"/"+cc[3];
                   }
                   //El valor nuevo no es un numero y la materia no contiene paralelos
                   else if((!isNumeric(nuevo))&&(cc.length==4)){
                       agregarMod = nuevo + "/" + cc[1] + "/" + cc[2]+"/"+cc[3];
                   }
                   //El valor nuevo es un numero y la materia no contiene paralelos
                   else if(isNumeric(nuevo)&&(cc.length!=4)){
                       agregarMod = cc[0]+"/"+cc[1]+"/"+nuevo;
                   }
                   else{
                       agregarMod = nuevo + "/" + cc[1] + "/" + cc[2];
                   }
                   materiasRegistradas.add(agregarMod);
                }else{
                    materiasRegistradas.add(read);
                }
            }
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        try(BufferedWriter wr = new BufferedWriter(new FileWriter(ruta))){
            for(String a: materiasRegistradas){
                wr.write(a);
                wr.write("\n");
            }
        }catch(IOException oi){
            System.out.println(oi);
        }
    }
    //Comprobar si es un numero
     public static boolean isNumeric(String s)
    {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
     //Método para escribir en materia.txt
     public static void agregarMateria(String ruta, String nombre, String codigo, String nivel){
         try(BufferedWriter wb = new BufferedWriter(new FileWriter(ruta,true))){
             wb.write(nombre+"/"+codigo+"/"+nivel);
             wb.write("\n");
         }catch(IOException on){
             System.out.println(on);
         }
     }
     //Agregar paralelo con las caracteristicas seleccionadas
     public static void agregarParalelo(String ruta, String nombreMateria, int numParalelo, String anio, String termino){
         ArrayList<String> contenidoArchivo = new ArrayList<>();
         try(BufferedReader wb = new BufferedReader(new FileReader(ruta))){
             String leer = wb.readLine();
             contenidoArchivo.add(leer);
             while((leer=wb.readLine())!=null){
                 String[] el = leer.split("/");
                 if(el[0].equals(nombreMateria)){
                     String nuevo;
                     if(el.length == 3){
                         if(numParalelo<10){
                             nuevo = el[0]+"/"+el[1]+"/"+el[2]+"/"+"P0"+ numParalelo+"-"+anio+"-"+termino;
                         }else{
                             nuevo = el[0]+"/"+el[1]+"/"+el[2]+"/"+"P"+ numParalelo;
                         }
                     }else{
                         if(numParalelo<10){
                             nuevo = el[0]+"/"+el[1]+"/"+el[2]+"/"+el[3]+",P0"+ numParalelo+"-"+anio+"-"+termino;
                         }else{
                             nuevo = el[0]+"/"+el[1]+"/"+el[2]+"/"+el[3]+",P"+ numParalelo;
                         }
                     }
                     contenidoArchivo.add(nuevo);
                 }else{
                     contenidoArchivo.add(leer);
                 }
             }
         }catch(IOException eio){
             System.out.println(eio);
         }
         try(BufferedWriter bufff = new BufferedWriter(new FileWriter(ruta))){
             for(String n: contenidoArchivo){
                 bufff.write(n);
                 bufff.write("\n");
             }
         }catch(IOException eoi){
             System.out.println(eoi);
         }
         
     }
     //Eliminar un paralelo de la materia 
     public static void eliminarParalelo(String ruta, String materia, Paralelo p){
         ArrayList<String> lectura = new ArrayList<>();
         try(BufferedReader r = new BufferedReader(new FileReader(ruta))){
             String l = r.readLine();
             lectura.add(l);
             while((l=r.readLine())!=null){
                 String[] a = l.split("/");
                 if(materia.equals(a[0])){
                     String leido = a[0]+"/"+a[1]+"/"+a[2];
                     
                     if(a.length==4){
                         ArrayList<String> s = new ArrayList<>();
                         String[] pa = a[3].split(",");
                         for(String e: pa){
                             String[] at = e.split("-");
                             
                             if(!(at[0]+"-"+"PAO"+"-"+at[2]+"-"+at[1]).equals(p.toString())){
                                 s.add(e);
                             }
                         }
                         if(!s.isEmpty()){
                             leido = leido +"/"+String.join(",",s);
                         }
                     }
                     lectura.add(leido);
                 }else{
                     lectura.add(l);
                 }
             }
         }catch(IOException d){
             System.out.println(d);
         }
         try(BufferedWriter bufff = new BufferedWriter(new FileWriter(ruta))){
             for(String n: lectura){
                 bufff.write(n);
                 bufff.write("\n");
             }
         }catch(IOException eoi){
             System.out.println(eoi);
         }
     }

    public static void main(String[] arr){
        //Comprobación de métodos
        /*
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
        //Método de actualización de datos en nombre de archivos (comprobación de funcionamiento)
        System.out.println("\n\n");
        File carpeta = new File(".\\archivos");
        if(carpeta.isDirectory()){
            for(String e: carpeta.list()){
                System.out.println(e);
            }
        }*/
        //NewClass.reemplazarTermino(".\\archivos\\materias.txt", "2023", "2", 1);
        //NewClass.reemplazarEnParalelo("2024", "2", 2023);
        /*
        System.out.println("\n\n");
        File carpeta = new File(".\\archivos");
        if(carpeta.isDirectory()){
            for(String e: carpeta.list()){
                if(e.contains(".csv")){
                    String[] par = e.split("-");
                    System.out.println(par[3]);

                }
            }
        }*/
       //ArrayList<Materia> materias = NewClass.leerMaterias(".\\archivos\\materias.txt");
       eliminarParalelo(".\\archivos\\materias.txt", "FP", new Paralelo(3,new TerminoAcademico("2025","1")));
       
    }
}
