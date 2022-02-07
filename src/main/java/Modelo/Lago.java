package Modelo;

public class Lago {

    private int IDLago, Tarifa, Cap_Patos, Patos_Dentro;
    private String Tamanho, Nombre_Lago;
    private CustomProgressBar barra;

    public Lago(int IDLago, String Nombre_Lago, String Tamanho, int Cap_Patos, int Patos_Dentro, int Tarifa  ) {
        this.IDLago = IDLago;
        this.Nombre_Lago = Nombre_Lago;
        this.Tamanho = Tamanho;
        this.Cap_Patos = Cap_Patos;
        this.Patos_Dentro = Patos_Dentro;
        this.Tarifa = Tarifa;

        this.barra = new CustomProgressBar(Patos_Dentro, Cap_Patos);
    }

    public int getIDLago() {return IDLago;}

    public void setIDLago(int IDLago) {this.IDLago = IDLago;}

    public int getTarifa() {return Tarifa;}

    public void setTarifa(int Tarifa) {
        this.Tarifa = Tarifa;}

    public int getCap_Patos() {return Cap_Patos;}

    public void setCap_Patos(int Cap_Patos) {
        this.Cap_Patos = Cap_Patos;}

    public int getPatos_dentro() {return Patos_Dentro;}

    public void setPatos_dentro(int Patos_Dentro) {
        this.Patos_Dentro = Patos_Dentro;}

    public String getTamanho() {return Tamanho;}

    public void setTamanho(String Tamanho) {
        this.Tamanho = Tamanho;}

    public String getNombre_Lago() {return Nombre_Lago;}

    public void setNombre_Lago(String Nombre_Lago) {
        this.Nombre_Lago = Nombre_Lago;}

    public CustomProgressBar getBarra() {
        return barra;
    }

    public void setBarra(CustomProgressBar barra) {
        this.barra = barra;
    }
}
