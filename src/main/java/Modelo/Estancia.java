package Modelo;

import java.util.Date;

public class Estancia {
    private int IDEstancia, IDCliente, IDPato, IDLago;
    private Date Fecha_Ingreso, Fecha_Salida;

    public Estancia(int IDEstancia, int IDCliente, int IDPato, int IDLago, Date fecha_Ingreso, Date fecha_Salida) {
        this.IDEstancia = IDEstancia;
        this.IDCliente = IDCliente;
        this.IDPato = IDPato;
        this.IDLago = IDLago;
        Fecha_Ingreso = fecha_Ingreso;
        Fecha_Salida = fecha_Salida;
    }

    public int getIDEstancia() {return IDEstancia;}

    public void setIDEstancia(int IDEstancia) {this.IDEstancia = IDEstancia;}

    public int getIDCliente() {return IDCliente;}

    public void setIDCliente(int IDCliente) {this.IDCliente = IDCliente;}

    public int getIDPato() {return IDPato;}

    public void setIDPato(int IDPato) {this.IDPato = IDPato;}

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}

    public Date getFecha_Ingreso() {return Fecha_Ingreso;}

    public void setFecha_Ingreso(Date fecha_Ingreso) {Fecha_Ingreso = fecha_Ingreso;}

    public Date getFecha_Salida() {return Fecha_Salida;}

    public void setFecha_Salida(Date fecha_Salida) {Fecha_Salida = fecha_Salida;}
}
