/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Prueba
 */
public class Servicio {
    private int idServicio;
    private int idCategoria;
    private String nombre;
    private int duracion;
    private int precio;
    private String detalle;
    private String servicio;
    private String estado;
    private String hora;
    private String inicio;
    private String termino;
    public Servicio(int idServicio, String nombre, String servicio, String estado, String hora,int precio) throws Exception {
        setIdServicio(idServicio);
        setNombre(nombre);
        setServicio(servicio);
        setEstado(estado);
        setHora(hora);
        setPrecio(precio);
    }
    
    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio.replaceAll("\\<.*?\\>", "");;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio.replaceAll("\\<.*?\\>", "");;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino.replaceAll("\\<.*?\\>", "");;
    }
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.replaceAll("\\<.*?\\>", "");;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora.replaceAll("\\<.*?\\>", "");;
    }
    
    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Servicio(int idServicio,int idCategoria, String nombre, int duracion, int precio, String detalle) throws Exception {
        setIdServicio(idServicio);
        setIdCategoria(idCategoria);
        setNombre(nombre);
        setDuracion(duracion);
        setPrecio(precio);
        setDetalle(detalle);
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) throws Exception {
        if(idCategoria>0){
            this.idCategoria = idCategoria;
        }else{
            throw new Exception("Debe selecionar una categoria");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if(!nombre.trim().equals("")){
            this.nombre = nombre.replaceAll("\\<.*?\\>", "");;
        }else{
            throw new Exception("Debe ingresar un nombre");
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) throws Exception {
        if(duracion>0){
            this.duracion = duracion;
        }else{
            throw new Exception("La duracion debe ser mayor a 0 minutos");
        }
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) throws Exception {
        if(precio>0){
            this.precio = precio;
        }else{
            throw new Exception("El precio debe ser mayor a 0");
        }
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) throws Exception {
        if(!detalle.trim().equals("")){
            this.detalle = detalle.replaceAll("\\<.*?\\>", "");;
        }else{
            throw new Exception("Campo detalle no debe estar vacio");
        }
    }
    
    public void calcularTermino(Date fecha) {
        try {
            Calendar ca=Calendar.getInstance();
            ca.setTime(fecha);
            setInicio(new SimpleDateFormat("hh:mm:ss").format(ca.getTime()));
            ca.add(Calendar.MINUTE, getDuracion());
            setTermino(new SimpleDateFormat("hh:mm:ss").format(ca.getTime()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
