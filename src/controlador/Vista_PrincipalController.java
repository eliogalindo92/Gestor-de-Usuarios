/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elio Galindo
 */
public class Vista_PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
@FXML
private MenuItem menuItemGestionar;
@FXML
private MenuItem menuItemSalir;


@FXML
public void gestionar(ActionEvent gestionar){

try{
   // Cargo la vista
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Vista_Gestor.fxml"));

                // Cargo la ventana
                Parent root = loader.load();
                // Creo el Scene
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait(); 

}
catch (IOException e) {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setHeaderText(null);
       alert.setTitle("Error");
       alert.setContentText(e.getMessage());
       alert.showAndWait();
            }

}

@FXML
private void salir(ActionEvent salir){
System.exit(0);
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
