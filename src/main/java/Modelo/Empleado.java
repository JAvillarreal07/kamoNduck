package Modelo;

public class Empleado {
    private int IDEmpleado, IDLago;
    private String DNI_Empleado, Nombre_Empleado, Apellidos_Empleado, Telefono_Empleado, Email_Empleado, Cargo, Horario_Trabajo, Turno;

    public Empleado(int IDEmpleado, String DNI_Empleado, String nombre_Empleado, String apellidos_Empleado, String telefono_Empleado, String email_Empleado, String cargo, String horario_Trabajo, String turno, int IDLago) {
        this.IDEmpleado = IDEmpleado;
        this.DNI_Empleado = DNI_Empleado;
        this.Nombre_Empleado = nombre_Empleado;
        this.Apellidos_Empleado = apellidos_Empleado;
        this.Telefono_Empleado = telefono_Empleado;
        this.Email_Empleado = email_Empleado;
        this.Cargo = cargo;
        this.Horario_Trabajo = horario_Trabajo;
        this.Turno = turno;
        this.IDLago = IDLago;
    }

    public int getIDEmpleado() {return IDEmpleado;}

    public void setIDEmpleado(int IDEmpleado) {this.IDEmpleado = IDEmpleado;}

    public String getDNI_Empleado() {return DNI_Empleado;}

    public void setDNI_Empleado(String DNI_Empleado) {
        this.DNI_Empleado = DNI_Empleado;
    }

    public String getNombre_Empleado() {return Nombre_Empleado;}

    public void setNombre_Empleado(String nombre_Empleado) {Nombre_Empleado = nombre_Empleado;}

    public String getApellidos_Empleado() {return Apellidos_Empleado;}

    public void setApellidos_Empleado(String apellidos_Empleado) {Apellidos_Empleado = apellidos_Empleado;}

    public String getTelefono_Empleado() {return Telefono_Empleado;}

    public void setTelefono_Empleado(String telefono_Empleado) {Telefono_Empleado = telefono_Empleado;}

    public String getEmail_Empleado() {return Email_Empleado;}

    public void setEmail_Empleado(String email_Empleado) {Email_Empleado = email_Empleado;}

    public String getCargo() {return Cargo;}

    public void setCargo(String cargo) {Cargo = cargo;}

    public String getHorario_Trabajo() {return Horario_Trabajo;}

    public void setHorario_Trabajo(String horario_Trabajo) {Horario_Trabajo = horario_Trabajo;}

    public String getTurno() {return Turno;}

    public void setTurno(String turno) {Turno = turno;}

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}
}
