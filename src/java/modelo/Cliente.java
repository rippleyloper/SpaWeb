/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Blob;
import java.util.Date;

/**
 *
 * @author Prueba
 */
public class Cliente extends Persona{
    private int idCliente;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        super(idBarrio, idGenero, dni, nombre, apellido, direccion, fechaNacimiento, numero, correo);
        this.idCliente = idCliente;
    }

    public Cliente(int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        super(idBarrio, idGenero, dni, nombre, apellido, direccion, fechaNacimiento, numero, correo);
    }
    
}
