package Controlador;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.Producto;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * @author josea
 */
public class VentanaPrincipalController implements Initializable {

    public JFXButton botonAnadir, botonModificar, botonEliminar;
    public ImageView imgAlmacen;
    public TableView tablaProductos, tablaProveedor;
    public TableColumn colIDProduc, colNomProduc, colTipoProduc, colCantProduc, colPorvProduc, colObserProduc;
    public Text labelAlmacen;


    private IOBaseDatos IO = new IOBaseDatos();

    private ObservableList<Producto> ListaProductos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iniciaTablas();
        try {
            iniciaRegistros();
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


            Producto p = new Producto(r1.getInt("IDProducto"),
                    r1.getString("Nombre_Producto"),
                    r1.getString("Tipo_Producto"),
                    r1.getInt("Cantidad"),
                    r1.getInt("Minimo"),
                    r1.getString("Observaciones"),
                    proveedor);

            if (r1 != null) {

                this.ListaProductos.add(p);
            }

            this.tablaProductos.setItems(ListaProductos);
            this.tablaProductos.refresh();
        }
    }



    public void iniciaTablas() {

        ListaProductos = FXCollections.observableArrayList();

        this.colIDProduc.setCellValueFactory(new PropertyValueFactory<>("IDProducto"));
        this.colNomProduc.setCellValueFactory(new PropertyValueFactory<>("Nombre_Producto"));
        this.colTipoProduc.setCellValueFactory(new PropertyValueFactory<>("Tipo_Producto"));
        this.colCantProduc.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        this.colObserProduc.setCellValueFactory(new PropertyValueFactory<>("Observaciones"));
        this.colPorvProduc.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));


    }

    public void accionEliminar() throws SQLException {
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
    }
}
