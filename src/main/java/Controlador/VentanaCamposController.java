package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class VentanaCamposController {
    @FXML
        private AnchorPane anchorpane;

        private String pathdelimage;
        public ImageView imagen;
    public void HandleButtonAction(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        FileChooser.ExtensionFilter extFilterJPG = new
                FileChooser.ExtensionFilter("Archivos JPG", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new
                FileChooser.ExtensionFilter("Archivos PNG", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        if (fileChooser!=null){
            File file = fileChooser.showOpenDialog(null);
            imagen.setImage(new Image(file.toURI().toString()));
            String urlDeLaImagen = file.toURI().toString();
        }


    }
}
