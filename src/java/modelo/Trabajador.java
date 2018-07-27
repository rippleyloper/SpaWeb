/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Prueba
 */
public class Trabajador extends Persona{
    private ArrayList<Listas> idEspecialidad;
    private Listas idSucursal;
    private String nombreEs;
    private Listas especialidad;
    
    public String getNombreEs() {
        return nombreEs;
    }

    public void setNombreEs(String nombreEs) {
        this.nombreEs = nombreEs.replaceAll("\\<.*?\\>", "");
    }
    
    public Trabajador(String nombreEs, String dni, String nombre, String apellido) throws Exception {
        super(dni, nombre, apellido);
        setNombreEs(nombreEs);
    }
    
    public Trabajador(ArrayList<Listas> idEspecialidad,Listas idSucursal, int idBarrio, int idGenero, String dni, String nombre, String apellido, String direccion, Date fechaNacimiento, String numero, String correo) throws Exception {
        super(idBarrio, idGenero, dni, nombre, apellido, direccion, fechaNacimiento, numero, correo);
        setIdEspecialidad(idEspecialidad);
        setIdSucursal(idSucursal);
    }

   
    
    
    
    public ArrayList<Listas> getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(ArrayList<Listas> idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Listas getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Listas especialidad) {
        this.especialidad = especialidad;
    }

    

    public Listas getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Listas idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
    
}
