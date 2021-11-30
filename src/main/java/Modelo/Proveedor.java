package Modelo;

public class Proveedor {

    private int IDProveedor;
    private String Nombre, Direccion, Telefono_Proveedor, Pais;

    public Proveedor(int IDProveedor, String nombre, String direccion, String telefono_Proveedor, String pais) {
        this.IDProveedor = IDProveedor;
        Nombre = nombre;
        Direccion = direccion;
        Telefono_Proveedor = telefono_Proveedor;
        Pais = pais;
    }

    public int getIDProveedor() {return IDProveedor;}

    public void setIDProveedor(int IDProveedor) {this.IDProveedor = IDProveedor;}

    public String getNombre() {return Nombre;}

    public void setNombre(String nombre) {Nombre = nombre;}

    public String getDireccion() {return Direccion;}

    public void setDireccion(String direccion) {Direccion = direccion;}

    public String getTelefono_Proveedor() {return Telefono_Proveedor;}

    public void setTelefono_Proveedor(String telefono_Proveedor) {Telefono_Proveedor = telefono_Proveedor;}

    public String getPais() {return Pais;}

    public void setPais(String pais) {Pais = pais;}
}
