/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import conexion.Conexion;
import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Persona;

/**
 *
 * @author Prueba
 */
public class ControladorCliente {
    
    public int agregarCliente(Cliente cliente,int asignacion) throws ClassNotFoundException, SQLException{
        if(obtenerId(cliente.getDni())==0){
            Conexion con =Conexion.newInstance();
            String sql="INSERT INTO `persona`( `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, "
                    + "`direccion`, `fechaNacimiento`, `numero`, `correo`, `activo`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm=con.getConexion().prepareStatement(sql);
            pstm.setInt(1, cliente.getIdBarrio());
            pstm.setInt(2, cliente.getIdGenero());
            pstm.setString(3, cliente.getDni());
            pstm.setString(4, cliente.getNombre());
            pstm.setString(5, cliente.getApellido());
            pstm.setString(6, cliente.getDireccion());
            pstm.setString(7, cliente.getFechaNacimiento());
            pstm.setString(8, cliente.getNumero());
            pstm.setString(9, cliente.getCorreo());
            pstm.setInt(10, 1);
            pstm.executeUpdate();
            sql="INSERT INTO `cliente`(`idPersona`, `activo`) VALUES (?,?)";
            pstm=con.getConexion().prepareStatement(sql);
            pstm.setInt(1, obtenerId(cliente.getDni()));
            pstm.setInt(2,1);
            pstm.executeUpdate();
            pstm.close();
            con.desconectar();
            return 1;
        }else{
            if(buscar(cliente.getDni())==0){
                Conexion con=Conexion.newInstance();
                String sql="INSERT INTO `cliente`(`idPersona`, `activo`) VALUES (?,?)";
                PreparedStatement pstm=con.getConexion().prepareStatement(sql);
                pstm=con.getConexion().prepareStatement(sql);
                pstm.setInt(1, obtenerId(cliente.getDni()));
                pstm.setInt(2,1);
                pstm.executeUpdate();
                pstm.close();
                con.desconectar();
                return 1;
            }
        }
        return 2;
    }
    public int buscar(String dni) throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT `idCliente` FROM `cliente` cli JOIN persona per on(per.idPersona=cli.idPersona) WHERE per.dni=?";
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        int resultado=0;
        ResultSet rs=stm.executeQuery();
        while(rs.next())
            resultado=rs.getInt("idCliente");
        stm.close();
        con.desconectar();
        return resultado;
    }
    
    public Persona obtenerCliente(String dni) throws ClassNotFoundException, SQLException, Exception{
        
        String sql="SELECT `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo` FROM `cliente`"
                + " join persona per on(cliente.idPersona=per.idPersona) where per.dni=? and cliente.activo=1";
       Conexion con=Conexion.newInstance();
       PreparedStatement stm=con.getConexion().prepareStatement(sql);
       stm.setString(1, dni);
       Persona cli=null;
       ResultSet rs=stm.executeQuery();
       while(rs.next()){
           cli=new Persona(rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo"));
       }
       stm.close();
       con.desconectar();
       return cli;
        
    }
    public int buscarEliminado(String dni) throws SQLException, ClassNotFoundException{
        
        int retorno=0;
        Conexion con=Conexion.newInstance();
        String sql="SELECT `idCliente` FROM `cliente` cli JOIN persona per on(per.idPersona=cli.idPersona) WHERE per.dni=? where cli.activo=0";
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        ResultSet rs=stm.executeQuery();
        while(rs.next()){
            retorno=rs.getInt("idCliente");
        }
        stm.close();
        con.desconectar();
        return retorno;
        
    }
    public  int obtenerId(String dni){
        try {
            Conexion con=Conexion.newInstance();
            int resultado=0;
            String sql="SELECT idPersona from persona where dni=?";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                resultado=rs.getInt("idPersona");
            }
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }
    public int modificar(Cliente cliente){
        try {
            String sql="UPDATE `persona` SET `idBarrio`=?,`idGenero`=?,`nombre`=?,`apellido`=?,`direccion`=?,`fechaNacimiento`=?,"
                    + "`numero`=?,`correo`=? WHERE `dni`=?";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, cliente.getIdBarrio());
            stm.setInt(2, cliente.getIdGenero());
            stm.setString(3, cliente.getNombre());
            stm.setString(4, cliente.getApellido());
            stm.setString(5, cliente.getDireccion());
            stm.setString(6, cliente.getFechaNacimiento());
            stm.setString(7, cliente.getNumero());
            stm.setString(8, cliente.getCorreo());
            stm.setString(9, cliente.getDni());
            int resultado=stm.executeUpdate();
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }
    public ArrayList<Persona> obtenerClientes()throws SQLException,Exception{
             String sql="SELECT `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`"
                     + " FROM `cliente` join persona per on(cliente.idPersona=per.idPersona) where cliente.activo=1";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ArrayList<Persona> clientes=new ArrayList<Persona>();
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                clientes.add(new Persona( rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo")));
            }
            stm.close();
            con.desconectar();
            return clientes;
    }
    public int eliminar(String dni){
        try {
            String sql="UPDATE `cliente` join persona per on(cliente.idPersona=per.idPersona) SET cliente.activo=0 WHERE per.dni=?";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            int resultado=stm.executeUpdate();
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }
    public ArrayList<Persona> obtenerClientesEliminados()throws SQLException,Exception{
             String sql="SELECT `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`"
                     + " FROM `cliente` join persona per on(cliente.idPersona=per.idPersona) where cliente.activo=0";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ArrayList<Persona> clientes=new ArrayList<Persona>();
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                clientes.add(new Persona( rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo")));
            }
            stm.close();
            con.desconectar();
            return clientes;
    }
    public int activar(String dni){
        try {
            String sql="UPDATE `cliente` join persona per on(cliente.idPersona=per.idPersona) SET cliente.activo=1 WHERE per.dni=?";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            int resultado=stm.executeUpdate();
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }
    public int obtenerDepartamento(int idBarrio){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT ba.idDepartamento as idDepartamento FROM `departamento` join barrio ba"
                    + " on(departamento.idDepartamento=ba.idDepartamento) where ba.idBarrio=?";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, idBarrio);
            ResultSet rs=stm.executeQuery();
            int retorno=0;
            while(rs.next()){
                retorno=rs.getInt("idDepartamento");
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 0;
        }
    }
}
