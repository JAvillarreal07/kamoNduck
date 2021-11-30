package Modelo;

public class Producto {

    private int IDProducto, cantidad, IDProveedor;
    private String Nombre_Producto, Observaciones;

    public Producto(int IDProducto, int cantidad, String nombre_Producto, String observaciones, int IDProveedor) {
        this.IDProducto = IDProducto;
        this.cantidad = cantidad;
        Nombre_Producto = nombre_Producto;
        this.Observaciones = observaciones;
        this.IDProveedor = IDProveedor;
    }

    public int getIDProducto() {return IDProducto;}

    public void setIDProducto(int IDProducto) {this.IDProducto = IDProducto;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public String getNombre_Producto() {return Nombre_Producto;}

    public void setNombre_Producto(String nombre_Producto) {Nombre_Producto = nombre_Producto;}

    public String getObservaciones() {return Observaciones;}

    public void setObservaciones(String observaciones) {Observaciones = observaciones;}

    public int getIDProveedor() {return IDProveedor;}

    public void setIDProveedor(int IDProveedor) {this.IDProveedor = IDProveedor;}
}
