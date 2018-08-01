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
import modelo.Trabajador;

/**
 *
 * @author Prueba
 */
public class ControladorTrabajador {

    //crea una persona con eso despues su usuario
    public boolean crearTrabajador(Trabajador trabajador, int idSucu) throws SQLException, ClassNotFoundException {

        if (obtenerId(trabajador.getDni()) == 0) {
            Conexion con = Conexion.newInstance();
            String sql = "INSERT INTO `persona`( `idBarrio`, `idGenero`, `dni`, `nombre`, `apellido`, "
                    + "`direccion`, `fechaNacimiento`, `numero`, `correo`, `activo`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = con.getConexion().prepareStatement(sql);
            pstm.setInt(1, trabajador.getIdBarrio());
            pstm.setInt(2, trabajador.getIdGenero());
            pstm.setString(3, trabajador.getDni());
            pstm.setString(4, trabajador.getNombre());
            pstm.setString(5, trabajador.getApellido());
            pstm.setString(6, trabajador.getDireccion());
            pstm.setString(7, trabajador.getFechaNacimiento());
            pstm.setString(8, trabajador.getNumero());
            pstm.setString(9, trabajador.getCorreo());
            pstm.setInt(10, 1);
            int resultado = pstm.executeUpdate();
            sql = "INSERT INTO `trabajador`( `idSucursal`, `idPersona`, `activo`) VALUES (?,?,?)";
            pstm = con.getConexion().prepareStatement(sql);
            pstm.setInt(1, idSucu);
            int idPer = obtenerId(trabajador.getDni());
            pstm.setInt(2, idPer);
            pstm.setInt(3, 1);
            resultado = pstm.executeUpdate();
            int idTra = buscar(trabajador.getDni());
            sql = "INSERT INTO `detalle_trabesp`(`idTrabajador`, `idEspecialidad`) VALUES (?,?)";
            pstm = con.getConexion().prepareStatement(sql);
            for (Listas ls : trabajador.getIdEspecialidad()) {
                pstm.setInt(1, idTra);
                pstm.setInt(2, ls.getId());
                pstm.execute();
            }
            pstm.close();
            con.desconectar();
            return true;
        } else {
            if (buscar(trabajador.getDni()) == 0) {
                Conexion con = Conexion.newInstance();
                String sql = "INSERT INTO `trabajador`( `idSucursal`, `idPersona`, `activo`) VALUES (?,?,?)";
                PreparedStatement pstm = con.getConexion().prepareStatement(sql);
                pstm.setInt(1, idSucu);
                int idPer = obtenerId(trabajador.getDni());
                pstm.setInt(2, idPer);
                pstm.setInt(3, 1);
                pstm.executeUpdate();
                int idTra = buscar(trabajador.getDni());
                sql = "INSERT INTO `detalle_trabesp`(`idTrabajador`, `idEspecialidad`) VALUES (?,?)";
                pstm = con.getConexion().prepareStatement(sql);
                for (Listas ls : trabajador.getIdEspecialidad()) {
                    pstm.setInt(1, idTra);
                    pstm.setInt(2, ls.getId());
                    System.out.println("ID" + idTra + " esp" + ls.getId());
                    pstm.execute();
                }
                pstm.close();
                con.desconectar();
                return true;
            }
        }
        return false;
    }

    private int obtenerId(String dni) {
        try {
            Conexion con = Conexion.newInstance();
            int resultado = 0;
            String sql = "SELECT idPersona from persona where dni=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                resultado = rs.getInt("idPersona");
            }
            stm.close();
            con.desconectar();
            return resultado;
        } catch (Exception e) {
            return 0;
        }
    }

    public String obtenerDniTrabajador(int idTrabajador) {
        try {
            Conexion con = Conexion.newInstance();
            String dniTrabajador = "";
            String sql = "SELECT dni FROM persona as p, trabajador as t WHERE t.idPersona = p.idPersona AND t.idTrabajador = ?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, idTrabajador);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                dniTrabajador = rs.getString("dni");
            }
            stm.close();
            con.desconectar();
            return dniTrabajador;
        } catch (Exception e){
            return "";
        }
            
    }

    public int buscar(String dni) {
        try {
            int retorno = 0;
            Conexion con = Conexion.newInstance();
            String sql = "select idTrabajador from trabajador tra join persona per on(tra.idPersona=per.idPersona) where per.dni=?";
            PreparedStatement stm = con.getConexion().prepareStatement(sql);
            stm.setString(1, dni);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("idTrabajador");
            }
            stm.close();
            con.desconectar();
            return retorno;
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean modificar(Trabajador trabajador) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE `persona` SET `idBarrio`=?,`idGenero`=?,`nombre`=?,`apellido`=?,`direccion`=?,`fechaNacimiento`=?,"
                + "`numero`=?,`correo`=? WHERE `dni`=?";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, trabajador.getIdBarrio());
        stm.setInt(2, trabajador.getIdGenero());
        stm.setString(3, trabajador.getNombre());
        stm.setString(4, trabajador.getApellido());
        stm.setString(5, trabajador.getDireccion());
        stm.setString(6, trabajador.getFechaNacimiento());
        stm.setString(7, trabajador.getNumero());
        stm.setString(8, trabajador.getCorreo());
        stm.setString(9, trabajador.getDni());
        stm.executeUpdate();
        int idTra = buscar(trabajador.getDni());
        sql = "DELETE FROM detalle_trabesp where idTrabajador =?";
        stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, idTra);
        stm.execute();
        sql = "INSERT INTO `detalle_trabesp`(`idTrabajador`, `idEspecialidad`) VALUES (?,?)";
        stm = con.getConexion().prepareStatement(sql);
        for (Listas ls : trabajador.getIdEspecialidad()) {
            stm.setInt(1, idTra);
            stm.setInt(2, ls.getId());
            stm.execute();
        }

        stm.close();
        con.desconectar();
        return true;
    }

    public Trabajador obtenerTrabajador(String dni) throws SQLException, ClassNotFoundException, Exception {

        String sql = "SELECT per.`idBarrio`, su.idSucursal as 'idSucursal',`idGenero`, `dni`, per.`nombre`, `apellido`, per.`direccion` as direccion , `fechaNacimiento`, per.`numero` as numero, `correo` FROM `persona` per"
                + " JOIN trabajador tra on(per.idPersona=tra.idPersona) "
                + "JOIN sucursal su on (tra.idSucursal=su.idSucursal) where per.dni=? and tra.activo=1";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        Trabajador tra = null;
        ResultSet rs = stm.executeQuery();
        rs.first();
        tra = new Trabajador(null, new Listas(rs.getInt("idSucursal")), rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"), rs.getString("correo"));
        int idTra = buscar(dni);
        ArrayList<Listas> listas = new ArrayList<Listas>();
        sql = "SELECT de.idEspecialidad as id , es.nombre as nombre FROM `detalle_trabesp` de JOIN especialidad es "
                + "on(de.idEspecialidad=es.idEspecialidad) where de.idTrabajador=?";
        stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, idTra);
        rs = stm.executeQuery();
        while (rs.next()) {
            listas.add(new Listas(rs.getInt("id"), rs.getString("nombre")));
        }
        tra.setIdEspecialidad(listas);
        stm.close();
        con.desconectar();
        return tra;
    }

    public ArrayList<Trabajador> obtener() throws ClassNotFoundException, SQLException, Exception {

        String sql = "SELECT tra.idSucursal as id,per.`idBarrio`, su.idSucursal as 'idSucursal',`idGenero`, `dni`, per.`nombre`, `apellido`, per.`direccion` as direccion , `fechaNacimiento`, per.`numero` as numero, `correo` FROM `persona` per"
                + " JOIN trabajador tra on(per.idPersona=tra.idPersona) "
                + "JOIN sucursal su on (tra.idSucursal=su.idSucursal) where  tra.activo=1";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int idTra = rs.getInt("id");
            Trabajador tra = null;
            tra = new Trabajador(null, new Listas(rs.getInt("idSucursal")), rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"), rs.getString("correo"));
            sql = "SELECT de.idEspecialidad  as id , es.nombre as nombre FROM `detalle_trabesp` de JOIN especialidad es "
                    + "on(de.idEspecialidad=es.idEspecialidad) where de.idTrabajador=?";
            stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, idTra);
            ResultSet ra = stm.executeQuery();
            ArrayList<Listas> listas = new ArrayList<Listas>();
            while (ra.next()) {
                listas.add(new Listas(rs.getInt("id"), rs.getString("nombre")));
            }
            tra.setIdEspecialidad(listas);
            trabajadores.add(tra);
        }
        stm.close();
        con.desconectar();
        return trabajadores;
    }

    public ArrayList<Trabajador> obtener(int especialidad) throws SQLException, Exception {
        String sql = "SELECT\n"
                + "	    per.`idBarrio`,\n"
                + "    su.nombre AS 'idSucursal',\n"
                + "    `idGenero`,\n"
                + "    `dni`,\n"
                + "    per.`nombre`,\n"
                + "    `apellido`,\n"
                + "    per.`direccion` AS direccion,\n"
                + "    `fechaNacimiento`,\n"
                + "    per.`numero` AS numero,\n"
                + "    `correo`,\n"
                + "    es.nombre AS 'idEspecialidad'\n"
                + "FROM\n"
                + "    detalle_trabesp dtra\n"
                + "JOIN trabajador tra on (dtra.idTrabajador=tra.idTrabajador)\n"
                + "JOIN persona per on (per.idPersona=tra.idPersona)\n"
                + "JOIN especialidad es on (dtra.idEspecialidad=es.idEspecialidad)\n"
                + "JOIN sucursal su on (tra.idSucursal=su.idSucursal)\n"
                + "where dtra.idEspecialidad=? and tra.activo=1";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, especialidad);
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ArrayList<Listas> listas = new ArrayList<Listas>();
            listas.add(new Listas(rs.getString("idEspecialidad")));
            trabajadores.add(new Trabajador(listas, new Listas(rs.getString("idSucursal")), rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"), rs.getString("correo")));
        }
        stm.close();
        con.desconectar();
        return trabajadores;
    }

    public boolean eliminar(String dni) throws SQLException, ClassNotFoundException {

        String sql = "UPDATE `trabajador` join persona per on(trabajador.idPersona=per.idPersona) SET trabajador.activo=0 WHERE per.dni=?";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        stm.executeUpdate();
        stm.close();
        con.desconectar();
        return true;
    }

    public ArrayList<Trabajador> obtenerEliminado() throws SQLException, ClassNotFoundException, Exception {
        String sql = "SELECT tra.idSucursal as id,per.`idBarrio`, su.idSucursal as 'idSucursal',`idGenero`, `dni`, per.`nombre`, `apellido`, per.`direccion` as direccion , `fechaNacimiento`, per.`numero` as numero, `correo` FROM `persona` per"
                + " JOIN trabajador tra on(per.idPersona=tra.idPersona) "
                + "JOIN sucursal su on (tra.idSucursal=su.idSucursal) where  tra.activo=0";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int idTra = rs.getInt("id");
            Trabajador tra = null;
            tra = new Trabajador(null, new Listas(rs.getInt("idSucursal")), rs.getInt("idBarrio"), rs.getInt("idGenero"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getDate("fechaNacimiento"), rs.getString("numero"), rs.getString("correo"));
            sql = "SELECT de.idEspecialidad  as id , es.nombre as nombre FROM `detalle_trabesp` de JOIN especialidad es "
                    + "on(de.idEspecialidad=es.idEspecialidad) where de.idTrabajador=?";
            stm = con.getConexion().prepareStatement(sql);
            stm.setInt(1, idTra);
            ResultSet ra = stm.executeQuery();
            ArrayList<Listas> listas = new ArrayList<Listas>();
            while (ra.next()) {
                listas.add(new Listas(rs.getInt("id"), rs.getString("nombre")));
            }
            tra.setIdEspecialidad(listas);
            trabajadores.add(tra);
        }
        stm.close();
        con.desconectar();
        return trabajadores;
    }

    public boolean activar(String dni) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `trabajador` join persona per on(trabajador.idPersona=per.idPersona) SET trabajador.activo=1 WHERE per.dni=?";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setString(1, dni);
        stm.executeUpdate();
        stm.close();
        con.desconectar();
        return true;
    }

    public ArrayList<Trabajador> buscarDetalle(int detalle) throws ClassNotFoundException, SQLException, Exception {

        String sql = "SELECT\n"
                + "    per.dni AS dni,\n"
                + "    per.nombre AS nombre,\n"
                + "    per.apellido AS apellido\n"
                + "FROM\n"
                + "    detalle_trabajador dt\n"
                + "JOIN trabajador tra ON\n"
                + "    (\n"
                + "        dt.idTrabajador = tra.idTrabajador\n"
                + "    )\n"
                + "JOIN persona per ON\n"
                + "    (tra.idPersona = per.idPersona)\n"
                + "WHERE dt.idServicio=?";
        Conexion con = Conexion.newInstance();
        PreparedStatement stm = con.getConexion().prepareStatement(sql);
        stm.setInt(1, detalle);
        ResultSet rs = stm.executeQuery();
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        if (rs != null) {
            while (rs.next()) {
                trabajadores.add(new Trabajador(" ", rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido")));
            }
        }
        stm.close();
        con.desconectar();
        return trabajadores;

    }
}
