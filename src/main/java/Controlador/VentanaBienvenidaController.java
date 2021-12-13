package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    }

    public void abrirVentana() throws IOException {



        //Abre la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaPrincipal.fxml"));
        Parent root = loader.load();
        VentanaPrincipalController controlador = loader.getController();

        if (botonAlmacen.isFocused()){
            controlador.moduloElegido("Almacén");
            controlador.cambiaModulo();

        }else  if (botonGestion.isFocused()){
            controlador.moduloElegido("Facturación");
            controlador.cambiaModulo();

        }else  if (botonEmpleados.isFocused()){
            controlador.moduloElegido("Empleados");
            controlador.cambiaModulo();

        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
        stage.setTitle("Empresa kamoNduck");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controlador.volverABienvenida();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Stage myStage = (Stage) this.botonAlmacen.getScene().getWindow();
        myStage.close();
    }

}
