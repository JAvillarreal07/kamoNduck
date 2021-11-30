package Modelo;

public class Empleado {
    private int IDEmpleado, IDLago;
    private String Nombre_Empleado, Apellidos_Empleado, Telefono_Empleado, Cargo, Horario_Trabajo, Turno;

    public Empleado(int IDEmpleado, String nombre_Empleado, String apellidos_Empleado, String telefono_Empleado, String cargo, String horario_Trabajo, String turno, int IDLago) {
        this.IDEmpleado = IDEmpleado;
        Nombre_Empleado = nombre_Empleado;
        Apellidos_Empleado = apellidos_Empleado;
        Telefono_Empleado = telefono_Empleado;
        Cargo = cargo;
        Horario_Trabajo = horario_Trabajo;
        Turno = turno;
        this.IDLago = IDLago;
    }

    public int getIDEmpleado() {return IDEmpleado;}

    public void setIDEmpleado(int IDEmpleado) {this.IDEmpleado = IDEmpleado;}

    public String getNombre_Empleado() {return Nombre_Empleado;}

    public void setNombre_Empleado(String nombre_Empleado) {Nombre_Empleado = nombre_Empleado;}

    public String getApellidos_Empleado() {return Apellidos_Empleado;}

    public void setApellidos_Empleado(String apellidos_Empleado) {Apellidos_Empleado = apellidos_Empleado;}

    public String getTelefono_Empleado() {return Telefono_Empleado;}

    public void setTelefono_Empleado(String telefono_Empleado) {Telefono_Empleado = telefono_Empleado;}

    public String getCargo() {return Cargo;}

    public void setCargo(String cargo) {Cargo = cargo;}

    public String getHorario_Trabajo() {return Horario_Trabajo;}

    public void setHorario_Trabajo(String horario_Trabajo) {Horario_Trabajo = horario_Trabajo;}

    public String getTurno() {return Turno;}

    public void setTurno(String turno) {Turno = turno;}

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}
}
