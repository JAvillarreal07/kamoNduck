package Controlador;

import Modelo.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author josea
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private ComboBox<String> menuModulos;
    @FXML
    private Accordion panelFiltros;
    @FXML
    private JFXButton botonLimpiarFiltros, reporteEstancia;

    // Módulo Almacén.
    @FXML
    private JFXTabPane tabsAlmacen;
    @FXML
    private Tab tabProductos, tabProveedores;
    @FXML
    public TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colNomProduc, colTipoProduc, colPorvProduc, colObserProduc;
    @FXML
    private TableColumn<Producto, Integer> colIDProduc, colCantProduc;
    @FXML
    private JFXTextField textFiltroIDProd, textFiltroNombreProd, textFiltroProveedor, textFiltroTipo, textFiltroCantidad;
    @FXML
    public TableView<Proveedor> tablaProveedor;
    @FXML
    private TableColumn<Producto, String> colProvNom, colProvDirec, colProvTel, colProvPais;
    @FXML
    private TableColumn<Producto, Integer> colProvID;
    @FXML
    private JFXTextField textFiltroIDProv, textFiltroNombreProv, textFiltroTelefono, textFiltroPais;
    @FXML
    private Label labelNomProdProv;
    @FXML
    public Text labelAlmacen, miniLabelAlmacen;
    @FXML
    public Line lineaAlamacen;
    @FXML
    private JFXButton botonAnadir, botonModificar, botonEliminar;
    @FXML
    private ImageView imgAlmacen;

    // Módulo Facturación.
    @FXML
    public JFXTabPane tabsFacturacion;
    @FXML
    public Tab tabPatos, tabCliente, tabLago, tabEstancia;
    @FXML
    public TableView<Pato> tablaPatos;
    @FXML
    public TableColumn<Pato, Integer> colIDpato, colEdadPato;
    @FXML
    public TableColumn<Pato, String> colNombrePato, colRazaPato, colNum_cartillaPato, colDescPato;
    @FXML
    private JFXTextField textFiltroIDPato, textFiltroNombrePato, textFiltroRazaPato, textFiltroEdadPato, textFiltroCartillaPato;
    @FXML
    public TableView<Cliente> tablaClientes;
    @FXML
    public TableColumn<Cliente, Integer> colIDCliente;
    @FXML
    public TableColumn<Cliente, String> colDNICliente, colNombreCliente, colApellidosCliente, colTelf1Cliente, colTelef2Cliente, colEmailCliente, colTipoPagoCliente;
    @FXML
    private JFXTextField textFiltroIDCliente, textFiltroNombreCliente, textFiltroApellidosCliente, textFiltroTel1Cliente, textFiltroTel2Cliente, textFiltroEmailCliente, textFiltroTipoPagoCliente;
    @FXML
    public TableView<Lago> tablaLagos;
    @FXML
    public TableColumn<Lago, Integer> colIDLago, colCapLago, colPatosAC, colTarifaPatos;
    @FXML
    public TableColumn<Lago, String> colNombreLago, colTamanoLago;
    @FXML
    public TableView<Estancia> tablaEstancia;
    @FXML
    public TableColumn<Estancia, Integer> colIDEstancia;
    @FXML
    public TableColumn<Estancia, Date> colFechaIngresoEstancia, colFechaSalidaEstancia;
    @FXML
    public TableColumn<Estancia, String> colNombrePatoEstancia, colNombreClienteEstancia, colNombreLagoEstancia;
    @FXML
    private JFXTextField textFiltroIDEstancia, textFiltroIngresoEstancia, textFiltroSalidaEstancia, textFiltroNomPatoEstancia, textFiltroNomClienteEstancia, textFiltroNomLagoEstancia;
    @FXML
    private Label labelNomPato, labelNomCliente, labelNomLago;
    @FXML
    private ImageView imagePato, imageLago, imageCliente;

    // Módulo Empleados.
    @FXML
    public Label labelNoModifiEmpleados, labelNombreEmpleado;
    @FXML
    public JFXListView<CustomListView> listViewEmpleados = new JFXListView<CustomListView>();
    @FXML
    public JFXListView<CustomListView> listViewEmpleadosActivos = new JFXListView<CustomListView>();
    @FXML
    public Rectangle rectanguloEmpleados;
    @FXML
    public Pane tarjetaEmplePanel;
    @FXML
    public ImageView tarjetaEmpleImg, tarjetaEmpleImgLago;
    @FXML
    public Label tarjetaTituloEmple, tarjetaEmpleNom, tarjetaEmpleApe, tarjetaEmpleDNI, tarjetaEmpleTel, tarjetaEmpleEmail, tarjetaEmpleCargo, tarjetaEmpleHorario, tarjetaEmpleTurno, tarjetaEmpleLago;
    @FXML
    public JFXButton botonAnadirEmple, botonModificarEmple, botonEliminarEmple;

    //Instancia de la clase IOBaseDatos.
    private IOBaseDatos IO = new IOBaseDatos();

    //Listas para contener los elementos de los Modelos.
    public ObservableList<Producto> ListaProductos = FXCollections.observableArrayList();
    private ObservableList<Proveedor> ListaProveedor = FXCollections.observableArrayList();
    private ObservableList<Pato> ListaPatos = FXCollections.observableArrayList();
    private ObservableList<Cliente> ListaClientes = FXCollections.observableArrayList();
    private ObservableList<Lago> ListaLagos = FXCollections.observableArrayList();
    private ObservableList<Estancia> ListaEstancias = FXCollections.observableArrayList();
    private ObservableList<Empleado> ListaEmpleados = FXCollections.observableArrayList();

    //Listas para contener los elementos filtrados de las tablas.
    private ObservableList<Producto> listaFiltrosProd = FXCollections.observableArrayList();
    private ObservableList<Proveedor> listaFiltrosProv = FXCollections.observableArrayList();
    private ObservableList<Pato> listaFiltrosPatos = FXCollections.observableArrayList();
    private ObservableList<Cliente> listaFiltrosClientes = FXCollections.observableArrayList();
    private ObservableList<Estancia> listaFiltrosEstancia = FXCollections.observableArrayList();

    //Listas para contener información sobre elementos y usarlos en los filtros.
    private ArrayList<Integer> IDProdFiltros = new ArrayList<Integer>();
    private ArrayList<String> nombresProdFiltros = new ArrayList<String>();
    private ArrayList<String> proveedorProdFiltros = new ArrayList<String>();
    private ArrayList<String> tipoProdFiltros = new ArrayList<String>();
    private ArrayList<Integer> cantidadProdFiltros = new ArrayList<Integer>();

    private ArrayList<Integer> IDProvFiltros = new ArrayList<Integer>();
    private ArrayList<String> nombresProvFiltros = new ArrayList<String>();
    private ArrayList<String> telefonoProvFiltros = new ArrayList<String>();
    private ArrayList<String> paisProvFiltros = new ArrayList<String>();

    private ArrayList<Integer> IDPatoFiltros = new ArrayList<Integer>();
    private ArrayList<String> nombresPatoFiltros = new ArrayList<String>();
    private ArrayList<String> razasPatoFiltros = new ArrayList<String>();
    private ArrayList<Integer> edadPatoFiltros = new ArrayList<Integer>();
    private ArrayList<Integer> cartillasPatoFiltros = new ArrayList<Integer>();

    private ArrayList<Integer> IDClienteFiltros = new ArrayList<Integer>();
    private ArrayList<String> DNIClienteFiltros = new ArrayList<String>();
    private ArrayList<String> nombresClienteFiltros = new ArrayList<String>();
    private ArrayList<String> apellidosClienteFiltros = new ArrayList<String>();
    private ArrayList<String> telefono1ClienteFiltros = new ArrayList<String>();
    private ArrayList<String> telefono2ClienteFiltros = new ArrayList<String>();
    private ArrayList<String> emailClienteFiltros = new ArrayList<String>();
    private ArrayList<String> tipoPagoClienteFiltros = new ArrayList<String>();

    private ArrayList<Integer> IDEstanciaFiltros = new ArrayList<Integer>();
    private ArrayList<Date> fechaIngresoEstanciaFiltros = new ArrayList<Date>();
    private ArrayList<Date> fechaSalidaEstanciaFiltros = new ArrayList<Date>();
    private ArrayList<String> nomPatoEstanciaFiltros = new ArrayList<String>();
    private ArrayList<String> nomClienteEstanciaFiltros = new ArrayList<String>();
    private ArrayList<String> nomLagoEstanciaFiltros = new ArrayList<String>();

    private ObservableList<String> nombresLagosFiltros = FXCollections.observableArrayList();

    private ArrayList<String> cargosExistentes = new ArrayList<String>();

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Lo primero que se ejecutará al iniciar el programa
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciaTablas();
        iniciaTodo();
    }

    public void iniciaTodo() {

        try {
            iniciaRegistros();
            iniciaFiltros();
            iniciaListView();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Rellena las listas con los datos de la BD.
    public void iniciaRegistros() throws SQLException {
        ListaProductos.clear();
        ListaProveedor.clear();
        ListaPatos.clear();
        ListaClientes.clear();
        ListaLagos.clear();
        ListaEstancias.clear();
        ListaEmpleados.clear();

        /******Para Productos******/
        //Consulta Productos.
        ResultSet r1 = IO.introduceRegistros("SELECT * FROM PRODUCTOS");

        while (r1.next()) {

            //Crea el nuevo producto.
            Producto prod = new Producto(r1.getInt("IDProducto"),
                    r1.getString("Nombre_Producto"),
                    r1.getString("Tipo_Producto"),
                    r1.getInt("Cantidad"),
                    r1.getInt("Minimo"),
                    r1.getDouble("precio"),
                    r1.getString("Observaciones"),
                    r1.getInt("IDProveedor"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r1 != null) {

                this.ListaProductos.add(prod);
            }

            this.tablaProductos.setItems(ListaProductos);
            this.tablaProductos.refresh();
        }
        /**************************/

        /******Para Proveedores******/
        //Consulta Proveedores.
        ResultSet r2 = IO.introduceRegistros("SELECT * FROM PROVEEDOR");

        while (r2.next()) {

            //Crea el nuevo proveedor.
            Proveedor prov = new Proveedor(r2.getInt("IDProveedor"),
                    r2.getString("Nombre_Proveedor"),
                    r2.getString("Direccion"),
                    r2.getString("Telefono_Proveedor"),
                    r2.getString("Pais"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r2 != null) {

                this.ListaProveedor.add(prov);
            }

            this.tablaProveedor.setItems(ListaProveedor);
            this.tablaProveedor.refresh();
        }
        /****************************/

        /******Para Patos******/
        //Consulta Patos.
        ResultSet r3 = IO.introduceRegistros("SELECT * FROM PATOS");

        while (r3.next()) {

            //Crea el nuevo pato.
            Pato pat = new Pato(r3.getInt("IDPato"),
                    r3.getString("Nombre_Pato"),
                    r3.getString("Raza"),
                    r3.getInt("Edad"),
                    r3.getInt("Num_Cartilla"),
                    r3.getString("Descripcion"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r3 != null) {

                this.ListaPatos.add(pat);
            }

            this.tablaPatos.setItems(ListaPatos);
            this.tablaPatos.refresh();
        }
        /**********************/

        /******Para Clientes******/
        //Consulta Clientes.
        ResultSet r4 = IO.introduceRegistros("SELECT * FROM CLIENTES");

        while (r4.next()) {

            //Crea el nuevo Cliente.
            Cliente clie = new Cliente(r4.getInt("IDCliente"),
                    r4.getString("DNI"),
                    r4.getString("Nombre_Cliente"),
                    r4.getString("Apellidos_Cliente"),
                    r4.getString("Telefono_Cliente1"),
                    r4.getString("Telefono_Cliente2"),
                    r4.getString("Email_Cliente"),
                    r4.getString("TipoPago"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r4 != null) {

                this.ListaClientes.add(clie);
            }

            this.tablaClientes.setItems(ListaClientes);
            this.tablaClientes.refresh();
        }
        /*************************/

        /******Para Lagos******/
        //Consulta Lagos.
        ResultSet r5 = IO.introduceRegistros("SELECT * FROM LAGOS");

        while (r5.next()) {

            //Crea el nuevo lago.
            Lago lag = new Lago(r5.getInt("IDLago"),
                    r5.getString("Nombre_Lago"),
                    r5.getString("Tamanho"),
                    r5.getInt("Cap_Patos"),
                    r5.getInt("Patos_Dentro"),
                    r5.getInt("Tarifa"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r5 != null) {

                this.ListaLagos.add(lag);
            }

            this.tablaLagos.setItems(ListaLagos);
            this.tablaLagos.refresh();
        }
        /**********************/

        /******Para Estancias******/
        //Consulta Estancia.
        ResultSet r6 = IO.introduceRegistros("SELECT * FROM ESTANCIA");

        while (r6.next()) {

            //Crea la nueva estancia.
            Estancia est = new Estancia(r6.getInt("IDEstancia"),
                    r6.getDate("Fecha_Ingreso"),
                    r6.getDate("Fecha_Salida"),
                    r6.getInt("IDPato"),
                    r6.getInt("IDCliente"),
                    r6.getInt("IDLago"));

            //Los guarda en la lista y los muestra en la tabla.
            if (r6 != null) {

                this.ListaEstancias.add(est);
            }

            this.tablaEstancia.setItems(ListaEstancias);
            this.tablaEstancia.refresh();
        }
        /**************************/

        /******Para Empleados******/
        //Consulta Empleados.
        ResultSet r7 = IO.introduceRegistros("SELECT * FROM EMPLEADOS");

        while (r7.next()) {

            //Crea el nuevo empleado.
            Empleado emple = new Empleado(r7.getInt("IDEmpleado"),
                    r7.getString("DNI_Empleado"),
                    r7.getString("Nombre_Empleado"),
                    r7.getString("Apellidos_Empleado"),
                    r7.getString("Telefono_Empleado"),
                    r7.getString("Email_Empleado"),
                    r7.getString("Cargo"),
                    r7.getString("Horario_Trabajo"),
                    r7.getString("Turno"),
                    r7.getInt("IDLago"));

            //Los guarda en la lista.
            if (r7 != null) {

                this.ListaEmpleados.add(emple);
            }
        }
        /**************************/
    }

    //Indica a cada celda de las tablas que tipo de valor almacenarán.
    public void iniciaTablas() {

        //Columnas Productos.
        this.colIDProduc.setCellValueFactory(new PropertyValueFactory<>("IDProducto"));
        this.colNomProduc.setCellValueFactory(new PropertyValueFactory<>("Nombre_Producto"));
        this.colTipoProduc.setCellValueFactory(new PropertyValueFactory<>("Tipo_Producto"));
        this.colCantProduc.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colObserProduc.setCellValueFactory(new PropertyValueFactory<>("Observaciones"));
        this.colPorvProduc.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));

        //Columnas Proveedor.
        this.colProvID.setCellValueFactory(new PropertyValueFactory<>("IDProveedor"));
        this.colProvNom.setCellValueFactory(new PropertyValueFactory<>("Nombre_Proveedor"));
        this.colProvDirec.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        this.colProvTel.setCellValueFactory(new PropertyValueFactory<>("Telefono_Proveedor"));
        this.colProvPais.setCellValueFactory(new PropertyValueFactory<>("Pais"));

        //Columnas Patos.
        this.colIDpato.setCellValueFactory(new PropertyValueFactory<>("IDPato"));
        this.colNombrePato.setCellValueFactory(new PropertyValueFactory<>("Nombre_Pato"));
        this.colRazaPato.setCellValueFactory(new PropertyValueFactory<>("Raza"));
        this.colEdadPato.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        this.colNum_cartillaPato.setCellValueFactory(new PropertyValueFactory<>("Num_Cartilla"));
        this.colDescPato.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

        //Columnas Clientes.
        this.colIDCliente.setCellValueFactory(new PropertyValueFactory<>("IDCliente"));
        this.colDNICliente.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        this.colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("Nombre_Cliente"));
        this.colApellidosCliente.setCellValueFactory(new PropertyValueFactory<>("Apellidos_Cliente"));
        this.colTelf1Cliente.setCellValueFactory(new PropertyValueFactory<>("Telefono_Cliente1"));
        this.colTelef2Cliente.setCellValueFactory(new PropertyValueFactory<>("Telefono_Cliente2"));
        this.colEmailCliente.setCellValueFactory(new PropertyValueFactory<>("Email_Cliente"));
        this.colTipoPagoCliente.setCellValueFactory(new PropertyValueFactory<>("TipoPago"));

        //Columnas Lagos.
        this.colIDLago.setCellValueFactory(new PropertyValueFactory<>("IDLago"));
        this.colNombreLago.setCellValueFactory(new PropertyValueFactory<>("Nombre_Lago"));
        this.colTamanoLago.setCellValueFactory(new PropertyValueFactory<>("Tamanho"));
        this.colCapLago.setCellValueFactory(new PropertyValueFactory<>("Cap_Patos"));
        this.colPatosAC.setCellValueFactory(new PropertyValueFactory<>("barra"));
        this.colTarifaPatos.setCellValueFactory(new PropertyValueFactory<>("Tarifa"));

        //Columnas Estancia.
        this.colIDEstancia.setCellValueFactory(new PropertyValueFactory<>("IDEstancia"));
        this.colFechaIngresoEstancia.setCellValueFactory(new PropertyValueFactory<>("Fecha_Ingreso"));
        this.colFechaSalidaEstancia.setCellValueFactory(new PropertyValueFactory<>("Fecha_Salida"));
        this.colNombrePatoEstancia.setCellValueFactory(new PropertyValueFactory<>("nombrePato"));
        this.colNombreClienteEstancia.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        this.colNombreLagoEstancia.setCellValueFactory(new PropertyValueFactory<>("nombreLago"));
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Prepara los filtros para buscar en las tablas.
    public void iniciaFiltros() {

        //Almacena los datos de cada producto en las Arrays impidiendo duplicados.
        for (int i = 0; i < ListaProductos.size(); i++) {

            IDProdFiltros.add(ListaProductos.get(i).getIDProducto());

            if (!nombresProdFiltros.contains(ListaProductos.get(i).getNombre_Producto())) {
                nombresProdFiltros.add(ListaProductos.get(i).getNombre_Producto());
            }

            if (!proveedorProdFiltros.contains(ListaProductos.get(i).getNombreProveedor())) {
                proveedorProdFiltros.add(ListaProductos.get(i).getNombreProveedor());
            }

            if (!tipoProdFiltros.contains(ListaProductos.get(i).getTipo_Producto())) {
                tipoProdFiltros.add(ListaProductos.get(i).getTipo_Producto());
            }

            if (!cantidadProdFiltros.contains(ListaProductos.get(i).getCantidad())) {
                cantidadProdFiltros.add(ListaProductos.get(i).getCantidad());
            }
        }

        //Almacena los datos de cada proveedor en las Arrays impidiendo duplicados.
        for (int i = 0; i < ListaProveedor.size(); i++) {

            IDProvFiltros.add(ListaProveedor.get(i).getIDProveedor());

            if (!nombresProvFiltros.contains(ListaProveedor.get(i).getNombre_Proveedor())) {
                nombresProvFiltros.add(ListaProveedor.get(i).getNombre_Proveedor());
            }

            if (!telefonoProvFiltros.contains(ListaProveedor.get(i).getTelefono_Proveedor())) {
                telefonoProvFiltros.add(ListaProveedor.get(i).getTelefono_Proveedor());
            }

            if (!paisProvFiltros.contains(ListaProveedor.get(i).getPais())) {
                paisProvFiltros.add(ListaProveedor.get(i).getPais());
            }
        }

        //Almacena los datos de cada pato en las Arrays impidiendo duplicados.
        for (int i = 0; i < ListaPatos.size(); i++) {

            IDPatoFiltros.add(ListaPatos.get(i).getIDPato());

            if (!nombresPatoFiltros.contains(ListaPatos.get(i).getNombre_Pato())) {
                nombresPatoFiltros.add(ListaPatos.get(i).getNombre_Pato());
            }

            if (!razasPatoFiltros.contains(ListaPatos.get(i).getRaza())) {
                razasPatoFiltros.add(ListaPatos.get(i).getRaza());
            }

            if (!edadPatoFiltros.contains(ListaPatos.get(i).getEdad())) {
                edadPatoFiltros.add(ListaPatos.get(i).getEdad());
            }

            cartillasPatoFiltros.add(ListaPatos.get(i).getNum_Cartilla());
        }

        //Almacena los datos de cada cliente en las Arrays impidiendo duplicados.
        for (int i = 0; i < ListaClientes.size(); i++) {

            IDClienteFiltros.add(ListaClientes.get(i).getIDCliente());

            DNIClienteFiltros.add(ListaClientes.get(i).getDNI());

            if (!nombresClienteFiltros.contains(ListaClientes.get(i).getNombre_Cliente())) {
                nombresClienteFiltros.add(ListaClientes.get(i).getNombre_Cliente());
            }

            if (!apellidosClienteFiltros.contains(ListaClientes.get(i).getApellidos_Cliente())) {
                apellidosClienteFiltros.add(ListaClientes.get(i).getApellidos_Cliente());
            }

            if (!telefono1ClienteFiltros.contains(ListaClientes.get(i).getTelefono_Cliente1())) {
                telefono1ClienteFiltros.add(ListaClientes.get(i).getTelefono_Cliente1());
            }

            if (!telefono2ClienteFiltros.contains(ListaClientes.get(i).getTelefono_Cliente2())) {
                telefono2ClienteFiltros.add(ListaClientes.get(i).getTelefono_Cliente2());
            }

            if (!tipoPagoClienteFiltros.contains(ListaClientes.get(i).getTipoPago())) {
                tipoPagoClienteFiltros.add(ListaClientes.get(i).getTipoPago());
            }

            emailClienteFiltros.add(ListaClientes.get(i).getEmail_Cliente());
        }

        //Almacena los datos de cada estancia en las Arrays impidiendo duplicados.
        for (int i = 0; i < ListaEstancias.size(); i++) {

            IDEstanciaFiltros.add(ListaEstancias.get(i).getIDEstancia());

            if (!fechaIngresoEstanciaFiltros.contains(ListaEstancias.get(i).getFecha_Ingreso())) {
                fechaIngresoEstanciaFiltros.add(ListaEstancias.get(i).getFecha_Ingreso());
            }

            if (!fechaSalidaEstanciaFiltros.contains(ListaEstancias.get(i).getFecha_Salida())) {
                fechaSalidaEstanciaFiltros.add(ListaEstancias.get(i).getFecha_Salida());
            }

            if (!nomPatoEstanciaFiltros.contains(ListaEstancias.get(i).getNombrePato())) {
                nomPatoEstanciaFiltros.add(ListaEstancias.get(i).getNombrePato());
            }

            if (!nomClienteEstanciaFiltros.contains(ListaEstancias.get(i).getNombreCliente())) {
                nomClienteEstanciaFiltros.add(ListaEstancias.get(i).getNombreCliente());
            }

            if (!nomLagoEstanciaFiltros.contains(ListaEstancias.get(i).getNombreLago())) {
                nomLagoEstanciaFiltros.add(ListaEstancias.get(i).getNombreLago());
            }
        }

        //Almacena los nombres de cada lago.
        for (int i = 0; i < ListaLagos.size(); i++) {
            nombresLagosFiltros.add(ListaLagos.get(i).getNombre_Lago());
        }

        //Almacena los cargos existentes.
        for (int i = 0; i < ListaEmpleados.size(); i++) {
            if (!cargosExistentes.contains(ListaEmpleados.get(i).getCargo())) {
                cargosExistentes.add(ListaEmpleados.get(i).getCargo());
            }
        }

        //Introduce los elementos para autocompletar en los elementos respectivos.
        /*---Productos-----------------------------------------------------------*/
        TextFields.bindAutoCompletion(textFiltroIDProd, IDProdFiltros);
        TextFields.bindAutoCompletion(textFiltroNombreProd, nombresProdFiltros);
        TextFields.bindAutoCompletion(textFiltroProveedor, proveedorProdFiltros);
        TextFields.bindAutoCompletion(textFiltroTipo, tipoProdFiltros);
        TextFields.bindAutoCompletion(textFiltroCantidad, cantidadProdFiltros);
        /*-----------------------------------------------------------------------*/

        /*---Proveedores--------------------------------------------------------*/
        TextFields.bindAutoCompletion(textFiltroIDProv, IDProvFiltros);
        TextFields.bindAutoCompletion(textFiltroNombreProv, nombresProvFiltros);
        TextFields.bindAutoCompletion(textFiltroTelefono, telefonoProvFiltros);
        TextFields.bindAutoCompletion(textFiltroPais, paisProvFiltros);
        /*----------------------------------------------------------------------*/

        /*---Patos------------------------------------------------------------------*/
        TextFields.bindAutoCompletion(textFiltroIDPato, IDPatoFiltros);
        TextFields.bindAutoCompletion(textFiltroNombrePato, nombresPatoFiltros);
        TextFields.bindAutoCompletion(textFiltroRazaPato, razasPatoFiltros);
        TextFields.bindAutoCompletion(textFiltroEdadPato, edadPatoFiltros);
        TextFields.bindAutoCompletion(textFiltroCartillaPato, cartillasPatoFiltros);
        /*--------------------------------------------------------------------------*/

        /*---Clientes----------------------------------------------------------------------*/
        TextFields.bindAutoCompletion(textFiltroIDCliente, IDClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroNombreCliente, nombresClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroApellidosCliente, apellidosClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroTel1Cliente, telefono1ClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroTel2Cliente, telefono2ClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroEmailCliente, emailClienteFiltros);
        TextFields.bindAutoCompletion(textFiltroTipoPagoCliente, tipoPagoClienteFiltros);
        /*----------------------------------------------------------------------------------*/

        /*---Estancias-------------------------------------------------------------------------*/
        TextFields.bindAutoCompletion(textFiltroIDEstancia, IDEstanciaFiltros);
        TextFields.bindAutoCompletion(textFiltroIngresoEstancia, fechaIngresoEstanciaFiltros);
        TextFields.bindAutoCompletion(textFiltroSalidaEstancia, fechaSalidaEstanciaFiltros);
        TextFields.bindAutoCompletion(textFiltroNomPatoEstancia, nomPatoEstanciaFiltros);
        TextFields.bindAutoCompletion(textFiltroNomClienteEstancia, nomClienteEstanciaFiltros);
        TextFields.bindAutoCompletion(textFiltroNomLagoEstancia, nomLagoEstanciaFiltros);
        /*-------------------------------------------------------------------------------------*/

        //Establece Listener a cada TextField destinado a funcionar como un filtrador.
        /*Filtros Productos*/
        textFiltroIDProd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNombreProd.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroProveedor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroTipo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroCantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });
        /*******************/

        /*Filtros Proveedor*/
        textFiltroIDProv.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNombreProv.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroTelefono.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroPais.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });
        /*******************/

        /*Filtros Pato*/
        textFiltroIDPato.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNombrePato.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroRazaPato.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroEdadPato.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroCartillaPato.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });
        /**************/

        /*Filtros Cliente*/
        textFiltroIDCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNombreCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroApellidosCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroTel1Cliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroTel2Cliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroEmailCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroTipoPagoCliente.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });
        /*****************/

        /*Filtros Estancia*/
        textFiltroIDEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroIngresoEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroSalidaEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNomPatoEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNomClienteEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });

        textFiltroNomLagoEstancia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filtrar();
            }
        });
        /******************/
    }

    //Se encarga de filtrar las tablas con los datos escritos en los TextFields.
    public void filtrar() {
        try {
            if (tabsAlmacen.isVisible()) {

                if (tabProductos.isSelected()) {
                    listaFiltrosProd.clear();

                    for (int i = 0; i < ListaProductos.size(); i++) {

                        if (String.valueOf(ListaProductos.get(i).getIDProducto()).contains(textFiltroIDProd.getText()) &&
                                ListaProductos.get(i).getNombre_Producto().toLowerCase().contains(textFiltroNombreProd.getText().toLowerCase()) &&
                                ListaProductos.get(i).getTipo_Producto().toLowerCase().contains(textFiltroTipo.getText().toLowerCase()) &&
                                String.valueOf(ListaProductos.get(i).getCantidad()).contains(textFiltroCantidad.getText()) &&
                                ListaProductos.get(i).getNombreProveedor().toLowerCase().contains(textFiltroProveedor.getText().toLowerCase())
                        ) {
                            listaFiltrosProd.add(ListaProductos.get(i));
                        }
                    }

                    tablaProductos.setItems(listaFiltrosProd);

                } else if (tabProveedores.isSelected()) {
                    listaFiltrosProv.clear();

                    for (int i = 0; i < ListaProveedor.size(); i++) {

                        if (String.valueOf(ListaProveedor.get(i).getIDProveedor()).contains(textFiltroIDProv.getText()) &&
                                ListaProveedor.get(i).getNombre_Proveedor().toLowerCase().contains(textFiltroNombreProv.getText().toLowerCase()) &&
                                ListaProveedor.get(i).getTelefono_Proveedor().toLowerCase().contains(textFiltroTelefono.getText().toLowerCase()) &&
                                ListaProveedor.get(i).getPais().toLowerCase().contains(textFiltroPais.getText().toLowerCase())
                        ) {
                            listaFiltrosProv.add(ListaProveedor.get(i));
                        }
                    }

                    tablaProveedor.setItems(listaFiltrosProv);
                }

            } else if (tabsFacturacion.isVisible()) {

                if (tabPatos.isSelected()) {
                    listaFiltrosPatos.clear();

                    for (int i = 0; i < ListaPatos.size(); i++) {

                        if (String.valueOf(ListaPatos.get(i).getIDPato()).contains(textFiltroIDPato.getText()) &&
                                ListaPatos.get(i).getNombre_Pato().toLowerCase().contains(textFiltroNombrePato.getText().toLowerCase()) &&
                                ListaPatos.get(i).getRaza().toLowerCase().contains(textFiltroRazaPato.getText().toLowerCase()) &&
                                String.valueOf(ListaPatos.get(i).getEdad()).contains(textFiltroEdadPato.getText().toLowerCase()) &&
                                String.valueOf(ListaPatos.get(i).getNum_Cartilla()).contains(textFiltroCartillaPato.getText().toLowerCase())
                        ) {
                            listaFiltrosPatos.add(ListaPatos.get(i));
                        }
                    }

                    tablaPatos.setItems(listaFiltrosPatos);

                } else if (tabCliente.isSelected()) {
                    listaFiltrosClientes.clear();

                    for (int i = 0; i < ListaClientes.size(); i++) {

                        if (String.valueOf(ListaClientes.get(i).getIDCliente()).contains(textFiltroIDCliente.getText()) &&
                                ListaClientes.get(i).getNombre_Cliente().toLowerCase().contains(textFiltroNombreCliente.getText().toLowerCase()) &&
                                ListaClientes.get(i).getApellidos_Cliente().toLowerCase().contains(textFiltroApellidosCliente.getText().toLowerCase()) &&
                                ListaClientes.get(i).getTelefono_Cliente1().contains(textFiltroTel1Cliente.getText().toLowerCase()) &&
                                ListaClientes.get(i).getTelefono_Cliente2().contains(textFiltroTel2Cliente.getText().toLowerCase()) &&
                                ListaClientes.get(i).getEmail_Cliente().contains(textFiltroEmailCliente.getText().toLowerCase()) &&
                                ListaClientes.get(i).getTipoPago().contains(textFiltroTipoPagoCliente.getText().toLowerCase())
                        ) {
                            listaFiltrosClientes.add(ListaClientes.get(i));
                        }
                    }

                    tablaClientes.setItems(listaFiltrosClientes);

                } else if (tabEstancia.isSelected()) {
                    listaFiltrosEstancia.clear();

                    for (int i = 0; i < ListaEstancias.size(); i++) {

                        if (String.valueOf(ListaEstancias.get(i).getIDEstancia()).contains(textFiltroIDEstancia.getText()) &&
                                String.valueOf(ListaEstancias.get(i).getFecha_Ingreso()).toLowerCase().contains(textFiltroIngresoEstancia.getText().toLowerCase()) &&
                                String.valueOf(ListaEstancias.get(i).getFecha_Salida()).toLowerCase().contains(textFiltroSalidaEstancia.getText().toLowerCase()) &&
                                ListaEstancias.get(i).getNombrePato().contains(textFiltroNomPatoEstancia.getText().toLowerCase()) &&
                                ListaEstancias.get(i).getNombreCliente().contains(textFiltroNomClienteEstancia.getText().toLowerCase()) &&
                                ListaEstancias.get(i).getNombreLago().contains(textFiltroNomLagoEstancia.getText().toLowerCase())
                        ) {
                            listaFiltrosEstancia.add(ListaEstancias.get(i));
                        }
                    }

                    tablaEstancia.setItems(listaFiltrosEstancia);
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Rellena las ListViews con los respectivos Empleados.
    public void iniciaListView() {

        //Listas para almacenar los elementos de las ListViews.
        List<CustomListView> list1 = new ArrayList<>();
        List<CustomListView> list2 = new ArrayList<>();

        Image iconEmple; //Icono para los Emepleados.

        //Recorre la lista de empleados.
        for (int i = 0; i < ListaEmpleados.size(); i++) {

            //Establece la imegen del Empleado.
            iconEmple = new Image(new File("ImgEmpleados/" + ListaEmpleados.get(i).getNombreCompleto().replace(" ", "_") + ".png").toURI().toString());

            //Si no tiene una imagen assignada se le pone una por defecto.
            if (iconEmple.getHeight() == 0.0) {
                iconEmple = new Image("/ImgEmpleados/Null.png");
            }

            //Añade al Empleado a la lista 1.
            list1.add(new CustomListView(iconEmple, ListaEmpleados.get(i).getNombre_Empleado(), ListaEmpleados.get(i).getApellidos_Empleado(), ListaEmpleados.get(i).getCargo()));

            //Si el eEmpleado trabaja hoy se añadirá también a la lista 2.
            if (trabajaHoy(ListaEmpleados.get(i).getHorario_Trabajo())) {
                list2.add(new CustomListView(iconEmple, ListaEmpleados.get(i).getNombre_Empleado(), ListaEmpleados.get(i).getApellidos_Empleado(), ListaEmpleados.get(i).getCargo()));
            }
        }

        //Transforma las Listas en ObservableList.
        ObservableList<CustomListView> myObservableList1 = FXCollections.observableList(list1);
        ObservableList<CustomListView> myObservableList2 = FXCollections.observableList(list2);

        //Mete los elementos de las listas en las ListViews.
        listViewEmpleados.setItems(myObservableList1);
        listViewEmpleadosActivos.setItems(myObservableList2);
        listViewEmpleadosActivos.setFixedCellSize(60); //Establece el tamaño máximo de las celdas de las ListViews.

        //Cambia el fondo de los empleados de la lista 2 según su Turno.
        for (int i = 0; i < listViewEmpleadosActivos.getItems().size(); i++) {

            String nombre = listViewEmpleadosActivos.getItems().get(i).getNombreCompleto();

            for (int j = 0; j < ListaEmpleados.size(); j++) {

                if (ListaEmpleados.get(j).getNombreCompleto().equals(nombre)) {

                    switch (ListaEmpleados.get(j).getTurno()) {
                        case "Diurno":
                            listViewEmpleadosActivos.getItems().get(i).getStyleClass().add("empleadoDia");
                            break;

                        case "Nocturno":
                            listViewEmpleadosActivos.getItems().get(i).getStyleClass().add("empleadoNoche");
                            break;

                        case "Partido":
                            listViewEmpleadosActivos.getItems().get(i).getStyleClass().add("empleadoPartido");
                            break;
                    }
                    break;
                }
            }
        }
    }

    //Mustra los datos del Empleado seleccionado en las ListViews en sus respectivos elementos.
    public void seleccionarEmpleado() {

        JFXListView<CustomListView> seleccionada = null; //Almacenará al empleado seleccionado.

        //Controla la ListView en la que se haya clicado.
        if (listViewEmpleados.isFocused()) {
            seleccionada = listViewEmpleados;

        } else if (listViewEmpleadosActivos.isFocused()) {
            seleccionada = listViewEmpleadosActivos;
        }

        //Recorre la lista de Empleados para encontrar el seleccionado y mostrar sus datos.
        for (int i = 0; i < ListaEmpleados.size(); i++) {

            if (ListaEmpleados.get(i).getNombreCompleto().equals(seleccionada.getSelectionModel().getSelectedItem().getNombreCompleto())) {
                try {

                    tarjetaEmpleNom.setText(ListaEmpleados.get(i).getNombre_Empleado());
                    tarjetaEmpleApe.setText(ListaEmpleados.get(i).getApellidos_Empleado());
                    tarjetaEmpleDNI.setText(ListaEmpleados.get(i).getDNI_Empleado());
                    tarjetaEmpleTel.setText(ListaEmpleados.get(i).getTelefono_Empleado());
                    tarjetaEmpleEmail.setText(ListaEmpleados.get(i).getEmail_Empleado());
                    tarjetaEmpleCargo.setText(ListaEmpleados.get(i).getCargo());
                    tarjetaEmpleHorario.setText(ListaEmpleados.get(i).getHorario_Trabajo());
                    tarjetaEmpleTurno.setText(ListaEmpleados.get(i).getTurno());
                    tarjetaEmpleLago.setText(ListaEmpleados.get(i).getNombreLago());
                    tarjetaEmpleImgLago.setImage(new Image(new File("ImgLagos/" + ListaEmpleados.get(i).getNombreLago().replace(" ", "_") + ".png").toURI().toString()));
                    tarjetaEmpleImg.setImage(new Image(new File("ImgEmpleados/" + seleccionada.getSelectionModel().getSelectedItem().getNombreCompleto().replace(" ", "_") + ".png").toURI().toString()));
                    break;

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Controla las acciones que realizará cada botón de Añadir, Modificar y Eliminar.
    public void accionesCRUD() throws SQLException, IOException, ParseException {
        if (botonAnadir.isFocused()) {

            if (tabLago.isSelected()) return;

            FXMLLoader loader = null;
            String tituoVentana = null;

            //Según el módulo y la pestaña seleccionada, se abrirá una ventana u otra.
            switch (menuModulos.getValue()) {
                case "Almacén":
                    if (tabProductos.isSelected()) {
                        loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposProductos.fxml"));
                        tituoVentana = "Añadir Producto";
                    } else if (tabProveedores.isSelected()) {
                        loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposProveedores.fxml"));
                        tituoVentana = "Añadir Proveedor";
                    }
                    break;

                case "Facturación":
                    if (tabPatos.isSelected()) {
                        loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposPatos.fxml"));
                        tituoVentana = "Añadir Pato";
                    } else if (tabCliente.isSelected()) {
                        loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposCliente.fxml"));
                        tituoVentana = "Añadir Cliente";
                    } else if (tabEstancia.isSelected()) {
                        loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposEstancia.fxml"));
                        tituoVentana = "Añadir Estancia";
                    }
                    break;
            }

            Parent root = loader.load();

            VentanaCamposController controlador = loader.getController();

            //Llama a métodos para establecer el autocompletado de los campos en la ventanas.
            if (menuModulos.getValue().equals("Almacén") && tabProductos.isSelected()) {
                controlador.iniciaFieldProv(nombresProvFiltros);

            } else if (menuModulos.getValue().equals("Facturación") && tabEstancia.isSelected()) {
                controlador.iniciaFieldEstancia(DNIClienteFiltros, cartillasPatoFiltros, nombresLagosFiltros);
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
            stage.setTitle(tituoVentana);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else if (botonAnadirEmple.isFocused()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposEmpleados.fxml"));
            Parent root = loader.load();

            VentanaCamposController controlador = loader.getController();

            controlador.iniciaFieldEmpleados(cargosExistentes, nombresLagosFiltros);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
            stage.setTitle("Añadir Empleado");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else if (botonEliminar.isFocused() || botonEliminarEmple.isFocused()) {

            String tabla = null, columnas = null;

            //Según el módulo y la pestaña seleccionada, se eliminará un elemento u otra.
            if (tabsAlmacen.isVisible() && tabProductos.isSelected()) {
                ObservableList<Producto> producto;
                producto = tablaProductos.getSelectionModel().getSelectedItems();
                tabla = "PRODUCTOS";
                columnas = "IDProducto = " + producto.get(0).getIDProducto();

            } else if (tabsAlmacen.isVisible() && tabProveedores.isSelected()) {
                ObservableList<Proveedor> proveedor;
                proveedor = tablaProveedor.getSelectionModel().getSelectedItems();
                tabla = "PROVEEDOR";
                columnas = "IDProveedor = " + proveedor.get(0).getIDProveedor();

            } else if (tarjetaEmplePanel.isVisible()) {
                tabla = "EMPLEADOS";
                columnas = "Nombre_Empleado = '" + tarjetaEmpleNom.getText() + "' AND Apellidos_Empleado = '" + tarjetaEmpleApe.getText() + "'";

            } else if (tabsFacturacion.isVisible() && tabPatos.isSelected()) {
                ObservableList<Pato> pato;
                pato = tablaPatos.getSelectionModel().getSelectedItems();
                tabla = "PATOS";
                columnas = "IDPato = " + pato.get(0).getIDPato();

            } else if (tabsFacturacion.isVisible() && tabCliente.isSelected()) {
                ObservableList<Cliente> cliente;
                cliente = tablaClientes.getSelectionModel().getSelectedItems();
                tabla = "CLIENTES";
                columnas = "IDCliente = " + cliente.get(0).getIDCliente();

            } else if (tabsFacturacion.isVisible() && tabEstancia.isSelected()) {
                ObservableList<Estancia> estancia;
                estancia = tablaEstancia.getSelectionModel().getSelectedItems();
                tabla = "ESTANCIA";
                columnas = "IDEstancia = " + estancia.get(0).getIDEstancia();
            }

            //Realiza la consulta para eliminar.
            this.IO.actualizaRegistros("DELETE FROM " + tabla + " WHERE " + columnas);

        } else if (botonModificar.isFocused()) {

            FXMLLoader loader = null;
            String tituoVentana = null;

            //Según el módulo y la pestaña seleccionada, se abrirá una ventana u otra.
            if (menuModulos.getValue().equals("Almacén") && tabProductos.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposProductos.fxml"));
                tituoVentana = "Modificar Producto";

            } else if (menuModulos.getValue().equals("Almacén") && tabProveedores.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposProveedores.fxml"));
                tituoVentana = "Modificar Proveedor";

            } else if (menuModulos.getValue().equals("Facturación") && tabPatos.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposPatos.fxml"));
                tituoVentana = "Modificar Pato";

            } else if (menuModulos.getValue().equals("Facturación") && tabCliente.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposCliente.fxml"));
                tituoVentana = "Modificar Cliente";

            } else if (menuModulos.getValue().equals("Facturación") && tabEstancia.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposEstancia.fxml"));
                tituoVentana = "Modificar Estancia";
            }

            Parent root = loader.load();

            VentanaCamposController controlador = loader.getController();

            //Llama a métodos para establecer el autocompletado de los campos en la ventanas
            // e rellenar los campos con los datos del elemento a modificar.
            if (menuModulos.getValue().equals("Almacén") && tabProductos.isSelected()) {
                controlador.iniciarCampos(tablaProductos.getSelectionModel().getSelectedItem());
                controlador.iniciaFieldProv(nombresProvFiltros);

            } else if (menuModulos.getValue().equals("Almacén") && tabProveedores.isSelected()) {
                controlador.iniciarCampos(tablaProveedor.getSelectionModel().getSelectedItem());

            } else if (menuModulos.getValue().equals("Facturación") && tabPatos.isSelected()) {
                controlador.iniciarCampos(tablaPatos.getSelectionModel().getSelectedItem());

            } else if (menuModulos.getValue().equals("Facturación") && tabCliente.isSelected()) {
                controlador.iniciarCampos(tablaClientes.getSelectionModel().getSelectedItem());

            } else if (menuModulos.getValue().equals("Facturación") && tabEstancia.isSelected()) {
                controlador.iniciarCampos(tablaEstancia.getSelectionModel().getSelectedItem());
                controlador.iniciaFieldEstancia(DNIClienteFiltros, cartillasPatoFiltros, nombresLagosFiltros);
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
            stage.setTitle(tituoVentana);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else if (botonModificarEmple.isFocused()) {
            JFXListView<CustomListView> seleccionada = null; //Almacenará al empleado seleccionado.

            //Controla la ListView en la que se haya clicado.
            if (listViewEmpleados.isFocused()) {
                seleccionada = listViewEmpleados;

            } else if (listViewEmpleadosActivos.isFocused()) {
                seleccionada = listViewEmpleadosActivos;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCamposEmpleados.fxml"));
            Parent root = loader.load();

            VentanaCamposController controlador = loader.getController();

            controlador.iniciaFieldEmpleados(cargosExistentes, nombresLagosFiltros);

            //Recorre la lista de Empleados para encontrar el seleccionado y mostrar sus datos.
            for (int i = 0; i < ListaEmpleados.size(); i++) {

                if (ListaEmpleados.get(i).getNombreCompleto().equals(tarjetaEmpleNom.getText() + " " + tarjetaEmpleApe.getText())) {
                    try {

                        Empleado encontrado = ListaEmpleados.get(i);
                        controlador.iniciarCampos(encontrado);
                        break;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
            stage.setTitle("Modificar Empleado");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else if (botonLimpiarFiltros.isFocused()){
            if (tabsAlmacen.isVisible()){
                if (tabProductos.isSelected()){
                    textFiltroIDProd.clear();
                    textFiltroNombreProd.clear();
                    textFiltroTipo.clear();
                    textFiltroCantidad.clear();
                    textFiltroProveedor.clear();

                } else if (tabProveedores.isSelected()){
                    textFiltroIDProv.clear();
                    textFiltroNombreProv.clear();
                    textFiltroTelefono.clear();
                    textFiltroPais.clear();
                }

            } else if (tabsFacturacion.isVisible()){
                if (tabPatos.isSelected()){
                    textFiltroIDPato.clear();
                    textFiltroNombrePato.clear();
                    textFiltroRazaPato.clear();
                    textFiltroEdadPato.clear();
                    textFiltroCartillaPato.clear();

                } else if (tabCliente.isSelected()){
                    textFiltroIDCliente.clear();
                    textFiltroNombreCliente.clear();
                    textFiltroApellidosCliente.clear();
                    textFiltroTel1Cliente.clear();
                    textFiltroTel2Cliente.clear();
                    textFiltroEmailCliente.clear();
                    textFiltroTipoPagoCliente.clear();

                } else if (tabEstancia.isSelected()){
                    textFiltroIDEstancia.clear();
                    textFiltroIngresoEstancia.clear();
                    textFiltroSalidaEstancia.clear();
                    textFiltroNomPatoEstancia.clear();
                    textFiltroNomClienteEstancia.clear();
                    textFiltroNomLagoEstancia.clear();
                }
            }

        }else if (reporteEstancia.isFocused()){
            //Almacena la estancia seleccionada en la tabla.
            ObservableList<Estancia> Seleccionado;
            Seleccionado = (ObservableList<Estancia>) tablaEstancia.getSelectionModel().getSelectedItems();

            //HashMap para guarda los parámetros que usará el reporte.
            HashMap<String, Object> parametros = new HashMap<String, Object>();

            //Inserta los parámetros.
            parametros.put("ParIDUsuario", Seleccionado.get(0).getIDCliente());
            parametros.put("ParFechaIng",Seleccionado.get(0).getFecha_Ingreso());

            JasperReport reporte;

            String archivo = "cashbill_chome_A4.jasper";

            try {
                //Crea el reporte y lo muestra.
                reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);

                JasperPrint print = JasperFillManager.fillReport(reporte, parametros, IO.conexion() );
                JasperExportManager.exportReportToPdfFile(print,"Factura-" + java.time.LocalDate.now() + "-" + Seleccionado.get(0).getNombreCliente() +".pdf");

                JasperViewer.viewReport(print, false);

            } catch (JRException jRException) {
                System.out.println(jRException.getMessage());
            }
        }

        iniciaTodo(); //Reinicia los datos del programa.
    }

    //Método para crear un reporte de pedido en caso de que la cantidad de existencias del producdo
    // sea menor que el mínimo que debería haber.
    public void reportePedirProd(int ID, String nomProducto){

        //HashMap para guarda los parámetros que usará el reporte.
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        //Inserta los parámetros.
        parametros.put("ParIDProducto",ID);

        JasperReport reporte;

        String archivo = "Orden de Compra Kamonduck.jasper";

        try {
            //Crea el reporte y lo muestra.
            reporte = (JasperReport) JRLoader.loadObjectFromFile(archivo);

            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, IO.conexion() );
            JasperExportManager.exportReportToPdfFile(print,"Orden Compra-" + java.time.LocalDate.now() + "-" + nomProducto + ".pdf");

            JasperViewer.viewReport(print, false);

        } catch (JRException jRException) {
            System.out.println(jRException.getMessage());
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Establece ene el ComboBox el módulo elegido en la ventana anterior.
    public void moduloElegido(String boton) {
        this.menuModulos.setValue(boton);
    }

    //Esconde o muestra elementos según se cambie de módulos.
    public void cambiaModulo() throws IOException {
        switch (menuModulos.getValue()) {
            case "Almacén":
                tabsAlmacen.setVisible(true);
                panelFiltros.setVisible(true);
                labelAlmacen.setVisible(true);
                miniLabelAlmacen.setVisible(true);
                lineaAlamacen.setVisible(true);
                labelNomProdProv.setVisible(true);
                imgAlmacen.setVisible(true);
                botonModificar.setVisible(true);
                botonEliminar.setVisible(true);
                botonAnadir.setVisible(true);

                tabsFacturacion.setVisible(false);

                labelNoModifiEmpleados.setVisible(false);
                labelNombreEmpleado.setVisible(false);
                listViewEmpleados.setVisible(false);
                listViewEmpleadosActivos.setVisible(false);
                rectanguloEmpleados.setVisible(false);
                tarjetaTituloEmple.setVisible(false);
                tarjetaEmplePanel.setVisible(false);

                reporteEstancia.setVisible(false);

                escuchaTabs();
                break;

            case "Facturación":
                tabsAlmacen.setVisible(false);
                panelFiltros.setVisible(true);
                labelAlmacen.setVisible(true);
                miniLabelAlmacen.setVisible(true);
                lineaAlamacen.setVisible(true);
                labelNomProdProv.setVisible(true);
                imgAlmacen.setVisible(true);
                botonModificar.setVisible(true);
                botonEliminar.setVisible(true);
                botonAnadir.setVisible(true);

                tabsFacturacion.setVisible(true);

                labelNoModifiEmpleados.setVisible(false);
                labelNombreEmpleado.setVisible(false);
                listViewEmpleados.setVisible(false);
                listViewEmpleadosActivos.setVisible(false);
                rectanguloEmpleados.setVisible(false);
                tarjetaTituloEmple.setVisible(false);
                tarjetaEmplePanel.setVisible(false);

                escuchaTabs();
                break;

            case "Empleados":
                tabsAlmacen.setVisible(false);
                panelFiltros.setVisible(false);
                labelAlmacen.setVisible(false);
                miniLabelAlmacen.setVisible(false);
                lineaAlamacen.setVisible(false);
                labelNomProdProv.setVisible(false);
                imgAlmacen.setVisible(false);
                botonModificar.setVisible(false);
                botonEliminar.setVisible(false);
                botonAnadir.setVisible(false);

                tabsFacturacion.setVisible(false);

                labelNoModifiEmpleados.setVisible(true);
                labelNombreEmpleado.setVisible(true);
                listViewEmpleados.setVisible(true);
                listViewEmpleadosActivos.setVisible(true);
                rectanguloEmpleados.setVisible(true);
                tarjetaTituloEmple.setVisible(true);
                tarjetaEmplePanel.setVisible(true);

                labelNomPato.setVisible(false);
                labelNomCliente.setVisible(false);
                labelNomLago.setVisible(false);

                imagePato.setVisible(false);
                imageLago.setVisible(false);
                imageCliente.setVisible(false);

                reporteEstancia.setVisible(false);
                break;

            case "Atrás":
                volverABienvenida();
                break;
        }
    }

    //Cierra la ventana actual (Ventana Principal) y vuelve a la anterior (Ventana bienvenida).
    public void volverABienvenida() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaBienvenida.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.getIcons().add(new Image("/Imagenes/iconoSolo.png"));
        stage.setTitle("Empresa kamoNduck");

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.botonModificar.getScene().getWindow();
        myStage.close();
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Se encarga de mostrar la información de los elemenotos seleccionados en las tablas.
    public void escuchaTablas() throws SQLException {

        if (tablaProductos.isFocused()) {
            //Almacena el producto seleccionado.
            ObservableList<Producto> Seleccionado;
            Seleccionado = (ObservableList<Producto>) tablaProductos.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Producto());

            //Muetra su imagen asignada en la ImageView.
            imgAlmacen.setImage(new Image(new File("ImgProductos/" + Seleccionado.get(0).getNombre_Producto().replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone una imagen por defecto.
            if (imgAlmacen.getImage().isError()) {
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            }

        } else if (tablaProveedor.isFocused()) {
            //Almacena al proveedor seleccionado.
            ObservableList<Proveedor> Seleccionado;
            Seleccionado = (ObservableList<Proveedor>) tablaProveedor.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Proveedor());

            //Muetra su imagen asignada en la ImageView.
            imgAlmacen.setImage(new Image(new File("ImgProveedores/" + Seleccionado.get(0).getNombre_Proveedor().replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone una imagen por defecto.
            if (imgAlmacen.getImage().isError()) {
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            }

        } else if (tablaPatos.isFocused()) {
            //Almacena al pato seleccionado.
            ObservableList<Pato> Seleccionado;
            Seleccionado = (ObservableList<Pato>) tablaPatos.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Pato());

            //Muetra su imagen asignada en la ImageView.
            imgAlmacen.setImage(new Image(new File("ImgPatos/" + (Seleccionado.get(0).getNombre_Pato() + " " + Seleccionado.get(0).getNum_Cartilla()).replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone una imagen por defecto.
            if (imgAlmacen.getImage().isError()) {
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            }

        } else if (tablaClientes.isFocused()) {
            //Almacena al cliente seleccionado.
            ObservableList<Cliente> Seleccionado;
            Seleccionado = (ObservableList<Cliente>) tablaClientes.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Cliente() + " " + Seleccionado.get(0).getApellidos_Cliente());

            //Muetra su imagen asignada en la ImageView.
            imgAlmacen.setImage(new Image(new File("ImgClientes/" + (Seleccionado.get(0).getNombre_Cliente() + " " + Seleccionado.get(0).getApellidos_Cliente()).replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone una imagen por defecto.
            if (imgAlmacen.getImage().isError()) {
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            }

        } else if (tablaLagos.isFocused()) {
            //Almacena al lago seleccionado.
            ObservableList<Lago> Seleccionado;
            Seleccionado = (ObservableList<Lago>) tablaLagos.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Lago());

            //Muetra su imagen asignada en la ImageView.
            imgAlmacen.setImage(new Image(new File("ImgLagos/" + Seleccionado.get(0).getNombre_Lago().replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone una imagen por defecto.
            if (imgAlmacen.getImage().isError()) {
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            }
        } else if (tablaEstancia.isFocused()) {
            //Almacena a la estancia seleccionada.
            ObservableList<Estancia> Seleccionado;
            Seleccionado = (ObservableList<Estancia>) tablaEstancia.getSelectionModel().getSelectedItems();

            labelNomPato.setText(Seleccionado.get(0).getNombrePato());
            labelNomCliente.setText(Seleccionado.get(0).getNombreCliente());
            labelNomLago.setText(Seleccionado.get(0).getNombreLago());

            //Muetra sus imagenes asignadas en las ImageView.
            imagePato.setImage(new Image(new File("ImgPatos/" + (Seleccionado.get(0).getNombrePato() + " " + String.valueOf(Seleccionado.get(0).getNumCartilla())).replace(" ", "_") + ".png").toURI().toString()));
            imageLago.setImage(new Image(new File("ImgLagos/" + Seleccionado.get(0).getNombreLago().replace(" ", "_") + ".png").toURI().toString()));
            imageCliente.setImage(new Image(new File("ImgClientes/" + Seleccionado.get(0).getNombreCliente().replace(" ", "_") + ".png").toURI().toString()));

            //En caso de no tener pone unas imagenes por defecto.
            if (imagePato.getImage().isError()) {
                imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
            }
            if (imageLago.getImage().isError()) {
                imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
            }
            if (imageCliente.getImage().isError()) {
                imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));
            }
        }
    }

    //Se encarga de cambiar los filtros y otros elementos según de se cambie de pestaña.
    public void escuchaTabs() {
        try {
            if (tabsAlmacen.isVisible()) {
                if (tabProductos.isSelected()) {

                    panelFiltros.setVisible(true);
                    botonAnadir.setVisible(true);
                    botonEliminar.setVisible(true);
                    botonModificar.setVisible(true);

                    mostrarFiltrosProd(true);
                    mostrarFiltrosProv(false);
                    mostrarFiltrosPato(false);
                    mostrarFiltrosCliente(false);
                    mostrarFiltrosEstancia(false);

                    labelNomPato.setVisible(false);
                    labelNomCliente.setVisible(false);
                    labelNomLago.setVisible(false);

                    imagePato.setVisible(false);
                    imageLago.setVisible(false);
                    imageCliente.setVisible(false);

                    labelNomProdProv.setVisible(true);
                    imgAlmacen.setVisible(true);

                    labelAlmacen.setText("ALMACÉN");
                    labelNomProdProv.setText("KAMO(N)DUCK");
                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));

                } else if (tabProveedores.isSelected()) {

                    panelFiltros.setVisible(true);
                    botonAnadir.setVisible(true);
                    botonEliminar.setVisible(true);
                    botonModificar.setVisible(true);

                    mostrarFiltrosProd(false);
                    mostrarFiltrosProv(true);
                    mostrarFiltrosPato(false);
                    mostrarFiltrosCliente(false);
                    mostrarFiltrosEstancia(false);

                    labelNomPato.setVisible(false);
                    labelNomCliente.setVisible(false);
                    labelNomLago.setVisible(false);

                    imagePato.setVisible(false);
                    imageLago.setVisible(false);
                    imageCliente.setVisible(false);

                    labelNomProdProv.setVisible(true);
                    imgAlmacen.setVisible(true);

                    labelAlmacen.setText("ALMACÉN");
                    labelNomProdProv.setText("KAMO(N)DUCK");
                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));

                }
            } else if (tabsFacturacion.isVisible()) {
                if (tabPatos.isSelected()) {

                    panelFiltros.setVisible(true);
                    botonAnadir.setVisible(true);
                    botonEliminar.setVisible(true);
                    botonModificar.setVisible(true);
                    reporteEstancia.setVisible(false);

                    mostrarFiltrosProd(false);
                    mostrarFiltrosProv(false);
                    mostrarFiltrosPato(true);
                    mostrarFiltrosCliente(false);
                    mostrarFiltrosEstancia(false);

                    labelNomPato.setVisible(false);
                    labelNomCliente.setVisible(false);
                    labelNomLago.setVisible(false);

                    imagePato.setVisible(false);
                    imageLago.setVisible(false);
                    imageCliente.setVisible(false);

                    labelNomProdProv.setVisible(true);
                    imgAlmacen.setVisible(true);

                    labelAlmacen.setText("FACTURACIÓN");
                    labelNomProdProv.setText("KAMO(N)DUCK");

                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));

                    labelNomPato.setText("");
                    labelNomCliente.setText("");
                    labelNomLago.setText("");

                } else if (tabCliente.isSelected()) {

                    panelFiltros.setVisible(true);
                    botonAnadir.setVisible(true);
                    botonEliminar.setVisible(true);
                    botonModificar.setVisible(true);
                    reporteEstancia.setVisible(false);

                    mostrarFiltrosProd(false);
                    mostrarFiltrosProv(false);
                    mostrarFiltrosPato(false);
                    mostrarFiltrosCliente(true);
                    mostrarFiltrosEstancia(false);

                    labelNomPato.setVisible(false);
                    labelNomCliente.setVisible(false);
                    labelNomLago.setVisible(false);

                    imagePato.setVisible(false);
                    imageLago.setVisible(false);
                    imageCliente.setVisible(false);

                    labelNomProdProv.setVisible(true);
                    imgAlmacen.setVisible(true);

                    labelAlmacen.setText("FACTURACIÓN");
                    labelNomProdProv.setText("KAMO(N)DUCK");

                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));

                    labelNomPato.setText("");
                    labelNomCliente.setText("");
                    labelNomLago.setText("");

                } else if (tabLago.isSelected()) {

                    panelFiltros.setVisible(false);
                    botonAnadir.setVisible(false);
                    botonEliminar.setVisible(false);
                    botonModificar.setVisible(false);
                    reporteEstancia.setVisible(false);

                    labelNomPato.setVisible(false);
                    labelNomCliente.setVisible(false);
                    labelNomLago.setVisible(false);

                    imagePato.setVisible(false);
                    imageLago.setVisible(false);
                    imageCliente.setVisible(false);

                    labelNomProdProv.setVisible(true);
                    imgAlmacen.setVisible(true);

                    labelAlmacen.setText("FACTURACIÓN");
                    labelNomProdProv.setText("KAMO(N)DUCK");

                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));

                    labelNomPato.setText("");
                    labelNomCliente.setText("");
                    labelNomLago.setText("");

                } else if (tabEstancia.isSelected()) {

                    panelFiltros.setVisible(true);
                    botonAnadir.setVisible(true);
                    botonEliminar.setVisible(true);
                    botonModificar.setVisible(true);
                    reporteEstancia.setVisible(true);

                    mostrarFiltrosProd(false);
                    mostrarFiltrosProv(false);
                    mostrarFiltrosPato(false);
                    mostrarFiltrosCliente(false);
                    mostrarFiltrosEstancia(true);

                    labelNomPato.setVisible(true);
                    labelNomCliente.setVisible(true);
                    labelNomLago.setVisible(true);

                    imagePato.setVisible(true);
                    imageLago.setVisible(true);
                    imageCliente.setVisible(true);

                    labelNomProdProv.setVisible(false);
                    imgAlmacen.setVisible(false);

                    labelAlmacen.setText("FACTURACIÓN");
                    labelNomProdProv.setText("KAMO(N)DUCK");

                    imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imagePato.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageLago.setImage(new Image("/Imagenes/iconoSolo.png"));
                    imageCliente.setImage(new Image("/Imagenes/iconoSolo.png"));
                }
            }
        } catch (NullPointerException e) {
        }
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Métodos para mostrar/ocultar los filtros.
    public void mostrarFiltrosProd(boolean sino){
        textFiltroIDProd.setVisible(sino);
        textFiltroNombreProd.setVisible(sino);
        textFiltroTipo.setVisible(sino);
        textFiltroCantidad.setVisible(sino);
        textFiltroProveedor.setVisible(sino);
    }

    public void mostrarFiltrosProv(boolean sino){
        textFiltroIDProv.setVisible(sino);
        textFiltroNombreProv.setVisible(sino);
        textFiltroTelefono.setVisible(sino);
        textFiltroPais.setVisible(sino);
    }

    public void mostrarFiltrosPato(boolean sino){
        textFiltroIDPato.setVisible(sino);
        textFiltroNombrePato.setVisible(sino);
        textFiltroRazaPato.setVisible(sino);
        textFiltroEdadPato.setVisible(sino);
        textFiltroCartillaPato.setVisible(sino);
    }

    public void mostrarFiltrosCliente(boolean sino){
        textFiltroIDCliente.setVisible(sino);
        textFiltroNombreCliente.setVisible(sino);
        textFiltroApellidosCliente.setVisible(sino);
        textFiltroTel1Cliente.setVisible(sino);
        textFiltroTel2Cliente.setVisible(sino);
        textFiltroEmailCliente.setVisible(sino);
        textFiltroTipoPagoCliente.setVisible(sino);
    }

    public void mostrarFiltrosEstancia(boolean sino){
        textFiltroIDEstancia.setVisible(sino);
        textFiltroIngresoEstancia.setVisible(sino);
        textFiltroSalidaEstancia.setVisible(sino);
        textFiltroNomPatoEstancia.setVisible(sino);
        textFiltroNomClienteEstancia.setVisible(sino);
        textFiltroNomLagoEstancia.setVisible(sino);
    }

    /*___________________________________________________________________________________________________________________________________________________________________________*/
    //Se usa para comprobar si un trabajador esta trabajando hoy o no.
    public boolean trabajaHoy(String horario) {
        String diaActul = null;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.MONDAY:
                diaActul = "L";
                break;
            case Calendar.TUESDAY:
                diaActul = "M";
                break;
            case Calendar.WEDNESDAY:
                diaActul = "X";
                break;
            case Calendar.THURSDAY:
                diaActul = "J";
                break;
            case Calendar.FRIDAY:
                diaActul = "V";
                break;
            case Calendar.SATURDAY:
                diaActul = "S";
                break;
            case Calendar.SUNDAY:
                diaActul = "D";
                break;
        }

        if (horario.contains(diaActul)) {
            return true;
        } else {
            return false;
        }
    }
    /*___________________________________________________________________________________________________________________________________________________________________________*/
}