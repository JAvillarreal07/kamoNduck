package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//Clase que maneja la conexión con la base de datos
public class IOBaseDatos {

    //Método para actualizar datos en la base de datos
    public void actualizaRegistros(String actualiza) throws SQLException{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/kamonduck";
            Connection conexBd = DriverManager.getConnection(urlCon, "root", "root");
            Statement encapsulaCons = conexBd.createStatement();

            int filActualizadas = encapsulaCons.executeUpdate(actualiza);
            if(filActualizadas > 0){System.out.print("Actualizado");}

            encapsulaCons.close();
            conexBd.close();
        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }

    //Método para insertar datos en la base de datos
    public ResultSet introduceRegistros(String consulta) {
        ResultSet resulCons = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/kamonduck";
            Connection conexBd = DriverManager.getConnection(urlCon, "root", "root");
            Statement encapsulaCons = conexBd.createStatement();

            resulCons = encapsulaCons.executeQuery(consulta);

            encapsulaCons.close();
            conexBd.close();
        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resulCons;
    }

    Connection con = null;

    public Connection conexion() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/kamonduck";
            con = DriverManager.getConnection(urlCon, "root", "root");


        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        }

        return con;
    }
}