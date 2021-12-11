package Modelo;

public class Pato {

    private String Nombre_Pato, Raza, Descripcion;
    private int IDPato, Edad, Num_Cartilla;

    public Pato(int IDPato, String Nombre_Pato, String Rraza, int Edad, int Num_Cartilla, String Descripcion) {
        this.IDPato = IDPato;
        this.Nombre_Pato = Nombre_Pato;
        this.Raza = Rraza;
        this.Edad = Edad;
        this.Num_Cartilla = Num_Cartilla;
        this.Descripcion = Descripcion;

    }

    public String getNombre_Pato() {return Nombre_Pato;}

    public void setNombre_Pato(String Nombre_Pato) {
        this.Nombre_Pato = Nombre_Pato;}

    public String getRaza() {return Raza;}

    public void setRaza(String Raza) {
        this.Raza = Raza;}

    public String getDescripcion() {return Descripcion;}

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;}

    public int getIDPato() {return IDPato;}

    public void setIDPato(int IDPato) {this.IDPato = IDPato;}

    public int getEdad() {return Edad;}

    public void setEdad(int Edad) {
        this.Edad = Edad;}

    public int getNum_Cartilla() {return Num_Cartilla;}

    public void setNum_Cartilla(int Num_Cartilla) {
        this.Num_Cartilla = Num_Cartilla;}
}
