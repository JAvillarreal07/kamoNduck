package Modelo;

public class Cliente {

    private int IDCliente;
    private String DNI, Nombre_Cliente, Apellidos_Cliente, Telefono_Cliente1, Telefono_Cliente2, Email_Cliente, TipoPago;

    public Cliente(int IDCliente, String DNI, String nombre_cliente, String apellidos_cliente, String telefono1, String telefono2, String email_Cliente, String tipoPago) {
        this.IDCliente = IDCliente;
        this.DNI = DNI;
        this.Nombre_Cliente = nombre_cliente;
        this.Apellidos_Cliente = apellidos_cliente;
        this.Telefono_Cliente1 = telefono1;
        this.Telefono_Cliente2 = telefono2;
        this.Email_Cliente = email_Cliente;
        this.TipoPago = tipoPago;
    }

    public int getIDCliente() {return IDCliente;}

    public void setIDCliente(int IDCliente) {this.IDCliente = IDCliente;}

    public String getDNI() {return DNI;}

    public void setDNI(String DNI) {this.DNI = DNI;}

    public String getNombre_Cliente() {return Nombre_Cliente;}

    public void setNombre_Cliente(String nombre_Cliente) {Nombre_Cliente = nombre_Cliente;}

    public String getApellidos_Cliente() {return Apellidos_Cliente;}

    public void setApellidos_Cliente(String apellidos_Cliente) {Apellidos_Cliente = apellidos_Cliente;}

    public String getTelefono1() {return Telefono_Cliente1;}

    public void setTelefono1(String telefono1) {Telefono_Cliente1 = telefono1;}

    public String getTelefono2() {return Telefono_Cliente2;}

    public void setTelefono2(String telefono2) {Telefono_Cliente2 = telefono2;}

    public String getEmail() {return Email_Cliente;}

    public void setEmail(String email) {Email_Cliente = email;}

    public String getTipoPago() {return TipoPago;}

    public void setTipoPago(String tipoPago) {TipoPago = tipoPago;}
}
