package Controlador;

import Controlador.Excepciones.*;
import Modelo.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaCamposController {

    // Elementos Patos
    public JFXTextField textNomPato, textRazaPato, textEdadPato, textNCartillaPato;
    public JFXTextArea textDescPato;

    // Elementos Productos
    public JFXTextField textNomProd, textTipoProd, textCantProd, textProvProd, textMinStockProd, textPrecProd;
    public JFXTextArea textObseProd;

    // Elementos Proveedores
    public JFXTextField textNomProv, textDireProv, textTelProv, textPaisProv;

    // Elementos Clientes
    public JFXTextField textNomCliente, textApeCliente, textTlf1Cliente, textTelf2Cliente, textEmailCliente, textDNICliente;
    public ComboBox CBTipPagoCliente;

    // Elementos Estancia
    public JFXTextField textDNIClEstancia, textNCartillaPatoEstancia;
    public DatePicker DPfechaEntradaEstancia, DPfechaSalidaEstancia;
    public ComboBox CBNombreLagoEstancia;

    //Botones comunes entre las ventanas.
    public JFXButton botonSubirImagen, botonEnviar, botonCancelar;

    //Instancia de la clase IOBaseDatos.
    IOBaseDatos IO = new IOBaseDatos();

    //Variables.
    public String pathImagen, nombreNuevoImagen, carpeta;
    public int IDPrd, IDPrv, IDPato, IDCliente, IDEstancia;
    public ImageView imagenActual, imagenPato, imagenCliente, imagenLago;
    public Image imagenNueva;

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Muestra la información del elemento que se desea modificar en los distintos elementos de la ventana.
    public void iniciarCampos(Producto pd) {
        textNomProd.setText(pd.getNombre_Producto());
        textTipoProd.setText(pd.getTipo_Producto());
        textCantProd.setText(String.valueOf(pd.getCantidad()));
        textProvProd.setText(pd.getNombreProveedor());
        textObseProd.setText(pd.getObservaciones());
        textMinStockProd.setText(String.valueOf(pd.getMinimo()));
        textPrecProd.setText(String.valueOf(pd.getPrecio()));

        try {
            imagenActual.setImage(new Image(new File("ImgProductos/" + pd.getNombre_Producto().replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPrd = pd.getIDProducto();
    }

    public void iniciarCampos(Proveedor pv) {
        textNomProv.setText(pv.getNombre_Proveedor());
        textDireProv.setText(pv.getDireccion());
        textTelProv.setText(pv.getTelefono_Proveedor());
        textPaisProv.setText(pv.getPais());
        try {
            imagenActual.setImage(new Image(new File("ImgProveedores/" + pv.getNombre_Proveedor().replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPrv = pv.getIDProveedor();
    }

    public void iniciarCampos(Pato pt) {
        textNomPato.setText(pt.getNombre_Pato());
        textRazaPato.setText(pt.getRaza());
        textEdadPato.setText(String.valueOf(pt.getEdad()));
        textNCartillaPato.setText(String.valueOf(pt.getNum_Cartilla()));
        textDescPato.setText(pt.getDescripcion());

        try {
            imagenActual.setImage(new Image(new File("ImgPatos/" + (pt.getNombre_Pato() + " " + pt.getNum_Cartilla()).replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPato = pt.getIDPato();
    }

    public void iniciarCampos(Cliente cl) {
        textNomCliente.setText(cl.getNombre_Cliente());
        textApeCliente.setText(cl.getApellidos_Cliente());
        textTlf1Cliente.setText(cl.getTelefono_Cliente1());
        textTelf2Cliente.setText(cl.getTelefono_Cliente2());
        textEmailCliente.setText(cl.getEmail_Cliente());
        CBTipPagoCliente.setValue(cl.getTipoPago());
        textDNICliente.setText(cl.getDNI());

        try {
            imagenActual.setImage(new Image(new File("ImgClientes/" + (cl.getNombre_Cliente() + " " + cl.getApellidos_Cliente()).replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDCliente = cl.getIDCliente();
    }

    public void iniciarCampos(Estancia est) throws SQLException {
        textDNIClEstancia.setText(est.getDNICliente());
        textNCartillaPatoEstancia.setText(est.getNumCartilla());
        DPfechaEntradaEstancia.setValue(LocalDate.parse(est.getFecha_Ingreso().toString()));
        DPfechaSalidaEstancia.setValue(LocalDate.parse(est.getFecha_Salida().toString()));
        CBNombreLagoEstancia.setValue(est.getNombreLago());

        textNCartillaPatoEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ResultSet paraImg = IO.introduceRegistros("SELECT Concat(Nombre_Pato, ' ', Num_Cartilla) AS 'Imagen' FROM Patos WHERE " +
                        "Num_Cartilla = " + newValue + "");

                try {
                    paraImg.next();

                    imagenPato.setImage(new Image("ImgPatos/" + paraImg.getString("Imagen").replace(" ", "_")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        textDNIClEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ResultSet paraImg = IO.introduceRegistros("SELECT Concat(Nombre_Cliente, ' ', Apellidos_Cliente) AS 'Imagen' FROM CLIENTES WHERE " +
                        "DNI = '" + newValue.toUpperCase() + "'");

                try {
                    paraImg.next();

                    imagenPato.setImage(new Image("ImgClientes/" + paraImg.getString("Imagen").replace(" ", "_")));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        CBNombreLagoEstancia.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                imagenLago.setImage(new Image("ImgLagos/" + newValue));
            }
        });

        IDEstancia = est.getIDEstancia();
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Permite autocompletar el compo Proveedor de Producto con los proveedores ya existentes.
    public void iniciaFieldProv(ArrayList<String> nombresProv) {

        TextFields.bindAutoCompletion(textProvProd, nombresProv);
        nombresProv.clear();
    }

    public void iniciaFieldEstancia(List<String> DNI, ArrayList<Integer> cartillas, ObservableList<String> lagos) {
        CBNombreLagoEstancia.getItems().clear();

        TextFields.bindAutoCompletion(textDNIClEstancia, DNI);
        TextFields.bindAutoCompletion(textNCartillaPatoEstancia, cartillas);
        CBNombreLagoEstancia.getItems().addAll(lagos);

        DNI.clear();
        cartillas.clear();
        lagos.clear();
    }

    //Permite autocompletar los compos necesarios con datos ya existentes..
    public void iniciaFieldEstancia(ArrayList<Cliente> Clientes, ArrayList<Pato> Patos, ArrayList<Lago> Lagos) {
        ArrayList<String> DNIClientes = null, cartillaPatos = null, nombresLagos = null;

        //Recoge todos losDNI de los clientes.
        for (int i = 0; i < Clientes.size(); i++) {
            DNIClientes.add(Clientes.get(i).getDNI());
        }

        //Recoge todos los números de cartilla de los patos.
        for (int i = 0; i < Patos.size(); i++) {
            cartillaPatos.add(String.valueOf(Patos.get(i).getNum_Cartilla()));
        }

        //Recoge todos los nombres de los lagos.
        for (int i = 0; i < Lagos.size(); i++) {
            nombresLagos.add(Lagos.get(i).getNombre_Lago());
        }

        CBNombreLagoEstancia.getItems().clear();
        CBNombreLagoEstancia.setItems((ObservableList) nombresLagos);

        TextFields.bindAutoCompletion(textDNIClEstancia, DNIClientes);
        TextFields.bindAutoCompletion(textNCartillaPatoEstancia, cartillaPatos);

        Clientes.clear();
        Patos.clear();
        Lagos.clear();
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Guarda la imegen seleccionada en su respectiva carpeta.
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

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Controla las acciones de los botones.
    public void accionBotones() {
        try {

            if (botonSubirImagen.isFocused()) {

                //Usa el explorador de archivos para poder seleccionar la imagen.
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir archivo");
                FileChooser.ExtensionFilter extFilterJPG = new
                        FileChooser.ExtensionFilter("Archivos JPG", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new
                        FileChooser.ExtensionFilter("Archivos PNG", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

                File file = fileChooser.showOpenDialog(null); //Almacena la imagen

                pathImagen = file.toURI().toString(); //Guarda la ruta.

                //Pone la imagen en el ImageView.
                imagenNueva = new Image(pathImagen);
                imagenActual.setImage(imagenNueva);

            } else if (botonEnviar.isFocused()) {

                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();

                //Realiza acciones según el elemento que se desea Añadir/Modificar.
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
                                        + textMinStockProd.getText() + ", "
                                        + textPrecProd.getText() + ", '"
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
                                    "Minimo = " + textMinStockProd.getText() + ", " +
                                    "Precio = " + textPrecProd.getText() + ", " +
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

                    case "Añadir Pato":
                        if (comprobacionGeneral("Pato")) {
                            IO.actualizaRegistros("INSERT INTO PATOS VALUES ("
                                    + "null" + ", '"
                                    + textNomPato.getText() + "', '"
                                    + textRazaPato.getText() + "', "
                                    + textEdadPato.getText() + ", "
                                    + textNCartillaPato.getText() + ", '"
                                    + textDescPato.getText() + "')");
                        }
                        break;

                    case "Modificar Pato":
                        if (comprobacionGeneral("Pato")) {
                            IO.actualizaRegistros("UPDATE PATOS set " +
                                    "Nombre_Pato = '" + textNomPato.getText() + "', " +
                                    "Raza = '" + textRazaPato.getText() + "', " +
                                    "Edad = " + textEdadPato.getText() + ", " +
                                    "Num_Cartilla = " + textNCartillaPato.getText() + ", " +
                                    "Descripcion = '" + textDescPato.getText() + "', " +
                                    "WHERE IDPato = " + IDPato);
                        }
                        break;

                    case "Añadir Cliente":
                        if (comprobacionGeneral("Cliente")) {
                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + textDNICliente.getText() + "', '"
                                    + textNomCliente.getText() + "', '"
                                    + textApeCliente.getText() + "', '"
                                    + textTlf1Cliente.getText() + "', '"
                                    + textTelf2Cliente.getText() + "', '"
                                    + textEmailCliente.getText() + "', '"
                                    + CBTipPagoCliente.getValue() + "')");
                        }
                        break;

                    case "Modificar Cliente":
                        if (comprobacionGeneral("Cliente")) {
                            IO.actualizaRegistros("UPDATE CLIENTES set " +
                                    "DNI = '" + textDNICliente.getText() + "', " +
                                    "Nombre_Cliente = '" + textNomCliente.getText() + "', " +
                                    "Apellidos_Cliente = '" + textApeCliente.getText() + "', " +
                                    "Telefono_Cliente1 = '" + textTlf1Cliente.getText() + "', " +
                                    "Telefono_Cliente2 = '" + textTelf2Cliente.getText() + "', " +
                                    "Email_Cliente = '" + textEmailCliente.getText() + "', " +
                                    "TipoPago = '" + CBTipPagoCliente.getValue() + "', " +
                                    "WHERE IDCliente = " + IDCliente);
                        }
                        break;

                    case "Añadir Estancia":
                        if (comprobacionGeneral("Estancia")) {
                            ResultSet busqueda = IO.introduceRegistros("SELECT patos.IDPato, clientes.IDCliente, lagos.IDLago FROM patos, clientes, lagos WHERE " +
                                    "clientes.DNI = '" + textDNIClEstancia.getText() + "' && " +
                                    "patos.Num_Cartilla = " + textNCartillaPatoEstancia.getText() + " && " +
                                    "lagos.IDLago = " + CBNombreLagoEstancia.getValue());

                            busqueda.next();

                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + DPfechaEntradaEstancia.getValue() + "', '"
                                    + DPfechaSalidaEstancia.getValue() + "', "
                                    + busqueda.getString("IDPato") + ", "
                                    + busqueda.getString("IDCliente") + ", "
                                    + busqueda.getString("IDLago") + ")");
                        }
                        break;

                    case "Modificar Estancia":
                        if (comprobacionGeneral("Estancia")) {
                            ResultSet busqueda = IO.introduceRegistros("SELECT patos.IDPato, clientes.IDCliente, lagos.IDLago FROM patos, clientes, lagos WHERE " +
                                    "clientes.DNI = '" + textDNIClEstancia.getText() + "' && " +
                                    "patos.Num_Cartilla = " + textNCartillaPatoEstancia.getText() + " && " +
                                    "lagos.IDLago = " + CBNombreLagoEstancia.getValue());

                            busqueda.next();

                            IO.actualizaRegistros("UPDATE ESTANCIA set " +
                                    "Fecha_Ingreso = '" + DPfechaEntradaEstancia.getValue() + "', " +
                                    "Fecha_Salida = '" + DPfechaSalidaEstancia.getValue() + "', " +
                                    "IDPato = " + busqueda.getString("IDPato") + ", " +
                                    "IDCliente = " + busqueda.getString("IDCliente") + ", " +
                                    "IDLago = " + busqueda.getString("IDLago") + ", " +
                                    "WHERE IDEstancia = " + IDEstancia);
                        }
                        break;
                }

                saveToFile(); //Guarda la imagen.
                stage.close(); //Cierra la ventana.

            } else if (botonCancelar.isFocused()) {
                //Cierra la ventana.
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (YaExisteException e) {
            e.printStackTrace();
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
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

    //Método para averiguar que el formato de una fecha es correcto o no.
    public boolean validDate(String fecha) {

        Date date;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = dateFormat.parse(fecha);
            Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
            calendario.setTime(date);
            int ano = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);
            if (mes > 12 || dia > 31 || ano > 2021) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Comprueba si el proveedor ya existe o no.
    public boolean existeComprobar(String comprueba, String para) throws SQLException {
        boolean respuesta = false;

        switch (para) {
            case "Producto":
                ResultSet proveedorExist1 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                        "Nombre_Proveedor = '" + comprueba + "'");

                if (proveedorExist1.next()) {
                    respuesta = true;
                }
                break;

            case "Proveedor":
                ResultSet proveedorExist2 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                        "Nombre_Proveedor = '" + comprueba + "' " +
                        "Direccion = '" + textDireProv.getText() + "' " +
                        "Pais = '" + textPaisProv.getText() + "'");

                if (proveedorExist2.next()) {
                    respuesta = true;
                }
                break;

            case "Pato":
                ResultSet patoExist = IO.introduceRegistros("SELECT IDPato FROM PATOS WHERE " +
                        "Num_Cartilla = " + comprueba);

                if (patoExist.next()) {
                    respuesta = true;
                }
                break;
        }


        return respuesta;
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Comprueba que todos los campos de la venta estén bien escritos.
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
                        } else if (!existeComprobar(textProvProd.getText(), "Producto")) {
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
                        } else if (existeComprobar(textNomProv.getText(), "Proveedor")) {
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

                case "Pato":
                    if (!textNomPato.getText().isEmpty() ||
                            !textRazaPato.getText().isEmpty() ||
                            !textEdadPato.getText().isEmpty() ||
                            !textNCartillaPato.getText().isEmpty()) {

                        if (!isNum(textEdadPato.getText())) {
                            throw new NoNumericoException("El campo 'Edad' solo puede contener números.");
                        } else if (!isNum(textNCartillaPato.getText())) {
                            throw new NoNumericoException("El campo 'Nº Cartilla' solo puede contener números.");
                        } else if (existeComprobar(textNCartillaPato.getText(), "Pato")) {
                            throw new YaExisteException("El pato con cartilla '" + textNCartillaPato.getText() + "' ya se encuentra registrado.");
                        } else if (textNomPato.getText().length() > 50) {
                            throw new MuchoTextoException("Nombre Pato", 50);
                        } else if (textRazaPato.getText().length() > 50) {
                            throw new MuchoTextoException("Raza", 50);
                        } else if (textDescPato.getText().length() > 100) {
                            throw new MuchoTextoException("Descripción", 100);
                        } else {

                            comprobado = true;
                        }
                    } else {
                        throw new CamposVaciosException();
                    }
                    break;

                case "Cliente":
                    if (!textNomCliente.getText().isEmpty() ||
                            !textApeCliente.getText().isEmpty() ||
                            !textEmailCliente.getText().isEmpty() ||
                            CBTipPagoCliente.getValue() != null ||
                            !textDNICliente.getText().isEmpty()) {

                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        Matcher mather = pattern.matcher(textEmailCliente.getText());

                        if (!isNum(textTlf1Cliente.getText().replace("+", ""))) {
                            throw new NoNumericoException("El campo 'Tlfn1' solo puede contener números y '+'.");
                        } else if (!isNum(textTelf2Cliente.getText().replace("+", ""))) {
                            throw new NoNumericoException("El campo 'Tlfn2' solo puede contener números y '+'.");
                        } else if (!mather.find()) {
                            throw new FormatoIncorrectoException("Email");
                        } else if (textDNICliente.getText().length() != 9 && !isNum(textDNICliente.getText().substring(0, 9)) && isNum(textDNICliente.getText().substring(9))) {
                            throw new NoNumericoException("El campo 'DNI' debe contener 9 carácteres (8 numéricos y una letra).");
                        } else if (existeComprobar(textDNICliente.getText(), "Cliente")) {
                            throw new YaExisteException("El cliente con DNI '" + textDNICliente.getText() + "' ya se encuentra registrado.");
                        } else {

                            comprobado = true;
                        }
                    } else {
                        throw new CamposVaciosException();
                    }
                    break;

                case "Estancia":
                    if (!textDNIClEstancia.getText().isEmpty() ||
                            !textNCartillaPatoEstancia.getText().isEmpty() ||
                            DPfechaEntradaEstancia.getValue() != null ||
                            DPfechaSalidaEstancia.getValue() != null ||
                            CBNombreLagoEstancia.getValue() != null) {

                        if (textDNIClEstancia.getText().length() != 9 && !isNum(textDNIClEstancia.getText().substring(0, 9)) && isNum(textDNIClEstancia.getText().substring(9))) {
                            throw new NoNumericoException("El campo 'DNI' debe contener 9 carácteres (8 numéricos y una letra).");
                        } else if (!existeComprobar(textDNIClEstancia.getText(), "Cliente")) {
                            throw new YaExisteException("El cliente con DNI '" + textDNIClEstancia.getText() + "' no se encuentra registrado.");
                        } else if (!isNum(textNCartillaPatoEstancia.getText())) {
                            throw new NoNumericoException("El campo 'Cartilla Pato' solo puede contener números.");
                        } else if (!existeComprobar(textNCartillaPatoEstancia.getText(), "Pato")) {
                            throw new YaExisteException("El pato con cartilla '" + textNCartillaPatoEstancia.getText() + "' no se encuentra registrado.");
                        } else if (!validDate(DPfechaEntradaEstancia.getValue().toString())) {
                            throw new FechaInvalidaException("El campo 'Fecha Ingreso' no contiene una fecha válida.");
                        } else if (!validDate(DPfechaSalidaEstancia.getValue().toString())) {
                            throw new FechaInvalidaException("El campo 'Fecha Salida' no contiene una fecha válida.");
                        } else if (DPfechaEntradaEstancia.getValue().compareTo(DPfechaSalidaEstancia.getValue()) > -1) {
                            throw new FechaInvalidaException("La fecha del campo 'Fecha Ingreso' debe ser anterior a la de 'Fecha Salida'.");
                        } else if (textNomCliente.getText().length() > 50) {
                            throw new MuchoTextoException("Nombre", 50);
                        } else if (textApeCliente.getText().length() > 50) {
                            throw new MuchoTextoException("Apellidos", 50);
                        } else if (textEmailCliente.getText().length() > 50) {
                            throw new MuchoTextoException("Email", 50);
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
        } catch (FormatoIncorrectoException e) {
            e.printStackTrace();
        } catch (FechaInvalidaException e) {
            e.printStackTrace();
        }

        return comprobado;
    }
    /*___________________________________________________________________________________________________________________________________________________________________________*/
}