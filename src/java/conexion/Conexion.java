/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Prueba
 */
public class Conexion {
    private Connection conexion;
    
    private Conexion() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd","spa","Spa1_Spa2_Spa3");
    }
    private Conexion(String puerto) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        conexion=DriverManager.getConnection("jdbc:mysql://localhost:"+puerto+"/bd","spa","Spa1_Spa2_Spa3");
    }
    public synchronized static  Conexion newInstance() throws ClassNotFoundException, SQLException{
        Conexion con=new Conexion();
        return con;
    }
    public synchronized static  Conexion newInstance(String puerto) throws ClassNotFoundException, SQLException{
        Conexion con=new Conexion(puerto);
        return con;
    }
    public Connection getConexion() {
        return conexion;
    }
    public void desconectar() throws SQLException{
        getConexion().close();
    }
}
