/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.sun.javafx.scene.layout.region.Margins;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utiles.Convert;

/**
 *
 * @author Elio Galindo
 */
public class Gestor {
     
     private ObservableList<Persona>personas;
     private ObservableList<Persona> buscarPersonas; 
     public static Gestor gestorControlador;
     
     public Gestor() {
               personas = FXCollections.observableArrayList();
               buscarPersonas = FXCollections.observableArrayList();
     }

     public static Gestor getGestor(){

     if(gestorControlador == null){
        gestorControlador = new Gestor();
     }
     return gestorControlador;
     }

    public ObservableList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ObservableList<Persona> personas) {
        this.personas = personas;
    }

    public ObservableList<Persona> getBuscarPersonas() {
        return buscarPersonas;
    }

    public void setBuscarPersonas(ObservableList<Persona> buscarPersonas) {
        this.buscarPersonas = buscarPersonas;
    }
     
    

    public void guardarFichero() throws IOException{
    File ficheroTemporal = new File("personas.dat");
    ficheroTemporal.delete();
    RandomAccessFile fichero = new RandomAccessFile(ficheroTemporal, "rw");
    Iterator<Persona>iterador = personas.iterator();
    while(iterador.hasNext()){
            Persona persona = iterador.next();
            byte[] arrayByte = Convert.toBytes(persona);
            fichero.writeInt(arrayByte.length);
            fichero.write(arrayByte);
        }
        fichero.close();

        }
      
        public void cargarFichero() throws IOException, ClassNotFoundException {
	File ficheroTemporal = new File("personas.dat");
	if (ficheroTemporal.exists()) {
                      RandomAccessFile fichero = new RandomAccessFile(ficheroTemporal, "rw");			
	    while (fichero.getFilePointer() < fichero.length()) {
	            int size = fichero.readInt();
	            byte[]  arrayByte = new byte[size];
	            fichero.read(arrayByte);
                              Persona persona = (Persona) Convert.toObject(arrayByte);
	            personas.add(persona);
                              buscarPersonas.add(persona);
	    }
	fichero.close();
	}

         }


}