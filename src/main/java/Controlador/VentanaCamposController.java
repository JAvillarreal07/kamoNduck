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
    public ComboBox<String> CBTipPagoCliente;

    // Elementos Estancia
    public JFXTextField textDNIClEstancia, textNCartillaPatoEstancia;
    public DatePicker DPfechaEntradaEstancia, DPfechaSalidaEstancia;
    public ComboBox<String> CBNombreLagoEstancia;

    // Elementos Empleados
    public JFXTextField textDNIEmp, textNomEmp, txtApEmp, txtTlfEmp, txtEmailEmp, txtCargoEmp, txtHorario;
    public ComboBox<String> comboTurno, comboNombreLago;

    //Botones comunes entre las ventanas.
    public JFXButton botonSubirImagen, botonEnviar, botonCancelar;
    public ImageView imagenActual, imagenPato, imagenCliente, imagenLago;

    //Instancia de la clase IOBaseDatos.
    IOBaseDatos IO = new IOBaseDatos();

    //Instancia de la clase VentanaPrincipalController.
    VentanaPrincipalController vpc = new VentanaPrincipalController();

    //Variables.
    public String pathImagen, nombreNuevoImagen, carpeta;
    public int IDPrd, IDPrv, IDPato, IDCliente, IDEstancia, IDEmpleado;
    public Image imagenNueva;

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Muestra la información del elemento que se desea modificar en los distintos elementos de la ventana.

    /******Para Productos******/
    public void iniciarCampos(Producto pd) {
        textNomProd.setText(pd.getNombre_Producto());
        textTipoProd.setText(pd.getTipo_Producto());
        textCantProd.setText(String.valueOf(pd.getCantidad()));
        textProvProd.setText(pd.getNombreProveedor());
        textObseProd.setText(pd.getObservaciones());
        textMinStockProd.setText(String.valueOf(pd.getMinimo()));
        textPrecProd.setText(String.valueOf(pd.getPrecio()));

        //Busca si el producto tiene una imagen asociada y la muestra.
        try {
            imagenActual.setImage(new Image(new File("ImgProductos/" + pd.getNombre_Producto().replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPrd = pd.getIDProducto(); //Almacena el ID del producto a modificar.
    }
    /**************************/

    /******Para Proveedores******/
    public void iniciarCampos(Proveedor pv) {
        textNomProv.setText(pv.getNombre_Proveedor());
        textDireProv.setText(pv.getDireccion());
        textTelProv.setText(pv.getTelefono_Proveedor());
        textPaisProv.setText(pv.getPais());

        //Busca si el proveedor tiene una imagen asociada y la muestra.
        try {
            imagenActual.setImage(new Image(new File("ImgProveedores/" + pv.getNombre_Proveedor().replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPrv = pv.getIDProveedor(); //Almacena el ID del proveedor a modificar.
    }
    /****************************/

    /******Para Patos******/
    public void iniciarCampos(Pato pt) {
        textNomPato.setText(pt.getNombre_Pato());
        textRazaPato.setText(pt.getRaza());
        textEdadPato.setText(String.valueOf(pt.getEdad()));
        textNCartillaPato.setText(String.valueOf(pt.getNum_Cartilla()));
        textDescPato.setText(pt.getDescripcion());

        //Busca si el pato tiene una imagen asociada y la muestra.
        try {
            imagenActual.setImage(new Image(new File("ImgPatos/" + (pt.getNombre_Pato() + " " + pt.getNum_Cartilla()).replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDPato = pt.getIDPato(); //Almacena el ID del pato a modificar.
    }
    /**********************/

    /******Para Clientes******/
    public void iniciarCampos(Cliente cl) {
        textNomCliente.setText(cl.getNombre_Cliente());
        textApeCliente.setText(cl.getApellidos_Cliente());
        textTlf1Cliente.setText(cl.getTelefono_Cliente1());
        textTelf2Cliente.setText(cl.getTelefono_Cliente2());
        textEmailCliente.setText(cl.getEmail_Cliente());
        CBTipPagoCliente.setValue(cl.getTipoPago());
        textDNICliente.setText(cl.getDNI());

        //Busca si el cliente tiene una imagen asociada y la muestra.
        try {
            imagenActual.setImage(new Image(new File("ImgClientes/" + (cl.getNombre_Cliente() + " " + cl.getApellidos_Cliente()).replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        IDCliente = cl.getIDCliente(); //Almacena el ID del cliente a modificar.
    }
    /*************************/

    /******Para Estancias******/
    public void iniciarCampos(Estancia est) throws SQLException {
        textDNIClEstancia.setText(est.getDNICliente());
        textNCartillaPatoEstancia.setText(est.getNumCartilla());
        DPfechaEntradaEstancia.setValue(LocalDate.parse(est.getFecha_Ingreso().toString()));
        DPfechaSalidaEstancia.setValue(LocalDate.parse(est.getFecha_Salida().toString()));
        CBNombreLagoEstancia.setValue(est.getNombreLago());

        imagenPato.setImage(new Image(new File("ImgPatos/" + (est.getNombrePato() + " " + est.getNumCartilla()).replace(" ", "_") + ".png").toURI().toString()));
        imagenCliente.setImage(new Image(new File("ImgClientes/" + est.getNombreCliente().replace(" ", "_") + ".png").toURI().toString()));
        imagenLago.setImage(new Image(new File("ImgLagos/" + est.getNombreLago().replace(" ", "_") + ".png").toURI().toString()));

        IDEstancia = est.getIDEstancia(); //Almacena el ID de la estancia a modificar.
    }

    public void escuchaCamposEstancia() {

        //Estos tres listener buscan las imagenes para el pato y el cliente escrito y para el lago seleccionado.
        textNCartillaPatoEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if  (newValue.length() == 6 && isNum(newValue)) {

                    ResultSet paraImg = IO.introduceRegistros("SELECT Concat(Nombre_Pato, ' ', Num_Cartilla) AS 'Imagen' FROM Patos WHERE " +
                            "Num_Cartilla = " + newValue + "");

                    try {
                        paraImg.next();

                        imagenPato.setImage(new Image(new File("ImgPatos/" + paraImg.getString("Imagen").replace(" ", "_") + ".png").toURI().toString()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        textDNIClEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() == 9 && isNum(newValue.substring(0, 9)) && !isNum(newValue.substring(9))) {
                    ResultSet paraImg = IO.introduceRegistros("SELECT Concat(Nombre_Cliente, ' ', Apellidos_Cliente) AS 'Imagen' FROM CLIENTES WHERE " +
                            "DNI = '" + newValue.toUpperCase() + "'");

                    try {
                        paraImg.next();

                        imagenCliente.setImage(new Image(new File("ImgClientes/" + paraImg.getString("Imagen").replace(" ", "_") + ".png").toURI().toString()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        CBNombreLagoEstancia.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                imagenLago.setImage(new Image(new File("ImgLagos/" + newValue.replace(" ", "_") + ".png").toURI().toString()));
            }
        });
    }
    /**************************/

    /******Para Empleados******/
    public void iniciarCampos(Empleado empl) throws SQLException {
        textDNIEmp.setText(empl.getDNI_Empleado());
        textNomEmp.setText(empl.getNombre_Empleado());
        txtApEmp.setText(empl.getApellidos_Empleado());
        txtTlfEmp.setText(empl.getTelefono_Empleado());
        txtEmailEmp.setText(empl.getEmail_Empleado());
        txtCargoEmp.setText(empl.getCargo());
        txtHorario.setText(empl.getHorario_Trabajo());
        comboTurno.setValue(empl.getTurno());
        comboNombreLago.setValue(empl.getNombreLago());

        //Busca si el empleado tiene una imagen asociada y la muestra.
        try {
            imagenActual.setImage(new Image(new File("ImgEmpleados/" + empl.getNombreCompleto().replace(" ", "_") + ".png").toURI().toString()));
        } catch (IllegalArgumentException e) {
        }

        comboNombreLago.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                imagenLago.setImage(new Image(new File("ImgLagos/" + newValue.replace(" ", "_") + ".png").toURI().toString()));
            }
        });

        IDEmpleado = empl.getIDEmpleado(); //Almacena el ID del empleado a modificar.
    }
    /**************************/

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Estos métodos establecen el autocompletado de los campos necesarios y los ComboBox.

    /******Para Proveedores******/
    public void iniciaFieldProv(ArrayList<String> nombresProv) {

        //Introduce los elementos para autocompletar en el elemento respectivo.
        TextFields.bindAutoCompletion(textProvProd, nombresProv);
        nombresProv.clear(); //Vacia la lista proporcionada para evitar duplicados.
    }
    /****************************/

    /******Para Estancias******/
    public void iniciaFieldEstancia(List<String> DNI, ArrayList<Integer> cartillas, ObservableList<String> lagos) {
        CBNombreLagoEstancia.getItems().clear(); //Vacia el ComboBox para evitar duplicados.

        //Introduce los elementos para autocompletar en los elementos respectivos.
        TextFields.bindAutoCompletion(textDNIClEstancia, DNI);
        TextFields.bindAutoCompletion(textNCartillaPatoEstancia, cartillas);
        CBNombreLagoEstancia.getItems().addAll(lagos);

        escuchaCamposEstancia();

        //Vacia las listas proporcionada para evitar duplicados.
        DNI.clear();
        cartillas.clear();
        lagos.clear();
    }
    /**************************/

    /******Para Empleados******/
    public void iniciaFieldEmpleados(ArrayList<String> cargo, ObservableList<String> lagos) {
        comboNombreLago.getItems().clear(); //Vacia el ComboBox para evitar duplicados.

        //Introduce los elementos para autocompletar en los elementos respectivos.
        TextFields.bindAutoCompletion(txtCargoEmp, cargo);
        comboNombreLago.getItems().addAll(lagos);

        //Vacia las listas proporcionada para evitar duplicados.
        cargo.clear();
        lagos.clear();
    }

    /**************************/

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Guarda la imegen seleccionada en su respectiva carpeta.
    public void saveToFile() {

        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();

        //Dependiendo del título de la ventana cambiará la carpeta destino y el nombre de la imagen.
        if (stage.getTitle().contains("Producto")) {
            nombreNuevoImagen = textNomProd.getText().replace(" ", "_");
            carpeta = "ImgProductos";

        } else if (stage.getTitle().contains("Proveedor")) {
            nombreNuevoImagen = textNomProv.getText().replace(" ", "_");
            carpeta = "ImgProveedores";

        } else if (stage.getTitle().contains("Pato")) {
            nombreNuevoImagen = (textNomPato.getText() + " " + textNCartillaPato.getText()).replace(" ", "_");
            carpeta = "ImgPatos";

        } else if (stage.getTitle().contains("Cliente")) {
            nombreNuevoImagen = (textNomCliente.getText() + " " + textApeCliente.getText()).replace(" ", "_");
            carpeta = "ImgClientes";

        } else if (stage.getTitle().contains("Empleado")) {
            nombreNuevoImagen = (textNomEmp.getText() + " " + txtApEmp.getText()).replace(" ", "_");
            carpeta = "ImgEmpleados";
        }

        //Guarda la imagen en formato png.
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

        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();

        try {
            if (!stage.getTitle().contains("Estancia") && botonSubirImagen.isFocused()) {

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
                boolean sinErrores = false;

                //Realiza acciones según el elemento que se desea Añadir/Modificar.
                switch (stage.getTitle()) {
                    case "Añadir Producto":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Producto", "Añadir")) {

                            //Buscar el ID del proveedor seleccionado.
                            ResultSet pvID1 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                                    "Nombre_Proveedor = '" + textProvProd.getText() + "'");

                            pvID1.next();

                            Integer IDBuscado1 = pvID1.getInt("IDProveedor");

                            //Consultar para comprobar si el producto ya existe o no.
                            ResultSet comprobando = IO.introduceRegistros("SELECT * FROM PRODUCTOS WHERE " +
                                    "Nombre_Producto = '" + textNomProd.getText() + "' " +
                                    "Tipo_Producto = '" + textTipoProd.getText() + "' " +
                                    "IDProveedor = " + IDBuscado1);

                            //Si el producto ya existe produce un error personalizado, en caso contrario realiza el INSERT.
                            if (comprobando.next()) {
                                throw new YaExisteException("El producto ya existe.");

                            } else {
                                //Añade el producto a la BD.
                                IO.actualizaRegistros("INSERT INTO PRODUCTOS VALUES ("
                                        + "null" + ", '"
                                        + textNomProd.getText() + "', '"
                                        + textTipoProd.getText() + "', "
                                        + textCantProd.getText() + ", "
                                        + textMinStockProd.getText() + ", "
                                        + textPrecProd.getText() + ", '"
                                        + textObseProd.getText() + "', "
                                        + IDBuscado1 + ")");

                                //Realiza un reporte de pedido en caso de que la cantidad de existencias del producdo
                                // sea menor que el mínimo que debería haber.
                                if (Integer.parseInt(textCantProd.getText()) < Integer.parseInt(textMinStockProd.getText())) {
                                    vpc.reportePedirProd(IDBuscado1, textNomProd.getText());
                                }
                            }

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Producto":
                        //Comprueba varios aspectos del los campos antes de seguir con el UPDATE.
                        if (comprobacionGeneral("Producto", "Modificar")) {

                            //Buscar el ID del proveedor seleccionado.
                            ResultSet pvID2 = IO.introduceRegistros("SELECT IDProveedor FROM PROVEEDOR WHERE " +
                                    "Nombre_Proveedor = '" + textProvProd.getText() + "'");

                            pvID2.next();

                            Integer IDBuscado2 = pvID2.getInt("IDProveedor");

                            //Modifica el producto en la BD.
                            IO.actualizaRegistros("UPDATE PRODUCTOS set " +
                                    "Nombre_Producto = '" + textNomProd.getText() + "', " +
                                    "Tipo_Producto = '" + textTipoProd.getText() + "', " +
                                    "Cantidad = " + textCantProd.getText() + ", " +
                                    "Minimo = " + textMinStockProd.getText() + ", " +
                                    "Precio = " + textPrecProd.getText() + ", " +
                                    "Observaciones = '" + textObseProd.getText() + "', " +
                                    "IDProveedor = " + IDBuscado2 +
                                    " WHERE IDProducto = " + IDPrd);

                            //Realiza un reporte de pedido en caso de que la cantidad de existencias del producdo
                            // sea menor que el mínimo que debería haber.
                            if (Integer.parseInt(textCantProd.getText()) < Integer.parseInt(textMinStockProd.getText())) {
                                vpc.reportePedirProd(IDPrd, textNomProd.getText());
                            }

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Añadir Proveedor":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Proveedor", "Añadir")) {

                            //Añade al proveedor a la BD.
                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + textNomProv.getText() + "', '"
                                    + textDireProv.getText() + "', '"
                                    + textTelProv.getText() + "', '"
                                    + textPaisProv.getText() + "')");

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Proveedor":
                        //Comprueba varios aspectos del los campos antes de seguir con el UPDATE.
                        if (comprobacionGeneral("Proveedor", "Modificar")) {

                            //Modifica al proveedor en la BD.
                            IO.actualizaRegistros("UPDATE PROVEEDOR set " +
                                    "Nombre_Proveedor = '" + textNomProv.getText() + "', " +
                                    "Direccion = '" + textDireProv.getText() + "', " +
                                    "Telefono_Proveedor = '" + textTelProv.getText() + "', " +
                                    "Pais = '" + textPaisProv.getText() + "' " +
                                    "WHERE IDProveedor = " + IDPrv);

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Añadir Pato":
                        if (comprobacionGeneral("Pato", "Añadir")) {

                            //Añade al pato a la BD.
                            IO.actualizaRegistros("INSERT INTO PATOS VALUES ("
                                    + "null" + ", '"
                                    + textNomPato.getText() + "', '"
                                    + textRazaPato.getText() + "', "
                                    + textEdadPato.getText() + ", "
                                    + textNCartillaPato.getText() + ", '"
                                    + textDescPato.getText() + "')");

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Pato":
                        //Comprueba varios aspectos del los campos antes de seguir con el UPDATE.
                        if (comprobacionGeneral("Pato", "Modificar")) {

                            //Modifica al pato en la BD.
                            IO.actualizaRegistros("UPDATE PATOS set " +
                                    "Nombre_Pato = '" + textNomPato.getText() + "', " +
                                    "Raza = '" + textRazaPato.getText() + "', " +
                                    "Edad = " + textEdadPato.getText() + ", " +
                                    "Num_Cartilla = " + textNCartillaPato.getText() + ", " +
                                    "Descripcion = '" + textDescPato.getText() + "' " +
                                    "WHERE IDPato = " + IDPato);

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Añadir Cliente":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Cliente", "Añadir")) {

                            //Añade al cliente a la BD.
                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + textDNICliente.getText().toUpperCase() + "', '"
                                    + textNomCliente.getText() + "', '"
                                    + textApeCliente.getText() + "', '"
                                    + textTlf1Cliente.getText() + "', '"
                                    + textTelf2Cliente.getText() + "', '"
                                    + textEmailCliente.getText() + "', '"
                                    + CBTipPagoCliente.getValue() + "')");

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Cliente":
                        //Comprueba varios aspectos del los campos antes de seguir con el UPDATE.
                        if (comprobacionGeneral("Cliente", "Modificar")) {

                            //Modifica el cliente en la BD.
                            IO.actualizaRegistros("UPDATE CLIENTES set " +
                                    "DNI = '" + textDNICliente.getText().toUpperCase() + "', " +
                                    "Nombre_Cliente = '" + textNomCliente.getText() + "', " +
                                    "Apellidos_Cliente = '" + textApeCliente.getText() + "', " +
                                    "Telefono_Cliente1 = '" + textTlf1Cliente.getText() + "', " +
                                    "Telefono_Cliente2 = '" + textTelf2Cliente.getText() + "', " +
                                    "Email_Cliente = '" + textEmailCliente.getText() + "', " +
                                    "TipoPago = '" + CBTipPagoCliente.getValue() + "' " +
                                    "WHERE IDCliente = " + IDCliente);

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Añadir Estancia":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Estancia", "Añadir")) {

                            //Buscar el ID del pato, cliente y lago seleccionado.
                            ResultSet busqueda = IO.introduceRegistros("SELECT patos.IDPato, clientes.IDCliente, lagos.IDLago FROM patos, clientes, lagos WHERE " +
                                    "clientes.DNI = '" + textDNIClEstancia.getText() + "' && " +
                                    "patos.Num_Cartilla = " + textNCartillaPatoEstancia.getText() + " && " +
                                    "lagos.Nombre_Lago = '" + CBNombreLagoEstancia.getValue() + "'");

                            busqueda.next();

                            //Añade la estancia a la BD.
                            IO.actualizaRegistros("INSERT INTO PROVEEDOR VALUES ("
                                    + "null" + ", '"
                                    + DPfechaEntradaEstancia.getValue() + "', '"
                                    + DPfechaSalidaEstancia.getValue() + "', "
                                    + busqueda.getString("IDPato") + ", "
                                    + busqueda.getString("IDCliente") + ", "
                                    + busqueda.getString("IDLago") + ")");

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Estancia":
                        //Comprueba varios aspectos del los campos antes de seguir con el UPDATE.
                        if (comprobacionGeneral("Estancia", "Modificar")) {

                            //Buscar el ID del pato, cliente y lago seleccionado.
                            ResultSet busqueda = IO.introduceRegistros("SELECT patos.IDPato, clientes.IDCliente, lagos.IDLago FROM patos, clientes, lagos WHERE " +
                                    "clientes.DNI = '" + textDNIClEstancia.getText() + "' && " +
                                    "patos.Num_Cartilla = " + textNCartillaPatoEstancia.getText() + " && " +
                                    "lagos.Nombre_Lago = '" + CBNombreLagoEstancia.getValue() + "'");

                            busqueda.next();

                            //Modifica la estancia en la BD.
                            IO.actualizaRegistros("UPDATE ESTANCIA set " +
                                    "Fecha_Ingreso = '" + DPfechaEntradaEstancia.getValue() + "', " +
                                    "Fecha_Salida = '" + DPfechaSalidaEstancia.getValue() + "', " +
                                    "IDPato = " + busqueda.getString("IDPato") + ", " +
                                    "IDCliente = " + busqueda.getString("IDCliente") + ", " +
                                    "IDLago = " + busqueda.getString("IDLago") +
                                    " WHERE IDEstancia = " + IDEstancia);

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Añadir Empleado":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Empleados", "Añadir")) {

                            //Buscar el ID del lago seleccionado.
                            ResultSet busqueda = IO.introduceRegistros("SELECT IDLago FROM EMPLEADOS WHERE " +
                                    "Nombre_Lago = " + comboNombreLago.getValue());

                            busqueda.next();

                            //Añade al empleado a la BD.
                            IO.actualizaRegistros("INSERT INTO EMPLEADOS VALUES ("
                                    + "null" + ", '"
                                    + textDNIEmp.getText().toUpperCase() + "', '"
                                    + textNomEmp.getText() + "', '"
                                    + txtApEmp.getText() + "', '"
                                    + txtTlfEmp.getText() + "', '"
                                    + txtEmailEmp.getText() + "', '"
                                    + txtCargoEmp.getText() + "', '"
                                    + txtHorario.getText() + "', '"
                                    + comboTurno.getValue() + "', "
                                    + busqueda.getInt("IDLago") + ")");

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;

                    case "Modificar Empleado":
                        //Comprueba varios aspectos del los campos antes de seguir con el INSERT.
                        if (comprobacionGeneral("Empleado", "Modificar")) {

                            //Buscar el ID del lago seleccionado.
                            ResultSet busqueda = IO.introduceRegistros("SELECT IDLago FROM EMPLEADOS WHERE " +
                                    "Nombre_Lago = " + comboNombreLago.getValue());

                            busqueda.next();

                            //Modifica al empleado en la BD.
                            IO.actualizaRegistros("UPDATE EMPLEADOS set " +
                                    "DNI_Empleado = '" + textDNIEmp.getText().toUpperCase() + "', " +
                                    "Nombre_Empleado = '" + textNomEmp.getText() + "', " +
                                    "Apellidos_Empleado = '" + txtApEmp.getText() + "', " +
                                    "Telefono_Empleado = '" + txtTlfEmp.getText() + "', " +
                                    "Email_Empleado = '" + txtEmailEmp.getText() + "', " +
                                    "Cargo = '" + txtCargoEmp.getText() + "', " +
                                    "Horario_Trabajo = '" + txtHorario.getText() + "', " +
                                    "Turno = '" + comboTurno.getValue() + "', " +
                                    "IDLago = " + busqueda.getInt("IDLago") +
                                    " WHERE IDEmpleado = " + IDEmpleado);

                            sinErrores = true;
                        } else {
                            sinErrores = false;
                        }

                        break;
                }

                if (sinErrores) {
                    saveToFile(); //Guarda la imagen.
                    stage.close(); //Cierra la ventana.
                }

            } else if (botonCancelar.isFocused()) {
                //Cierra la ventana.
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
    //Comprueba si el producto/proveedor/pato ya existe o no.
    public boolean existeComprobar(String comprueba, String para) throws SQLException {
        boolean respuesta = false;

        try {

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
                            "Num_Cartilla = '" + comprueba + "'");

                    if (patoExist.next()) {
                        respuesta = true;
                    }
                    break;

                case "Cliente":
                    ResultSet clienteExist = IO.introduceRegistros("SELECT IDCliente FROM CLIENTES WHERE " +
                            "DNI = '" + comprueba + "'");

                    if (clienteExist.next()) {
                        respuesta = true;
                    }
                    break;

                case "Empleado":
                    ResultSet empExist = IO.introduceRegistros("SELECT IDEmpleado FROM EMPLEADOS WHERE " +
                            "DNI_Empleado = '" + comprueba + "'");

                    if (empExist.next()) {
                        respuesta = true;
                    }
                    break;
            }
        } catch (NullPointerException e) {
        }
        ;

        return respuesta;
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Comprueba que todos los campos de la ventana estén bien escritos.
    public boolean comprobacionGeneral(String aComprobar, String accion) {
        boolean comprobado = false;
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();

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

                        if (!isNum(textTelProv.getText().replace("+", "").replace(" ", ""))) {
                            throw new NoNumericoException("El campo 'Teléfono' solo puede contener números y '+'.");
                        } else if (accion.equals("Añadir") && existeComprobar(textNomProv.getText(), "Proveedor")) {
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
                        } else if (accion.equals("Añadir") && existeComprobar(textNCartillaPato.getText(), "Pato")) {
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

                        if (!isNum(textTlf1Cliente.getText().replace("+", "").replace(" ", ""))) {
                            throw new NoNumericoException("El campo 'Tlfn1' solo puede contener números y '+'.");
                        } else if (!isNum(textTelf2Cliente.getText().replace("+", "").replace(" ", ""))) {
                            throw new NoNumericoException("El campo 'Tlfn2' solo puede contener números y '+'.");
                        } else if (!mather.find()) {
                            throw new FormatoIncorrectoException("Email");
                        } else if (textDNICliente.getText().length() != 9 && !isNum(textDNICliente.getText().substring(0, 9)) && isNum(textDNICliente.getText().substring(9))) {
                            throw new NoNumericoException("El campo 'DNI' debe contener 9 carácteres (8 numéricos y una letra).");
                        } else if (accion.equals("Añadir") && existeComprobar(textDNICliente.getText(), "Cliente")) {
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
                        } else {

                            comprobado = true;
                        }
                    } else {
                        throw new CamposVaciosException();
                    }
                    break;

                case "Empleado":
                    if (!textDNIEmp.getText().isEmpty() ||
                            !textNomEmp.getText().isEmpty() ||
                            !txtApEmp.getText().isEmpty() ||
                            !txtTlfEmp.getText().isEmpty() ||
                            !txtEmailEmp.getText().isEmpty() ||
                            !txtCargoEmp.getText().isEmpty() ||
                            !txtHorario.getText().isEmpty() ||
                            comboTurno.getValue() != null) {

                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        Matcher mather = pattern.matcher(txtEmailEmp.getText());

                        if (textDNIEmp.getText().length() != 9 && !isNum(textDNIEmp.getText().substring(0, 9)) && isNum(textDNIEmp.getText().substring(9))) {
                            throw new NoNumericoException("El campo 'DNI' debe contener 9 carácteres (8 numéricos y una letra).");
                        } else if (accion.equals("Añadir") && existeComprobar(textDNIEmp.getText(), "Empleado")) {
                            throw new YaExisteException("El empleado con DNI '" + textDNIEmp.getText() + "' ya se encuentra registrado.");
                        } else if (!isNum(txtTlfEmp.getText().replace("+", "").replace(" ", ""))) {
                            throw new NoNumericoException("El campo 'Tlfn' solo puede contener números y '+'.");
                        } else if (!mather.find()) {
                            throw new FormatoIncorrectoException("Email");
                        } else if (textNomEmp.getText().length() > 50) {
                            throw new MuchoTextoException("Nombre", 50);
                        } else if (txtApEmp.getText().length() > 50) {
                            throw new MuchoTextoException("Apellidos", 50);
                        } else if (txtTlfEmp.getText().length() > 16) {
                            throw new MuchoTextoException("Tlfn", 16);
                        } else if (txtEmailEmp.getText().length() > 50) {
                            throw new MuchoTextoException("Email", 50);
                        } else if (txtCargoEmp.getText().length() > 50) {
                            throw new MuchoTextoException("Cargo", 50);
                        } else if (txtHorario.getText().length() > 20) {
                            throw new MuchoTextoException("Horario", 20);
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