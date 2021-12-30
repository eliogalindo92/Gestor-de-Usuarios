/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
     
     



}