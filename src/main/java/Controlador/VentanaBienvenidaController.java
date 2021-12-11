package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author josea
 */
public class VentanaBienvenidaController implements Initializable {

    VentanaPrincipalController vpc = new VentanaPrincipalController();


    public JFXButton botonAlmacen, botonGestion, botonEmpleados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //botonAlmacen.setStyle("-fx-background-color: purple");

    }

    public void pulsado() throws IOException {

        //Abre la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaPrincipal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
        stage.setTitle("Empresa kamoNduck");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
