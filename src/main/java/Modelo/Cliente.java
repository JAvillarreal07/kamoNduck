package Modelo;

public class Cliente {

    private int IDCliente;
    private String DNI, Nombre_Cliente, Apellidos_Cliente, Telefono_Cliente1, Telefono_Cliente2, Email_Cliente, TipoPago;

    public Cliente(int IDCliente, String DNI, String Nombre_Cliente, String Apellidos_Cliente, String Telefono_Cliente1, String Telefono_Cliente2, String Email_Cliente, String TipoPago) {
        this.IDCliente = IDCliente;
        this.DNI = DNI;
        this.Nombre_Cliente = Nombre_Cliente;
        this.Apellidos_Cliente = Apellidos_Cliente;
        this.Telefono_Cliente1 = Telefono_Cliente1;
        this.Telefono_Cliente2 = Telefono_Cliente2;
        this.Email_Cliente = Email_Cliente;
        this.TipoPago = TipoPago;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String Nombre_Cliente) {
        this.Nombre_Cliente = Nombre_Cliente;
    }

    public String getApellidos_Cliente() {
        return Apellidos_Cliente;
    }

    public void setApellidos_Cliente(String Apellidos_Cliente) {
        this.Apellidos_Cliente = Apellidos_Cliente;
    }

    public String getTelefono_Cliente1() {
        return Telefono_Cliente1;
    }

    public void setTelefono_Cliente1(String Telefono_Cliente1) {
        this.Telefono_Cliente1 = Telefono_Cliente1;
    }

    public String getTelefono_Cliente2() {
        return Telefono_Cliente2;
    }

    public void setTelefono_Cliente2(String Telefono_Cliente2) {
        this.Telefono_Cliente2 = Telefono_Cliente2;
    }

    public String getEmail_Cliente() {
        return Email_Cliente;
    }

    public void setEmail_Cliente(String Email_Cliente) {
        this.Email_Cliente = Email_Cliente;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String TipoPago) {
        this.TipoPago = TipoPago;
    }
}
