/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.PreparedStatement;
import modelo.Sucursal;

/**
 *
 * @author Prueba
 */
public class ControladorSucursal {
    public String agregarSucursal(Sucursal sucursal){
        try {
            Conexion con=Conexion.newInstance();
            String sql="INSERT INTO `sucursal`( `idBarrio`, `nombre`, `numero`, `direccion`) VALUES (?,?,?,?)";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1,sucursal.getIdBarrio());
            stm.setString(2, sucursal.getNombre());
            stm.setString(3, sucursal.getNumero());
            stm.setString(4, sucursal.getDireccio());
            int resultaod=stm.executeUpdate();
            stm.close();
            con.desconectar();
            if(resultaod==1){
                return "Sucursal agregada correctamente";
            }
            else{
                return "Sucursal no agregada";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
