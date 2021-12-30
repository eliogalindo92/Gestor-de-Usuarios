/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author Elio Galindo
 */
public class Vista_GuardarController implements Initializable {

    /**
     * Initializes the controller class.
     */
@FXML
private TextField textFieldID;
@FXML
private TextField textFieldNombre;
@FXML
private TextField textFieldApellidos;
@FXML
private DatePicker datePickerFecha;
@FXML
private Button botonGuardar;
@FXML
private Button botonCancelar;

private Persona persona;
private ObservableList<Persona>personas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarAtributos(ObservableList<Persona> personas) {
        this.personas = personas;
    }

    public void inicializarAtributos(ObservableList<Persona> personas, Persona persona) {
        this.personas = personas;
        this.persona = persona;
        this.textFieldID.setText(this.persona.getNumeroID());
        this.textFieldNombre.setText(this.persona.getNombre());
        this.textFieldApellidos.setText(this.persona.getApellidos());
        this.datePickerFecha.setValue(this.persona.getFechaNacimiento());
    }

    public Persona getPersona() {
        return persona;
    }

@FXML
private void guardar(ActionEvent guardar){

String numeroID = textFieldID.getText();
String nombre = textFieldNombre.getText();
String apellidos = textFieldApellidos.getText();
LocalDate fechaNacimiento = datePickerFecha.getValue();

Persona nuevaPersona;
nuevaPersona = new Persona(numeroID, nombre, apellidos, fechaNacimiento);

if(!personas.contains(nuevaPersona)){

       //Modificando
         if(this.persona != null){
         this.persona.setNumeroID(numeroID);
         this.persona.setNombre(nombre);
         this.persona.setApellidos(apellidos);
         this.persona.setFechaNacimiento(fechaNacimiento);
         
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();

         }
         
         //Guardando
   //      else{
               this.persona = nuevaPersona;
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha guardado correctamente");
                alert.showAndWait();
    //           }
          //Cerrando ventana
         Stage stage = (Stage)this.botonGuardar.getScene().getWindow();
         stage.close();

}
else{
  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La persona ya está registrada");
            alert.showAndWait();
}
}
@FXML
private void cancelar(ActionEvent cancelar){
        this.persona = null;

        // Cerrar la ventana
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
}
}
