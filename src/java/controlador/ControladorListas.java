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

/**
 *
 * @author Prueba
 */
public class ControladorListas {
    public ArrayList<Listas> obtenerDepartamento(){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT `idDepartamento`, `nombre` FROM `departamento` WHERE activo=1";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            ArrayList<Listas> departamentos=new ArrayList<Listas>();
            while(rs.next()){
                departamentos.add(new Listas(rs.getInt("idDepartamento"), rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return departamentos;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerBario(int id){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT `idBarrio`, `nombre` FROM `barrio`  WHERE idDepartamento=? and activo=1 ORDER BY nombre";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs=stm.executeQuery();
            ArrayList<Listas> barrios=new ArrayList<Listas>();
            while(rs.next()){
                barrios.add(new Listas(rs.getInt("idBarrio"), rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return barrios;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerCategoriaServicio(int idServicio){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT `idCategoria`, `nombre` FROM `categoria` WHERE Tipo_Servicio_idTipo=? and activo=1";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, idServicio);
            ResultSet rs=stm.executeQuery();
            ArrayList<Listas>categorias=new ArrayList<Listas>();
            while(rs.next()){
                categorias.add(new Listas(rs.getInt("idCategoria"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return categorias;
            
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas>obtenerServicio(int id) throws ClassNotFoundException, SQLException{
        String sql="SELECT idServicio, nombre from servicio where idCategoria=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs=stm.executeQuery();
        ArrayList<Listas> listas=new ArrayList<Listas>();
        if(rs!=null){
            while(rs.next())
                listas.add(new Listas(rs.getInt("idServicio"), rs.getString("nombre")));
        }
        return listas;
    }
    public ArrayList<Listas> obtenerEspecialidad(){
        try {
            Conexion con=Conexion.newInstance();
            String slq="SELECT `idEspecialidad`, `nombre` FROM `especialidad` WHERE activo=1";
            PreparedStatement stm=con.getConexion().prepareStatement(slq);
            ArrayList<Listas> especialidades=new ArrayList<Listas>();
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                especialidades.add(new Listas(rs.getInt("idEspecialidad"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return especialidades;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerGenero(){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT idGenero, nombre FROM `genero` WHERE activo=1";
            ArrayList<Listas>generos=new ArrayList<Listas>();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                generos.add(new Listas(rs.getInt("idGenero"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return generos;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerTipoServicio(){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT `idTipo`, `nombre` FROM `tipo_servicio` WHERE activo=1";
            ArrayList<Listas> tipoServicios=new ArrayList<Listas>();
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                tipoServicios.add(new Listas(rs.getInt("idTipo"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return tipoServicios;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerTipoUsuario(){
        try {
            Conexion con=Conexion.newInstance();
            String slq="SELECT `idTipo`, `nombre` FROM `tipo_usuario` WHERE activo=1";
            ArrayList<Listas> tipoUsuarios=new ArrayList<Listas>();
            PreparedStatement stm=con.getConexion().prepareStatement(slq);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                tipoUsuarios.add(new Listas(rs.getInt("idTipo"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return tipoUsuarios;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<Listas> obtenerSucursal(){
        try {
            Conexion con=Conexion.newInstance();
            String slq="SELECT `idSucursal`, CONCAT('Sucursal: ',`nombre`) as nombre FROM `sucursal` WHERE activo=1";
            PreparedStatement stm=con.getConexion().prepareStatement(slq);
            ResultSet rs=stm.executeQuery();
            ArrayList<Listas> listas=new ArrayList<Listas>();
            while(rs.next()){
                listas.add(new Listas(rs.getInt("idSucursal"),rs.getString("nombre")));
            }
            stm.close();
            con.desconectar();
            return listas;
        } catch (Exception e) {
            return null;
        }
    }
    
}   
