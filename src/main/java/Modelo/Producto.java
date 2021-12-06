package Modelo;

public class Producto {

    private int IDProducto, cantidad, IDProveedor, Minimo;
    private String Tipo_Producto, Nombre_Producto, Observaciones;

    public Producto(int IDProducto, int cantidad, int minimo, String Tipo_Producto, String nombre_Producto, String observaciones, int IDProveedor) {
        this.IDProducto = IDProducto;
        this.cantidad = cantidad;
        this.Minimo = minimo;
        this.Tipo_Producto = Tipo_Producto;
        this.Nombre_Producto = nombre_Producto;
        this.Observaciones = observaciones;
        this.IDProveedor = IDProveedor;
    }

    public String getTipo_Producto() {
        return Tipo_Producto;
    }

    public void setTipo_Producto(String tipo_Producto) {
        Tipo_Producto = tipo_Producto;
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

    public int getMinimo() {return Minimo;}

    public void setMinimo(int minimo) {Minimo = minimo;}
}
