package Modelo;

import Controlador.IOBaseDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Estancia {
    private int IDEstancia, IDCliente, IDPato, IDLago;
    private String nombrePato, nombreCliente, nombreLago;
    private Date Fecha_Ingreso, Fecha_Salida;

    private IOBaseDatos IO = new IOBaseDatos();

    public Estancia(int IDEstancia, Date Fecha_Ingreso, Date Fecha_Salida, int IDPato, int IDCliente, int IDLago) {
        this.IDEstancia = IDEstancia;
        this.Fecha_Ingreso = Fecha_Ingreso;
        this.Fecha_Salida = Fecha_Salida;
        this.IDPato = IDPato;
        this.IDCliente = IDCliente;
        this.IDLago = IDLago;

        try {
            this.nombrePato = buscarNombres("Pato", this.IDPato);
            this.nombreCliente = buscarNombres("Cliente", this.IDCliente);
            this.nombreLago = buscarNombres("Lago", this.IDLago);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getIDEstancia() {
        return IDEstancia;
    }

    public void setIDEstancia(int IDEstancia) {
        this.IDEstancia = IDEstancia;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public int getIDPato() {
        return IDPato;
    }

    public void setIDPato(int IDPato) {
        this.IDPato = IDPato;
    }

    public int getIDLago() {
        return IDLago;
    }

    public void setIDLago(int IDLago) {
        this.IDLago = IDLago;
    }

    public Date getFecha_Ingreso() {
        return Fecha_Ingreso;
    }

    public void setFecha_Ingreso(Date Fecha_Ingreso) {
        Fecha_Ingreso = Fecha_Ingreso;
    }

    public Date getFecha_Salida() {
        return Fecha_Salida;
    }

    public void setFecha_Salida(Date Fecha_Salida) {
        Fecha_Salida = Fecha_Salida;
    }

    public String getNombrePato() {
        return nombrePato;
    }

    public void setNombrePato(String nombrePato) {
        this.nombrePato = nombrePato;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreLago() {
        return nombreLago;
    }

    public void setNombreLago(String nombreLago) {
        this.nombreLago = nombreLago;
    }

    public String getDNICliente() throws SQLException {
        ResultSet Consult = IO.introduceRegistros("SELECT DNI FROM CLIENTES WHERE IDCliente = " + this.IDCliente);
        Consult.next();

        return Consult.getString("DNI");
    }

    public String getNumCartilla() throws SQLException {
        ResultSet Consult = IO.introduceRegistros("SELECT Num_Cartilla FROM PATOS WHERE IDPato = " + this.IDPato);
        Consult.next();

        return Consult.getString("Num_Cartilla");
    }

    private String buscarNombres(String deQue, int ID) throws SQLException {
        String encontrado = "", columna = "", tabla = "", coincidir = "";
        switch (deQue) {
            case "Pato":
                columna = "Nombre_Pato";
                tabla = "PATOS";
                coincidir = "IDPato";
                break;
            case "Cliente":
                columna = "CONCAT(Nombre_Cliente, ' ', Apellidos_Cliente) AS 'Nombre Completo'";
                tabla = "CLIENTES";
                coincidir = "IDCliente";
                break;
            case "Lago":
                columna = "Nombre_Lago";
                tabla = "LAGOS";
                coincidir = "IDLago";
                break;
        }
        ResultSet Consult = IO.introduceRegistros("SELECT " + columna + " FROM " + tabla + " WHERE " + coincidir + " = " + ID);
        Consult.next();

        switch (deQue) {
            case "Pato":
                return encontrado = Consult.getString("Nombre_Pato");
            case "Cliente":
                return encontrado = Consult.getString("Nombre Completo");
            case "Lago":
                return encontrado = Consult.getString("Nombre_Lago");
        }

        return encontrado = "Nada";
    }
}
