/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

private ObservableList<Persona> personas;
private ObservableList<Persona> buscarPersonas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personas = FXCollections.observableArrayList();
        buscarPersonas = FXCollections.observableArrayList();
        this.tablaPersonas.setItems(personas);
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
            controlador.inicializarAtributos(personas);

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
                personas.add(nuevaPersona);
                if (nuevaPersona.getNombre().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase()) || 
                    nuevaPersona.getApellidos().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase()) ||
                    nuevaPersona.getNumeroID().contains(this.textFieldBuscar.getText())) {
                    this.buscarPersonas.add(nuevaPersona);
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
        } else {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Vista_Guardar.fxml"));

                Parent root = loader.load();

                Vista_GuardarController guardarController = loader.getController();
                guardarController.inicializarAtributos(personas, modificarPersona);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();

                Persona personaModificada = guardarController.getPersona();
                if (personaModificada != null) {
                    if (!personaModificada.getNombre().toLowerCase().contains(this.textFieldBuscar.getText().toLowerCase())) {
                        this.buscarPersonas.remove(personaModificada);
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
            this.personas.remove(personaSeleccionada);
            this.buscarPersonas.remove(personaSeleccionada);
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

        if (buscarPersonas.isEmpty()) {
            this.tablaPersonas.setItems(personas);
        } else {

            this.buscarPersonas.clear();

            for (Persona persona : this.personas) {
                if (persona.getNumeroID().contains(busqueda.toString()) || 
                    persona.getNombre().toLowerCase().contains(busqueda.toLowerCase()) || 
                    persona.getApellidos().toLowerCase().contains(busqueda.toLowerCase())) {
                    this.buscarPersonas.add(persona);
                }
            }
            this.tablaPersonas.setItems(buscarPersonas);

        }
    }
}
