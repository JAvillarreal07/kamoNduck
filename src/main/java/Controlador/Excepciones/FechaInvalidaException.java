package Controlador.Excepciones;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class FechaInvalidaException extends Exception{
    public FechaInvalidaException(String mensaje){
        super(mensaje);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/Imagenes/error-logo.png"));
        alert.setContentText(mensaje);

        try {
            String nombreAudio = "src/main/resources/Sonidos/Quack_Sound_Effect.mp3";

            int numRandom = new Random().nextInt(100);
            if (numRandom <= 4){
                nombreAudio = "src/main/resources/Sonidos/Angry_Donald_Duck_Sound.mp3";
            }else if (numRandom > 4){
                nombreAudio = "src/main/resources/Sonidos/Quack_Sound_Effect.mp3";
            }

            FileInputStream direccion = null;
            direccion = new FileInputStream(new File(nombreAudio).getAbsolutePath());
            Player player;
            BufferedInputStream bis = new BufferedInputStream(direccion);
            player = new Player(bis);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        alert.showAndWait();
    }
}
