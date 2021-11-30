package Modelo;

public class Lago {

    private int IDLago, Tarifa, Cap_Patos;
    private String Tamanho, Nombre_Lago;

    public Lago(int IDLago, int tarifa, int cap_Patos, String tamanho, String nombre_Lago) {
        this.IDLago = IDLago;
        Tarifa = tarifa;
        Cap_Patos = cap_Patos;
        Tamanho = tamanho;
        Nombre_Lago = nombre_Lago;
    }

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}

    public int getTarifa() {return Tarifa;}

    public void setTarifa(int tarifa) {Tarifa = tarifa;}

    public int getCap_Patos() {return Cap_Patos;}

    public void setCap_Patos(int cap_Patos) {Cap_Patos = cap_Patos;}

    public String getTamanho() {return Tamanho;}

    public void setTamanho(String tamanho) {Tamanho = tamanho;}

    public String getNombre_Lago() {return Nombre_Lago;}

    public void setNombre_Lago(String nombre_Lago) {Nombre_Lago = nombre_Lago;}
}
