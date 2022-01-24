package Controlador;

import Modelo.Producto;
import Modelo.Proveedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class VentanaCamposController {

    // Elementos Patos
    public JFXTextField textNomPato, textRazaPato, textEdadPato, textNCartillaPato;
    public JFXTextArea textDescPato;

    // Elementos Productos
    public JFXTextField textNomProd, textTipoProd, textCantProd, textProvProd;
    public JFXTextArea textObseProd;

    // Elementos Proveedores
    public JFXTextField textNomProv, textDireProv, textTelProv, textPaisProv;


    public JFXButton botonSubirImagen, botonEnviar, botonCancelar;

    IOBaseDatos IO = new IOBaseDatos();

    public String pathImagen, nombreNuevoImagen, carpeta;
    public int IDPrd, IDPrv;
    public ImageView imagenActual;
    public Image imagenNueva;



    public void iniciarCampos(Producto pd) throws ParseException, SQLException {
        textNomProd.setText(pd.getNombre_Producto());
        textTipoProd.setText(pd.getTipo_Producto());
        textCantProd.setText(String.valueOf(pd.getCantidad()));
        textProvProd.setText(pd.getProveedor());
        textObseProd.setText(pd.getObservaciones());
        try {
            imagenActual.setImage(new Image("/ImgProductos/" + pd.getNombre_Producto().replace(" ", "_") + ".png"));
        } catch (IllegalArgumentException e) {
        }

        ResultSet pdID = IO.introduceRegistros("SELECT IDProducto FROM PRODUCTOS WHERE " +
                "Nombre_Producto = '" + textNomProd.getText() + "' AND " +
                "Tipo_Producto = '" + textTipoProd.getText() + "' AND " +
                "Cantidad = " + textCantProd.getText() + " AND " +
                "Observaciones = '" + textObseProd.getText() + "'");

        pdID.next();

        IDPrd = pdID.getInt("IDProducto");
    }

    public void iniciarCampos(Proveedor pv) throws ParseException, SQLException {
        textNomProv.setText(pv.getNombre_Proveedor());
        textDireProv.setText(pv.getDireccion());
        textTelProv.setText(pv.getTelefono_Proveedor());
        textPaisProv.setText(pv.getPais());
        try {
            imagenActual.setImage(new Image("/ImgProveedores/" + pv.getNombre_Proveedor().replace(" ", "_") + ".png"));
        } catch (IllegalArgumentException e) {
        }

        ResultSet pvID = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                "Nombre_Proveedor = '" + textNomProv.getText() + "' AND " +
                "Direccion = '" + textDireProv.getText() + "' AND " +
                "Telefono_Proveedor = '" + textTelProv.getText() + "' AND " +
                "Pais = '" + textPaisProv.getText() + "'");

        pvID.next();

        IDPrv = pvID.getInt("IDProveedor");
    }

    public void iniciaFieldProv(ArrayList<String> nombresProvFiltros) {

        TextFields.bindAutoCompletion(textProvProd, nombresProvFiltros);
        nombresProvFiltros.clear();
    }

    public void saveToFile() {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        if (stage.getTitle().contains("Producto")){
            nombreNuevoImagen = textNomProd.getText().replace(" ", "_");
            carpeta = "ImgProductos";

        }else if (stage.getTitle().contains("Proveedor")){
            nombreNuevoImagen = textNomProv.getText().replace(" ", "_");
            carpeta = "ImgProveedores";

        }

        try {
            File outputFile = new File("src/main/resources/" + carpeta + "/" + nombreNuevoImagen + ".png");

            BufferedImage bImage = SwingFXUtils.fromFXImage(imagenNueva, null);
            ImageIO.write(bImage, "png", outputFile);
        } catch (NullPointerException a){} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accionBotones() throws SQLException {
        if (botonSubirImagen.isFocused()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir archivo");
            FileChooser.ExtensionFilter extFilterJPG = new
                    FileChooser.ExtensionFilter("Archivos JPG", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new
                    FileChooser.ExtensionFilter("Archivos PNG", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            File file = fileChooser.showOpenDialog(null);

            pathImagen = file.toURI().toString();
            imagenNueva = new Image(pathImagen);
            imagenActual.setImage(imagenNueva);

        } else if (botonEnviar.isFocused()) {
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            switch (stage.getTitle()) {
                case "Añadir Producto":
                    ResultSet pvID1 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                            "Nombre_Proveedor = '" + textProvProd.getText() + "'");

                    pvID1.next();
                    Integer IDBuscado1 = pvID1.getInt("IDProveedor");

                    IO.actualizaRegistros("INSERT INTO PRODUCTOS VALUES ("
                            + "null" + ", '"
                            + textNomProd.getText() + "', '"
                            + textTipoProd.getText() + "', "
                            + textCantProd.getText() + ", "
                            + 100 + ", "
                            + 50 + ", '"
                            + textObseProd.getText() + "', "
                            + IDBuscado1 + ")");
                    break;

                case "Modificar Producto":
                    ResultSet pvID2 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                            "Nombre_Proveedor = '" + textProvProd.getText() + "'");

                    pvID2.next();
                    Integer IDBuscado2 = pvID2.getInt("IDProveedor");

                    IO.actualizaRegistros("UPDATE PRODUCTOS set " +
                            "Nombre_Producto = '" + textNomProd.getText() + "', " +
                            "Tipo_Producto = '" + textTipoProd.getText() + "', " +
                            "Cantidad = " + textCantProd.getText() + ", " +
                            "Minimo = " + 100 + ", " +
                            "Precio = " + 50 + ", " +
                            "Observaciones = '" + textObseProd.getText() + "', " +
                            "IDProveedor = " + IDBuscado2 +
                            " WHERE IDProducto = " + IDPrd);
                    break;

                case "Añadir Proveedor":
                    IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                            + "null" + ", '"
                            + textNomProv.getText() + "', '"
                            + textDireProv.getText() + "', '"
                            + textTelProv.getText() + "', '"
                            + textPaisProv.getText() + "')");
                    break;

                case "Modificar Proveedor":
                    IO.actualizaRegistros("UPDATE PROVEEDOR set " +
                            "Nombre_Proveedor = '" + textNomProv.getText() + "', " +
                            "Direccion = '" + textDireProv.getText() + "', " +
                            "Telefono_Proveedor = '" + textTelProv.getText() + "', " +
                            "Pais = '" + textPaisProv.getText() + "', " +
                            "WHERE IDProveedor = " + IDPrv);
                    break;
            }

            saveToFile();
            stage.close();

        } else if (botonCancelar.isFocused()) {
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();

        }
    }
}
