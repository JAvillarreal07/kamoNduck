package Modelo;

public class Pato {

    private String Nombre_Pato, Raza, Descripcion;
    private int IDPato, Edad, Num_Cartilla;

    public Pato(int IDPato, String nombre_pato, String raza, int edad, int num_Cartilla, String descripcion) {
        this.IDPato = IDPato;
        this.Nombre_Pato = nombre_pato;
        this.Raza = raza;
        this.Edad = edad;
        this.Num_Cartilla = num_Cartilla;
        this.Descripcion = descripcion;

    }

    public String getNombre_Pato() {return Nombre_Pato;}

    public void setNombre_Pato(String nombre_Pato) {Nombre_Pato = nombre_Pato;}

    public String getRaza() {return Raza;}

    public void setRaza(String raza) {Raza = raza;}

    public String getDescripcion() {return Descripcion;}

    public void setDescripcion(String descripcion) {Descripcion = descripcion;}

    public int getIDPato() {return IDPato;}

    public void setIDPato(int IDPato) {this.IDPato = IDPato;}

    public int getEdad() {return Edad;}

    public void setEdad(int edad) {Edad = edad;}

    public int getNum_Cartilla() {return Num_Cartilla;}

    public void setNum_Cartilla(int num_Cartilla) {Num_Cartilla = num_Cartilla;}
}
