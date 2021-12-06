package Controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * @author josea
 */
public class VentanaPrincipalController implements Initializable {

    private String botonPulsado;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public String getBotonPulsado() {
        return botonPulsado;
    }

    public void setBotonPulsado(String botonPulsado) {
        this.botonPulsado = botonPulsado;
    }

    public void botonPulsado(){
        System.out.println(getBotonPulsado());
    }
}
