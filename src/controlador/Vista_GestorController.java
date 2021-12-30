/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;
import modelo.Gestor;

/**
 * FXML Controller class
 *
 * @author Elio Galindo
 */
public class Vista_GestorController implements Initializable {
    /**
     * Initializes the controller class.
     */

@FXML
private Button botonAgregar;
@FXML
private Button botonModificar;
@FXML
private Button botonEliminar;
@FXML
private TextField textFieldBuscar;
@FXML
 private TableView<Persona> tablaPersonas;
@FXML
private TableColumn columnaID;
@FXML
private TableColumn columnaNombre;
@FXML
private TableColumn columnaApellidos;
@FXML
private TableColumn columnaFecha;

//private ObservableList<Persona> personas;
//private ObservableList<Persona> buscarPersonas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try { 
         Gestor.getGestor().cargarFichero();
       }
        catch(IOException e){
        e.printStackTrace();
        }       
        catch (ClassNotFoundException ex) {
        Logger.getLogger(Vista_GestorController.class.getName()).log(Level.SEVERE, null, ex);
    }
        this.tablaPersonas.setItems(Gestor.getGestor().getPersonas());
        this.columnaID.setCellValueFactory(new PropertyValueFactory("numeroID"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
    }
        
    @FXML
    private void agregar(ActionEvent agregar){
             try {

            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Vista_Guardar.fxml"));

            // Cargo la ventana
            Parent root = loader.load();

            // Cojo el guardarController
            Vista_GuardarController controlador = loader.getController();
            controlador.inicializarAtributos(Gestor.getGestor().getPersonas());

            // Creo el Scene
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

            // cojo la persona devuelta
            Persona nuevaPersona = controlador.getPersona();
            if (nuevaPersona != null) {
                Gestor.getGestor().getPersonas().add(nuevaPersona);
                Gestor.getGestor().guardarFichero();
                if (nuevaPersona.getNombre().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase()) || 
                    nuevaPersona.getApellidos().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase()) ||
                    nuevaPersona.getNumeroID().contains(this.textFieldBuscar.getText())) {
                    Gestor.getGestor().getBuscarPersonas().add(nuevaPersona);
                }
                this.tablaPersonas.refresh();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
          }

    

    }
@FXML
private void modificar(ActionEvent modificar){
      
        Persona modificarPersona = this.tablaPersonas.getSelectionModel().getSelectedItem();

        if (modificarPersona == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } 
        else {

            try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Vista_Guardar.fxml"));

                   Parent root = loader.load();
                   Vista_GuardarController guardarController = loader.getController();
                   guardarController.inicializarAtributos(Gestor.getGestor().getPersonas(), modificarPersona);

                   Scene scene = new Scene(root);
                   Stage stage = new Stage();
                   stage.initModality(Modality.APPLICATION_MODAL);
                   stage.setScene(scene);
                   stage.setResizable(false);
                   stage.showAndWait();

                   Persona personaModificada = guardarController.getPersona();
                   if(personaModificada != null) {
                       Gestor.getGestor().guardarFichero();
                       if (!personaModificada.getNombre().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase())) {
                            Gestor.getGestor().getBuscarPersonas().remove(personaModificada);
                    }
                    this.tablaPersonas.refresh();
                }

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
     }
}
    @FXML
     private void eliminar(ActionEvent eliminar){
        Persona personaSeleccionada = this.tablaPersonas.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            // Elimino la persona
            Gestor.getGestor().getPersonas().remove(personaSeleccionada);
            try{
               Gestor.getGestor().guardarFichero();
               }
               catch(IOException e){
              e.printStackTrace();
               }       
            Gestor.getGestor().getBuscarPersonas().remove(personaSeleccionada);
            this.tablaPersonas.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona eliminada");
            alert.showAndWait();

        }
     }
    @FXML
    private void buscar(KeyEvent buscar){
            String busqueda = this.textFieldBuscar.getText();

        if (Gestor.getGestor().getBuscarPersonas().isEmpty()) {
            this.tablaPersonas.setItems(Gestor.getGestor().getPersonas());
        } else {

            Gestor.getGestor().getBuscarPersonas().clear();

            for (Persona persona : Gestor.getGestor().getPersonas()) {
                if (persona.getNumeroID().contains(busqueda) || 
                    persona.getNombre().toLowerCase().contains(busqueda.toLowerCase()) || 
                    persona.getApellidos().toLowerCase().contains(busqueda.toLowerCase())) {
                    Gestor.getGestor().getBuscarPersonas().add(persona);
                }
            }
            this.tablaPersonas.setItems(Gestor.getGestor().getBuscarPersonas());

        }
    }
}
