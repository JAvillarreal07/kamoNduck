package Controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Producto;
import Modelo.Proveedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * @author josea
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    public JFXButton botonAnadir, botonModificar, botonEliminar;
    @FXML
    public ImageView imgAlmacen;
    @FXML
    public TableView tablaProductos, tablaProveedor;
    @FXML
    public TableColumn colIDProduc, colNomProduc, colTipoProduc, colCantProduc, colPorvProduc, colObserProduc;
    @FXML
    public TableColumn colProvID, colProvNom, colProvDirec, colProvTel, colProvPais;
    @FXML
    public Tab tabProductos, tabProveedores;
    @FXML
    public JFXTextField textFiltroIDProd, textFiltroIDProv, textFiltroNombreProd, textFiltroNombreProv, textFiltroProveedor, textFiltroTipo, textFiltroCantidad, textFiltroTelefono, textFiltroPais;
    @FXML
    public Label labelNomProdProv;
    @FXML
    public ComboBox menuModulos;
    @FXML
    public JFXTabPane tabsAlmacen;
    @FXML

    private IOBaseDatos IO = new IOBaseDatos();

    private ObservableList<Producto> ListaProductos;
    private ObservableList<Proveedor> ListaProveedor;

    ObservableList<String> nombreEmpleados = FXCollections.observableArrayList("Engineering", "MCA", "MBA", "Graduation", "MTECH", "Mphil", "Phd");
    ListView<String> listView = new ListView<String>(nombreEmpleados);


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
        try {
            iniciaRegistros();
            iniciaFiltros();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciaRegistros() throws SQLException {

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
    }


    public void iniciaTablas() {

        ListaProductos = FXCollections.observableArrayList();
        ListaProveedor = FXCollections.observableArrayList();

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

    public void iniciaFiltros(){
        for (int i = 0; i < ListaProductos.size(); i++){
            IDProdFiltros.add(ListaProductos.get(i).getIDProducto());
            nombresProdFiltros.add(ListaProductos.get(i).getNombre_Producto());
            proveedorProdFiltros.add(ListaProductos.get(i).getProveedor());
            tipoProdFiltros.add(ListaProductos.get(i).getTipo_Producto());
            cantidadProdFiltros.add(ListaProductos.get(i).getCantidad());
        }

        for (int i = 0; i < ListaProveedor.size(); i++){
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

        FilteredList<Producto> filteredDataProd = new FilteredList<>(ListaProductos, b -> true);

        //Establece el predicado del filtro siempre que el filtro cambie.
        textFiltroNombreProd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataProd.setPredicate(producto -> {

                //Si el texto del filtro está vacío, mostrará todos los pacientes.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compara los datos en la tabla con lo escrito en el filtro.
                String lowerCaseFilter = newValue.toLowerCase();

                if (producto.getNombre_Producto().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // No coincide nada.
                }
            });
        });

        sorter(filteredDataProd);

        textFiltroTipo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataProd.setPredicate(producto -> {

                //Si el texto del filtro está vacío, mostrará todos los pacientes.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compara los datos en la tabla con lo escrito en el filtro.
                String lowerCaseFilter = newValue.toLowerCase();

                if (producto.getTipo_Producto().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // No coincide nada.
                }
            });
        });

        sorter(filteredDataProd);
    }

    public void sorter(FilteredList<Producto> filteredDataProd){
        //Envuelve el FilteredList en un SortedList.
        SortedList<Producto> sortedDataPaci = new SortedList<>(filteredDataProd);

        //Vincula el comparador SortedList al comparador TableView.
        //sortedDataPaci.comparatorProperty().bind(tablaProductos.comparatorProperty());

        //Agregue los datos ordenados (y filtrados) a la tabla.
        tablaProductos.setItems(sortedDataPaci);
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
                break;

            case "Facturación":
                tabsAlmacen.setVisible(false);
                break;

            case "Empleados":
                tabsAlmacen.setVisible(false);
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
            } catch (NullPointerException e){}

        } else if (tabProveedores.isSelected()) {

            try{
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
            } catch (NullPointerException e){}
        }
    }

    public void moduloElegido(String boton) {
        this.menuModulos.setValue(boton); //Atope
    }
}
