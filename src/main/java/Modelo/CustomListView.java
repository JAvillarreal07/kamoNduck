package Modelo;

import Controlador.IOBaseDatos;
import Controlador.VentanaPrincipalController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;

public class CustomListView extends HBox {
    private ImageView ImagenView = new ImageView();
    private Label NombreCompleto = new Label();
    private String Nombre, Apellidos;

    public CustomListView(Image imagen, String nombre, String apellidos, String cargo) {
        super();

        this.ImagenView.setImage(imagen);
        this.NombreCompleto.setText(nombre + " " + apellidos + "\n  Cargo: " + cargo);
        this.Nombre = nombre;
        this.Apellidos = apellidos;


        this.NombreCompleto.setMaxWidth(Double.MAX_VALUE);
        this.NombreCompleto.setMaxHeight(Double.MAX_VALUE);


        this.ImagenView.setPreserveRatio(true);
        this.ImagenView.setFitHeight(60.0);

        HBox.setHgrow(NombreCompleto, Priority.ALWAYS);
        this.getChildren().addAll(ImagenView, NombreCompleto);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getNombreCompleto() {
        return Nombre + " " + Apellidos;
    }

    public ImageView getImagenView() {
        return ImagenView;
    }

    public Label getLabel() {
        return NombreCompleto;
    }

}
