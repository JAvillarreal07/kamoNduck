package Modelo;

public class Lago {

    private int IDLago, Tarifa, Cap_Patos, Patos_Dentro;
    private String Tamanho, Nombre_Lago;

    public Lago(int IDLago, String nombre_Lago, String tamanho, int cap_Patos, int patos_Dentro, int tarifa  ) {
        this.IDLago = IDLago;
        this.Nombre_Lago = nombre_Lago;
        this.Tamanho = tamanho;
        this.Cap_Patos = cap_Patos;
        this.Patos_Dentro = patos_Dentro;
        this.Tarifa = tarifa;
    }

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}

    public int getTarifa() {return Tarifa;}

    public void setTarifa(int tarifa) {Tarifa = tarifa;}

    public int getCap_Patos() {return Cap_Patos;}

    public void setCap_Patos(int cap_Patos) {Cap_Patos = cap_Patos;}

    public int getPatos_dentro() {return Patos_Dentro;}

    public void setPatos_dentro(int patos_dentro) {Patos_Dentro = patos_dentro;}

    public String getTamanho() {return Tamanho;}

    public void setTamanho(String tamanho) {Tamanho = tamanho;}

    public String getNombre_Lago() {return Nombre_Lago;}

    public void setNombre_Lago(String nombre_Lago) {Nombre_Lago = nombre_Lago;}
}
