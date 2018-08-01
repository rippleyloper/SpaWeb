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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Detalle;
import modelo.ListaDetalleServicio;
import modelo.Listas;
import modelo.Persona;
import modelo.Servicio;

/**
 *
 * @author Prueba
 */
public class ControladorServicio {

    public boolean agregarServicio(Servicio servicio) throws SQLException, ClassNotFoundException {

        Conexion con = Conexion.newInstance();
        String sql = "INSERT INTO `servicio`( `idCategoria`, `nombre`, `duracion`, `precio`, `detalle`) "
                + "VALUES (?,?,?,?,?)";
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, servicio.getIdCategoria());
        stm.setString(2, servicio.getNombre());
        stm.setInt(3, servicio.getDuracion());
        stm.setInt(4, servicio.getPrecio());
        stm.setString(5, servicio.getDetalle());
        int resultado = stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (resultado > 0) ? true : false;

    }

    public boolean eliminarServicio(int id) throws ClassNotFoundException, SQLException {

        Conexion con = Conexion.newInstance();
        String sql = "UPDATE servicio SET activo=0 WHERE idServicio=?";
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, id);
        int resultado = stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (resultado > 0) ? true : false;
    }

    public int obtenerId() {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT COUNT(idDetalle)as total FROM `detalle_servicio`";
            int resultado = 0;
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                resultado = rs.getInt("total");
            }
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Servicio> obtenerPorTipo(int id) throws SQLException, ClassNotFoundException, Exception {
        Conexion con = Conexion.newInstance();
        String sql = "SELECT `idServicio`, `idCategoria`, `nombre`, `duracion`, `precio`, `detalle` FROM `servicio` WHERE activo=1 AND idCategoria=?";
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        if (rs != null) {
            while (rs.next()) {
                //int idCategoria, String nombre, int duracion, int precio, String detalle
                servicios.add(new Servicio(rs.getInt("idServicio"), rs.getInt("idCategoria"), rs.getString("nombre"), rs.getInt("duracion"), rs.getInt("precio"), rs.getString("detalle")));
            }
        }
        stm.close();
        con.desconectar();
        return servicios;
    }

    public Servicio buscarServicio(int id) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT se.idServicio ,se.idCategoria,se.nombre, `duracion`, `precio`,se.detalle FROM `servicio` se join categoria ca on(se.idCategoria=ca.idCategoria) "
                    + "join tipo_servicio ti on(ca.Tipo_Servicio_idTipo=ti.idTipo) where  se.idServicio=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Servicio servicio = null;
            if (rs != null) {
                while (rs.next()) {
                    //int idCategoria, String nombre, int duracion, int precio, String detalle
                    servicio = new Servicio(rs.getInt("se.idServicio"), rs.getInt("se.idCategoria"), rs.getString("se.nombre"), rs.getInt("duracion"), rs.getInt("precio"), rs.getString("se.detalle"));
                }
            }
            stm.close();
            con.desconectar();
            return servicio;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean modificar(Servicio servicio) throws SQLException, ClassNotFoundException {
        Conexion con = Conexion.newInstance();
        String sql = "UPDATE `servicio` SET `idCategoria`=?,`nombre`=?,`duracion`=?,`precio`=?,`detalle`=? WHERE `idServicio`=?";
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, servicio.getIdCategoria());
        stm.setString(2, servicio.getNombre());
        stm.setInt(3, servicio.getDuracion());
        stm.setInt(4, servicio.getPrecio());
        stm.setString(5, servicio.getDetalle());
        stm.setInt(6, servicio.getIdServicio());
        int rs = stm.executeUpdate();
        stm.close();
        con.desconectar();
        return (rs > 0) ? true : false;
    }

    public int obtenerIdTipo(int id) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT `Tipo_Servicio_idTipo` FROM `categoria` where idCategoria=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            int retorno = 0;
            if (rs != null) {
                while (rs.next()) {
                    retorno = rs.getInt("Tipo_Servicio_idTipo");
                }
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Servicio> obtenerPorFecha(String fecha) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT ds.idDetalle as id, ser.nombre as servicio, CONCAT(per.nombre,' ',per.apellido)as nombre, asai.nombre as estado, CONCAT(hora,' HRS') as hora , ds.precio as precio FROM `detalle_servicio` ds"
                    + " JOIN persona per on(ds.idPersona=per.idPersona)"
                    + " JOIN servicio ser on(ds.idServicio=ser.idServicio) "
                    + "JOIN asistencia asai on(ds.idAsistencia=asai.idAsistencia) where ds.fecha=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            stm.setDate(1, new Date(format.parse(fecha).getTime()));

            ArrayList<Servicio> servicios = new ArrayList<Servicio>();
            ResultSet rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    servicios.add(new Servicio(rs.getInt("id"), rs.getString("nombre"), rs.getString("servicio"), rs.getString("estado"), rs.getString("hora"), rs.getInt("precio")));
                }
            }
            stm.close();
            con.desconectar();
            return servicios;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Servicio> obtenerPorFecha(String fecha, int id) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT ds.idDetalle as id, ser.nombre as servicio, CONCAT(per.nombre,' ',per.apellido)as nombre, asai.nombre as estado, CONCAT(hora,' HRS') as hora , ds.precio as precio FROM `detalle_servicio` ds"
                    + " JOIN persona per on(ds.idPersona=per.idPersona)"
                    + " JOIN servicio ser on(ds.idServicio=ser.idServicio) "
                    + "JOIN asistencia asai on(ds.idAsistencia=asai.idAsistencia) where ds.fecha=? and per.idPersona=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            stm.setDate(1, new Date(format.parse(fecha).getTime()));
            stm.setInt(2, id);
            ArrayList<Servicio> servicios = new ArrayList<Servicio>();
            ResultSet rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    servicios.add(new Servicio(rs.getInt("id"), rs.getString("nombre"), rs.getString("servicio"), rs.getString("estado"), rs.getString("hora"), rs.getInt("precio")));
                }
            }
            stm.close();
            con.desconectar();
            return servicios;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Servicio> obtenerPorFecha(String fecha, String dni) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT ds.idDetalle as id, ser.nombre as servicio, CONCAT(per.nombre,' ',per.apellido)as nombre, asai.nombre as estado, CONCAT(hora,' HRS') as hora, ds.precio as precio FROM `detalle_servicio` ds"
                    + " JOIN persona per on(ds.idPersona=per.idPersona)"
                    + " JOIN servicio ser on(ds.idServicio=ser.idServicio) "
                    + "JOIN asistencia asai on(ds.idAsistencia=asai.idAsistencia) where ds.fecha=? and per.dni=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            stm.setDate(1, new Date(format.parse(fecha).getTime()));
            stm.setString(2, dni);
            ArrayList<Servicio> servicios = new ArrayList<Servicio>();
            ResultSet rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    servicios.add(new Servicio(rs.getInt("id"), rs.getString("nombre"), rs.getString("servicio"), rs.getString("estado"), rs.getString("hora"), rs.getInt("precio")));
                }
            }
            stm.close();
            con.desconectar();
            return servicios;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<ListaDetalleServicio> obtenerDetalleServicioPorFecha(String fecha) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "select ds.idDetalle, ds.idPersona as idCliente, idTrabajador, idAsistencia, s.idServicio, hora, fecha, s.precio, descuento, pagado, observacion, duracion, nombre , activo from detalle_trabajador as dt, detalle_servicio as ds, servicio as s WHERE ds.idServicio = ds.idServicio AND ds.idDetalle = dt.idDetalle AND s.idServicio = ds.idServicio AND fecha = ?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setString(1, fecha);
            ResultSet rs = stm.executeQuery();
            ArrayList<ListaDetalleServicio> servicioDetalles = new ArrayList<ListaDetalleServicio>();
           
           
           

            while (rs.next()) {
                ListaDetalleServicio listaDetalleObj = new ListaDetalleServicio();
                listaDetalleObj.setIdDetalle(rs.getInt("idDetalle"));
                listaDetalleObj.setIdPersona(rs.getInt("idCliente"));
                listaDetalleObj.setIdAsistencia(rs.getInt("idAsistencia"));
                listaDetalleObj.setIdServicio(rs.getInt("idServicio"));
                listaDetalleObj.setHora(rs.getTime("hora"));
                listaDetalleObj.setFecha(rs.getDate("fecha"));
                listaDetalleObj.setPrecio(rs.getInt("precio"));
                listaDetalleObj.setDescuento(rs.getInt("descuento"));
                listaDetalleObj.setPagado(rs.getInt("pagado"));
                listaDetalleObj.setObservacion(rs.getString("observacion"));
                listaDetalleObj.setActivo(rs.getInt("activo"));
                listaDetalleObj.setDuracion(rs.getInt("duracion"));
                listaDetalleObj.setIdTrabajador(rs.getInt("idTrabajador"));

                servicioDetalles.add(listaDetalleObj);

            }
            stm.close();
            con.desconectar();

            return servicioDetalles;
        } catch (Exception e) {
            return null;
        }
    }

    public Detalle obtenerDetalle(int id) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT per.dni as dni ,CONCAT(per.nombre,' ',per.apellido) as nombre,ser.nombre as nombreSer,ds.precio as precio,ds.hora as hora,ds.observacion as observacion, ds.idAsistencia as id FROM `detalle_servicio` ds  "
                    + "JOIN persona per on(ds.idPersona=per.idPersona)  "
                    + "JOIN servicio ser on(ds.idServicio=ser.idServicio) where ds.idDetalle=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Detalle det = null;
            if (rs != null) {
                while (rs.next()) {
                    det = new Detalle(rs.getString("dni"), rs.getString("nombre"), rs.getString("nombreSer"), rs.getInt("precio"), rs.getString("hora"), rs.getString("observacion"), rs.getInt("id"));
                }
            }
            stm.close();
            con.desconectar();
            return det;
        } catch (Exception e) {
            String valor = e.getMessage();
            return null;
        }
    }

    public ArrayList<Detalle> obtenerTrabajadore(int id) {
        try {
            Conexion con = Conexion.newInstance();
            String sql = "SELECT detra.idDetalle as id,CONCAT(per.nombre,' ',per.apellido) as nombre ,es.nombre as nombreSer from detalle_trabajador detra "
                    + "JOIN trabajador tra on(detra.idTrabajador=tra.idTrabajador) "
                    + "JOIN especialidad es on(es.idEspecialidad=tra.idEspecialidad)"
                    + " JOIN persona per on(tra.idPersona=per.idPersona) where idServicio=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            ArrayList<Detalle> detalles = new ArrayList<Detalle>();
            if (rs != null) {
                while (rs.next()) {
                    detalles.add(new Detalle(String.valueOf(rs.getInt("id")), rs.getString("nombre"), rs.getString("nombreSer")));
                }
            }
            stm.close();
            con.desconectar();
            return detalles;
        } catch (Exception e) {
            return null;
        }
    }

}
