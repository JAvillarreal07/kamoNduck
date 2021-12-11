package Modelo;

public class Producto {

    private int IDProducto, Cantidad, Minimo;
    private String Proveedor, Tipo_Producto, Nombre_Producto, Observaciones;

    public Producto(int IDProducto, String Nombre_Producto, String Tipo_Producto, int Cantidad, int Minimo, String Observaciones, String Proveedor) {
        this.IDProducto = IDProducto;
        this.Nombre_Producto = Nombre_Producto;
        this.Tipo_Producto = Tipo_Producto;
        this.Cantidad = Cantidad;
        this.Minimo = Minimo;
        this.Observaciones = Observaciones;
        this.Proveedor = Proveedor;
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

    public String getObservaciones() {return Observaciones;}

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;}

    public String getProveedor() {return Proveedor;}

    public void setProveedor(String Proveedor) {this.Proveedor = Proveedor;}
}
