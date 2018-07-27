/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Listas;

/**
 *
 * @author Tulio
 */
public class ControladorEspecialidad {
    public boolean agregarTipo(Listas ls) throws SQLException, ClassNotFoundException{
        String sql="INSERT INTO `especialidad`(`nombre`,activo) VALUES (?,?)";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, ls.getNombres());
        stm.setInt(2,1);
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
    public boolean moficiarTipo(Listas ls) throws SQLException, ClassNotFoundException{
        String sql="UPDATE `especialidad` SET `nombre`=? WHERE `idEspecialidad`=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setString(1, ls.getNombres());
        stm.setInt(2,ls.getId());
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
    //UPDATE `tipo_servicio` SET `activo`=? WHERE `idTipo`=?
    public boolean eliminarTipo(int id) throws SQLException, ClassNotFoundException{
        String sql="UPDATE `especialidad` SET `activo`=0 WHERE idEspecialidad=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setInt(1, id);
        int rs=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
}
