package Controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * JavaFX App
 */
//Clase principal encargada de ejecutar la ventana principal.
public class Iniciar extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Vista/VentanaBienvenida.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Vista/Pruebas.fxml"));
        stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Allerta+Stencil");
        stage.setTitle("Empresa kamoNduck");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        String currentPath=VentanaBienvenidaController.class
                .getProtectionDomain()
                .getCodeSource().getLocation()
                .toURI().getPath()
                .replace('/', File.separator.charAt(0)).substring(1);
        if(args.length==0 && Runtime.getRuntime().maxMemory()/1024/1024<980) {
            Runtime.getRuntime().exec("java -Xmx1024m -jar "+currentPath+" restart");
            return;
        }
        launch();
    }
}
