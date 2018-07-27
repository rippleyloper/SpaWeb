/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Categoria;

/**
 *
 * @author Tulio
 */
public class ControladorCategoria {
    public boolean agregarCategoria(Categoria cate) throws ClassNotFoundException, SQLException{
        String sql="INSERT INTO `categoria`(`nombre`, `Tipo_Servicio_idTipo`) VALUES (?,?)";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, cate.getNombre());
        stm.setInt(2, cate.getId());
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
    public boolean modificarCategoria(Categoria cate) throws SQLException, ClassNotFoundException{
        String sql="UPDATE `categoria` SET `nombre`=?,`Tipo_Servicio_idTipo`=? WHERE idCategoria=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, cate.getNombre());
        stm.setInt(2, cate.getId());
        stm.setInt(3, cate.getIdCate());
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
    //
    public boolean eliminarCategoria(int id) throws SQLException, ClassNotFoundException{
        String sql="update categoria set activo=0 WHERE idCategoria=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setInt(1, id);
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
}
