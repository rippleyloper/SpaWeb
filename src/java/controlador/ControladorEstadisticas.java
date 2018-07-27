/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

/**
 *
 * @author Tulio
 */
public class ControladorEstadisticas {
    public int obtenerClientesA()throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="select count(idCliente) as id from cliente where activo=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerClientesI()throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="select count(idCliente) as id from cliente where activo=0";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    
    public int obtenerTrabajadoresA()throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idTrabajador) as id from trabajador WHERE activo=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTrabajadoresI()throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idTrabajador) as id from trabajador WHERE activo=0";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTotalAgendas() throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) as id FROM detalle_servicio";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTotalAsistio() throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) as id FROM detalle_servicio where idAsistencia=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTotalNoA() throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) as id FROM detalle_servicio where idAsistencia=2";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTotalEspera() throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) as id FROM detalle_servicio where idAsistencia=3";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerTotalCancelado() throws ClassNotFoundException, SQLException, ParseException, Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) as id FROM detalle_servicio where idAsistencia=4";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        if(rs.first())
            result=rs.getInt("id");
        stm.close();
        con.desconectar();
        return result;
    }
    public int calcularTotalDescuento() throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT TRUNCATE(precio*(descuento/100),0) as total, precio FROM `detalle_servicio` where idAsistencia=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int total=0;
        while(rs.next()){
            if(rs.getInt("total")==0)
                continue;
            total+=rs.getInt("total");
        }
        stm.close();
        con.desconectar();
        return total;
    }
    public int calcularTotal() throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT TRUNCATE(precio-(precio*(descuento/100)),0) as total, precio FROM `detalle_servicio` where idAsistencia=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int total=0;
        while(rs.next()){
            total+=rs.getInt("total");
        }
        stm.close();
        con.desconectar();
        return total;
    }
    public int calcularPagado() throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT SUM(pagado) as total from detalle_servicio where idAsistencia=1";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int total=0;
        rs.first();
        total=rs.getInt("total");
        stm.close();
        con.desconectar();
        return total;
    }
    public int obtenerServiciosHoy() throws SQLException, ClassNotFoundException,Exception{
        Conexion con=Conexion.newInstance();
        String sql="SELECT COUNT(idDetalle) total from detalle_servicio where idAsistencia=3 and DATE_FORMAT(fecha, \"%M %d %Y\")=date_format(curdate(),\"%M %d %Y\")";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        int result=0;
        rs.first();
        result=rs.getInt("total");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerCumMen() throws ClassNotFoundException, SQLException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT count(idPersona) as total FROM persona where date_format(fechaNacimiento,'%m') = date_format(curdate(),'%m')";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        rs.first();
        int result=0;
        result=rs.getInt("total");
        stm.close();
        con.desconectar();
        return result;
    }
    public int obtenerCumDia() throws ClassNotFoundException, SQLException{
        Conexion con=Conexion.newInstance();
        String sql="SELECT count(idPersona) as total FROM persona where date_format(fechaNacimiento,'%m-%d') = date_format(curdate(),'%m-%d');";
        Statement stm=con.getConexion().createStatement();
        ResultSet rs=stm.executeQuery(sql);
        rs.first();
        int result=0;
        result=rs.getInt("total");
        stm.close();
        con.desconectar();
        return result;
    }
}
