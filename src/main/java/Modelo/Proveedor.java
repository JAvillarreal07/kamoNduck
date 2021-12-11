package Modelo;

public class Proveedor {

    private int IDProveedor;
    private String Nombre_Proveedor, Direccion, Telefono_Proveedor, Pais;

    public Proveedor(int IDProveedor, String Nombre_Proveedor, String Direccion, String Telefono_Proveedor, String Pais) {
        this.IDProveedor = IDProveedor;
        this.Nombre_Proveedor = Nombre_Proveedor;
        this.Direccion = Direccion;
        this.Telefono_Proveedor = Telefono_Proveedor;
        this.Pais = Pais;
    }

    public int getIDProveedor() {return IDProveedor;}

    public void setIDProveedor(int IDProveedor) {this.IDProveedor = IDProveedor;}

    public String getNombre() {return Nombre_Proveedor;}

    public void setNombre(String Nombre) {Nombre_Proveedor = Nombre;}

    public String getDireccion() {return Direccion;}

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;}

    public String getTelefono_Proveedor() {return Telefono_Proveedor;}

    public void setTelefono_Proveedor(String Telefono_Proveedor) {
        this.Telefono_Proveedor = Telefono_Proveedor;}

    public String getPais() {return Pais;}

    public void setPais(String Pais) {
        this.Pais = Pais;}
}
