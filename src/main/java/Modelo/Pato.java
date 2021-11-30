package Modelo;

public class Pato {

    private String Nombre, Raza, Descripcion;
    private int IDPato, Edad, Num_Cartilla;

    public Pato(String nombre, String raza, String descripcion, int IDPato, int edad, int num_Cartilla) {
        Nombre = nombre;
        Raza = raza;
        Descripcion = descripcion;
        this.IDPato = IDPato;
        Edad = edad;
        Num_Cartilla = num_Cartilla;


    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getIDPato() {
        return IDPato;
    }

    public void setIDPato(int IDPato) {
        this.IDPato = IDPato;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public int getNum_Cartilla() {
        return Num_Cartilla;
    }

    public void setNum_Cartilla(int num_Cartilla) {
        Num_Cartilla = num_Cartilla;
    }
}
