package Controlador;

import Controlador.Excepciones.CamposVaciosException;
import Controlador.Excepciones.MuchoTextoException;
import Controlador.Excepciones.NoNumericoException;
import Controlador.Excepciones.YaExisteException;
import Modelo.Producto;
import Modelo.Proveedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
            imagenActual.setImage(new Image(new File("ImgProductos/" + pd.getNombre_Producto().replace(" ", "_") + ".png").toURI().toString()));
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
            imagenActual.setImage(new Image(new File("ImgProveedores/" + pv.getNombre_Proveedor().replace(" ", "_") + ".png").toURI().toString()));
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
        if (stage.getTitle().contains("Producto")) {
            nombreNuevoImagen = textNomProd.getText().replace(" ", "_");
            carpeta = "ImgProductos";

        } else if (stage.getTitle().contains("Proveedor")) {
            nombreNuevoImagen = textNomProv.getText().replace(" ", "_");
            carpeta = "ImgProveedores";
        }

        try {
            File outputFile = new File(carpeta + "/" + nombreNuevoImagen + ".png");

            BufferedImage bImage = SwingFXUtils.fromFXImage(imagenNueva, null);
            ImageIO.write(bImage, "png", outputFile);

        } catch (NullPointerException a) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accionBotones() {
        try {
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
                        if (comprobacionGeneral("Producto")) {

                            ResultSet pvID1 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                                    "Nombre_Proveedor = '" + textProvProd.getText() + "'");

                            pvID1.next();
                            Integer IDBuscado1 = pvID1.getInt("IDProveedor");

                            ResultSet comprobando = IO.introduceRegistros("SELECT * FROM PRODUCTOS WHERE " +
                                    "Nombre_Producto = '" + textNomProd.getText() + "' " +
                                    "Tipo_Producto = '" + textTipoProd.getText() + "' " +
                                    "IDProveedor = " + IDBuscado1);

                            if (comprobando.next()) {
                                throw new YaExisteException("El producto ya existe.");

                            } else {
                                IO.actualizaRegistros("INSERT INTO PRODUCTOS VALUES ("
                                        + "null" + ", '"
                                        + textNomProd.getText() + "', '"
                                        + textTipoProd.getText() + "', "
                                        + textCantProd.getText() + ", "
                                        + 100 + ", "
                                        + 50 + ", '"
                                        + textObseProd.getText() + "', "
                                        + IDBuscado1 + ")");
                            }
                        }

                        break;

                    case "Modificar Producto":
                        if (comprobacionGeneral("Producto")) {
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
                        }
                        break;

                    case "Añadir Proveedor":
                        if (comprobacionGeneral("Proveedor")) {
                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + textNomProv.getText() + "', '"
                                    + textDireProv.getText() + "', '"
                                    + textTelProv.getText() + "', '"
                                    + textPaisProv.getText() + "')");
                        }
                        break;

                    case "Modificar Proveedor":
                        if (comprobacionGeneral("Proveedor")) {
                            IO.actualizaRegistros("UPDATE PROVEEDOR set " +
                                    "Nombre_Proveedor = '" + textNomProv.getText() + "', " +
                                    "Direccion = '" + textDireProv.getText() + "', " +
                                    "Telefono_Proveedor = '" + textTelProv.getText() + "', " +
                                    "Pais = '" + textPaisProv.getText() + "', " +
                                    "WHERE IDProveedor = " + IDPrv);
                        }
                        break;
                }

                saveToFile();
                stage.close();

            } else if (botonCancelar.isFocused()) {
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (YaExisteException e) {
            e.printStackTrace();
        }
    }

    //Método para controlar que en algunos campos solo se escriban números.
    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {

            Integer.parseInt(strNum.replace(",", "."));

        } catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    public boolean existeProv(String proveedor, int accion) throws SQLException {
        boolean respuesta = false;

        switch (accion) {
            case 1:
                ResultSet proveedorExist1 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                        "Nombre_Proveedor = '" + proveedor + "'");

                if (proveedorExist1.next()) {
                    respuesta = true;
                }
                break;
            case 2:
                ResultSet proveedorExist2 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                        "Nombre_Proveedor = '" + proveedor + "' " +
                        "Direccion = '" + textDireProv.getText() + "' " +
                        "Pais = '" + textPaisProv.getText() + "'");

                if (proveedorExist2.next()) {
                    respuesta = true;
                }
                break;
        }


        return respuesta;
    }

    public boolean comprobacionGeneral(String aComprobar) {
        boolean comprobado = false;

        try {
            switch (aComprobar) {
                case "Producto":
                    if (!textNomProd.getText().isEmpty() ||
                            !textTipoProd.getText().isEmpty() ||
                            !textCantProd.getText().isEmpty() ||
                            !textProvProd.getText().isEmpty()) {

                        if (!isNum(textCantProd.getText())) {
                            throw new NoNumericoException("Los campos numéricos (Cantidad, Mínimo y Precio) solo pueden contener números y '.' o ','.");
                        } else if (!existeProv(textProvProd.getText(), 1)) {
                            throw new YaExisteException("El proveedor '" + textProvProd.getText() + "' no se encuentra registrado.");
                        } else if (textNomProd.getText().length() > 100) {
                            throw new MuchoTextoException("Nombre producto", 100);
                        } else if (textTipoProd.getText().length() > 50) {
                            throw new MuchoTextoException("Tipo", 50);
                        } else if (textProvProd.getText().length() > 100) {
                            throw new MuchoTextoException("Proveedor", 100);
                        } else if (textObseProd.getText().length() > 250) {
                            throw new MuchoTextoException("Observaciones", 250);
                        } else {

                            comprobado = true;
                        }
                    } else {
                        throw new CamposVaciosException();
                    }
                    break;
                case "Proveedor":
                    if (!textNomProv.getText().isEmpty() ||
                            !textDireProv.getText().isEmpty() ||
                            !textTelProv.getText().isEmpty() ||
                            !textPaisProv.getText().isEmpty()) {

                        if (!isNum(textTelProv.getText().replace("+", ""))) {
                            throw new NoNumericoException("El campo 'Teléfono' solo puede contener números y '+'.");
                        } else if (existeProv(textNomProv.getText(), 2)) {
                            throw new YaExisteException("El proveedor '" + textNomProv.getText() + "' ya se encuentra registrado.");
                        } else if (textNomProv.getText().length() > 100) {
                            throw new MuchoTextoException("Nombre empresa", 100);
                        } else if (textDireProv.getText().length() > 100) {
                            throw new MuchoTextoException("Dirección", 100);
                        } else if (textTelProv.getText().length() > 16) {
                            throw new MuchoTextoException("Teléfono", 16);
                        } else if (textPaisProv.getText().length() > 50) {
                            throw new MuchoTextoException("País", 50);
                        } else {

                            comprobado = true;
                        }
                    } else {
                        throw new CamposVaciosException();
                    }
                    break;
            }

        } catch (CamposVaciosException e) {
            e.printStackTrace();
        } catch (MuchoTextoException e) {
            e.printStackTrace();
        } catch (NoNumericoException e) {
            e.printStackTrace();
        } catch (YaExisteException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return comprobado;
    }
}