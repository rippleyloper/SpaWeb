/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import controlador.ControladorCliente;
import controlador.ControladorDetalleServicio;
import controlador.ControladorEstadisticas;
import controlador.ControladorTrabajador;
import controlador.ControladorUsuario;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import modelo.Cliente;
import modelo.DetalleServicio;
import modelo.Trabajador;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Tulio    
 */
public class Mai {
    public static void main(String[] args) {
        try {
            System.out.println(DigestUtils.md5Hex("5404too603"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
