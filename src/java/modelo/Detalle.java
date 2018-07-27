/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Prueba
 */
public class Detalle {
    private String dni;
    private String nombre;
    private String nombreSer;
    private int precio;
    private String hora;
    private String observaciones;
    private int id;
    public Detalle(String dni, String nombre, String nombreSer, int precio, String hora, String observaciones,int id) {
        setDni(dni);
        setNombre(nombre);
        setNombreSer(nombreSer);
        setPrecio(precio);
        setHora(hora);
        setObservaciones(observaciones);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Detalle(String dni, String nombre, String nombreSer) {
        setDni(dni);
        setNombre(nombre);
        setNombreSer(nombreSer);
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni.replaceAll("\\<.*?\\>", "");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.replaceAll("\\<.*?\\>", "");
    }

    public String getNombreSer() {
        return nombreSer;
    }

    public void setNombreSer(String nombreSer) {
        this.nombreSer = nombreSer.replaceAll("\\<.*?\\>", "");
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora.replaceAll("\\<.*?\\>", "");
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
