/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.DetalleServicio;
import modelo.Persona;
import modelo.Trabajador;

/**
 *
 * @author Prueba
 */
public class ControladorDetalleServicio {
    private int obtenerId(){
        try {
            Conexion con=Conexion.newInstance();
            String sql="SELECT MAX(idDetalle) as id from detalle_servicio";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            ResultSet rs=stm.executeQuery();
            int retorno=0;
            while(rs.next()){
                retorno=rs.getInt("id");
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 1550;
        }
    }
    public String insertarDetalle(DetalleServicio detalle){
        try {
            Conexion con=Conexion.newInstance();
            String sql="INSERT INTO `detalle_servicio`(`idServicio`, `idPersona`, `idAsistencia`, `hora`, `fecha`, `observacion`,precio,descuento,pagado)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, detalle.getIdServicio());
            System.out.println(detalle.getIdPersona());
            stm.setInt(2,detalle.getIdPersona());
            stm.setInt(3, 3);
            stm.setTime(4, detalle.getHora());
            stm.setDate(5, detalle.getFecha());
            stm.setString(6, detalle.getDetalle());
            stm.setInt(7, detalle.getPrecio());
            stm.setDouble(8, detalle.getDescuento());
            stm.setDouble(9, detalle.getPagado());
            int retorno=stm.executeUpdate();
            stm.close();
            con.desconectar();
            if(retorno==1){
                return "Agregado";
            }else{
                return "No Agregado";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
    public String agregarDetalleTrabajador(int[] trabajadores){
        try {
            Conexion con=Conexion.newInstance();
            String sql="INSERT INTO `detalle_trabajador`(`idServicio`, `idTrabajador`) VALUES (?,?);";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            int acum=0;
            for (int i = 0; i <trabajadores.length; i++) {
                stm=con.getConexion().prepareStatement(sql);
                stm.setInt(1, obtenerId());
                stm.setInt(2, trabajadores[i]);
                stm.executeUpdate();
                acum++;
            }
            stm.close();
            con.desconectar();
            return "Agregado";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
    public String agregarDetalleTrabajador(int trabajadores){
        try {
            Conexion con=Conexion.newInstance();
            String sql="INSERT INTO `detalle_trabajador`(`idServicio`, `idTrabajador`) VALUES (?,?);";
            PreparedStatement stm=con.getConexion().prepareStatement(sql);
            stm=con.getConexion().prepareStatement(sql);
            stm.setInt(1, obtenerId());
            stm.setInt(2, trabajadores);
            stm.executeUpdate();
            stm.close();
            con.desconectar();
            return "Agregado";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public ArrayList<DetalleServicio> obtenerServicios() throws ClassNotFoundException, SQLException, ParseException, Exception{
        
        String sql="SELECT ds.idDetalle as id,per.dni as dni, per.nombre as nombre,per.apellido as apellido,car.nombre as categoria,tSer.nombre as tipo,ser.nombre as servicio,ser.duracion as duracion,ds.fecha as inicio,ds.hora as hora,ds.precio as precio,asis.nombre as asis,ds.observacion as detalle, ds.descuento as descuento, ds.pagado as pagado FROM detalle_servicio ds "
                + "JOIN Persona per on(ds.idPersona=per.idPersona) "
                + "JOIN servicio ser on (ser.idServicio=ds.idServicio)"
                + "JOIN asistencia asis on(ds.idAsistencia=asis.idAsistencia) "
                + "JOIN categoria car on(car.idCategoria=ser.idCategoria) "
                + "JOIN tipo_servicio tSer on(tSer.idTipo=car.Tipo_Servicio_idTipo)"
                + "WHERE ds.idAsistencia=3";
        Conexion con=Conexion.newInstance();
        ResultSet rs=con.getConexion().prepareStatement(sql).executeQuery();
        ArrayList<DetalleServicio> servicios=new ArrayList<DetalleServicio>();
        if(rs!=null){
            //int idDetalle, int idServicio, int idPersona, int idAsistencia, Time hora, Date fecha, String detalle, int precio, Persona per,int duracion
            while(rs.next()){
                servicios.add(new DetalleServicio(rs.getInt("id"), rs.getString("servicio"), rs.getString("tipo"),rs.getString("categoria"), rs.getString("asis"), rs.getString("hora"), new Date(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("inicio")).getTime()), rs.getString("detalle"), rs.getInt("precio"), new Persona(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido")), rs.getInt("duracion"),rs.getInt("descuento"),rs.getInt("pagado")));
            }   
        }
        con.desconectar();
        return servicios;
        
    }
    public boolean marcarComo(int id, int asis) throws ClassNotFoundException, SQLException{
        String sql="update detalle_servicio set idAsistencia=? WHERE idDetalle=?";
        Conexion con=Conexion.newInstance();
        PreparedStatement stm=con.getConexion().prepareStatement(sql);
        stm.setInt(1, asis);
        stm.setInt(2, id);
        int result=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (result>=1)?true :false;
    }
    public boolean pagar(int id,int monto) throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="UPDATE detalle_servicio set pagado=? WHERE idDetalle=?";
        PreparedStatement stm=con.getConexion().prepareCall(sql);
        stm.setInt(1, monto);
        stm.setInt(2, id);
        int res=stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (res>0)?true:false;
    }
    public boolean asistido() throws SQLException, ClassNotFoundException{
        Conexion con=Conexion.newInstance();
        String sql="UPDATE detalle_servicio set idAsistencia=2 where date_format(fecha,\"%M %d %Y\")=date_format(subdate(current_date, 1),\"%M %d %Y\");";
        Statement stm=con.getConexion().createStatement();
        int rs=stm.executeUpdate(sql);
        stm.close();
        con.desconectar();
        return (rs>0)?true:false;
    }
    
}
