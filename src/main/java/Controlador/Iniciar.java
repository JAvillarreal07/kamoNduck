package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
//Clase principal encargada de ejecutar la ventana principal.
public class Iniciar extends Application {
//Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/VentanaBienvenida.fxml"));
        stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Empresa kamoNduck");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
