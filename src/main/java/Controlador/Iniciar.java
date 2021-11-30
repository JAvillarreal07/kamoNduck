package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

/**
 * JavaFX App
 */
//Clase principal encargada de ejecutar la ventana principal.
public class Iniciar extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Vista.VentanaPrincipal.fxml"));
        //stage.getIcons().add(new Image("libro-logo.png"));
        Scene scene = new Scene(root);
        stage.setTitle("kamoNduck");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
