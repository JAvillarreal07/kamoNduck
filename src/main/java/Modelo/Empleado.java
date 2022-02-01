package Modelo;

import Controlador.IOBaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado {
    private int IDEmpleado, IDLago;
    private String DNI_Empleado, Nombre_Empleado, Apellidos_Empleado, Telefono_Empleado, Email_Empleado, Cargo, Horario_Trabajo, Turno;

    private IOBaseDatos IO = new IOBaseDatos();

    public Empleado(int IDEmpleado, String DNI_Empleado, String Nombre_Empleado, String Apellidos_Empleado, String Telefono_Empleado, String Email_Empleado, String Cargo, String Horario_Trabajo, String Turno, int IDLago) {
        this.IDEmpleado = IDEmpleado;
        this.DNI_Empleado = DNI_Empleado;
        this.Nombre_Empleado = Nombre_Empleado;
        this.Apellidos_Empleado = Apellidos_Empleado;
        this.Telefono_Empleado = Telefono_Empleado;
        this.Email_Empleado = Email_Empleado;
        this.Cargo = Cargo;
        this.Horario_Trabajo = Horario_Trabajo;
        this.Turno = Turno;
        this.IDLago = IDLago;
    }

    public int getIDEmpleado() {return IDEmpleado;}

    public void setIDEmpleado(int IDEmpleado) {this.IDEmpleado = IDEmpleado;}

    public String getDNI_Empleado() {return DNI_Empleado;}

    public void setDNI_Empleado(String DNI_Empleado) {
        this.DNI_Empleado = DNI_Empleado;
    }

    public String getNombre_Empleado() {return Nombre_Empleado;}

    public void setNombre_Empleado(String Nombre_Empleado) {
        this.Nombre_Empleado = Nombre_Empleado;}

    public String getApellidos_Empleado() {return Apellidos_Empleado;}

    public void setApellidos_Empleado(String Apellidos_Empleado) {
        this.Apellidos_Empleado = Apellidos_Empleado;}

    public String getTelefono_Empleado() {return Telefono_Empleado;}

    public void setTelefono_Empleado(String Telefono_Empleado) {
        this.Telefono_Empleado = Telefono_Empleado;}

    public String getEmail_Empleado() {return Email_Empleado;}

    public void setEmail_Empleado(String email_Empleado) {Email_Empleado = email_Empleado;}

    public String getCargo() {return Cargo;}

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;}

    public String getHorario_Trabajo() {return Horario_Trabajo;}

    public void setHorario_Trabajo(String Horario_Trabajo) {
        this.Horario_Trabajo = Horario_Trabajo;}

    public String getTurno() {return Turno;}

    public void setTurno(String Turno) {
        this.Turno = Turno;}

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}

    public String getNombreCompleto(){
        return Nombre_Empleado + " " + Apellidos_Empleado;
    }

    public String getNombreLago() throws SQLException {
        ResultSet Consult = IO.introduceRegistros("SELECT Nombre_Lago FROM LAGOS WHERE IDLago = " + this.IDLago);
        Consult.next();

        return Consult.getString("Nombre_Lago");
    }
}
