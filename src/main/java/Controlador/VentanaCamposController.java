package Controlador;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaCamposController {
    @FXML
        private AnchorPane anchorpane;

        public String pathdelimage;
        public ImageView imagen;
        public Image image;
        public String nombrenuevo;
    public void HandleButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo");
        FileChooser.ExtensionFilter extFilterJPG = new
                FileChooser.ExtensionFilter("Archivos JPG", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new
                FileChooser.ExtensionFilter("Archivos PNG", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            File file = fileChooser.showOpenDialog(null);
            imagen.setImage(new Image(file.toURI().toString()));
            //por si hace falta tener el path aqui va
            pathdelimage = file.toURI().toString();

            image = new Image(pathdelimage);
    }
    public void saveToFile() {
        //nombrenuevo = "francisquita";
        File outputFile = new File("C:\\Users\\User-01\\Documents\\MTC\\interfaces\\kamoNduck\\src\\main\\resources\\FotosEmpleados\\"+nombrenuevo+".png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
