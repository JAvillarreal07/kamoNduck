package Controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Empleado;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.CustomListView;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

/**
 * @author josea
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private ComboBox<String> menuModulos;


    @FXML
    private JFXButton botonAnadir, botonModificar, botonEliminar;
    @FXML
    private ImageView imgAlmacen;
    @FXML
    public TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, String> colNomProduc, colTipoProduc, colPorvProduc, colObserProduc;
    @FXML
    private TableColumn<Producto, Integer> colIDProduc, colCantProduc;
    @FXML
    private TableView<Proveedor> tablaProveedor;
    @FXML
    private TableColumn<Producto, String> colProvNom, colProvDirec, colProvTel, colProvPais;
    @FXML
    private TableColumn<Producto, Integer> colProvID;
    @FXML
    private Tab tabProductos, tabProveedores;
    @FXML
    private JFXTextField textFiltroIDProd, textFiltroIDProv, textFiltroNombreProd, textFiltroNombreProv, textFiltroProveedor, textFiltroTipo, textFiltroCantidad, textFiltroTelefono, textFiltroPais;
    @FXML
    private Label labelNomProdProv;
    @FXML
    private JFXTabPane tabsAlmacen;
    @FXML
    private TitledPane panelFiltros;
    @FXML
    public Text labelAlmacen, miniLabelAlmacen;
    @FXML
    public Line lineaAlamacen;
    @FXML
    public Label labelNoModifiEmpleados, labelNombreEmpleado;
    @FXML
    public JFXListView<CustomListView> listViewEmpleados = new JFXListView<CustomListView>();
    @FXML
    public JFXListView<CustomListView> listViewEmpeadosActivos = new JFXListView<CustomListView>();
    @FXML
    public ImageView imgEmpleados;

    private IOBaseDatos IO = new IOBaseDatos();

    public ObservableList<Producto> ListaProductos = FXCollections.observableArrayList();
    private ObservableList<Proveedor> ListaProveedor = FXCollections.observableArrayList();
    private ObservableList<Empleado> ListaEmpleados = FXCollections.observableArrayList();

    private ObservableList<Producto> listaFiltrosProd = FXCollections.observableArrayList();
    private ObservableList<Proveedor> listaFiltrosProv = FXCollections.observableArrayList();


    private ArrayList<Integer> IDProdFiltros = new ArrayList<Integer>();
    private ArrayList<Integer> IDProvFiltros = new ArrayList<Integer>();
    private ArrayList<String> nombresProdFiltros = new ArrayList<String>();
    private ArrayList<String> nombresProvFiltros = new ArrayList<String>();
    private ArrayList<String> proveedorProdFiltros = new ArrayList<String>();
    private ArrayList<String> tipoProdFiltros = new ArrayList<String>();
    private ArrayList<Integer> cantidadProdFiltros = new ArrayList<Integer>();
    private ArrayList<String> telefonoProvFiltros = new ArrayList<String>();
    private ArrayList<String> paisProvFiltros = new ArrayList<String>();

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

    public void iniciaRegistros() throws SQLException {
        ListaProductos.clear();
        ListaProveedor.clear();
        ListaEmpleados.clear();

        ResultSet r1 = IO.introduceRegistros("SELECT * FROM PRODUCTOS");

        while (r1.next()) {

            ResultSet proveedorConsult = IO.introduceRegistros("SELECT Nombre_Proveedor FROM PROVEEDOR WHERE IDProveedor = " + r1.getInt("IDProveedor"));
            proveedorConsult.next();
            String proveedor = proveedorConsult.getString("Nombre_Proveedor");

            Producto prod = new Producto(r1.getInt("IDProducto"),
                    r1.getString("Nombre_Producto"),
                    r1.getString("Tipo_Producto"),
                    r1.getInt("Cantidad"),
                    r1.getInt("Minimo"),
                    r1.getString("Observaciones"),
                    proveedor);

            if (r1 != null) {

                this.ListaProductos.add(prod);
            }

            this.tablaProductos.setItems(ListaProductos);
            this.tablaProductos.refresh();
        }


        ResultSet r2 = IO.introduceRegistros("SELECT * FROM PROVEEDOR");

        while (r2.next()) {

            Proveedor prov = new Proveedor(r2.getInt("IDProveedor"),
                    r2.getString("Nombre_Proveedor"),
                    r2.getString("Direccion"),
                    r2.getString("Telefono_Proveedor"),
                    r2.getString("Pais"));

            if (r2 != null) {

                this.ListaProveedor.add(prov);
            }

            this.tablaProveedor.setItems(ListaProveedor);
            this.tablaProveedor.refresh();
        }

        ResultSet r3 = IO.introduceRegistros("SELECT * FROM EMPLEADOS");

        while (r3.next()) {

            Empleado emple = new Empleado(r3.getInt("IDEmpleado"),
                    r3.getString("DNI_Empleado"),
                    r3.getString("Nombre_Empleado"),
                    r3.getString("Apellidos_Empleado"),
                    r3.getString("Telefono_Empleado"),
                    r3.getString("Email_Empleado"),
                    r3.getString("Cargo"),
                    r3.getString("Horario_Trabajo"),
                    r3.getString("Turno"),
                    r3.getInt("IDLago"));

            if (r3 != null) {

                this.ListaEmpleados.add(emple);
            }

        }
    }

    public void iniciaTablas() {

        this.colIDProduc.setCellValueFactory(new PropertyValueFactory<>("IDProducto"));
        this.colNomProduc.setCellValueFactory(new PropertyValueFactory<>("Nombre_Producto"));
        this.colTipoProduc.setCellValueFactory(new PropertyValueFactory<>("Tipo_Producto"));
        this.colCantProduc.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colObserProduc.setCellValueFactory(new PropertyValueFactory<>("Observaciones"));
        this.colPorvProduc.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));

        this.colProvID.setCellValueFactory(new PropertyValueFactory<>("IDProveedor"));
        this.colProvNom.setCellValueFactory(new PropertyValueFactory<>("Nombre_Proveedor"));
        this.colProvDirec.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        this.colProvTel.setCellValueFactory(new PropertyValueFactory<>("Telefono_Proveedor"));
        this.colProvPais.setCellValueFactory(new PropertyValueFactory<>("Pais"));
    }

    public void iniciaFiltros() {
        for (int i = 0; i < ListaProductos.size(); i++) {
            IDProdFiltros.add(ListaProductos.get(i).getIDProducto());
            nombresProdFiltros.add(ListaProductos.get(i).getNombre_Producto());
            proveedorProdFiltros.add(ListaProductos.get(i).getProveedor());
            tipoProdFiltros.add(ListaProductos.get(i).getTipo_Producto());
            cantidadProdFiltros.add(ListaProductos.get(i).getCantidad());
        }

        for (int i = 0; i < ListaProveedor.size(); i++) {
            IDProvFiltros.add(ListaProveedor.get(i).getIDProveedor());
            nombresProvFiltros.add(ListaProveedor.get(i).getNombre_Proveedor());
            telefonoProvFiltros.add(ListaProveedor.get(i).getTelefono_Proveedor());
            paisProvFiltros.add(ListaProveedor.get(i).getPais());
        }


        TextFields.bindAutoCompletion(textFiltroIDProd, IDProdFiltros);
        TextFields.bindAutoCompletion(textFiltroNombreProd, nombresProdFiltros);
        TextFields.bindAutoCompletion(textFiltroProveedor, proveedorProdFiltros);
        TextFields.bindAutoCompletion(textFiltroTipo, tipoProdFiltros);
        TextFields.bindAutoCompletion(textFiltroCantidad, cantidadProdFiltros);

        TextFields.bindAutoCompletion(textFiltroIDProv, IDProvFiltros);
        TextFields.bindAutoCompletion(textFiltroNombreProv, nombresProvFiltros);
        TextFields.bindAutoCompletion(textFiltroTelefono, telefonoProvFiltros);
        TextFields.bindAutoCompletion(textFiltroPais, paisProvFiltros);

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
    }

    public void filtrar() {
        if (tabProductos.isSelected()) {
            listaFiltrosProd.clear();

            for (int i = 0; i < ListaProductos.size(); i++) {
                try {
                    if (String.valueOf(ListaProductos.get(i).getIDProducto()).contains(textFiltroIDProd.getText()) &&
                            ListaProductos.get(i).getNombre_Producto().toLowerCase().contains(textFiltroNombreProd.getText().toLowerCase()) &&
                            ListaProductos.get(i).getTipo_Producto().toLowerCase().contains(textFiltroTipo.getText().toLowerCase()) &&
                            String.valueOf(ListaProductos.get(i).getCantidad()).contains(textFiltroCantidad.getText()) &&
                            ListaProductos.get(i).getProveedor().toLowerCase().contains(textFiltroProveedor.getText().toLowerCase())
                    ) {
                        listaFiltrosProd.add(ListaProductos.get(i));
                    }
                } catch (NullPointerException e) {
                }
            }

            tablaProductos.setItems(listaFiltrosProd);
        } else if (tabProveedores.isSelected()) {
            listaFiltrosProv.clear();

            for (int i = 0; i < ListaProveedor.size(); i++) {
                try {
                    if (String.valueOf(ListaProveedor.get(i).getIDProveedor()).contains(textFiltroIDProv.getText()) &&
                            ListaProveedor.get(i).getNombre_Proveedor().toLowerCase().contains(textFiltroNombreProv.getText().toLowerCase()) &&
                            ListaProveedor.get(i).getTelefono_Proveedor().toLowerCase().contains(textFiltroTelefono.getText().toLowerCase()) &&
                            ListaProveedor.get(i).getPais().toLowerCase().contains(textFiltroPais.getText().toLowerCase())
                    ) {
                        listaFiltrosProv.add(ListaProveedor.get(i));
                    }
                } catch (NullPointerException e) {
                }
            }

            tablaProveedor.setItems(listaFiltrosProv);
        }
    }

    public void iniciaListView() {

        List<CustomListView> list1 = new ArrayList<>();
        List<CustomListView> list2 = new ArrayList<>();
        for (int i = 0; i < ListaEmpleados.size(); i++) {
            list1.add(new CustomListView(new Image("/ImgEmpleados/" + ListaEmpleados.get(i).getNombreCompleto().replace(" ", "_") + ".png"), ListaEmpleados.get(i).getNombre_Empleado(), ListaEmpleados.get(i).getApellidos_Empleado(), ListaEmpleados.get(i).getCargo()));
            if (trabajaHoy(ListaEmpleados.get(i).getHorario_Trabajo())) {
                list2.add(new CustomListView(new Image("/ImgEmpleados/" + ListaEmpleados.get(i).getNombreCompleto().replace(" ", "_") + ".png"), ListaEmpleados.get(i).getNombre_Empleado(), ListaEmpleados.get(i).getApellidos_Empleado(), ListaEmpleados.get(i).getCargo()));
            }
        }


        ObservableList<CustomListView> myObservableList1 = FXCollections.observableList(list1);
        ObservableList<CustomListView> myObservableList2 = FXCollections.observableList(list2);
        listViewEmpleados.setItems(myObservableList1);
        listViewEmpeadosActivos.setItems(myObservableList2);
        listViewEmpeadosActivos.setFixedCellSize(60);

        for (int i = 0; i < listViewEmpeadosActivos.getItems().size(); i++) {
            String nombre = listViewEmpeadosActivos.getItems().get(i).getNombreCompleto();
            for (int j = 0; j < ListaEmpleados.size(); j++) {
                if (ListaEmpleados.get(j).getNombreCompleto().equals(nombre)) {
                    switch (ListaEmpleados.get(j).getTurno()) {
                        case "Diurno":
                            listViewEmpeadosActivos.getItems().get(i).setStyle("-fx-background-color: pink");
                            break;
                        case "Nocturno":
                            listViewEmpeadosActivos.getItems().get(i).setStyle("-fx-background-color: cyan");
                            break;
                    }
                    break;
                }
            }
        }
    }

    public void accionesCRUD() throws SQLException, IOException {
        if (botonAnadir.isFocused()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaCampos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/Imagenes/iconosolo.png"));
            stage.setTitle("Modificar médico");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else if (botonEliminar.isFocused()) {
            ObservableList<Producto> SingleProduct;
            SingleProduct = tablaProductos.getSelectionModel().getSelectedItems();

            this.IO.actualizaRegistros("DELETE FROM PRODUCTOS where IDProducto = " + SingleProduct.get(0).getIDProducto());
            SingleProduct.forEach(ListaProductos::remove);

            iniciaTablas();
            try {
                iniciaRegistros();
            } catch (SQLException ex) {
                Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (botonModificar.isFocused()) {
            System.out.println("B3");
        }
    }

    public void cambiaModulo() throws IOException {
        switch (menuModulos.getValue().toString()) {
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


                labelNoModifiEmpleados.setVisible(false);
                labelNombreEmpleado.setVisible(false);
                listViewEmpleados.setVisible(false);
                listViewEmpeadosActivos.setVisible(false);
                imgEmpleados.setVisible(false);
                break;

            case "Facturación":
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

                labelNoModifiEmpleados.setVisible(false);
                labelNombreEmpleado.setVisible(false);
                listViewEmpleados.setVisible(false);
                listViewEmpeadosActivos.setVisible(false);
                imgEmpleados.setVisible(false);
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

                labelNoModifiEmpleados.setVisible(true);
                labelNombreEmpleado.setVisible(true);
                listViewEmpleados.setVisible(true);
                listViewEmpeadosActivos.setVisible(true);
                imgEmpleados.setVisible(true);
                break;

            case "Atrás":
                volverABienvenida();
                break;
        }
    }

    public void volverABienvenida() throws IOException {
        //Abre la ventana
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

    public void escuchaTablas() {

        if (tablaProductos.isFocused()) {
            ObservableList<Producto> Seleccionado;
            Seleccionado = (ObservableList<Producto>) tablaProductos.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Producto());
            imgAlmacen.setImage(new Image("/ImgProductos/" + Seleccionado.get(0).getNombre_Producto().replace(" ", "_") + ".png"));
        } else if (tablaProveedor.isFocused()) {
            ObservableList<Proveedor> Seleccionado;
            Seleccionado = (ObservableList<Proveedor>) tablaProveedor.getSelectionModel().getSelectedItems();

            labelNomProdProv.setText(Seleccionado.get(0).getNombre_Proveedor());
            imgAlmacen.setImage(new Image("/ImgProveedores/" + Seleccionado.get(0).getNombre_Proveedor().replace(" ", "_") + ".png"));
        }
    }

    public void escuchaTabs() {

        if (tabProductos.isSelected()) {
            try {
                textFiltroIDProd.setVisible(true);
                textFiltroNombreProd.setVisible(true);
                textFiltroTipo.setVisible(true);
                textFiltroCantidad.setVisible(true);
                textFiltroProveedor.setVisible(true);

                textFiltroIDProv.setVisible(false);
                textFiltroNombreProv.setVisible(false);
                textFiltroTelefono.setVisible(false);
                textFiltroPais.setVisible(false);

                labelNomProdProv.setText("KAMO(N)DUCK");
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            } catch (NullPointerException e) {
            }

        } else if (tabProveedores.isSelected()) {

            try {
                textFiltroIDProd.setVisible(false);
                textFiltroNombreProd.setVisible(false);
                textFiltroTipo.setVisible(false);
                textFiltroCantidad.setVisible(false);
                textFiltroProveedor.setVisible(false);

                textFiltroIDProv.setVisible(true);
                textFiltroNombreProv.setVisible(true);
                textFiltroTelefono.setVisible(true);
                textFiltroPais.setVisible(true);


                labelNomProdProv.setText("KAMO(N)DUCK");
                imgAlmacen.setImage(new Image("/Imagenes/iconoSolo.png"));
            } catch (NullPointerException e) {
            }
        }
    }

    public void moduloElegido(String boton) {
        this.menuModulos.setValue(boton); //Atope
    }

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
}
