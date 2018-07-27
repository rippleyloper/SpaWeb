/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Listas;
import modelo.Persona;
import modelo.Usuario;


/**
 *
 * @author Prueba
 */
public class ControladorUsuario {
    
    public Listas crearUsuario(Usuario usuario){
        try {
            if(obtenerId(usuario.getDni()) == 0){
                Conexion con =Conexion.newInstance();
                String sql="INSERT INTO `persona`( `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, "
                        + "`direccion`, `fechaNacimiento`, `numero`, `correo`, `activo`) VALUES (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pstm=con.getConexion().prepareStatement(sql);
                pstm.setInt(1, usuario.getIdBarrio());
                pstm.setInt(2, usuario.getIdGenero());
                pstm.setString(3, usuario.getDni());
                pstm.setString(4, usuario.getNombre());
                pstm.setString(5, usuario.getApellido());
                pstm.setString(6, usuario.getDireccion());
                pstm.setString(7, usuario.getFechaNacimiento());
                pstm.setString(8, usuario.getNumero());
                pstm.setString(9, usuario.getCorreo());
                pstm.setInt(10, 1);
                int resultado=pstm.executeUpdate();
                sql="INSERT INTO `usuario`(`idPersona`, `idTipo`, `contrasena`,activo) VALUES (?,?,?,?)";
                pstm=con.getConexion().prepareStatement(sql);
                pstm.setInt(1, obtenerId(usuario.getDni()));
                pstm.setInt(2, usuario.getIdTipoUsuario());
                pstm.setString(3, usuario.getContrasena());
                pstm.setInt(4, 1);
                resultado=pstm.executeUpdate();
                pstm.close();
                con.desconectar();
                return new Listas(1,"Agregado");
            }else{
                if(buscar(usuario.getDni())==0){
                    Conexion con=Conexion.newInstance();
                    String sql="INSERT INTO `usuario`(`idPersona`, `idTipo`, `contrasena`,activo) VALUES (?,?,?,?)";
                    PreparedStatement pstm=con.getConexion().prepareStatement(sql);
                    pstm.setInt(1, obtenerId(usuario.getDni()));
                    pstm.setInt(2, usuario.getIdTipoUsuario());
                    pstm.setString(3, usuario.getContrasena());
                    pstm.setInt(4, 1);
                    int resultado=pstm.executeUpdate();
                    pstm.close();
                    con.desconectar();
                    return new Listas(1,"Agregado");
                }else{
                    return new Listas(2,"Usuario ya existe o esta eliminado");
                }
            }
            
        } catch (Exception e) {
            return new Listas(2,e.getMessage());
        }
    }
    private int obtenerId(String dni){
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
    public int buscar(String dni){
        try {
            int retorno=0;
            Conexion con=Conexion.newInstance();
            String sql="select idUsuario from usuario join persona per on(usuario.idPersona=per.idPersona) where per.dni=?";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                retorno=rs.getInt("idUsuario");
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int modificar(Usuario usuario){
        try {
            Conexion con=Conexion.newInstance();
            String sql="UPDATE `usuario` join persona per on(usuario.idPersona=per.idPersona) SET `idTipo`=?,`contrasena`=? WHERE per.dni=?";
            PreparedStatement pstm=con.getConexion().prepareStatement(sql);
            pstm.setInt(1, usuario.getIdTipoUsuario());
            pstm.setString(2,usuario.getContrasena());
            pstm.setString(3, usuario.getDni());
            int resultado=pstm.executeUpdate();
            pstm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }
    public Usuario obtenerUsuario(String dni) throws SQLException, ClassNotFoundException, Exception{
        
            String sql="SELECT  `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`, us.idTipo, us.contrasena FROM `persona` "
                    + "JOIN usuario us on(persona.idPersona=us.idPersona) where persona.dni=? and (persona.activo=1 and us.activo=1)";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            Usuario user=null;
            ResultSet rs=stm.executeQuery();
            rs.first();
            user=new Usuario(rs.getInt("us.idTipo"),"abbbb", rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo"));
            stm.close();
            con.desconectar();
            return user;
        
    }
    public ArrayList<Usuario> obtener(){
        try {
            String sql="SELECT  `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`, us.idTipo, us.contrasena FROM `persona` "
                    + "JOIN usuario us on(persona.idPersona=us.idPersona) where (persona.activo=1 and us.activo=1)";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                usuarios.add(new Usuario(rs.getInt("us.idTipo"),"12345", rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo")));
            }
            stm.close();
            con.desconectar();
            return usuarios;
        } catch (Exception e) {
            return null;
        }
    }
    public Listas eliminar(String dni){
        try {
            String sql="UPDATE `usuario` join persona per on(usuario.idPersona=per.idPersona) SET usuario.activo=0 WHERE per.dni=?";
            Conexion con=Conexion.newInstance();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            int resultado=stm.executeUpdate();
            stm.close();
            con.desconectar();
            return new Listas(1,"Eliminado");
        } catch (Exception e) {
            return new Listas(2,e.getMessage());
        }
    }
    public ArrayList<Usuario> obtenerEliminados() throws ClassNotFoundException, SQLException, Exception{
        
        String sql="SELECT  `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, `direccion`, `fechaNacimiento`, `numero`, `correo`, us.idTipo, us.contrasena FROM `persona` "
                + "JOIN usuario us on(persona.idPersona=us.idPersona) where us.activo=0";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        ArrayList<Usuario> usuarios=new ArrayList<Usuario>();
        ResultSet rs=stm.executeQuery();
        while(rs.next()){
            usuarios.add(new Usuario(rs.getInt("us.idTipo"),rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"),rs.getString("correo")));
        }
        stm.close();
        con.desconectar();
        return usuarios;
        
    }
    public boolean activar(String dni) throws SQLException, ClassNotFoundException{
        
        String sql="UPDATE `usuario` join persona per on(usuario.idPersona=per.idPersona) SET usuario.activo=1 WHERE per.dni=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        int resultado=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (resultado==0)?false:true;
    }
    public int buscarEliminado(String dni){
        try {
            int retorno=0;
            Conexion con=Conexion.newInstance();
            String sql="select idUsuario from usuario join persona per on(usuario.idPersona=per.idPersona) where per.dni=? and(usuario.activo=0 and per.activo=1)";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                retorno=rs.getInt("idUsuario");
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 0;
        }
    }
    public String ingresar(Persona persona){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT CONCAT(per.nombre,' ',per.apellido) as nombre,us.idTipo as id, us.idPersona as idPer from usuario us "
                    + "JOIN persona per on(us.idPersona=per.idPersona) where us.contrasena=? and per.dni=? and us.activo=1";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setString(1, persona.getPassword());
            stm.setString(2, persona.getDni());
            ResultSet rs=stm.executeQuery();
            if(rs!=null){
                String retorno="";
                while(rs.next()){
                    return retorno=rs.getString("nombre")+","+rs.getInt("id")+","+rs.getInt("idPer");
                }
            }
            return ""; 
        } catch (Exception e) {
            return "";
        }
    }
    public int obtenerIdSucu(int idPersona){
        try {
            Conexion con = Conexion.newInstance();
            String sql="SELECT sucursal.idSucursal as idSucursal FROM sucursal JOIN trabajador "
                    + "tra ON (tra.idSucursal=sucursal.idSucursal) WHERE tra.idPersona=?";
            PreparedStatement stm=con.getConexion().prepareCall(sql);
            stm.setInt(1, idPersona);
            ResultSet rs=stm.executeQuery();
            rs.first();
            return rs.getInt("idSucursal");
            
        } catch (Exception e) {
            return 0;
        }
    }
}
