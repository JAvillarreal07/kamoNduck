package Modelo;

import Controlador.IOBaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {

    private int IDProducto, Cantidad, Minimo, IDProveedor;
    private double Precio;
    private String nombreProveedor, Tipo_Producto, Nombre_Producto, Observaciones;

    private IOBaseDatos IO = new IOBaseDatos();

    public Producto(int IDProducto, String Nombre_Producto, String Tipo_Producto, int Cantidad, int Minimo, double Precio, String Observaciones, int IDProveedor) {
        this.IDProducto = IDProducto;
        this.Nombre_Producto = Nombre_Producto;
        this.Tipo_Producto = Tipo_Producto;
        this.Cantidad = Cantidad;
        this.Minimo = Minimo;
        this.Precio = Precio;
        this.Observaciones = Observaciones;
        this.IDProveedor = IDProveedor;

        try {
            nombreProveedor = buscarNombre(IDProveedor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getIDProducto() {return IDProducto;}

    public void setIDProducto(int IDProducto) {this.IDProducto = IDProducto;}

    public String getNombre_Producto() {return Nombre_Producto;}

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;}

    public String getTipo_Producto() {
        return Tipo_Producto;
    }

    public void setTipo_Producto(String Tipo_Producto) {
        this.Tipo_Producto = Tipo_Producto;
    }

    public int getCantidad() {return Cantidad;}

    public void setCantidad(int Cantidad) {this.Cantidad = Cantidad;}

    public int getMinimo() {return Minimo;}

    public void setMinimo(int Minimo) {
        this.Minimo = Minimo;}

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public String getObservaciones() {return Observaciones;}

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;}

    public int getIDProveedor() {return IDProveedor;}

    public void setIDProveedor(int IDProveedor) {this.IDProveedor = IDProveedor;}

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    private String buscarNombre(int ID) throws SQLException {
        String encontrado = "";
        ResultSet Consult = IO.introduceRegistros("SELECT Nombre_Proveedor FROM PROVEEDOR WHERE IDProveedor = " + ID);
        Consult.next();

        return encontrado = Consult.getString("Nombre_Proveedor");
    }
}
